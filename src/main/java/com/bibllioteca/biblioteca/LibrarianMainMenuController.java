package com.bibllioteca.biblioteca;

import com.bibllioteca.biblioteca.domain.Book;
import com.bibllioteca.biblioteca.domain.BookUser;
import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.utils.WindowObserver;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class LibrarianMainMenuController extends WindowObserver implements Initializable {
    Service service;
    Librarian librarian;

    @FXML
    private ListView<Book> booksList;

    @FXML
    private Button logOutButton;

    @FXML
    private Button addBookButton;

    @FXML
    private Button editBookButton;

    @FXML
    private Button removeBookButton;

    @FXML
    private Button returnBookButton;

    @FXML
    private Label nameLabel;

    private Parent root;

    public void setService(Service service) {
        this.service = service;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(librarian.getFirstName()+" "+librarian.getLastName());
        List<Book> books=service.findAllBooks();
        final ObservableList<Book> data = FXCollections.observableArrayList(
                books
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

    @FXML
    void addBookButtonClicked(MouseEvent event) throws IOException {
        BookFieldsController bookFieldsController = new BookFieldsController();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("bookFields.fxml"));
        bookFieldsController.addObserver(this);
        bookFieldsController.setService(service);
        bookFieldsController.setActionType("Add");
        loader.setController(bookFieldsController);
        root=loader.load();

        Stage stage = new Stage();
        Scene scene=new Scene(root);
        stage.setTitle("Add a book");
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void editBookButtonClicked(MouseEvent event) throws IOException {
        BookFieldsController bookFieldsController = new BookFieldsController();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("bookFields.fxml"));
        bookFieldsController.addObserver(this);
        bookFieldsController.setService(service);
        bookFieldsController.setActionType("Edit");
        bookFieldsController.setSelectedBook(booksList.getSelectionModel().getSelectedItem());
        loader.setController(bookFieldsController);
        root=loader.load();

        Stage stage = new Stage();
        Scene scene=new Scene(root);
        stage.setTitle("Add a book");
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void removeBookButtonClicked(MouseEvent event) {
        Book book=booksList.getSelectionModel().getSelectedItem();
        service.removeBook(book.getIdUnique());
        update();
    }

    @FXML
    void returnBookButtonClicked(MouseEvent event) {
        Book book=booksList.getSelectionModel().getSelectedItem();
        if(book.getStatus().equals("UNAVAILABLE")) {
            service.editBookStatus(book.getIdUnique(), "AVAILABLE");
            service.returnBook(book.getIdUnique());
            update();
        }
    }

    @Override
    public void update() {
        nameLabel.setText(librarian.getFirstName()+" "+librarian.getLastName());
        List<Book> books=service.findAllBooks();
        final ObservableList<Book> data = FXCollections.observableArrayList(
                books
        );
        booksList.setItems(data);
    }
}
