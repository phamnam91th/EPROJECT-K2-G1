package vn.aptech.Controller.Admin.Management;

import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.Branch;
import vn.aptech.Model.Model;
import vn.aptech.Views.BranchCellFactory;

import javax.persistence.EntityManager;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class BranchController implements Initializable {
    public ListView<Branch> branchList_lv;
    public TextField name_tf;
    public TextField address_tf;
    public TextField hotline_tf;
    public TextField email_tf;
    public Button save_btn;
    public Button update_btn;
    public Button clear_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Branch");

        branchList_lv.setItems(LoginController.getBranchObservableList());
        branchList_lv.setCellFactory(new BranchCellFactory());

        LoginController.getBranchObservableList().addListener(((ListChangeListener<Branch>) change -> {
            branchList_lv.setItems(LoginController.getBranchObservableList());
            System.out.println("change");
        }));


        branchList_lv.getSelectionModel().selectedItemProperty().addListener((observableValue, branch, t1) -> {
            name_tf.setText(t1.getName());
            address_tf.setText(t1.getAddress());
            hotline_tf.setText(t1.getHotline());
            email_tf.setText(t1.getEmail());
        });

        save_btn.setOnAction(actionEvent -> {
            Branch branch = new Branch();
            setBranch(branch, "new");
            Model.getInstance().getData().add(branch);
            LoginController.getBranchObservableList().add(branch);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Branch branch = null;
            try {
                em.getTransaction().begin();
                branch = em.find(Branch.class, branchList_lv.getSelectionModel().getSelectedItem().getId());
                setBranch(branch, "update");
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Model.getInstance().getData().closeConnect();
            }
            LoginController.getBranchObservableList().remove(branchList_lv.getSelectionModel().getSelectedItem());
            LoginController.getBranchObservableList().add(branch);

        });

        clear_btn.setOnAction(actionEvent -> {
            name_tf.setText("");
            address_tf.setText("");
            hotline_tf.setText("");
            email_tf.setText("");
        });
    }

    public void setBranch(Branch branch, String type) {
        branch.setName(name_tf.getText());
        branch.setAddress(address_tf.getText());
        branch.setEmail(email_tf.getText());
        branch.setHotline(hotline_tf.getText());
        branch.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (type.equals("new")) {
            branch.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            branch.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }
    }


}
