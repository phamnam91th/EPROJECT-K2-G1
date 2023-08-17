package vn.aptech.Controller.Admin;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.*;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class DashboardController implements Initializable {

    public BarChart<String, Number> myBarChart;
    public CategoryAxis barCharX;
    public NumberAxis barCharY;
    public PieChart myPieChart;
    public LineChart<String, Number> myLineChart;
    public CategoryAxis xLine;
    public NumberAxis yLine;
    public Label number_car_active_lb;
    public Label number_car_off_lb;
    public Label total_income_lb;
    public Label total_ticket_pending_lb;
    public Label total_ticket_confirm_lb;
    public Button refresh_btn;
    private boolean run;
    private String activeCar;
    private String offCar;
    private final String toDay;
    private final String toMonth;
    private final String toYear;
    private String totalTicketPending;
    private String totalTicketConfirm;
    private String totalIncome;
    private final Map<Integer, Integer> numberDayInMonth;
    public List<Ticket> tickets;
    public List<Ticket> listInMonth;
    public List<Ticket> listInYear;
    public Thread currentThread;

    public DashboardController() {
        Date day = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        toDay = dateFormat.format(day);
        this.toMonth = toDay.split("-")[1];
        this.toYear = toDay.split("-")[0];
        System.out.println(day);
        numberDayInMonth = new HashMap<>() {{
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }};
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myBarChart.setTitle("Revenue by day of current month");
        myPieChart.setTitle("Revenue by branch");
        myLineChart.setTitle("Monthly revenue of " + toYear);
        myPieChart.setClockwise(true);
        myPieChart.setAnimated(true);
        System.out.println(LoginController.getTaskListObservableList());

        listInMonth = Model.getInstance().getData().getListResult("select t from ticket t inner join task_list tl on t.taskListId=tl.id inner join ticket_status ts on t.status=ts.id where tl.dateApply like '%-" + toMonth + "-%' and ts.name='done' ");
        listInYear = Model.getInstance().getData().getListResult("select t from ticket t inner join task_list tl on t.taskListId=tl.id inner join ticket_status ts on t.status=ts.id where tl.dateApply like '" + toYear + "-%' and ts.name='done' ");

        run = true;

        Task<Void> task1 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (run) {
                    tickets = Model.getInstance().getData().getListResult("select t from ticket t inner join ticket_status ts on t.status=ts.id INNER JOIN task_list tl on t.taskListId=tl.id where ts.name='done' and tl.dateApply like '" + toDay + "' ");
                    System.out.println(listInYear.size());
                    updateInfo();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            number_car_active_lb.setText(activeCar);
                            number_car_off_lb.setText(offCar);
                            total_income_lb.setText(totalIncome);
                            total_ticket_pending_lb.setText(totalTicketPending);
                            total_ticket_confirm_lb.setText(totalTicketConfirm);
                            myBarChart.getData().clear();
                            myBarChart.getData().add(getChart());
                            myPieChart.dataProperty().set(getPieChart());
                            myLineChart.getData().clear();
                            myLineChart.getData().add(getLineChart());
                        }
                    });
                    Thread.sleep(LoginController.getDelay());
//                    Thread.sleep(30000);
                }
                return null;
            }
        };
        currentThread = new Thread(task1);
        currentThread.start();

        refresh_btn.setOnAction(actionEvent -> {
            System.out.println("refresh");
            try {
                currentThread.interrupt();
            } catch (Exception e) {
                Thread t2 = new Thread(task1);
                currentThread = t2;
                t2.start();
            }
        });
    }


    public void updateInfo() {
        double Income = 0;
        for (Ticket ticket : tickets) {
            TaskList task = findItem(ticket.getTaskListId(), LoginController.getTaskListObservableList(), t -> t.getId() == ticket.getTaskListId());
            RouterList router = findItem(task.getRouterListId(), LoginController.getRouterListObservableList(), r -> r.getId() == task.getRouterListId());
            Income += ticket.getNumberOfTicket() * router.getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.totalIncome = decimalFormat.format(Income);

        Long active = Model.getInstance().getData().getSignleResult("select count(s.status) from task_list s where s.status = 1");
        Long totalCar = Model.getInstance().getData().getSignleResult("select count(s.id) from list_car s");
        Long inactive = totalCar - active;
        this.activeCar = String.valueOf(active);
        this.offCar = String.valueOf(inactive);

        Long ticketPending = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id  where ts.name='pending' ");
        if (ticketPending == null) {
            totalTicketPending = "0";
        } else {
            totalTicketPending = String.valueOf(ticketPending);
        }

        Long ticketConfirm = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id  where (ts.name='confirm' or ts.name='done') and tl.dateApply='" + toDay + "' ");
        if (ticketConfirm == null) {
            totalTicketConfirm = "0";
        } else {
            totalTicketConfirm = String.valueOf(ticketConfirm);
        }
    }

    public XYChart.Series<String, Number> getChart() {
        // tinh so ngay co trong thang hien tai
        int loop = 0;
        Set<Integer> set = this.numberDayInMonth.keySet();
        for (Integer key : set) {
            if (key == Integer.parseInt(this.toMonth)) {
                loop = this.numberDayInMonth.get(key);
            }
        }


        // Map luu tru so luong ticket ban duoc moi ngay trong thang
        Map<Integer, Integer> ticketSellInMonth = new HashMap<>();
        for (int i = 1; i <= loop; i++) {
            int nOT = 0;
            for (Ticket ticket : listInMonth) {
                TaskList taskList = findItem(ticket.getTaskListId(), LoginController.getTaskListObservableList(), t -> t.getId() == ticket.getTaskListId());
                Date date = taskList.getDateApply();
                if (i == date.getDate()) {
                    nOT += ticket.getNumberOfTicket();
                }
            }
            ticketSellInMonth.put(i, nOT);
        }

        // set Chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Customer");

        Set<Integer> set1 = ticketSellInMonth.keySet();
        for (Integer key : set1) {
            series.getData().add(new XYChart.Data<>(String.valueOf(key), ticketSellInMonth.get(key)));
        }
        return series;
    }


    //show pie chart
    public ObservableList<PieChart.Data> getPieChart() {
        Map<String, Integer> revenues = new HashMap<>();
        LoginController.getBranchObservableList().forEach(s -> {
            AtomicInteger nOT = new AtomicInteger();
            listInMonth.forEach(l -> {
                if (findBranch(l.getTaskListId()).getId() == s.getId()) {
                    nOT.addAndGet(l.getNumberOfTicket());
                }
            });
            revenues.put(s.getName(), nOT.get());
        });

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        Set<String> set = revenues.keySet();
        for (String key : set) {
            data.add(new PieChart.Data(key, revenues.get(key)));
        }
        return data;
    }


    // show line chart
    public XYChart.Series<String, Number> getLineChart() {
        Map<Integer, Integer> ticketSellInYear = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            int nOT = 0;
            for (Ticket ticket : listInYear) {
                TaskList taskList = findItem(ticket.getTaskListId(), LoginController.getTaskListObservableList(), t -> t.getId() == ticket.getTaskListId());
                Date date = taskList.getDateApply();
                if (i == (date.getMonth() + 1)) {
                    nOT += ticket.getNumberOfTicket();
                }
            }
            ticketSellInYear.put(i, nOT);
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Month");
        Set<Integer> set = ticketSellInYear.keySet();
        for (Integer key : set) {
//            System.out.println(key + "--" + ticketSellInYear.get(key));
            series.getData().add(new XYChart.Data<>(String.valueOf(key), ticketSellInYear.get(key)));
        }
        return series;
    }

    public <T, V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public Branch findBranch(int idTask) {
        TaskList task = findItem(idTask, LoginController.getTaskListObservableList(), taskList -> taskList.getId() == idTask);
        int idRouter = task.getRouterListId();
        RouterList router = findItem(idRouter, LoginController.getRouterListObservableList(), routerList -> routerList.getId() == idRouter);
        int idBranch = router.getStartPoint();
        return findItem(idBranch, LoginController.getBranchObservableList(), branch1 -> branch1.getId() == idBranch);
    }

}
