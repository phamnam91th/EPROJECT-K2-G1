package vn.aptech.Controller.Admin.Management;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vn.aptech.Controller.Admin.DashboardController;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.Branch;
import vn.aptech.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class BranchCellController implements Initializable {
    public Button del_btn;
    public Label address_lb;
    public Label email_lb;

    public Label hotline_lb;

    public Label name_lb;

    private final Branch branch;

    public BranchCellController(Branch branch) {
        this.branch = branch;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        address_lb.setText(branch.getAddress());
        email_lb.setText(branch.getEmail());
        hotline_lb.setText(branch.getHotline());
        name_lb.setText(branch.getName());

        del_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().delete(branch, branch.getId());
            LoginController.getBranchObservableList().remove(branch);
        });

    }

    public static void main(String[] args) {

    }
}
