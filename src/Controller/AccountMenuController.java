package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;
import Model.*;
import ap.javafx.*;

public class AccountMenuController extends Controller<Account> {
	@FXML private TextField usernameTf;
	@FXML private PasswordField passwordTf;
	@FXML private TextField accountTypeTf;
	@FXML private Button close;
	@FXML private Text feedback;
	
	@FXML public void initialize() {
		
	}
    
    public final Account getAccount(){
    	return model;
    }

    /*
     * Opens a new window when a user clicks on the buttons
     */
    
    @FXML public void OpenDepositWindow(ActionEvent event) throws Exception{ 
    	Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
        ViewLoader.showStage(model, "/View/Deposit.fxml", "Deposit", stage2);
    }
    
    @FXML public void OpenWithdrawWindow(ActionEvent event) throws Exception{  
    	Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
        ViewLoader.showStage(model, "/View/Withdraw.fxml", "Withdraw", stage2);
    }
    
    @FXML public void OpenTransferWindow(ActionEvent event) throws Exception{
    	Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
        ViewLoader.showStage(model, "/View/Transfer.fxml", "Transfer", stage2);    	
    }	

    @FXML public void OpenTransactionsHistoryWindow(ActionEvent event) throws Exception{    
    	getAccount().reverseTransactionsList();
    	Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
        ViewLoader.showStage(model, "/View/TransactionsHistory.fxml", "Transactions History", stage2);    	   	   	
    }	
    
    @FXML public void OpenAccountDetailsWindow(ActionEvent event) throws Exception{ 
    	Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
        ViewLoader.showStage(model, "/View/AccountDetails.fxml","Account Details", stage2);    	   	
    }	
    

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
