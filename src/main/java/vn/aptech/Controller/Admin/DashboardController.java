package vn.aptech.Controller.Admin;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import vn.aptech.Model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public StackedBarChart<String, Number> stackChart;
    public CategoryAxis x;
    public NumberAxis y;
    public PieChart myPieChart;
    public LineChart<String, Number> myLineChart;
    public CategoryAxis xLine;
    public NumberAxis yLine;
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
    private static final StringProperty loading = new SimpleStringProperty("start");

    public static StringProperty getLoading() {
        return loading;
    }

    public static void setLoading(String loading) {
        DashboardController.loading.set(loading);
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

    public DashboardController() throws IOException {
        System.out.println("Dashboard");

        taskListObservableList = Model.getInstance().getData().getObservableList("task_list");
        FXCollections.reverse(taskListObservableList);
        listCarObservableList = Model.getInstance().getData().getObservableList("list_car");
        routerListObservableList = Model.getInstance().getData().getObservableList("router_list");
        usersObservableList = Model.getInstance().getData().getObservableList("users");
        taskStatusObservableList = Model.getInstance().getData().getObservableList("task_status");
        branchObservableList = Model.getInstance().getData().getObservableList("branch");
        employeeObservableList  = Model.getInstance().getData().getObservableList("employee");
        roleObservableList  = Model.getInstance().getData().getObservableList("role");
        positionsObservableList  = Model.getInstance().getData().getObservableList("positions");
        ticketObservableList =  Model.getInstance().getData().getObservableList("ticket");
        ticketStatusObservableList =  Model.getInstance().getData().getObservableList("ticket_status");

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
        setLoading("end");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Customer");
        series1.getData().add(new XYChart.Data<>("1",200));
        series1.getData().add(new XYChart.Data<>("2",310));
        series1.getData().add(new XYChart.Data<>("3",240));
        series1.getData().add(new XYChart.Data<>("4",150));
        series1.getData().add(new XYChart.Data<>("5",200));
        series1.getData().add(new XYChart.Data<>("6",310));
        series1.getData().add(new XYChart.Data<>("7",240));
        series1.getData().add(new XYChart.Data<>("8",150));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Product");
        series2.getData().add(new XYChart.Data<>("1", 20));
        series2.getData().add(new XYChart.Data<>("2", 60));
        series2.getData().add(new XYChart.Data<>("3", 40));
        series2.getData().add(new XYChart.Data<>("4", 30));
        series2.getData().add(new XYChart.Data<>("5", 20));
        series2.getData().add(new XYChart.Data<>("6", 60));
        series2.getData().add(new XYChart.Data<>("7", 40));
        series2.getData().add(new XYChart.Data<>("8", 30));

        stackChart.getData().addAll(series1, series2);


        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Ha Noi", 200),
                new PieChart.Data("Thanh Hoa", 160),
                new PieChart.Data("Bac Giang", 189),
                new PieChart.Data("Thai Binh", 220)
        );
        myPieChart.dataProperty().set(data);
        myPieChart.setTitle("Doanh thu tung co so");
        myPieChart.setClockwise(true);
        myPieChart.setAnimated(true);


        myLineChart.setTitle("Doanh thu tung thang nam 2023");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("1", 25300));
        series.getData().add(new XYChart.Data<>("2", 18300));
        series.getData().add(new XYChart.Data<>("3", 20200));
        series.getData().add(new XYChart.Data<>("4", 34400));
        series.getData().add(new XYChart.Data<>("5", 33300));
        series.getData().add(new XYChart.Data<>("6", 12300));
        series.getData().add(new XYChart.Data<>("7", 18700));
        series.setName("Doanh thu theo thang");
        myLineChart.getData().add(series);

    }


}
