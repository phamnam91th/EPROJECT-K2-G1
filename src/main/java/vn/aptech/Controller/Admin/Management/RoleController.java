package vn.aptech.Controller.Admin.Management;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import vn.aptech.Model.Role;

import java.net.URL;
import java.util.ResourceBundle;

public class RoleController implements Initializable {
    private static ObservableList<Role> roleObservableList;
    private static ObservableList<String> roleListName;

    public static ObservableList<Role> getRoleObservableList() {
        return roleObservableList;
    }

    public static ObservableList<String> getRoleListName() {
        return roleListName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
