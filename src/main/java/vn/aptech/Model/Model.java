package vn.aptech.Model;

import vn.aptech.Views.AccountType;
import vn.aptech.Views.ViewFactory;

public class Model {
    private static volatile Model instance;
    private  AccountType accountType;
    private final ViewFactory viewFactory;
    private final Data data;
    private boolean loginSuccess;
    private Users currentUse;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.data = new Data();
        // private constructor
    }


    public static Model getInstance() {
        if (instance == null) {
            synchronized (Model.class) {
                if (instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    public Data getData() {
        return data;
    }

    public static void setModel(Model model) {
        Model.instance = model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public Users getUsers() {
        return currentUse;
    }

    public void setUsers(Users users) {
        this.currentUse = users;
    }

    public void isLogin(String userName, String password) {
        Users users = Model.getInstance().data.getLoginResult(userName, password);
        if(users != null) {
            currentUse = users;
            loginSuccess = true;
            if(users.getRoleId() == 1) {
                setAccountType(AccountType.ADMIN);
            } else {
                setAccountType(AccountType.CLIENT);
            }
        }else {
            loginSuccess = false;
        }
    }
}