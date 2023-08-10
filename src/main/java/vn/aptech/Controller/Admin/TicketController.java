package vn.aptech.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.*;

import javax.persistence.EntityManager;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TicketController implements Initializable {
    public TextField s_customer_phone_tf;
    public TextField s_customer_name_tf;
    public ChoiceBox<String> s_branch_cb;
    public DatePicker s_day_dp;
    public Button search_btn;
    public TableView<Ticket> ticket_tv;
    public TableColumn<Ticket, Integer> tv_id_col;
    public TableColumn<Ticket, String> tv_code_col;
    public TableColumn<Ticket, String> tv_customer_name_col;
    public TableColumn<Ticket, String> tv_customer_phone_col;
    public TableColumn<Ticket, Integer> tv_quantity_col;
    public TableColumn<Ticket, String> tv_branch_col;
    public TableColumn<Ticket, String> tv_task_code_col;
    public TableColumn<Ticket, String> tv_employee_apply_col;
    public TableColumn<Ticket, Date> tv_day_apply_col;
    public TableColumn<Ticket, String> tv_status_col;
    public TableColumn<Ticket, ImageView> tv_action_col;
    public ChoiceBox<String> m_task_cb;
    public ChoiceBox<String> m_employee_apply_cb;
    public ChoiceBox<String> m_status_cb;
    public Button save_btn;
    public Button update_btn;
    public Button delete_btn;
    public Button refresh_btn;
    public Button confirm_btn;
    public Button done_btn;
    public Button cancel_btn;
    public TextField customer_name_tf;
    public TextField customer_phone_tf;
    public TextField quantity_tf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> taskListPending = getTaskPending();

        System.out.println(LoginController.getTicketStatusListName());

        tv_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_code_col.setCellValueFactory(new PropertyValueFactory<>("code"));
        tv_customer_name_col.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tv_customer_phone_col.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        tv_quantity_col.setCellValueFactory(new PropertyValueFactory<>("numberOfTicket"));
        showTaskCol();
        showEmployee();
        showStatusCol();
        showBranchCol();
        showDayApplyCol();
        showActionCol();
        ticket_tv.setItems(LoginController.getTicketObservableList());

        LoginController.getTicketObservableList().addListener((ListChangeListener<Ticket>) change -> {
            ticket_tv.setItems(LoginController.getTicketObservableList());
        });


        // theo doi List Task de cap nhat danh sach peding(chi chon duoc nhung xe chua chay)
        LoginController.getTaskListObservableList().addListener((ListChangeListener<TaskList>) change -> {
            taskListPending.clear();
            LoginController.getTaskListObservableList().forEach(s -> {
                String status = findItem(s.getStatus(), LoginController.getTaskStatusObservableList(), i -> i.getId() == s.getStatus()).getName();
                if (status.equals("pending")) {
                    taskListPending.add(s.getCode());
                }
            });
        });


        m_status_cb.setItems(LoginController.getTicketStatusListName());
        System.out.println(taskListPending);

        m_task_cb.setItems(taskListPending);
        m_employee_apply_cb.setItems(LoginController.getEmployeeListName());

        s_branch_cb.setItems(LoginController.getBranchListName());

        ticket_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, ticket, t1) -> {
            customer_name_tf.setText(t1.getCustomerName());
            customer_phone_tf.setText(t1.getCustomerPhone());
            quantity_tf.setText(String.valueOf(t1.getNumberOfTicket()));
            m_status_cb.setValue(findItem(t1.getStatus(), LoginController.getTicketStatusObservableList(), ticketStatus -> ticketStatus.getId() == t1.getStatus()).getName());
            m_task_cb.setValue(findItem(t1.getTaskListId(), LoginController.getTaskListObservableList(), taskList -> taskList.getId() == t1.getTaskListId()).getCode());
            m_employee_apply_cb.setValue(findItem(t1.getEmployeeId(), LoginController.getEmployeeObservableList(), employee -> employee.getId() == t1.getEmployeeId()).getCode());
        });

        save_btn.setOnAction(actionEvent -> {
            TaskList task = findItem(m_task_cb.getValue(), LoginController.getTaskListObservableList(), t -> t.getCode().equals(m_task_cb.getValue()));
            if (Integer.parseInt(quantity_tf.getText()) <= task.getSeatAvailable()) {
                Ticket ticket = new Ticket();
                setTicket(ticket, "new");
                Model.getInstance().getData().add(ticket);
                LoginController.getTicketObservableList().add(0, ticket);
            } else {
                Model.getInstance().getViewFactory().showAlertInfo("Warming", "The number of seats you have selected exceeds the number of seats available.");
            }
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Ticket ticket = null;
            int index = LoginController.getTicketObservableList().indexOf(ticket_tv.getSelectionModel().getSelectedItem());
            try {
                em.getTransaction().begin();
                ticket = em.find(Ticket.class, ticket_tv.getSelectionModel().getSelectedItem().getId());
                setTicket(ticket, "update");
                em.merge(ticket);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Model.getInstance().getData().closeConnect();
            }
            LoginController.getTicketObservableList().remove(index);
            LoginController.getTicketObservableList().add(index, ticket);
        });

        // thay doi trang thai status
        confirm_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Ticket ticket = null;

            int index = LoginController.getTicketObservableList().indexOf(ticket_tv.getSelectionModel().getSelectedItem());
            try {
                em.getTransaction().begin();
                ticket = em.find(Ticket.class, ticket_tv.getSelectionModel().getSelectedItem().getId());
                TicketStatus status = findItem("confirm", LoginController.getTicketStatusObservableList(), s -> s.getName().equals("confirm"));
                ticket.setStatus(status.getId());
                em.merge(ticket);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            TaskList task;
            try {
                em.getTransaction().begin();
                task = em.find(TaskList.class, ticket_tv.getSelectionModel().getSelectedItem().getTaskListId());
                task.setSeatAvailable(task.getSeatAvailable() - ticket_tv.getSelectionModel().getSelectedItem().getNumberOfTicket());
                em.merge(task);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Model.getInstance().getData().closeConnect();
            }
            LoginController.getTicketObservableList().remove(index);
            LoginController.getTicketObservableList().add(index, ticket);
        });

        delete_btn.setOnAction(actionEvent -> {
            Ticket ticket = ticket_tv.getSelectionModel().getSelectedItem();
            Model.getInstance().getData().delete(ticket, ticket.getId());
            LoginController.getTicketObservableList().remove(ticket);
        });

        refresh_btn.setOnAction(actionEvent -> {
            s_customer_name_tf.setText(null);
            s_customer_phone_tf.setText(null);
            s_branch_cb.setValue(null);
            s_day_dp.setValue(null);
            int n = LoginController.getTicketObservableList().size();
            if (n > 0) {
                LoginController.getTicketObservableList().subList(0, n).clear();
            }
            Model.getInstance().getData().getObservableList("select t from ticket t order by t.id DESC").forEach(s -> {
                LoginController.getTicketObservableList().add((Ticket) s);
            });
        });

        search_btn.setOnAction(actionEvent -> {
            ObservableList<Ticket> listSearchResult;
            String customerName;
            String customerPhone;
            String branch;
            String dateApply;

            if (!Objects.equals(s_customer_name_tf.getText(), "")) {
                customerName = s_customer_name_tf.getText();
            } else {
                customerName = "%";
            }

            if (!Objects.equals(s_customer_phone_tf.getText(), "")) {
                customerPhone = s_customer_phone_tf.getText();
            } else {
                customerPhone = "%";
            }

            if (s_branch_cb.getValue() != null) {
                branch = s_branch_cb.getValue();
            } else {
                branch = "%";
            }

            if (s_day_dp.getValue() == null) {
                dateApply = "%";
            } else {
                dateApply = s_day_dp.getValue().toString();
            }

            listSearchResult = Model.getInstance().getData().getTicketSearchResult(customerName, customerPhone, branch, dateApply);
            ticket_tv.setItems(null);
            ticket_tv.setItems(listSearchResult);
            ticket_tv.refresh();
        });
    }

    public void setTicket(Ticket ticket, String type) {
        ticket.setCode("");
        ticket.setCustomerName(customer_name_tf.getText());
        ticket.setCustomerPhone(customer_phone_tf.getText());

        TaskList taskList = findItem(m_task_cb.getValue(), LoginController.getTaskListObservableList(), task -> task.getCode().equals(m_task_cb.getValue()));
        ticket.setTaskListId(taskList.getId());

        Employee employee = findItem(LoginController.getUsername(), LoginController.getEmployeeObservableList(), em -> em.getCode().equals(m_employee_apply_cb.getValue()));
        ticket.setEmployeeId(employee.getId());

        ticket.setBranchId(findBranch(taskList.getId()).getId());
        ticket.setNumberOfTicket(Integer.parseInt(quantity_tf.getText()));

        TicketStatus status = findItem(m_status_cb.getValue(), LoginController.getTicketStatusObservableList(), s -> s.getName().equals(m_status_cb.getValue()));
        ticket.setStatus(status.getId());

        ticket.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (type.equals("new")) {
            ticket.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            ticket.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }


    }

    public ObservableList<String> getTaskPending() {
        ObservableList<String> taskListActive = FXCollections.observableArrayList();
        LoginController.getTaskListObservableList().forEach(s -> {
            String status = findItem(s.getStatus(), LoginController.getTaskStatusObservableList(), i -> i.getId() == s.getStatus()).getName();
            if (status.equals("pending")) {
                taskListActive.add(s.getCode());
            }
        });
        return taskListActive;
    }

    private void showTaskCol() {
        tv_task_code_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getTaskListId();
                    setText(findItem(id, LoginController.getTaskListObservableList(), router -> router.getId() == id).getCode());
                } else {
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
                    setText(findItem(id, LoginController.getEmployeeObservableList(), router -> router.getId() == id).getCode() + "-" + findItem(id, LoginController.getEmployeeObservableList(), router -> router.getId() == id).getlName());
                } else {
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
                    int id = param.getTableView().getItems().get(currentIndex).getStatus();
                    setText(findItem(id, LoginController.getTicketStatusObservableList(), router -> router.getId() == id).getName());
                } else {
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
                } else {
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
                    TaskList task = findItem(taskListId, LoginController.getTaskListObservableList(), taskList -> taskList.getId() == taskListId);
                    setText(task.getDateApply().toString());
                } else {
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
                    TicketStatus status = findItem(stt, LoginController.getTicketStatusObservableList(), ticketStatus -> ticketStatus.getId() == stt);

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
