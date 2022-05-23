package com.bibllioteca.biblioteca;
import com.bibllioteca.biblioteca.domain.Book;
import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.utils.WindowObservable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookFieldsController extends WindowObservable {

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField bookCodeTextField;

    @FXML
    private Button commitButton;

    @FXML
    private TextField titleTextField;

    Service service;
    String actionType;
    Book selectedBook;

    public void setService(Service service) {
        this.service = service;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setSelectedBook(Book selectedBook){this.selectedBook=selectedBook;}

    @FXML
    void commitButtonClicked(MouseEvent event) {
        int idBook=Integer.parseInt(bookCodeTextField.getText());
        String title=titleTextField.getText();
        String author=authorTextField.getText();
        if(actionType.equals("Add"))
            service.addBook(idBook,title, author);
        else
            service.editBook(selectedBook.getIdUnique(),idBook,title,author);
        notifyObservers();
    }

}
