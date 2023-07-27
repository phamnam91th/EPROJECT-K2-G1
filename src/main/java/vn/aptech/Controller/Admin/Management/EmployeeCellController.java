package vn.aptech.Controller.Admin.Management;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Controller.Admin.DashboardController;
import vn.aptech.Model.Employee;
import vn.aptech.Model.Model;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCellController implements Initializable {
    public Label code_lb;
    public Label fName_lb;
    public Label lName_lb;
    public Label phone_lb;
    public Button del_btn;

    private final Employee employee;

    public EmployeeCellController(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        code_lb.setText(employee.getCode());
        fName_lb.setText(employee.getfName());
        lName_lb.setText(employee.getlName());
        phone_lb.setText(employee.getPhone());

        del_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().delete(employee, employee.getId());
            DashboardController.getEmployeeObservableList().remove(employee);
        });
    }
}
