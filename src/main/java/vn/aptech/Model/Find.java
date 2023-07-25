package vn.aptech.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vn.aptech.Controller.Admin.Management.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Find {
    // Common method to find an object by its id
    public static  <T> T findItemById(int id, ObservableList<T> itemList, Predicate<T> predicate) {
        for (T item : itemList) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        List<ListCar> listCars = new ArrayList<>();
        ObservableList<ListCar> listCarObservableList = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("list_car").forEach(s -> {
            listCarObservableList.add((ListCar) s);
        });

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        Model.getInstance().getData().getObservableList("employee").forEach(s -> {
            employeeObservableList.add((Employee) s);
        });


        ListCar cars = findItemById(1, listCarObservableList, ca -> ca.getId() == 1);
        System.out.println(cars);

        Employee e = findItemById(2, employeeObservableList, employee -> employee.getId() == 2);
        System.out.println(e);

    }
}

