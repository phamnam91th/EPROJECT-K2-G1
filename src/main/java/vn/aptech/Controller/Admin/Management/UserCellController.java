package vn.aptech.Controller.Admin.Management;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Model.Model;
import vn.aptech.Model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCellController implements Initializable {
    public Button del_btn;

    public Label employee_create_lb;

    public Label password_lb;

    public Label role_lb;

    public Label username_lb;
    private final Users user;
    public UserCellController(Users users) {
        this.user = users;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lb.setText(user.getUserName());
        password_lb.setText(user.getPassword());
        employee_create_lb.setText(UserController.idToEmployee(user.getEmployeeCreate()).getCode() + "-" + UserController.idToEmployee(user.getEmployeeCreate()).getlName());
        role_lb.setText(UserController.idToRole(user.getRoleId()).getName());

        del_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().delete(user, user.getId());
            UserController.getUsersObservableList().remove(user);
        });
    }
}
