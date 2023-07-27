package vn.aptech.Controller.Admin.Management;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Controller.Admin.DashboardController;
import vn.aptech.Model.Model;
import vn.aptech.Model.RouterList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class RouterCellController implements Initializable {
    public Button del_btn;
    public Label code_lb;
    public Label destination_lb;
    public Label startAt_lb;
    public Label startPoint_lb;
    private final RouterList routerList;

    public RouterCellController(RouterList routerList) {
        this.routerList = routerList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        code_lb.setText(routerList.getCode());
        startPoint_lb.setText(findItemById(routerList.getStartPoint(), DashboardController.getBranchObservableList(), branch -> branch.getId() == routerList.getStartPoint()).getName());
        destination_lb.setText(findItemById(routerList.getStartPoint(), DashboardController.getBranchObservableList(), branch -> branch.getId() == routerList.getDestination()).getName());
        startAt_lb.setText(routerList.getStartTime().toString());

        del_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().delete(routerList, routerList.getId());
            DashboardController.getBranchObservableList().remove(routerList);
        });
    }

    public <T> T findItemById(int id, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }
}
