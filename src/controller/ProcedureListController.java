package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.ProcedureBo;
import model.entity.Procedure;
import model.entity.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProcedureListController {

    ProcedureBo procedureBo = new ProcedureBo();
    ObservableList<Procedure> procedureObservableList;

    @FXML
    TableView tableview_procedures;

    @FXML
    TableColumn<Procedure, String> tablecolumn_procedure_name, tablecolumn_procedure_price;

    @FXML
    private void initialize() {
        System.out.println("View ProcedureList iniciada");
        refreshProductList();
    }

    private void refreshProductList(){
        List<Procedure> procedureList = procedureBo.getAllProcedures();
        procedureObservableList = FXCollections.observableList(procedureList);
        tablecolumn_procedure_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablecolumn_procedure_price.setCellValueFactory((procedure) -> {
            Double price = procedure.getValue().getPrice();
            DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");
            return new SimpleStringProperty("R$ " + decimalFormat.format(price));
        });
        tableview_procedures.setItems(procedureObservableList);
    }
}
