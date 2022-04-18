package com.bibllioteca.biblioteca;

import com.bibllioteca.biblioteca.domain.Librarian;
import com.bibllioteca.biblioteca.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Service service;
    String accountType;

    public LoginController(){
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @FXML
    private Label ErrorMessageLoginIn;

    @FXML
    private PasswordField InvisiblePasswordField;

    @FXML
    private Button LogInButton;

    @FXML
    private Button RegisterButton;

    @FXML
    private CheckBox ShowPasswordCheckBox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField VisiblePasswordField;

    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        VisiblePasswordField.setManaged(false);
        VisiblePasswordField.setVisible(false);

        VisiblePasswordField.managedProperty().bind(ShowPasswordCheckBox.selectedProperty());
        VisiblePasswordField.visibleProperty().bind(ShowPasswordCheckBox.selectedProperty());

        InvisiblePasswordField.managedProperty().bind(ShowPasswordCheckBox.selectedProperty().not());
        InvisiblePasswordField.visibleProperty().bind(ShowPasswordCheckBox.selectedProperty().not());
        VisiblePasswordField.textProperty().bindBidirectional(InvisiblePasswordField.textProperty());

    }

    @FXML
    void LogInButtonClicked(MouseEvent event) throws IOException {
        String firstName=firstNameTextField.getText();
        String lastName=lastNameTextField.getText();
        String password=InvisiblePasswordField.getText();


        try {

            if(accountType.equals("librarian")) {
                Librarian librarian=service.checkLibrarianAccount(Integer.parseInt(password),firstName,lastName);

                LibrarianMainMenuController librarianController = new LibrarianMainMenuController();
                FXMLLoader loader= new FXMLLoader(getClass().getResource("librarianMainMenu.fxml"));
                librarianController.setService(service);
                librarianController.setLibrarian(librarian);
                loader.setController(librarianController);
                root=loader.load();

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene=new Scene(root);
                stage.setTitle("Library");
                stage.setScene(scene);

                stage.show();
            }
            else if(accountType.equals("user")){
                User user=service.checkUserAccount(Integer.parseInt(password),firstName,lastName);

                UserMainMenuController userController = new UserMainMenuController();
                FXMLLoader loader= new FXMLLoader(getClass().getResource("userMainMenu.fxml"));
                userController.setService(service);
                userController.setUser(user);
                loader.setController(userController);
                root=loader.load();

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene=new Scene(root);
                stage.setTitle("Library");
                stage.setScene(scene);

                stage.show();
            }

        }catch(ServiceException ex){
            ErrorMessageLoginIn.setText(ex.getMessage());
        }

    }

    @FXML
    void RegisterButtonClicked(MouseEvent event){

    }

}
