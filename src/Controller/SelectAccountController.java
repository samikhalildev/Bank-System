package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;
import Model.*;
import ap.javafx.*;

public class SelectAccountController extends Controller<Customer> {
	@FXML private TableView <Account> accountsTv;
	
	@FXML private Button selectAccountBtn;
	@FXML private Button removeAccountBtn;
	@FXML private Button addAccountBtn;
	
	@FXML private Button close;
	@FXML private Text feedback;
	
	@FXML private TableColumn <Account, String> accountTypeClm;
	@FXML private TableColumn <Account, String> balanceClm;
	
	@FXML public void initialize() {
		accountsTv.setItems(getCustomer().getAccounts());
		
		accountTypeClm.setCellValueFactory(cellData -> cellData.getValue().accountTypeProperty());
		balanceClm.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asString());
		
		accountsTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldAccount, newAccount) -> 
		        selectAccountBtn.setDisable(newAccount == null));
		
		accountsTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldAccount, newAccount) -> 
		        removeAccountBtn.setDisable(newAccount == null));
	}
    
    public final Customer getCustomer(){
    	return model;
    }

    public String getCustomerName(){
    	return getCustomer().getName();
    }
    
    public Account getSelectedAccount(){
    	return accountsTv.getSelectionModel().getSelectedItem();
    }
	
    /*
     * Opens a new window when a user clicks on the buttons
     */
    
    @FXML public void handleViewAccount(ActionEvent event) throws Exception{   
    	
    	if(getSelectedAccount() != null){
    		Stage stage2 = new Stage();
			
			stage2.getIcons().add(new Image("/View/stage-icon.png"));
    		ViewLoader.showStage(getSelectedAccount(), "/View/AccountMenu.fxml", getSelectedAccount().getAccountType() + " Account", stage2);
    	}
    }
    
    @FXML public void handleAddAccount(ActionEvent event) throws Exception{    
    	Stage stage2 = new Stage();
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
		
        ViewLoader.showStage(model, "/View/AddNewAccount.fxml", getCustomerName() + "'s Account", stage2);
    }

    @FXML public void handleRemoveAccount(ActionEvent event) throws Exception{    
    	
    	if(getSelectedAccount() != null)
    		getCustomer().removeAccount(getSelectedAccount());
    	
    }


    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
