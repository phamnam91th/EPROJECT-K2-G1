package vn.aptech.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import vn.aptech.Model.*;
import javax.persistence.EntityManager;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class TaskController implements Initializable {

    public TextField s_code_tf;
    public ChoiceBox<String> s_select_car_cb;
    public ChoiceBox<String> s_select_driver_cb;
    public ChoiceBox<String> s_select_router_cb;
    public DatePicker s_select_day_dp;

    public ChoiceBox<String> m_select_car_cb;
    public ChoiceBox<String> m_select_router_cb;
    public ChoiceBox<String> m_select_driver_cb;
    public DatePicker m_date_active_dp;
    public ChoiceBox<String> m_select_status_cb;

    public Button save_btn;
    public Button update_btn;
    public Button delete_btn;
    public Button search_btn;
    public TableView<TaskList> taskList_tv;
    public TableColumn<TaskList, Integer> id_col;
    public TableColumn<TaskList, String> code_col;
    public TableColumn<TaskList, String> driver_name_col;
    public TableColumn<TaskList, Date> date_active_col;
    public TableColumn<TaskList, String> status_col;
    public TableColumn<TaskList, String> router_code_col;
    public TableColumn<TaskList, ImageView> action_col;

    public Button refresh;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        code_col.setCellValueFactory(new PropertyValueFactory<>("code"));
        date_active_col.setCellValueFactory(new PropertyValueFactory<>("dateApply"));
        showStatusCol();
        showDriverNameCol();
        showRouterCol();
        showActionCol();

        m_select_car_cb.setItems(DashboardController.getCarListName());
        m_select_router_cb.setItems(DashboardController.getRouterListCode());
        m_select_driver_cb.setItems(DashboardController.getUsersListName());
        m_select_status_cb.setItems(DashboardController.getTaskStatusListName());

        taskList_tv.setItems(DashboardController.getTaskListObservableList());


        DashboardController.getTaskListObservableList().addListener((ListChangeListener<TaskList>) change -> taskList_tv.setItems(DashboardController.getTaskListObservableList()));

        taskList_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, taskList, t1) -> {
            m_select_car_cb.setValue(findItem(t1.getListCarId(), DashboardController.getListCarObservableList(), car -> car.getId() == t1.getListCarId()).getLicensePlates());
            m_select_driver_cb.setValue(findItem(t1.getUserId(), DashboardController.getUsersObservableList(), user -> user.getId() == t1.getUserId()).getUserName());
            m_select_router_cb.setValue(findItem(t1.getRouterListId(), DashboardController.getRouterListObservableList(), router -> router.getId() == t1.getRouterListId()).getCode());
            m_date_active_dp.setValue(t1.getDateApply().toLocalDate());
            m_select_status_cb.setValue(findItem(t1.getStatus(), DashboardController.getTaskStatusObservableList(), status -> status.getId() == t1.getStatus()).getName());
        });

        save_btn.setOnAction(actionEvent -> {
            TaskList task = new TaskList();
            setTasList(task, "new");
            Model.getInstance().getData().add(task);
            DashboardController.getTaskListObservableList().add(task);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            TaskList task = null;
            int index = DashboardController.getTaskListObservableList().indexOf(taskList_tv.getSelectionModel().getSelectedItem());
            try{
                em.getTransaction().begin();
                task = em.find(TaskList.class, taskList_tv.getSelectionModel().getSelectedItem().getId());
                setTasList(task, "update");
                em.merge(task);
                em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Model.getInstance().getData().closeConnect();
            }
            DashboardController.getTaskListObservableList().remove(index);
            DashboardController.getTaskListObservableList().add(index,task);
        });

        delete_btn.setOnAction(actionEvent -> {
            TaskList task = taskList_tv.getSelectionModel().getSelectedItem();
            Model.getInstance().getData().delete(task, task.getId());
            DashboardController.getTaskListObservableList().remove(task);
        });

        refresh.setOnAction(actionEvent -> {
            int n = DashboardController.getTaskListObservableList().size();
            if (n > 0) {
                DashboardController.getTaskListObservableList().subList(0, n).clear();
            }
            Model.getInstance().getData().getObservableList("task_list").forEach(s -> {
                DashboardController.getTaskListObservableList().add((TaskList) s);
            });
            FXCollections.reverse(DashboardController.getTaskListObservableList());
        });

        search_btn.setOnAction(actionEvent -> {



        });
    }

    public void setTasList(TaskList taskList, String type) {
        ListCar listCar = findItem(m_select_car_cb.getValue(), DashboardController.getListCarObservableList(), car->car.getLicensePlates().equals(m_select_car_cb.getValue()));
        taskList.setListCarId(listCar.getId());
        RouterList router = findItem(m_select_router_cb.getValue(), DashboardController.getRouterListObservableList(), rt -> rt.getCode().equals(m_select_router_cb.getValue()));
        taskList.setRouterListId(router.getId());
        taskList.setDateApply(Date.valueOf(m_date_active_dp.getValue()));
        TaskStatus status = findItem(m_select_status_cb.getValue(), DashboardController.getTaskStatusObservableList(), stt -> stt.getName().equals(m_select_status_cb.getValue()));
        taskList.setStatus(status.getId());
        Users user = findItem(m_select_driver_cb.getValue(), DashboardController.getUsersObservableList(), item -> item.getUserName().equals(m_select_driver_cb.getValue()));
        taskList.setUserId(user.getId());
        String code = m_select_car_cb.getValue() + "/" + m_date_active_dp.getValue() + "/" + m_select_router_cb.getValue() + "/" + m_select_driver_cb.getValue();
        taskList.setCode(code);
        taskList.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(type.equals("new")) {
            taskList.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            taskList.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }
    }

    private void showStatusCol() {
        status_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getStatus();
                    setText(findItem(id, DashboardController.getTaskStatusObservableList(), status -> status.getId() == id).getName());
                }else {
                    setText("");
                }
            }
        });
    }

    private void showDriverNameCol() {
        driver_name_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getUserId();
                    setText(findItem(id, DashboardController.getUsersObservableList(), user -> user.getId() == id).getUserName());
                }else {
                    setText("");
                }
            }
        });
    }

    private void showRouterCol() {
        router_code_col.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int id = param.getTableView().getItems().get(currentIndex).getRouterListId();
                    setText(findItem(id, DashboardController.getRouterListObservableList(), router -> router.getId() == id).getCode());
                }else {
                    setText("");
                }
            }
        });
    }


    private void showActionCol() {
        action_col.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty().getValue();
                    int stt = param.getTableView().getItems().get(currentIndex).getStatus();
                    TaskStatus status = findItem(stt, DashboardController.getTaskStatusObservableList(), taskStatus -> taskStatus.getId() == stt);
                    switch (status.getName()) {
                        case "active" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/active.png")));
                            imageView.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView);
                        }
                        case "done" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/done.png")));
                            imageView.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView);
                        }
                        case "cancel" -> {
                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/cancel.png")));
                            imageView.setImage(image);
                            setStyle("-fx-alignment: center");
                            setGraphic(imageView);
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

    private void addButtonToTable(TableColumn<TaskList, Void> colBtn, String text, String color) {
        Callback<TableColumn<TaskList, Void>, TableCell<TaskList, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<TaskList, Void> call(final TableColumn<TaskList, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();

                    {

                        btn.setOnAction((ActionEvent event) -> {
                            TaskList data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setText(text);
                            btn.setStyle(color);
                        }
                    }
                };
            }
        };
        colBtn.setCellFactory(cellFactory);
    }

    public static void main(String[] args) {
//        TaskController controller = new TaskController();
//        routerListObservableList = FXCollections.observableArrayList();
//        Model.getInstance().getData().getObservableList("router_list").forEach(s -> {
//            routerListObservableList.add((RouterList) s);
//        });
//        RouterList t =  controller.findItem("HN-TH-09", routerListObservableList, router -> router.getCode().equals("HN-TH-09"));
//        System.out.println(t);
    }
}
