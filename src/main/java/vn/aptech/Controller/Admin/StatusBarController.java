package vn.aptech.Controller.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vn.aptech.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusBarController implements Initializable {
    public Button minimize_btn;
    public Button close_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        minimize_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage) minimize_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().minimizeStage(stage);
        });

        close_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage) close_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });
    }
}
