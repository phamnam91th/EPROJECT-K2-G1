package vn.aptech.Model;

import vn.aptech.Views.AccountType;
import vn.aptech.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final Data data;
    private AccountType accountType;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.data = new Data();
    }

    public static synchronized Model getInstance() {
        if(model ==null) {
            model = new Model();
        }
        return model;
    }

    public static void setModel(Model model) {
        Model.model = model;
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

    public Data getData() {
        return data;
    }
}
