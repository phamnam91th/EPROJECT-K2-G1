package vn.aptech.Controller.Client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ClientController implements Initializable {
    public Label user_lb;
    public Button start_btn;
    public Button end_btn;
    public Button close_btn;
    public TableView<Ticket> list_customer_tv;
    public TableColumn<Ticket, Integer> id_col;
    public TableColumn<Ticket, String> customer_code_col;
    public TableColumn<Ticket, String> customer_name_col;
    public TableColumn<Ticket, String> customer_phone_col;
    public TableColumn<Ticket, Integer> quantity_col;
    public TableColumn<Ticket, String> status_col;
    public TableColumn action_confirm_col;
    public TableColumn action_cancel_col;
    public TableColumn<Ticket, String> task_code_col;
    public static ObservableList<Ticket> tickets;
    public static ObservableList<String> taskListToDay;
    public Label start_at_lb;
    public Label end_at_lb;
    public ChoiceBox<String> task_select_cb;
    public Button go_btn;
    public Button refresh_btn;
    public Button logout_btn;

    private TaskList taskSelect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        user_lb.setText("Welcome " + LoginController.getUsername());
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        String userName = LoginController.getUsername();
        taskListToDay = Model.getInstance().getData().getObservableList("select tl.code from task_list tl inner join users u on tl.userId = u.id where tl.dateApply = '"+today+"' and u.userName = '"+userName+"' ");
        tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id inner join users u on tl.userId=u.id where (ts.name='confirm' or ts.name='done') and tl.dateApply = '"+ today +"' and u.userName = '"+ userName +"' ");

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        customer_code_col.setCellValueFactory(new PropertyValueFactory<>("code"));
        customer_name_col.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customer_phone_col.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTicket"));
        showStatusCol();
        showTaskCol();
        showActionConfirmCol();
        showActionCancelCol();

        list_customer_tv.setItems(tickets);
        task_select_cb.setItems(taskListToDay);
        tickets.addListener((ListChangeListener<Ticket>) change -> list_customer_tv.setItems(tickets));

        task_select_cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                start_at_lb.setText("");
                end_at_lb.setText("");
                taskSelect = findItem(t1, LoginController.getTaskListObservableList(),t->t.getCode().equals(t1));
                if(taskSelect.getStartTime()!=null) {
                    start_at_lb.setText("Start at : " + taskSelect.getStartTime());
                }
                if(taskSelect.getEndTime()!=null) {
                    end_at_lb.setText("End at : " + taskSelect.getEndTime());
                }
                System.out.println(taskSelect);
            }
        });

        refresh_btn.setOnAction(actionEvent -> {
            taskListToDay = Model.getInstance().getData().getObservableList("select tl.code from task_list tl inner join users u on tl.userId = u.id where tl.dateApply = '"+today+"' and u.userName = '"+userName+"' ");
            tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id inner join users u on tl.userId=u.id where (ts.name='confirm' or ts.name='done') and tl.dateApply = '"+ today +"' and u.userName = '"+ userName +"' ");
            list_customer_tv.setItems(tickets);
            task_select_cb.setItems(taskListToDay);
        });

        go_btn.setOnAction(actionEvent -> {
            if(task_select_cb.getValue() != null) {
                tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id inner join users u on tl.userId=u.id where (ts.name='confirm' or ts.name='done') and tl.dateApply = '"+ today +"' and u.userName = '"+ userName +"' and tl.code = '"+task_select_cb.getValue()+"'  ");
            }
            list_customer_tv.setItems(tickets);
        });

        start_btn.setOnAction(actionEvent -> {
            if(start_at_lb.getText().isEmpty()) {
                if(taskSelect != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = dFormat.format(timestamp);
                    start_at_lb.setText("Start at : " + str);

                    Model.getInstance().getData().getConnect();
                    EntityManager em = Model.getInstance().getData().getEm();
                    TaskList task = null;
                    TaskStatus statusActive = findItem("active", LoginController.getTaskStatusObservableList(), t->t.getName().equals("active"));
                    try {
                        em.getTransaction().begin();
                        task = em.find(TaskList.class, taskSelect.getId());
                        task.setStartTime(timestamp);
                        task.setStatus(statusActive.getId());
                        em.merge(task);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Model.getInstance().getData().closeConnect();
                    }
                } else {
                    Model.getInstance().getViewFactory().showAlertInfo("Warning", "Please select your task !");
                }
            } else {
                Model.getInstance().getViewFactory().showAlertInfo("Already", "The ride has started!");
            }
        });


        end_btn.setOnAction(actionEvent -> {
            if(end_at_lb.getText().isEmpty()) {
                if(taskSelect != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = dFormat.format(timestamp);
                    end_at_lb.setText("End at : " + str);

                    Model.getInstance().getData().getConnect();
                    EntityManager em = Model.getInstance().getData().getEm();
                    TaskList task = null;
                    TaskStatus statusDone = findItem("done", LoginController.getTaskStatusObservableList(), t->t.getName().equals("done"));
                    try {
                        em.getTransaction().begin();
                        task = em.find(TaskList.class, taskSelect.getId());
                        task.setEndTime(timestamp);
                        task.setStatus(statusDone.getId());
                        em.merge(task);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Model.getInstance().getData().closeConnect();
                    }
                } else {
                    Model.getInstance().getViewFactory().showAlertInfo("Warning", "Please select your task !");
                }
            } else {
                Model.getInstance().getViewFactory().showAlertInfo("Already", "The ride has ended!");
            }
        });

        close_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().exitProgram();
        });

        logout_btn.setOnAction(actionEvent -> {
            try {
                onLogout();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void showStatusCol() {
        status_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getStatus();
                    setText(findItem(id, LoginController.getTicketStatusObservableList(), router -> router.getId() == id).getName());
                }else {
                    setText("");
                }
            }
        });
    }

    private void showTaskCol() {
        task_code_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getTaskListId();
                    setText(findItem(id, LoginController.getTaskListObservableList(), t -> t.getId() == id).getCode());
                }else {
                    setText("");
                }
            }
        });
    }

    private void showActionConfirmCol() {
        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Ticket, Void> call(final TableColumn<Ticket, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Model.getInstance().getData().getConnect();
                            EntityManager em = Model.getInstance().getData().getEm();
                            Ticket ticket = null;
                            int index = tickets.indexOf(getTableView().getItems().get(getIndex()));
                            try{
                                em.getTransaction().begin();
                                ticket = em.find(Ticket.class, getTableView().getItems().get(getIndex()).getId());
                                TicketStatus ts = findItem("done", LoginController.getTicketStatusObservableList(), s->s.getName().equals("done"));
                                ticket.setStatus(ts.getId());
                                em.merge(ticket);
                                em.getTransaction().commit();
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Model.getInstance().getData().closeConnect();
                            }
                            tickets.remove(index);
                            tickets.add(index,ticket);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setText("Confirm");
                        }
                    }
                };
            }
        };
        action_confirm_col.setCellFactory(cellFactory);
    }

    private void showActionCancelCol() {
        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Ticket, Void> call(final TableColumn<Ticket, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Model.getInstance().getData().getConnect();
                            EntityManager em = Model.getInstance().getData().getEm();
                            Ticket ticket = null;
                            int index = tickets.indexOf(getTableView().getItems().get(getIndex()));
                            try{
                                em.getTransaction().begin();
                                ticket = em.find(Ticket.class, getTableView().getItems().get(getIndex()).getId());
                                TicketStatus ts = findItem("cancel", LoginController.getTicketStatusObservableList(), s->s.getName().equals("cancel"));
                                ticket.setStatus(ts.getId());
                                em.merge(ticket);
                                em.getTransaction().commit();
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Model.getInstance().getData().closeConnect();
                            }
                            tickets.remove(index);
                            tickets.add(index,ticket);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setText("Cancel");
                        }
                    }
                };
            }
        };
        action_cancel_col.setCellFactory(cellFactory);
    }

    public <T,V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public void onLogout() throws IOException {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
//        ObservableList<Ticket> tickets = Model.getInstance().getData().getObservableList("select t from ticket t inner join ticket_status ts on t.status=ts.id inner join task_list tl on t.taskListId=tl.id inner join users u on tl.userId=u.id where ts.name='done' and tl.dateApply = '2023-07-30' and u.userName = 'c1011'  ");
//        tickets.forEach(System.out::println);
//        Date time = new Date(System.currentTimeMillis());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss / dd-MM-yyyy");
//        String str = dateFormat.format(time);
//        System.out.println(str);
        taskListToDay = Model.getInstance().getData().getObservableList("select tl.code from task_list tl inner join users u on tl.userId = u.id where tl.dateApply = '2023-08-05' and u.userName = 'c1006' ");
        taskListToDay.forEach(System.out::println);


    }
}
