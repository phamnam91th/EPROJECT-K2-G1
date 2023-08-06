package vn.aptech.Controller.Admin;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.Employee;
import vn.aptech.Model.Model;
import vn.aptech.Views.AdminMenuOptions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
        Employee employee = findItem(Model.getInstance().getUsers().getEmployeeId(), LoginController.getEmployeeObservableList(), e -> e.getId() == Model.getInstance().getUsers().getEmployeeId());

        account_lb.setText(employee.getlName() + " " + employee.getfName());

        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(actionEvent -> {
            onDashboard();
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });

        management_btn.setOnAction(actionEvent -> {
            onManagement();
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });

        task_btn.setOnAction(actionEvent -> {
            onTask();
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });

        ticket_btn.setOnAction(actionEvent -> {
            onTicket();
            ticket_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });

        report_btn.setOnAction(actionEvent -> {
            onReport();
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });
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
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
        });
        browser_btn.setOnAction(actionEvent -> {
            onBrowser();
            ticket_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            dashboard_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            management_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            task_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            report_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            setting_btn.setStyle("-fx-background-color: #132A13; -fx-effect: dropshadow(three-pass-box, #07230c, 5,0,0,6);");
            browser_btn.setStyle("-fx-background-color: #1e651e; -fx-effect: dropshadow(three-pass-box, #15561e, 5,0,0,6);");
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

    public <T, V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

}
