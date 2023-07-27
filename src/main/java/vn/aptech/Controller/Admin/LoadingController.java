package vn.aptech.Controller.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {
    public static ProgressBar load;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static Stage getStage() {
        return (Stage) load.getScene().getWindow();
    }
}
