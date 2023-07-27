package vn.aptech.Controller.Admin.Management;

import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import vn.aptech.Controller.Admin.DashboardController;
import vn.aptech.Model.*;
import vn.aptech.Views.EmployeeCellFactory;

import javax.persistence.EntityManager;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    public TextField fName_tf;
    public TextField mName_tf;
    public TextField lName_tf;
    public DatePicker dob_dp;
    public TextField address_tf;
    public TextField identification_tf;
    public TextField phoneNumber_tf;
    public TextField email_tf;
    public ChoiceBox<String> positions_cb;
    public Button save_btn;
    public Button update_btn;
    public ListView<Employee> employee_lv;
    public Button clear_btn;
    public TextField code_tf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Employee");

        employee_lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        employee_lv.setItems(DashboardController.getEmployeeObservableList());

        DashboardController.getEmployeeObservableList().addListener((ListChangeListener<Employee>) change -> {
            employee_lv.setItems(DashboardController.getEmployeeObservableList());
        });

        employee_lv.setCellFactory(new EmployeeCellFactory());
        positions_cb.setItems(DashboardController.getPositionsListName());
        employee_lv.getSelectionModel().selectedItemProperty().addListener((observableValue, employee, t1) -> {
            fName_tf.setText(t1.getfName());
            mName_tf.setText(t1.getmName());
            lName_tf.setText(t1.getlName());
            LocalDate date = t1.getDob().toLocalDate();
            dob_dp.setValue(date);
            address_tf.setText(t1.getAddress());
            identification_tf.setText(t1.getPersonId());
            phoneNumber_tf.setText(t1.getPhone());
            email_tf.setText(t1.getEmail());
            code_tf.setText(t1.getCode());

            positions_cb.setItems(DashboardController.getPositionsListName());
            positions_cb.setValue(DashboardController.getPositionsListName().get(getPositionIndex(t1.getPositionsId())));
        });


        clear_btn.setOnAction(actionEvent -> {
            fName_tf.setText("");
            mName_tf.setText("");
            lName_tf.setText("");
            dob_dp.setValue(null);
            address_tf.setText("");
            identification_tf.setText("");
            phoneNumber_tf.setText("");
            email_tf.setText("");
            code_tf.setText("");
            positions_cb.setItems(DashboardController.getPositionsListName());
            positions_cb.setValue(DashboardController.getPositionsListName().get(0));
        });

        save_btn.setOnAction(actionEvent -> {
            Employee employee  = new Employee();
            setEmployee(employee, "new");

//            Model.getInstance().getData().addEmployee(employee);
            Model.getInstance().getData().add(employee);
            DashboardController.getEmployeeObservableList().add(employee);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Employee employee = null;
            try{
                em.getTransaction().begin();
                employee = em.find(Employee.class, employee_lv.getSelectionModel().getSelectedItem().getId());
                setEmployee(employee, "update");
                em.merge(employee);
                em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Model.getInstance().getData().closeConnect();
            }
            int index = DashboardController.getEmployeeObservableList().indexOf(employee_lv.getSelectionModel().getSelectedItem());
            DashboardController.getEmployeeObservableList().remove(employee_lv.getSelectionModel().getSelectedItem());
            DashboardController.getEmployeeObservableList().add(index,employee);
        });


    }

    public void setEmployee(Employee employee, String type) {
        employee.setCode(code_tf.getText());
        employee.setfName(fName_tf.getText());
        employee.setmName(mName_tf.getText());
        employee.setlName(lName_tf.getText());
        employee.setDob(Date.valueOf(dob_dp.getValue()));
        employee.setAddress(address_tf.getText());
        employee.setPersonId(identification_tf.getText());
        employee.setPhone(phoneNumber_tf.getText());
        employee.setEmail(email_tf.getText());
        String positionsName = positions_cb.getValue();
        employee.setPositionsId(getPositionsId(positionsName));
        employee.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(type.equals("new")) {
            employee.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            employee.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }
    }

    public int getPositionIndex(int positionsId) {
        int index = 0;
        for(int i=0; i<DashboardController.getPositionsObservableList().size(); i++) {
            if(positionsId == DashboardController.getPositionsObservableList().get(i).getId()) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getPositionsId(String name) {
        int id = 0;
        for(int i=0; i<DashboardController.getPositionsObservableList().size(); i++) {
            if(name.equals(DashboardController.getPositionsObservableList().get(i).getName())) {
                id = DashboardController.getPositionsObservableList().get(i).getId();
                break;
            }
        }
        return id;
    }


    public static LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}
