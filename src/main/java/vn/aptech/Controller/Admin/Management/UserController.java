package vn.aptech.Controller.Admin.Management;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import vn.aptech.Controller.LoginController;
import vn.aptech.Model.*;
import vn.aptech.Views.UserCellFactory;

import javax.persistence.EntityManager;
import javax.xml.bind.DatatypeConverter;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class UserController implements Initializable {
    public ListView<Users> listUser_lv;
    public TextField userName_tf;
    public TextField password_tf;
    public ChoiceBox<String> employeeName_cb;
    public ChoiceBox<String> employeeCreate_cb;
    public ChoiceBox<String> role_cb;
    public Button save_btn;
    public Button update_btn;
    public Button clear_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User");
        clear_btn.setDisable(true);

        ObservableList<String> employeeListName = FXCollections.observableArrayList();
        LoginController.getEmployeeObservableList().forEach(s -> {
            employeeListName.add(s.getCode()+"-"+s.getlName()+" "+s.getfName());
        });

//        employeeListName = Model.getInstance().getData().getListName("employee", "lName");

        listUser_lv.setItems(LoginController.getUsersObservableList());
        listUser_lv.setCellFactory(new UserCellFactory());

        LoginController.getUsersObservableList().addListener((ListChangeListener<Users>) change -> {
            listUser_lv.setItems(LoginController.getUsersObservableList());
        });

        employeeName_cb.setItems(employeeListName);
        employeeCreate_cb.setItems(employeeListName);
        role_cb.setItems(LoginController.getRoleListName());

        listUser_lv.getSelectionModel().selectedItemProperty().addListener((observableValue, users, t1) -> {
            userName_tf.setText(t1.getUserName());
            password_tf.setText(t1.getPassword());
            employeeName_cb.setItems(employeeListName);
            employeeName_cb.setValue(idToEmployee(t1.getEmployeeId()).getCode() + "-" + idToEmployee(t1.getEmployeeId()).getlName());
            employeeCreate_cb.setItems(employeeListName);
            employeeCreate_cb.setValue(idToEmployee(t1.getEmployeeCreate()).getCode() + "-" + idToEmployee(t1.getEmployeeCreate()).getlName());
            role_cb.setItems(LoginController.getRoleListName());
            role_cb.setValue(idToRole(t1.getRoleId()).getName());
        });

        save_btn.setOnAction(actionEvent -> {
            Users users = new Users();
            try {
                setUser(users,"new");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            if( Model.getInstance().getData().add(users)) {
                LoginController.getUsersObservableList().add(users);
            }
        });

        clear_btn.setOnAction(actionEvent -> {
            userName_tf.setText("");
            password_tf.setText("");
            employeeName_cb.setItems(employeeListName);
            employeeCreate_cb.setItems(employeeListName);
            role_cb.setItems(LoginController.getRoleListName());
            employeeCreate_cb.setValue(null);
            employeeName_cb.setValue(null);
            role_cb.setValue(null);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Users user = null;
            int index = LoginController.getUsersObservableList().indexOf(listUser_lv.getSelectionModel().getSelectedItem());
            try{
                em.getTransaction().begin();
                user = em.find(Users.class, listUser_lv.getSelectionModel().getSelectedItem().getId());
                setUser(user, "update");
                em.merge(user);
                em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Model.getInstance().getData().closeConnect();
            }
            LoginController.getUsersObservableList().remove(index);
            LoginController.getUsersObservableList().add(index,user);
        });


    }

    public void setUser(Users user, String type) throws NoSuchAlgorithmException {
        user.setUserName(userName_tf.getText());
        user.setPassword(Model.getInstance().getData().getEncodePassword(password_tf.getText()));
        user.setEmployeeId(codeAndNameToId(employeeName_cb.getValue()).getId());
        user.setEmployeeCreate(codeAndNameToId(employeeCreate_cb.getValue()).getId());
        user.setRoleId(nameToId(role_cb.getValue()).getId());
        user.setFlag("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(type.equals("new")) {
            user.setCreateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        } else {
            user.setUpdateAt(Timestamp.valueOf(dateFormat.format(timestamp)));
        }
    }

    public <T,V> T findItem(V nameOrId, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public static Employee idToEmployee(int id) {
        Employee employee = null;
        for(Employee e: LoginController.getEmployeeObservableList()) {
            if(id == e.getId()) {
                employee = e;
            }
        }
        return employee;
    }

    public static Employee codeAndNameToId(String codeAndName) {
        Employee employee = new Employee();
        String code = codeAndName.split("-")[0];
        for(Employee e: LoginController.getEmployeeObservableList()) {
            if(code.equals(e.getCode())) {
                employee = e;
            }
        }
       return employee;
    }

    public static Role idToRole(int id) {
        Role role = null;
        for(Role r: LoginController.getRoleObservableList()) {
            if(id == r.getId()) {
                role = r;
            }
        }
        return role;
    }

    public static Role nameToId(String name) {
        Role role = null;
        for(Role r: LoginController.getRoleObservableList()) {
            if(name.equals(r.getName())) {
                role = r;
            }
        }
        return role;
    }


}
