package vn.aptech.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import vn.aptech.Controller.Admin.Management.EmployeeCellController;
import vn.aptech.Model.Employee;
import javafx.scene.control.ListView;

public class EmployeeCellFactory implements Callback<ListView<Employee>, ListCell<Employee>> {
    @Override
    public ListCell<Employee> call(ListView<Employee> employeeListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Employee employee, boolean empty) {
                super.updateItem(employee, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/EmployeeCell.fxml"));
                    EmployeeCellController controller = new EmployeeCellController(employee);
                    loader.setController(controller);
                    setText(null);
                    try {
                        setGraphic(loader.load());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
