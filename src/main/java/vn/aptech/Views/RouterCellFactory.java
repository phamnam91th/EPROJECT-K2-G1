package vn.aptech.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import vn.aptech.Controller.Admin.Management.RouterCellController;
import vn.aptech.Model.RouterList;

public class RouterCellFactory implements Callback<ListView<RouterList>, ListCell<RouterList>> {
    @Override
    public ListCell<RouterList> call(ListView<RouterList> routerListListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(RouterList routerList, boolean empty) {
                super.updateItem(routerList, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/RouterCell.fxml"));
                    RouterCellController controller = new RouterCellController(routerList);
                    loader.setController(controller);
                    setText(null);
                    try {
                        setGraphic(loader.load());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
