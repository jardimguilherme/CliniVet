package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.entity.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ContentController {

    HomeController homeController;
    Employee activeEmployee;

    @FXML
    Text text_username;

    @FXML
    TabPane tabpane_tabs;

    @FXML
    Tab tab_clientes, tab_produtos, tab_servicos, tab_agenda_consultas, tab_agenda_servicos, tab_pedido, tab_admin;

    @FXML
    Pane pane_main_content;

    @FXML
    private void initialize() {
        System.out.println("View CONTENT iniciada.");
        activeTab(tabpane_tabs.getSelectionModel().getSelectedItem());
    }

    public void handleLogout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Efetuar Logout");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("Tem certeza de que deseja sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            homeController.logout();
        }
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
        setActiveUser(homeController.getActiveEmployee());
        addTabListener();
    }

    private void addTabListener(){
        tabpane_tabs.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if(newTab != oldTab){
                activeTab(newTab);
            }
        });
    }

    private void activeTab(Tab tab){
        String view = null;
        List<Node> children = pane_main_content.getChildren();
        children.clear();

        if(tab == tab_clientes){
            view = "/view/CustomerList.fxml";
        }

        if(tab == tab_produtos){
            view = "/view/ProductList.fxml";
        }

        if(tab == tab_produtos){
            view = "/view/ProductList.fxml";
        }

        if(tab == tab_servicos){
            view = "/view/ProcedureList.fxml";
        }

        if(tab == tab_admin){
            view = "/view/EmployeeList.fxml";
        }


        if(view != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
                AnchorPane tabContent = loader.load();
//                ContentController controller = loader.getController();
//                controller.setHomeController(this);
                children.add(tabContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setActiveUser(Employee employee){
        activeEmployee = employee;
        text_username.setText(activeEmployee.getName());
        if(!activeEmployee.isAdmin()){
            tabpane_tabs.getTabs().remove(tab_admin);
        }
    }

}
