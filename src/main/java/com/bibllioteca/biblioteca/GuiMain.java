package com.bibllioteca.biblioteca;

import com.bibllioteca.biblioteca.repository.RepositoryBooks;
import com.bibllioteca.biblioteca.repository.RepositoryLibrarians;
import com.bibllioteca.biblioteca.repository.RepositoryUsers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GuiMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties props=new Properties();
        try {
            props.load(new FileReader("db.config.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find db.config "+e);
        }

        RepositoryLibrarians repositoryLibrarians=new RepositoryLibrarians(props);
        RepositoryUsers repositoryUsers=new RepositoryUsers(props);
        RepositoryBooks repositoryBooks=new RepositoryBooks(props);
        Service service=new Service(repositoryUsers,repositoryLibrarians,repositoryBooks);

        FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("loginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Log In");
        stage.setScene(scene);

        LoginMenuController loginController = fxmlLoader.getController();
        loginController.setService(service);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}