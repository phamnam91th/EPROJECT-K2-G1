package vn.aptech.Controller.Admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vn.aptech.Model.*;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TicketController implements Initializable {
    public TextField s_code_tf;
    public ChoiceBox<String> s_customer_name_cb;
    public ChoiceBox<String> s_customer_phone_cb;
    public ChoiceBox<String> s_branch_cb;
    public DatePicker s_day_dp;
    public Button search_btn;
    public TableView<Ticket> ticket_tv;
    public TableColumn<Ticket, Integer> tv_id_col;
    public TableColumn<Ticket, String> tv_code_col;
    public TableColumn<Ticket, String> tv_customer_name_col;
    public TableColumn<Ticket, String> tv_customer_phone_col;
    public TableColumn<Ticket, String> tv_branch_col;
    public TableColumn<Ticket, String> tv_task_code_col;
    public TableColumn<Ticket, String> tv_employee_apply_col;
    public TableColumn<Ticket, Date> tv_day_apply_col;
    public TableColumn<Ticket, String> tv_status_col;
    public TableColumn<Ticket, ImageView> tv_action_col;
    public ChoiceBox<String> m_customer_name_cb;
    public ChoiceBox<String> m_customer_phone_cb;
    public ChoiceBox<String> m_branch_cb;
    public ChoiceBox<String> m_task_cb;
    public ChoiceBox<String> m_employee_apply_cb;
    public ChoiceBox<String> m_status_cb;
    public Button save_btn;
    public Button update_btn;
    public Button delete_btn;
    public Button refresh_btn;
    public Button confirm_btn;
    public Button cancel_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticket_tv.setItems(DashboardController.getTicketObservableList());
        tv_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_code_col.setCellValueFactory(new PropertyValueFactory<>("code"));
        tv_customer_name_col.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tv_customer_phone_col.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        showTaskCol();
        showEmployee();
        showStatusCol();
        showBranchCol();
        showDayApplyCol();
        showActionCol();

        m_branch_cb.setItems(DashboardController.getBranchListName());
        m_status_cb.setItems(DashboardController.getTicketStatusListName());
        m_task_cb.setItems(DashboardController.getTaskListName());
        m_employee_apply_cb.setItems(DashboardController.getEmployeeListName());
        ticket_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, ticket, t1) -> {
            m_customer_name_cb.setValue(t1.getCustomerName());
            m_customer_phone_cb.setValue(t1.getCustomerPhone());
            m_branch_cb.setValue(findBranch(t1.getTaskListId()).getName());
            m_status_cb.setValue(findItem(t1.getStatus(), DashboardController.getTicketStatusObservableList(), ticketStatus -> ticketStatus.getId() == t1.getStatus()).getName());
            m_task_cb.setValue(findItem(t1.getTaskListId(), DashboardController.getTaskListObservableList(), taskList -> taskList.getId() == t1.getTaskListId()).getCode());
            m_employee_apply_cb.setValue(findItem(t1.getEmployeeId(), DashboardController.getEmployeeObservableList(), employee -> employee.getId() == t1.getEmployeeId()).getCode());
        });

    }

    private void showTaskCol() {
        tv_task_code_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getTaskListId();
                    setText(findItem(id, DashboardController.getTaskListObservableList(), router -> router.getId() == id).getCode());
                }else {
                    setText("");
                }
            }
        });
    }

    private void showEmployee() {
        tv_employee_apply_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getEmployeeId();
                    setText(findItem(id, DashboardController.getEmployeeObservableList(), router -> router.getId() == id).getCode() + "-" + findItem(id, DashboardController.getEmployeeObservableList(), router -> router.getId() == id).getlName());
                }else {
                    setText("");
                }
            }
        });
    }
    private void showStatusCol() {
        tv_status_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getEmployeeId();
                    setText(findItem(id, DashboardController.getTicketStatusObservableList(), router -> router.getId() == id).getName());
                }else {
                    setText("");
                }
            }
        });
    }
    private void showBranchCol() {
        tv_branch_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int taskListId = param.getTableView().getItems().get(currentIndex).getTaskListId();
                    Branch branch = findBranch(taskListId);
                    setText(branch.getName());
                }else {
                    setText("");
                }
            }
        });
    }
    private void showDayApplyCol() {
        tv_day_apply_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int taskListId = param.getTableView().getItems().get(currentIndex).getTaskListId();
                    TaskList task = findItem(taskListId, DashboardController.getTaskListObservableList(), taskList -> taskList.getId() == taskListId);
                    setText(task.getDateApply().toString());
                }else {
                    setText("");
                }
            }
        });
    }
    private void showActionCol() {
        tv_action_col.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView1 = new ImageView();

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int stt = param.getTableView().getItems().get(currentIndex).getStatus();
                    TicketStatus status = findItem(stt, DashboardController.getTicketStatusObservableList(), ticketStatus -> ticketStatus.getId() == stt);

                    switch (status.getName()) {
                        case "pending" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/pending.png")));
                            imageView1.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView1);
                        }
                        case "confirm" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/confirm.png")));
                            imageView1.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView1);
                        }
                        case "done" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/done.png")));
                            imageView1.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView1);
                        }
                        default -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/cancel.png")));
                            imageView1.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView1);
                        }
                    }
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    public <T,V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public Branch findBranch(int idTask) {
        TaskList task = findItem(idTask, DashboardController.getTaskListObservableList(), taskList -> taskList.getId() == idTask);
        int idRouter = task.getRouterListId();
        RouterList router = findItem(idRouter, DashboardController.getRouterListObservableList(), routerList -> routerList.getId() == idRouter);
        int idBranch = router.getStartPoint();
        return findItem(idBranch, DashboardController.getBranchObservableList(), branch1 -> branch1.getId() == idBranch);
    }
}
