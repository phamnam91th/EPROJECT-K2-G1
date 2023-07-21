package vn.aptech.Controller.Admin;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import vn.aptech.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {
    public BorderPane managementPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getManagementSelectMenuItem().addListener(((observableValue, managementMenuType, t1) -> {
            switch (t1) {
                case EMPLOYEE -> managementPane.setCenter(Model.getInstance().getViewFactory().getEmployeeView());
                case BRANCH -> managementPane.setCenter(Model.getInstance().getViewFactory().getBranchView());
                case ROUTER -> managementPane.setCenter(Model.getInstance().getViewFactory().getRouterView());
                default -> managementPane.setCenter(Model.getInstance().getViewFactory().getUserView());
            }
        }));
    }
}
