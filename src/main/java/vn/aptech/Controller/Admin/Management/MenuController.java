package vn.aptech.Controller.Admin.Management;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import vn.aptech.Model.Model;
import vn.aptech.Views.ManagementMenuType;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button user_btn;
    public Button employee_btn;
    public Button branch_btn;
    public Button router_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getListener();
    }

    public void getListener() {
        user_btn.setOnAction(actionEvent -> onUser());
        employee_btn.setOnAction(actionEvent -> onEmployee());
        branch_btn.setOnAction(actionEvent -> onBranch());
        router_btn.setOnAction(actionEvent -> onRouter());
    }

    public void onUser() {
        Model.getInstance().getViewFactory().getManagementSelectMenuItem().set(ManagementMenuType.USER);
    }
    public void onEmployee() {
        Model.getInstance().getViewFactory().getManagementSelectMenuItem().set(ManagementMenuType.EMPLOYEE);
    }
    public void onBranch() {
        Model.getInstance().getViewFactory().getManagementSelectMenuItem().set(ManagementMenuType.BRANCH);
    }
    public void onRouter() {
        Model.getInstance().getViewFactory().getManagementSelectMenuItem().set(ManagementMenuType.ROUTER);
    }

}
