package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import Model.*;
import ap.javafx.*;

public class AddNewAccountController extends Controller<Customer> {
	@FXML private TextField accountTypeTf;
	
	@FXML private Button addAccountBtn;
	@FXML private Button close;
	@FXML private Text feedback;
	
	@FXML public void initialize() {
		accountTypeTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	addAccountBtn.setDisable(getAccountType().isEmpty()));
	}
    
    public final Customer getCustomer(){
    	return model;
    }
    
    public String getAccountType(){
    	return accountTypeTf.getText();
    }

    /*
     * Opens a new window when a user clicks on the buttons
     */
    
    @FXML public void handleAddAccount(ActionEvent event) throws Exception{
    	Account account = new Account(getAccountType());
    	getCustomer().addAccount(account);
    	
        stage.close();
    }

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
