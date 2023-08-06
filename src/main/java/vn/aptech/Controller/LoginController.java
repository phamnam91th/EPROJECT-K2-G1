package vn.aptech.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import vn.aptech.Model.*;
import vn.aptech.Views.AccountType;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class LoginController extends Thread implements Initializable {
    public ImageView logo;
    public TextField username_tf;
    public TextField password_tf;
    public Button login_btn;
    public Label error_lbl;
    public Button close_btn;
    public Button minimize_btn;
    public Label loadingLabel;
    private static String username;
    private static String password;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    private static ObservableList<TaskList> taskListObservableList;
    private static ObservableList<ListCar> listCarObservableList;
    private static ObservableList<RouterList> routerListObservableList;
    private static ObservableList<Users> usersObservableList;
    private static ObservableList<TaskStatus> taskStatusObservableList;
    private static ObservableList<Branch> branchObservableList;
    private static ObservableList<Employee> employeeObservableList;
    private static ObservableList<Role> roleObservableList;
    private static ObservableList<Positions> positionsObservableList;
    private static ObservableList<Ticket> ticketObservableList;
    private static ObservableList<TicketStatus> ticketStatusObservableList;
    private static ObservableList<TypeCar> typeCarObservableList;

    private static ObservableList<String> taskListName;
    private static ObservableList<String> carListName;
    private static ObservableList<String> routerListCode;
    private static ObservableList<String> usersListName;
    private static ObservableList<String> taskStatusListName;
    private static ObservableList<String> branchListName;
    private static ObservableList<String> employeeListName;
    private static ObservableList<String> roleListName;
    private static ObservableList<String> positionsListName;
    private static ObservableList<String> ticketListName;
    private static ObservableList<String> ticketStatusListName;
    private static int delay;

    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delay) {
        LoginController.delay = delay;
    }

    public static ObservableList<TaskList> getTaskListObservableList() {
        return taskListObservableList;
    }

    public static ObservableList<ListCar> getListCarObservableList() {
        return listCarObservableList;
    }

    public static ObservableList<RouterList> getRouterListObservableList() {
        return routerListObservableList;
    }

    public static ObservableList<Users> getUsersObservableList() {
        return usersObservableList;
    }

    public static ObservableList<TaskStatus> getTaskStatusObservableList() {
        return taskStatusObservableList;
    }

    public static ObservableList<Branch> getBranchObservableList() {
        return branchObservableList;
    }

    public static ObservableList<Employee> getEmployeeObservableList() {
        return employeeObservableList;
    }

    public static ObservableList<Role> getRoleObservableList() {
        return roleObservableList;
    }

    public static ObservableList<Positions> getPositionsObservableList() {
        return positionsObservableList;
    }

    public static ObservableList<Ticket> getTicketObservableList() {
        return ticketObservableList;
    }

    public static ObservableList<TicketStatus> getTicketStatusObservableList() {
        return ticketStatusObservableList;
    }

    public static ObservableList<TypeCar> getTypeCarObservableList() {
        return typeCarObservableList;
    }

    public static ObservableList<String> getTaskListName() {
        return taskListName;
    }

    public static ObservableList<String> getCarListName() {
        return carListName;
    }

    public static ObservableList<String> getRouterListCode() {
        return routerListCode;
    }

    public static ObservableList<String> getUsersListName() {
        return usersListName;
    }

    public static ObservableList<String> getTaskStatusListName() {
        return taskStatusListName;
    }

    public static ObservableList<String> getBranchListName() {
        return branchListName;
    }

    public static ObservableList<String> getEmployeeListName() {
        return employeeListName;
    }

    public static ObservableList<String> getRoleListName() {
        return roleListName;
    }

    public static ObservableList<String> getPositionsListName() {
        return positionsListName;
    }

    public static ObservableList<String> getTicketListName() {
        return ticketListName;
    }

    public static ObservableList<String> getTicketStatusListName() {
        return ticketStatusListName;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readTimeDelay();


        close_btn.setOnAction(actionEvent -> {
            System.exit(0);
        });

        minimize_btn.setOnAction(actionEvent -> onMinimize());
        loadingLabel.setText("Loading...");
        loadingLabel.setVisible(false);
        error_lbl.setVisible(false);

        login_btn.setOnAction(event -> {
            username = username_tf.getText();
            password = password_tf.getText();
            loadingLabel.setVisible(true);

            LoginService loginService = new LoginService();
            loginService.setOnSucceeded(e -> {
                loadingLabel.setVisible(false);
                try {
                    onLogin();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            loginService.start();
        });
    }

    public static void readTimeDelay() {
        try {
            Path path = Paths.get("config.txt");
            File file = new File(path.toUri());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str ;
            while ((str = bufferedReader.readLine()) != null) {
                String key = str.split(":")[0];
                if(key.equals("time_delay")) {
                    String value = str.split(":")[1];
                    delay = Integer.parseInt(value);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void onMinimize() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().minimizeStage(stage);
    }

    public void onLogin() throws IOException, InterruptedException {
        if (Model.getInstance().isLoginSuccess()) {
            error_lbl.setVisible(false);
            taskListObservableList = Model.getInstance().getData().getObservableList("select tl from task_list tl order by tl.id DESC");
            listCarObservableList = Model.getInstance().getData().getObservableList("select lc from list_car lc");
            routerListObservableList = Model.getInstance().getData().getObservableList("select rl from router_list rl");
            usersObservableList = Model.getInstance().getData().getObservableList("select u from users u");
            taskStatusObservableList = Model.getInstance().getData().getObservableList("select ts from task_status ts");
            branchObservableList = Model.getInstance().getData().getObservableList("select b from branch b");
            employeeObservableList = Model.getInstance().getData().getObservableList("select e from employee e");
            roleObservableList = Model.getInstance().getData().getObservableList("select r from role r");
            positionsObservableList = Model.getInstance().getData().getObservableList("select p from positions p");
            ticketObservableList = Model.getInstance().getData().getObservableList("select t from ticket t order by t.id DESC");
            ticketStatusObservableList = Model.getInstance().getData().getObservableList("select ts from ticket_status ts");
            typeCarObservableList = Model.getInstance().getData().getObservableList("select tc from type_car tc");
            taskListName = FXCollections.observableArrayList(taskListObservableList.stream().map(TaskList::getCode).toList());
            carListName = FXCollections.observableArrayList(listCarObservableList.stream().map(ListCar::getLicensePlates).toList());
            routerListCode = FXCollections.observableArrayList(routerListObservableList.stream().map(RouterList::getCode).toList());
            usersListName = FXCollections.observableArrayList(usersObservableList.stream().map(Users::getUserName).toList());
            taskStatusListName = FXCollections.observableArrayList(taskStatusObservableList.stream().map(TaskStatus::getName).toList());
            branchListName = FXCollections.observableArrayList(branchObservableList.stream().map(Branch::getName).toList());
            employeeListName = FXCollections.observableArrayList(employeeObservableList.stream().map(Employee::getCode).toList());
            roleListName = FXCollections.observableArrayList(roleObservableList.stream().map(Role::getName).toList());
            positionsListName = FXCollections.observableArrayList(positionsObservableList.stream().map(Positions::getName).toList());
            ticketListName = FXCollections.observableArrayList(ticketObservableList.stream().map(Ticket::getCode).toList());
            ticketStatusListName = FXCollections.observableArrayList(ticketStatusObservableList.stream().map(TicketStatus::getName).toList());

            if(Model.getInstance().getAccountType() == AccountType.ADMIN) {
                try {
                    Model.getInstance().getViewFactory().showAdminWindow();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Model.getInstance().getViewFactory().showClientWindow();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            Stage stage = (Stage) error_lbl.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_lbl.setVisible(true);
            error_lbl.setText("Error : Login false");
        }


    }

    private static class LoginService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() {
                    System.out.println(LoginController.getUsername());
                    System.out.println(LoginController.getPassword());
                    Model.getInstance().isLogin(LoginController.getUsername(), LoginController.getPassword());
                    try {
                        sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
    }

    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("config.txt");
        File file = new File("config.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        System.out.println(bufferedReader.readLine());
    }
}
