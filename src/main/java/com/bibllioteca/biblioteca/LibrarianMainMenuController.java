package com.bibllioteca.biblioteca;

import com.bibllioteca.biblioteca.domain.Book;
import com.bibllioteca.biblioteca.domain.Librarian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibrarianMainMenuController implements Initializable {
    Service service;
    Librarian librarian;

    @FXML
    private ListView<Book> booksList;

    @FXML
    private Button logOutButton;

    @FXML
    private Label nameLabel;

    public void setService(Service service) {
        this.service = service;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(librarian.getFirstName()+" "+librarian.getLastName());
        List<Book> availableBooks=service.findAllAvailableBooks();
        final ObservableList<Book> data = FXCollections.observableArrayList(
                availableBooks
        );

        booksList.setItems(data);

    }

    @FXML
    void logOutButtonClicked(MouseEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("loginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Log In");
        stage.setScene(scene);

        LoginMenuController loginController = fxmlLoader.getController();
        loginController.setService(service);

        stage.show();
    }
}