package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.entity.Employee;

import java.io.IOException;
import java.util.List;

public class HomeController {

    Employee activeEmployee;

    @FXML
    StackPane stackpane_content;

    @FXML
    private void initialize() {
        System.out.println("View HOME iniciada");
        loadLoginScreen();
    }

    public void logout(){
        setActiveEmployee(null);
        loadLoginScreen();
    }

    public void loadContent(Employee employee){
        setActiveEmployee(employee);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Content.fxml"));
            Pane loginPane = loader.load();
            ContentController controller = loader.getController();
            controller.setHomeController(this);
            List<Node> children = stackpane_content.getChildren();
            children.clear();
            children.add(loginPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLoginScreen(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Pane loginPane = loader.load();
            LoginController controller = loader.getController();
            controller.setHomeController(this);
            List<Node> children = stackpane_content.getChildren();
            children.clear();
            children.add(loginPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee getActiveEmployee() {
        return activeEmployee;
    }

    public void setActiveEmployee(Employee activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

}
