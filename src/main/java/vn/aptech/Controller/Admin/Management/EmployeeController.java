package vn.aptech.Controller.Admin.Management;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import vn.aptech.Model.Employee;
import vn.aptech.Model.Model;
import vn.aptech.Views.EmployeeCellFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Employee> employeeList = Model.getInstance().getData().getEmployeeList();
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employeeList);
        employee_lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        employee_lv.setItems(employeeObservableList);
        employee_lv.setCellFactory(new EmployeeCellFactory());

        employee_lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observableValue, Employee employee, Employee t1) {
                fName_tf.setText(t1.getfName());
                mName_tf.setText(t1.getmName());
                lName_tf.setText(t1.getlName());
                LocalDate date = t1.getDob().toLocalDate();
                dob_dp.setValue(date);
                address_tf.setText(t1.getAddress());
                identification_tf.setText(t1.getPersonId());
                phoneNumber_tf.setText(t1.getPhone());
                email_tf.setText(t1.getEmail());

            }
        });
    }

    public static void main(String[] args) {
        List<Employee> employeeList = Model.getInstance().getData().getEmployeeList();
        employeeList.forEach(System.out::println);
        Date date = employeeList.get(0).getDob();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String str = dateFormat.format(date);
        LocalDate localDate = LOCAL_DATE(str);
        System.out.println(str);
        System.out.println(localDate);
    }

    public static LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}
