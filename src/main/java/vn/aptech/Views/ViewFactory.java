package vn.aptech.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.aptech.Controller.Admin.AdminController;
import vn.aptech.Controller.Admin.ManagementController;
import vn.aptech.Controller.Client.ClientController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewFactory {
    private Alert alert;
    private AccountType loginAccountType;
    private boolean loginFlag;
    // loading
    private AnchorPane loadingView;
    // Admin View
    private final ObjectProperty<AdminMenuOptions> adminSelectMenuItem;
    private AnchorPane dashboardView;
    private BorderPane managementView;
    private AnchorPane taskView;
    private AnchorPane ticketView;
    private AnchorPane reportView;
    private AnchorPane settingView;
    private AnchorPane browserView;

    // Management view
    private final ObjectProperty<ManagementMenuType> managementSelectMenuItem;
    private AnchorPane userView;
    private AnchorPane employeeView;
    private AnchorPane branchView;
    private AnchorPane routerView;


    private double x = 0;
    private double y = 0;


    public ViewFactory() {
        this.loginAccountType = AccountType.ADMIN;
        this.adminSelectMenuItem = new SimpleObjectProperty<>();
        this.managementSelectMenuItem = new SimpleObjectProperty<>();
    }

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
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

    public AnchorPane getBrowserView() {
        if (browserView == null) {
            try {
                browserView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Browser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return browserView;
    }

    public void showBrowser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Browser.fxml"));
        createStage(loader);
    }

    public void showTask() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Task.fxml"));
        createStage(loader);
    }




    public void showLoading() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Loading.fxml"));
        createStage(loader);
    }

    public void showSettingView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Setting.fxml"));
        createStage(loader);
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

    //show client window
    public void showClientWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
//        ClientController controller = new ClientController();
//        loader.setController(controller);
        createStage(loader);
    }


    // Loading view
    public AnchorPane getLoadingView() {
        if (loadingView == null) {
            try {
                loadingView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Loading.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loadingView;
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
//            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("APP MANAGEMENT");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/logo.png"))));
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void exitProgram() {
        javafx.application.Platform.exit();
        System.exit(0);
    }

    public void minimizeStage(Stage stage) {
        stage.setIconified(true);
    }

    public void showAlertInfo(String title, String content) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    public boolean isIntNumber(String number) {
        boolean flag = false;
        try {
            int a = Integer.parseInt(number);
            flag = true;
        } catch (Exception e) {
            System.out.println("value not is number ");
//            showAlertInfo("Please enter again", "Value must is number");
        }
        return flag;
    }
    public boolean isDoubleNumber(String number) {
        boolean flag = false;
        try {
            double a = Double.parseDouble(number);
            flag = true;
        } catch (Exception e) {
            System.out.println("value not is number ");
//            showAlertInfo("Please enter again", "Value must is number");
        }
        return flag;
    }

    public boolean isDayFormat(String day) {
        Pattern pattern = Pattern.compile("^(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/\\d{4}$");
        Matcher matcher = pattern.matcher(day);
        return matcher.find();
    }

    public static void main(String[] args) {
        ViewFactory viewFactory = new ViewFactory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(viewFactory.isDayFormat("12/14/2023"));
    }



}
