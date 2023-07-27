package vn.aptech.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import vn.aptech.Model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;

public class LoginController extends Thread implements Initializable {
    public ImageView logo;
    public TextField username_tf;
    public TextField password_tf;
    public Button login_btn;
    public Label error_lbl;
    public Button close_btn;
    public Button minimize_btn;
    private static final StringProperty flag = new SimpleStringProperty("true");
    public Label loadingLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close_btn.setOnAction(actionEvent -> {
            System.exit(0);
        });

        minimize_btn.setOnAction(actionEvent -> onMinimize());
        loadingLabel.setText("Loading...");
        loadingLabel.setVisible(false);
        error_lbl.setVisible(false);

        login_btn.setOnAction(event -> {
            loadingLabel.setVisible(true);
            LoginService loginService = new LoginService();
            loginService.setOnSucceeded(e -> {
                loadingLabel.setVisible(false);
                try {
                    onLogin();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            loginService.start();

        });


    }


    public void onMinimize() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().minimizeStage(stage);
    }

    public void onLogin() throws IOException, InterruptedException {
        if(flag.get().equals("true")) {
            try {
                Model.getInstance().getViewFactory().showAdminWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage = (Stage) error_lbl.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }else {
            error_lbl.setVisible(true);
        }


    }

    private static class LoginService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() {
                    // Simulate login process (replace this with your actual login logic)
                    try {
                        sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
    }
}
