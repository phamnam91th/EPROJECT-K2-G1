package vn.aptech.Controller.Admin.Management;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import vn.aptech.Model.*;
import vn.aptech.Views.EmployeeCellFactory;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    private static ObservableList<Employee> employeeList;
    private static ObservableList<String> positions;
    private static ObservableList<Positions> positionsObservableList;

    public static ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        positionsObservableList = Model.getInstance().getData().getPositionsList();
        employeeList = Model.getInstance().getData().getEmployeeList();
        employee_lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        employee_lv.setItems(employeeList);
        
        employeeList.addListener((ListChangeListener<Employee>) change -> {
            employee_lv.setItems(employeeList);
        });
        
        
        employee_lv.setCellFactory(new EmployeeCellFactory());
        positions = getPositionsListName();
        positions_cb.setItems(positions);
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

            positions_cb.setItems(positions);
            positions_cb.setValue(positions.get(getPositionIndex(t1.getPositionsId())));
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
            positions_cb.setItems(positions);
            positions_cb.setValue(positions.get(0));
        });

        save_btn.setOnAction(actionEvent -> {
            Employee employee  = new Employee();
            setEmployee(employee, "new");

            Model.getInstance().getData().addEmployee(employee);
            employeeList.add(employee);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Employee employee = null;
            try{
                em.getTransaction().begin();
                employee = em.find(Employee.class, employee_lv.getSelectionModel().getSelectedItem().getId());
                setEmployee(employee, "update");
                em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Model.getInstance().getData().closeConnect();
            }
            employeeList.remove(employee_lv.getSelectionModel().getSelectedItem());
            employeeList.add(employee);
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
//        employee.setPositionsId(1);
        employee.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(type.equals("new")) {
            employee.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            employee.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }
    }

    public ObservableList<Positions> getPositionsObservableList() {
        return FXCollections.observableArrayList(Model.getInstance().getData().getPositionsList());
    }

    public int getPositionIndex(int positionsId) {
        int index = 0;
        for(int i=0; i<positionsObservableList.size(); i++) {
            if(positionsId == positionsObservableList.get(i).getId()) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getPositionsId(String name) {
        int id = 0;
        for(int i=0; i<positionsObservableList.size(); i++) {
            if(name.equals(positionsObservableList.get(i).getName())) {
                id = positionsObservableList.get(i).getId();
                break;
            }
        }
        return id;
    }

    public ObservableList<String> getPositionsListName() {
        ObservableList<String> strings = FXCollections.observableArrayList();
        ObservableList<Positions> positionsObservableList = getPositionsObservableList();
        positionsObservableList.forEach(s -> {
            strings.add(s.getName());
        });
        return strings;
    }




    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        controller.getPositionsListName().forEach(System.out::println);
    }

    public static LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}
