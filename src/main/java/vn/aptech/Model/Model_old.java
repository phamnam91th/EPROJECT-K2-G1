package vn.aptech.Model;

import vn.aptech.Views.AccountType;
import vn.aptech.Views.ViewFactory;

public class Model_old {
    private static  Model_old model;
    private final ViewFactory viewFactory;
    private final Data data;
    private AccountType accountType;
    private boolean loginSuccess;
    private Users currentUse;

    private Model_old() {
        this.viewFactory = new ViewFactory();
        this.data = new Data();
    }

    public static synchronized Model_old getInstance() {
        if(model ==null) {
            model = new Model_old();
        }
        return model;
    }

    public Data getData() {
        return data;
    }

    public static void setModel(Model_old model) {
        Model_old.model = model;
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
        Users users = Model_old.getInstance().data.getLoginResult(userName, password);
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

    public static void main(String[] args) {
        Users user = Model_old.getInstance().data.getLoginResult("c1001", "123456");
        System.out.println(user);
    }
}
