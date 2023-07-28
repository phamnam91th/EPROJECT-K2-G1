package vn.aptech.Controller.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vn.aptech.Model.Model;
import vn.aptech.Views.AdminMenuOptions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button dashboard_btn;
    public Button management_btn;
    public Button ticket_btn;
    public Button report_btn;
    public Button logout_btn;
    public Button task_btn;
    public Label account_lb;
    public Button setting_btn;
    public Button browser_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(actionEvent -> onDashboard());
        management_btn.setOnAction(actionEvent -> onManagement());
        task_btn.setOnAction(actionEvent -> onTask());
        ticket_btn.setOnAction(actionEvent -> onTicket());
        report_btn.setOnAction(actionEvent -> onReport());
        logout_btn.setOnAction(actionEvent -> {
            try {
                onLogout();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        setting_btn.setOnAction(actionEvent -> {
            try {
                Model.getInstance().getViewFactory().showSettingView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        browser_btn.setOnAction(actionEvent -> {
            onBrowser();
        });
    }

    public void onDashboard() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.DASHBOARD);
    }

    public void onManagement() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.MANAGEMENT);
    }

    public void onTask() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.TASK);
    }

    public void onTicket() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.TICKET);
    }

    public void onReport() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.REPORT);
    }

    public void onLogout() throws IOException {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public void onBrowser() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.BROWSER);
    }


}
