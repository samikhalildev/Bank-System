package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Model.*;
import ap.javafx.*;

public class TransactionHistoryController extends Controller<Account> {
	@FXML private TableView <Transaction> transactionsTv;


	@FXML private Button close;
	
	@FXML public void initialize() {
		transactionsTv.setItems(getAccount().getTransactions());
	}
    
    public final Account getAccount(){
    	return model;
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
