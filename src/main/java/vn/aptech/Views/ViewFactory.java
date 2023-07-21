package vn.aptech.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.aptech.Controller.Admin.AdminController;
import vn.aptech.Controller.Admin.ManagementController;

import java.io.IOException;

public class ViewFactory {

    private AccountType loginAccountType;
    // Admin View
    private final ObjectProperty<AdminMenuOptions> adminSelectMenuItem;
    private AnchorPane dashboardView;
    private BorderPane managementView;
    private AnchorPane taskView;
    private AnchorPane ticketView;
    private AnchorPane reportView;

    // Management view
    private final ObjectProperty<ManagementMenuType> managementSelectMenuItem;
    private AnchorPane userView;
    private AnchorPane employeeView;
    private AnchorPane branchView;
    private AnchorPane routerView;


    private double x = 0 ;
    private double y = 0;


    public ViewFactory() {
        this.loginAccountType = AccountType.ADMIN;
        this.adminSelectMenuItem = new SimpleObjectProperty<>();
        this.managementSelectMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }


    // Admin view section
    public ObjectProperty<AdminMenuOptions> getAdminSelectMenuItem() {
        return adminSelectMenuItem;
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public BorderPane getManagementView() {
        if (managementView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management.fxml"));
                ManagementController controller = new ManagementController();
                loader.setController(controller);
                managementView = loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return managementView;
    }

    public AnchorPane getTaskView() {
        if (taskView == null) {
            try {
                taskView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Task.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskView;
    }

    public AnchorPane getTicketView() {
        if (ticketView == null) {
            try {
                ticketView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Ticket.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ticketView;
    }

    public AnchorPane getReportView() {
        if (reportView == null) {
            try {
                reportView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Report.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportView;
    }



    // show login window
    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    // show admin window
    public void showAdminWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    // Management view section

    public ObjectProperty<ManagementMenuType> getManagementSelectMenuItem() {
        return managementSelectMenuItem;
    }

    public AnchorPane getUserView() {
        if (userView == null) {
            try {
                userView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/User.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userView;
    }

    public AnchorPane getEmployeeView() {
        if (employeeView == null) {
            try {
                employeeView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/Employee.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employeeView;
    }

    public AnchorPane getBranchView() {
        if (branchView == null) {
            try {
                branchView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/Branch.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return branchView;
    }

    public AnchorPane getRouterView() {
        if (routerView == null) {
            try {
                routerView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/Router.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return routerView;
    }

    public void createStage(FXMLLoader loader) throws IOException {
        Parent root = loader.load();

        Scene scene = null;
        try {
            scene = new Scene(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MB-BANK");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void minimizeStage(Stage stage) {
        stage.setIconified(true);
    }

}
