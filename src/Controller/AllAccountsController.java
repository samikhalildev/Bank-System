package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;
import Model.*;
import ap.javafx.*;

public class AllAccountsController extends Controller<Customer> {
	@FXML private TableView <Account> accountsTv;
	
	@FXML private TableColumn <Account, String> accountTypeClm;
	@FXML private TableColumn <Account, String> balanceClm;
	@FXML private TableColumn <Account, String> totalDepositsClm;
	@FXML private TableColumn <Account, String> totalWithdrawnClm;
	@FXML private TableColumn <Account, String> totolTransfersClm;
	
	@FXML private Button viewTransactionsBtn;
	@FXML private Button removeAccountBtn;

	@FXML private Text feedback;
	@FXML private Button close;
	
	@FXML public void initialize() {
		accountsTv.setItems(getCustomer().getAccounts());
		
		accountTypeClm.setCellValueFactory(cellData -> cellData.getValue().accountTypeProperty());
		balanceClm.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asString());
		totalDepositsClm.setCellValueFactory(cellData -> cellData.getValue().totalDepositsProperty().asString());
		totalWithdrawnClm.setCellValueFactory(cellData -> cellData.getValue().totalWithdrawnProperty().asString());
		totolTransfersClm.setCellValueFactory(cellData -> cellData.getValue().totalTransferProperty().asString());
		
		accountsTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldAccount, newAccount) -> 
		        viewTransactionsBtn.setDisable(newAccount == null));

		accountsTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldAccount, newAccount) -> 
		        removeAccountBtn.setDisable(newAccount == null));
	}
    
    public final Customer getCustomer(){
    	return model;
    }
    
    private Account getSelectedAccount(){
    	return accountsTv.getSelectionModel().getSelectedItem();
    }
    
    @FXML public void handleRemoveAccount(ActionEvent event) throws Exception{
    	Account account = getSelectedAccount();
    	
    	if(account != null){
    		getCustomer().removeAccount(account);
    		feedback.setText("Account removed!");
    	}
    }
    
    @FXML public void handleViewTransactions(ActionEvent event) throws Exception{
    	Account account = getSelectedAccount();
    	
    	if(account != null){
        	Stage stage2 = new Stage();
    		
    		stage2.getIcons().add(new Image("/View/stage-icon.png"));
    		ViewLoader.showStage(account, "/View/TransactionsHistory.fxml", getCustomer().getName() + "'s Transactions", stage2); 
    	}
    }

    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
