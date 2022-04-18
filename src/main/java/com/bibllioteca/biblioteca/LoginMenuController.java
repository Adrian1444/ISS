package com.bibllioteca.biblioteca;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMenuController {

    Service service;

    private Parent root;

    @FXML
    private Button userButton;

    @FXML
    private Button librarianButton;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    void librarianButtonClicked(MouseEvent event) throws IOException {
        LoginController loginController = new LoginController();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        loginController.setService(service);
        loginController.setAccountType("librarian");
        loader.setController(loginController);
        root=loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("Log in as librarian");
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void userButtonClicked(MouseEvent event) throws IOException {
        LoginController loginController = new LoginController();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        loginController.setService(service);
        loginController.setAccountType("user");
        loader.setController(loginController);
        root=loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("Log in as user");
        stage.setScene(scene);

        stage.show();
    }

}
