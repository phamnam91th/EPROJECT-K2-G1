package vn.aptech.Controller.Admin.Management;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Model.Role;
import vn.aptech.Model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCellController implements Initializable {
    public Button del_btn;

    public Label employee_create_lb;

    public Label password_lb;

    public Label role_lb;

    public Label username_lb;
    private Users user;
    public UserCellController(Users users) {
        this.user = users;
    }

    private static ObservableList<Users> userObservableList;
    private static ObservableList<String> userListName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lb.setText(user.getUserName());
        password_lb.setText(user.getPassword());
        employee_create_lb.setText(String.valueOf(user.getEmployeeCreate()));

    }
}
