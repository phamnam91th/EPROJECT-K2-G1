package vn.aptech.Controller.Admin.Management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import vn.aptech.Model.Model;
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
        System.out.println("Role");
        roleObservableList  = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("role").forEach(s -> {
            roleObservableList.add((Role) s);
        });

        roleListName = Model.getInstance().getData().getListName("role", "name");

    }

}
