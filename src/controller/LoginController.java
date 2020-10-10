package controller;

import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.bo.EmployeeBo;
import model.entity.Employee;
import util.AlertBuilder;

public class LoginController {

    HomeController homeController;
    EmployeeBo employeeBo = new EmployeeBo();

    @FXML
    TextField txtfield_login;

    @FXML
    PasswordField passfield_password;

    @FXML
    private void initialize() {
        System.out.println("View Login iniciada");
    }

    public void handleLogin(){

        String login = txtfield_login.getText();
        String password = passfield_password.getText();

        if(login.isBlank() || login.isEmpty()){
            AlertBuilder.build("error", "Login em branco", null, "Por favor, preencha um login.").showAndWait();
            return;
        }

        if(password.isBlank() || password.isEmpty()){
            AlertBuilder.build("error", "Senha em branco", null, "Por favor, preencha uma senha.").showAndWait();
            return;
        }

        Employee loginEmployee = employeeBo.login(login, password);
        if(loginEmployee != null){
            System.out.println("Logado com o " + loginEmployee.getName());
            homeController.loadContent(loginEmployee);
            return;
        }

        passfield_password.setText("");
        AlertBuilder.build("error", "Não autorizado", null, "Login e/ou senha incorretos.").showAndWait();

    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

}
