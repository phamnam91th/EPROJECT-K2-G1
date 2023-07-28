package vn.aptech.Controller.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.Model;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SettingController implements Initializable {
    public TextField dashboard_delay_tf;
    public Button save_btn;
    private static final String path = "E:\\PROJECT\\EPROJECT-K2-G1\\src\\main\\resources\\Config\\config.txt";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str ;
            while ((str = bufferedReader.readLine()) != null) {
                String key = str.split(":")[0];
                if(key.equals("time_delay")) {
                    String value = str.split(":")[1];
                    dashboard_delay_tf.setText(value);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        save_btn.setOnAction(actionEvent -> {
            File file = new File(path);
            String value = dashboard_delay_tf.getText();
            String str = "time_delay:"+value;
            try{
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str);
                bufferedWriter.flush();
                LoginController.setDelay(Integer.parseInt(value));
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) dashboard_delay_tf.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });
    }

}
