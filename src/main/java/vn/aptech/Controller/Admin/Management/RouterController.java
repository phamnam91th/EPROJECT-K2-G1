package vn.aptech.Controller.Admin.Management;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import vn.aptech.Controller.Admin.DashboardController;
import vn.aptech.Model.Model;
import vn.aptech.Model.RouterList;
import vn.aptech.Views.RouterCellFactory;

import javax.persistence.EntityManager;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class RouterController implements Initializable {
    public ListView<RouterList> router_lv;
    public TextField code_tf;
    public ChoiceBox<String> startPoint_cb;
    public ChoiceBox<String> destination_cb;
    public TextField startAt_tf;
    public TextField endAt_tf;
    public TextField price_tf;
    public Button save_btn;
    public Button update_btn;
    public Button clear_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Router");

        router_lv.setItems(DashboardController.getRouterListObservableList());
        router_lv.setCellFactory(new RouterCellFactory());
        startPoint_cb.setItems(DashboardController.getBranchListName());
        destination_cb.setItems(DashboardController.getBranchListName());

        DashboardController.getRouterListObservableList().addListener((ListChangeListener<RouterList>) change -> {
            router_lv.setItems(DashboardController.getRouterListObservableList());
        });

        router_lv.getSelectionModel().selectedItemProperty().addListener((observableValue, routerList, t1) -> {
            code_tf.setText(t1.getCode());
            startPoint_cb.setValue(findItem(t1.getStartPoint(), DashboardController.getBranchObservableList(), branch -> branch.getId() == t1.getStartPoint()).getName());
            destination_cb.setValue(findItem(t1.getStartPoint(), DashboardController.getBranchObservableList(), branch -> branch.getId() == t1.getDestination()).getName());
            startAt_tf.setText(t1.getStartTime().toString());
            endAt_tf.setText(t1.getEndTime().toString());
            price_tf.setText(String.valueOf(t1.getPrice()));
        });

        save_btn.setOnAction(actionEvent -> {
            RouterList router = new RouterList();
            setRouter(router,"new");
            Model.getInstance().getData().add(router);
            DashboardController.getRouterListObservableList().add(router);
        });

        clear_btn.setOnAction(actionEvent -> {
            code_tf.setText("");
            startPoint_cb.setValue(null);
            destination_cb.setValue(null);
            startAt_tf.setText("");
            endAt_tf.setText("");
            price_tf.setText("");
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            RouterList router = null;
            try{
                em.getTransaction().begin();
                router = em.find(RouterList.class, router_lv.getSelectionModel().getSelectedItem().getId());
                setRouter(router, "update");
                em.merge(router);
                em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Model.getInstance().getData().closeConnect();
            }
            DashboardController.getRouterListObservableList().remove(router_lv.getSelectionModel().getSelectedItem());
            DashboardController.getRouterListObservableList().add(router);
        });

    }

    public void setRouter(RouterList router, String type) {
        router.setCode(code_tf.getText());
        router.setStartPoint(findItem(startPoint_cb.getValue(), DashboardController.getBranchObservableList(), car->car.getName().equals(startPoint_cb.getValue())).getId());
        router.setDestination(findItem(destination_cb.getValue(), DashboardController.getBranchObservableList(), car->car.getName().equals(destination_cb.getValue())).getId());
        String[] startTime = startAt_tf.getText().split(":");
        Time st = new Time(Integer.parseInt(startTime[0]),Integer.parseInt(startTime[1]),Integer.parseInt(startTime[2]));
        router.setStartTime(st);
        String[] endTime = endAt_tf.getText().split(":");
        Time et = new Time(Integer.parseInt(endTime[0]),Integer.parseInt(endTime[1]),Integer.parseInt(endTime[2]));
        router.setEndTime(et);
        router.setPrice(Double.parseDouble(price_tf.getText()));
        router.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(type.equals("new")) {
            router.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }else {
            router.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }

    }


    public <T,V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }


}
