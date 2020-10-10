package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.EmployeeBo;
import model.entity.Employee;
import java.util.List;

public class EmployeeListController {

    EmployeeBo employeeBo = new EmployeeBo();
    ObservableList<Employee> employeeObservableList;

    @FXML
    TableView tableview_employees;

    @FXML
    TableColumn<Employee, String> tablecolumn_employee_name, tablecolumn_employee_function;

    @FXML
    private void initialize() {
        System.out.println("View EmployeeList iniciada");
        refreshEmployeeList();
    }

    private void refreshEmployeeList(){
        List<Employee> employeeList = employeeBo.getAllEmployees();
        employeeObservableList = FXCollections.observableList(employeeList);
        tablecolumn_employee_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablecolumn_employee_function.setCellValueFactory((employee) ->  new SimpleStringProperty(employee.getValue().getFunction()));
        tableview_employees.setItems(employeeObservableList);
    }
}