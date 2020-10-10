package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.ProductBo;
import model.entity.Product;

import javax.swing.text.MaskFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class ProductListController {

    ProductBo productBo = new ProductBo();
    ObservableList<Product> productObservableList;

    @FXML
    TableView tableview_products;

    @FXML
    TableColumn<Product, String> tablecolumn_product_name, tablecolumn_product_price;

    @FXML
    private void initialize() {
        System.out.println("View ProductList iniciada");
        refreshProductList();
    }

    private void refreshProductList(){
        List<Product> productList = productBo.getAllProducts();
        productObservableList = FXCollections.observableList(productList);
        tablecolumn_product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablecolumn_product_price.setCellValueFactory((product) -> {
            Double price = product.getValue().getPrice();
            DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");
            return new SimpleStringProperty("R$ " + decimalFormat.format(price));
        });
        tableview_products.setItems(productObservableList);
    }
}
