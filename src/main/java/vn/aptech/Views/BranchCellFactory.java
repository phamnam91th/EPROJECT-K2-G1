package vn.aptech.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import vn.aptech.Controller.Admin.Management.BranchCellController;
import vn.aptech.Model.Branch;

public class BranchCellFactory implements Callback<ListView<Branch>, ListCell<Branch>> {

    @Override
    public ListCell<Branch> call(ListView<Branch> branchListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Branch branch, boolean empty) {
                super.updateItem(branch, empty);
                if(empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Management/BranchCell.fxml"));
                    BranchCellController controller = new BranchCellController(branch);
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
