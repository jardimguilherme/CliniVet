package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.CustomerBo;
import model.bo.EmployeeBo;
import model.entity.Customer;
import model.entity.Employee;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

public class CustomerListController {

    CustomerBo customerBo = new CustomerBo();
    ObservableList<Customer> customerObservableList;

    @FXML
    TableView tableview_customers;

    @FXML
    TableColumn<Customer, String> tablecolumn_customer_name, tablecolumn_customer_cpf;

    @FXML
    private void initialize() {
        System.out.println("View CustomerList iniciada");
        refreshCustomerList();
    }

    private void refreshCustomerList(){
        List<Customer> customerList = customerBo.getAllCustomers();
        customerObservableList = FXCollections.observableList(customerList);
        tablecolumn_customer_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablecolumn_customer_cpf.setCellValueFactory((customer) -> {
            String cpf = customer.getValue().getCpf();
            try {
                MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
                maskFormatter.setValueContainsLiteralCharacters(false);
                return new SimpleStringProperty(maskFormatter.valueToString(cpf));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new SimpleStringProperty(cpf);
        });
        tableview_customers.setItems(customerObservableList);
    }
}
