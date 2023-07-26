package vn.aptech.Controller.Admin.Management;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private static ObservableList<Employee> employeeObservableList;
    private static ObservableList<Role> roleObservableList;
    private static ObservableList<Users> usersObservableList;
    private static ObservableList<String> userListName;
    private static ObservableList<String> employeeListName;
    private static ObservableList<String> roleListName;

    public static ObservableList<Users> getUsersObservableList() {
        return usersObservableList;
    }

    public static ObservableList<String> getUserListName() {
        return userListName;
    }

    public static ObservableList<String> getEmployeeListName() {
        return employeeListName;
    }

    public static ObservableList<String> getRoleListName() {
        return roleListName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User");
        clear_btn.setDisable(true);
        usersObservableList  = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("users").forEach(s -> {
            usersObservableList.add((Users) s);
        });

        employeeObservableList  = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("employee").forEach(s -> {
            employeeObservableList.add((Employee) s);
        });

        roleObservableList  = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("role").forEach(s -> {
            roleObservableList.add((Role) s);
        });

        employeeListName = FXCollections.observableArrayList();
        employeeObservableList.forEach(s -> {
            employeeListName.add(s.getCode()+"-"+s.getlName()+" "+s.getfName());
        });

//        employeeListName = Model.getInstance().getData().getListName("employee", "lName");
        userListName = Model.getInstance().getData().getListName("users", "userName");
        roleListName = Model.getInstance().getData().getListName("role", "name");

        listUser_lv.setItems(usersObservableList);
        listUser_lv.setCellFactory(new UserCellFactory());

        usersObservableList.addListener((ListChangeListener<Users>) change -> {
            listUser_lv.setItems(usersObservableList);
        });

        employeeName_cb.setItems(employeeListName);
        employeeCreate_cb.setItems(employeeListName);
        role_cb.setItems(roleListName);

        listUser_lv.getSelectionModel().selectedItemProperty().addListener((observableValue, users, t1) -> {
            userName_tf.setText(t1.getUserName());
            password_tf.setText(t1.getPassword());
            employeeName_cb.setItems(employeeListName);
            employeeName_cb.setValue(idToEmployee(t1.getEmployeeId()).getCode() + "-" + idToEmployee(t1.getEmployeeId()).getlName());
            employeeCreate_cb.setItems(employeeListName);
            employeeCreate_cb.setValue(idToEmployee(t1.getEmployeeCreate()).getCode() + "-" + idToEmployee(t1.getEmployeeCreate()).getlName());
            role_cb.setItems(roleListName);
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
                usersObservableList.add(users);
            }
        });

        clear_btn.setOnAction(actionEvent -> {
            userName_tf.setText("");
            password_tf.setText("");
            employeeName_cb.setItems(employeeListName);
            employeeCreate_cb.setItems(employeeListName);
            role_cb.setItems(roleListName);
            employeeCreate_cb.setValue(null);
            employeeName_cb.setValue(null);
            role_cb.setValue(null);
        });

        update_btn.setOnAction(actionEvent -> {
            Model.getInstance().getData().getConnect();
            EntityManager em = Model.getInstance().getData().getEm();
            Users user = null;
            int index = usersObservableList.indexOf(listUser_lv.getSelectionModel().getSelectedItem());
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
            usersObservableList.remove(index);
            usersObservableList.add(index,user);
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
        for(Employee e: employeeObservableList) {
            if(id == e.getId()) {
                employee = e;
            }
        }
        return employee;
    }

    public static Employee codeAndNameToId(String codeAndName) {
        Employee employee = new Employee();
        String code = codeAndName.split("-")[0];
        for(Employee e: employeeObservableList) {
            if(code.equals(e.getCode())) {
                employee = e;
            }
        }
       return employee;
    }

    public static Role idToRole(int id) {
        Role role = null;
        for(Role r: roleObservableList) {
            if(id == r.getId()) {
                role = r;
            }
        }
        return role;
    }

    public static Role nameToId(String name) {
        Role role = null;
        for(Role r: roleObservableList) {
            if(name.equals(r.getName())) {
                role = r;
            }
        }
        return role;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "ILoveJava";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        System.out.println(myHash);
    }


}
