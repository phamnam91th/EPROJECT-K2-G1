package vn.aptech.Controller.Admin;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vn.aptech.Model.Model;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StatusBarController extends Thread implements Initializable {
    public Button minimize_btn;
    public Button close_btn;
    public Label timeLabel;
    public Label dateLabel;
    private boolean flag;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("status bar");
        Timenow();
        Datenow();
        minimize_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage) minimize_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().minimizeStage(stage);
        });

        close_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage) close_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().exitProgram();
        });
    }

    public void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while (!flag) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    timeLabel.setText(timenow);
                });
            }
        });
        thread.start();
    }

    public void Datenow() {
        Thread t2 = new Thread(() -> {
            SimpleDateFormat  sdf = new SimpleDateFormat("dd-MM-yyyy");
            while (!flag) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String datenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    dateLabel.setText(datenow);
                });
            }
        });
        t2.start();
    }

}
