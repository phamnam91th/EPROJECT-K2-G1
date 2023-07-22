package vn.aptech.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import vn.aptech.Controller.Admin.Management.UserCellController;
import vn.aptech.Controller.Admin.Management.UserController;
import vn.aptech.Model.Users;

public class UserCellFactory implements Callback<ListView<Users>, ListCell<Users>> {
    @Override
    public ListCell<Users> call(ListView<Users> usersListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Users user, boolean empty) {
                super.updateItem(user, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/UserCell.fxml"));
                    UserCellController controller = new UserCellController(user);
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
