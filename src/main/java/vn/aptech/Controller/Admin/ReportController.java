package vn.aptech.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ReportController implements Initializable {
    public Button report_btn;
    public Label tk_pending_lb;
    public Label tk_confirm_lb;
    public Label tk_done_lb;
    public Label tk_cancel_lb;
    public Label tk_total_lb;
    public TableView<ReportByCar> car_report_tv;
    public TableColumn<ReportByCar, String> car_name_col;
    public TableColumn<ReportByCar, Integer> car_number_of_task_col;
    public TableColumn<ReportByCar, Integer> car_number_of_ticket_col;
    public TableColumn<ReportByCar, Integer> car_number_of_ticket_done_col;
    public TableColumn<ReportByCar, Integer> car_number_of_ticket_cancel_col;
    public TableColumn<ReportByCar, Double> car_total_revenue_col;
    public DatePicker select_day_dp;
    public Button print_btn;
    public Label report_status_lb;
    public Label total_lb;
    private Date select_day;

    private static ObservableList<String> selectReportList;
    private static ObservableList<ReportByCar> reportByCarObservableList;
    private static ObservableList<ReportByBranch> reportByBranchObservableList;
    private static double allTotal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        report_btn.setOnAction(actionEvent -> {

            if(select_day_dp.getValue() == null) {
                Model.getInstance().getViewFactory().showAlertInfo("Warning", "Please select day !");
            } else {
                showReport();
            }


        });

        print_btn.setOnAction(actionEvent -> {
            try {
                createPdf();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Print Report");
            alert.setContentText("Print done");
            alert.show();
        });
    }

    public void showReport() {
        select_day = Date.valueOf(select_day_dp.getValue());
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        Long ticketPending = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id  where ts.name='pending' and tl.dateApply='"+ select_day +"'  ");
        Long ticketConfirm = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id  where ts.name='confirm' and tl.dateApply='"+ select_day +"'  ");
        Long ticketDone = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id  where ts.name='done' and tl.dateApply='"+ select_day +"'  ");
        Long ticketCancel = Model.getInstance().getData().getSignleResult("select sum(t.numberOfTicket) from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id  where ts.name='cancel' and tl.dateApply='"+ select_day +"'  ");
        if(ticketCancel == null) {
            ticketCancel = 0L;
        }
        if(ticketPending == null) {
            ticketPending = 0L;
        }
        if(ticketConfirm == null) {
            ticketConfirm = 0L;
        }
        if(ticketDone == null) {
            ticketDone = 0L;
        }
        long totalTicket = ticketDone+ticketCancel+ticketConfirm+ticketPending;
        report_status_lb.setText("REPORT DAY " + select_day);

        tk_pending_lb.setText("Ticket Pending :" + ticketPending);
        tk_confirm_lb.setText("Ticket confirm :" + ticketConfirm);
        tk_done_lb.setText("Ticket done :" + ticketDone);
        tk_cancel_lb.setText("Ticket cancel :" + ticketCancel);
        tk_total_lb.setText("Ticket total :" + totalTicket);

        reportByCarObservableList = getReportByCarObservableList();

        showCarNameCol();
//        car_name_col.setCellValueFactory(new PropertyValueFactory<>("carId"));
        car_number_of_task_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTask"));
        car_number_of_ticket_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTicket"));
        car_number_of_ticket_done_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTicketDone"));
        car_number_of_ticket_cancel_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTicketCancel"));
        car_total_revenue_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        car_report_tv.setItems(reportByCarObservableList);
        total_lb.setText(decimalFormat.format(allTotal));
    }

    private void showCarNameCol() {
        car_name_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getCarId();
                    setText(findItem(id, LoginController.getListCarObservableList(), c -> c.getId() == id).getLicensePlates());
                }else {
                    setText("");
                }
            }
        });
    }

    public void createPdf() throws IOException {
        PDDocument report = null;
        PDPage page = null;

        try{
            report = new PDDocument();
            page = new PDPage();

            report.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(report, page);
            content.beginText();
            content.setFont(font, 12);
            content.setNonStrokingColor(Color.BLUE);
            content.setLeading(14.5f);
            content.newLineAtOffset(25, 725);

            content.showText("TICKET REPORT DAY " + select_day);
            content.newLine();
            content.showText("-------------------------------------");
            content.newLine();
            content.showText(tk_pending_lb.getText());
            content.newLine();
            content.showText(tk_confirm_lb.getText());
            content.newLine();
            content.showText(tk_done_lb.getText());
            content.newLine();
            content.showText(tk_cancel_lb.getText());
            content.newLine();
            content.showText("-------------------------------------");
            content.newLine();
            content.showText(tk_total_lb.getText());
            content.newLine();
            content.showText("-------------------------------------");
            content.newLine();
            content.showText(String.format("%-15s", "Car"));
            content.showText(String.format("%-10s", "task"));
            content.showText(String.format("%-17s", "Total ticket"));
            content.showText(String.format("%-17s", "Ticket done"));
            content.showText(String.format("%-17s", "Ticket cancel"));
            content.showText(String.format("%-17s", "Total revenue"));
            content.newLine();

            for(ReportByCar rp: reportByCarObservableList) {
                content.showText(String.format("%-15s", findItem(rp.getCarId(), LoginController.getListCarObservableList(), c -> c.getId() == rp.getCarId()).getLicensePlates()));
                content.showText(String.format("%-10s", rp.getNumberOfTask()));
                content.showText(String.format("%-17s",rp.getNumberOfTicket()));

                content.showText(String.format("%-17s",rp.getNumberOfTicketDone()));
                content.showText(String.format("%-17s",rp.getNumberOfTicketCancel()));
                content.showText(String.format("%-17s",rp.getTotalRevenue()));
                content.newLine();
            }
            content.newLine();
            content.showText("Total : ");
            content.showText(total_lb.getText());

            content.endText();
            content.close();
            Path path = Paths.get("src/main/resources/Report/report" + select_day + ".pdf");
            report.save(path.toUri().getPath());
            report.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public ObservableList<ReportByCar> getReportByCarObservableList() {
        ObservableList<ReportByCar> reportByCars = FXCollections.observableArrayList();
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join task_list tl on t.taskListId = tl.id where tl.dateApply = '"+ select_day.toString() + "'" );
        allTotal = 0;
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        for(String car: LoginController.getCarListName()) {
            ReportByCar report = new ReportByCar();
            ListCar listCar = findItem(car, LoginController.getListCarObservableList(), c -> c.getLicensePlates().equals(car));
            int carId = listCar.getId();
            int numberOfTask = 0;
            int numberOfTicket = 0;
            int numberOfTicketDone = 0;
            int numberOfTicketCancel = 0;
            double totalRevenue = 0;
            for(Ticket ticket: tickets) {
                TaskList task = findItem(ticket.getTaskListId(), LoginController.getTaskListObservableList(), t -> t.getId() == ticket.getTaskListId());
                RouterList router = findItem(task.getRouterListId(), LoginController.getRouterListObservableList(), r -> r.getId() == task.getRouterListId());
                if(findCar(ticket.getTaskListId()).getLicensePlates().equals(car)) {
                    numberOfTask++;
                    numberOfTicket += ticket.getNumberOfTicket();
                    if(ticket.getStatus() == 3) {
                        numberOfTicketDone += ticket.getNumberOfTicket();
                        totalRevenue += numberOfTicketDone * router.getPrice();
                    }
                    if(ticket.getStatus() == 4) {
                        numberOfTicketCancel += ticket.getNumberOfTicket();
                    }
                }
            }

            report.setCarId(carId);
            report.setNumberOfTask(numberOfTask);
            report.setNumberOfTicket(numberOfTicket);
            report.setNumberOfTicketDone(numberOfTicketDone);
            report.setNumberOfTicketCancel(numberOfTicketCancel);
            report.setTotalRevenue(totalRevenue);
            report.setTotal(decimalFormat.format(totalRevenue));
            reportByCars.add(report);
            allTotal += totalRevenue;
        }
        return reportByCars;
    }



    public ListCar findCar(int taskId) {
        TaskList task = findItem(taskId, LoginController.getTaskListObservableList(), t -> t.getId() == taskId);
        return findItem(task.getListCarId(), LoginController.getListCarObservableList(), c -> c.getId() == task.getListCarId());
    }

    public <T,V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
//        ObservableList<Ticket> tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join task_list tl on t.taskListId = tl.id where tl.dateApply = '2023-07-30'");
//        for (Ticket ticket: tickets) {
//            System.out.println(ticket);
//        }

        for(String car: LoginController.getCarListName()) {
            System.out.println(car);
        }

    }
}
