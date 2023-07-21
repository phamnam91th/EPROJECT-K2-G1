package vn.aptech;

import javafx.application.Application;
import javafx.stage.Stage;
import vn.aptech.Model.Model;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }

}
