package vn.aptech.Controller.Admin;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {
    public Button back_btn;
    public Button forward_btn;
    public Button reload_btn;
    public TextField address_tf;
    public Button go_btn;
    public Button zoomOut_btn;
    public Button zoomIn_btn;
    public WebView webview_wv;
    private WebEngine engine;
    private double webZoom;
    private WebHistory history;
    List<String> listUrl = new ArrayList<>();
    private static int currenPage = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webview_wv.getEngine();
        webZoom = 1;
        webview_wv.setZoom(webZoom);

        back_btn.setOnAction(actionEvent -> {
            history = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = history.getEntries();
            if(history.getCurrentIndex() > 0) {
                history.go(-1);
                address_tf.setText(String.valueOf(entries.get(history.getCurrentIndex()).getUrl()));
            }
        });

        forward_btn.setOnAction(actionEvent -> {
            history = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = history.getEntries();
            if(history.getCurrentIndex() < entries.size()-1) {
                history.go(1);
                address_tf.setText(String.valueOf(entries.get(history.getCurrentIndex()).getUrl()));
            }
            System.out.println(history.getCurrentIndex());
            System.out.println(entries.size());
        });

        reload_btn.setOnAction(actionEvent -> {
            engine.reload();
        });

        zoomOut_btn.setOnAction(actionEvent -> {
            webZoom -= 0.25;
            webview_wv.setZoom(webZoom);
        });

        zoomIn_btn.setOnAction(actionEvent -> {
            webZoom += 0.25;
            webview_wv.setZoom(webZoom);
        });

        go_btn.setOnAction(actionEvent -> {
            String urlAddress = address_tf.getText();
            if(urlAddress.contains("https://") || urlAddress.contains("http://")) {
                engine.load(urlAddress);
            } else if(urlAddress.contains("localhost")){
                urlAddress = "http://" + urlAddress;
                engine.load(urlAddress);
            }else  {
                urlAddress = "https://" + urlAddress;
                engine.load(urlAddress);
            }

            listUrl.add(urlAddress);
            currenPage++;
        });














    }
}
