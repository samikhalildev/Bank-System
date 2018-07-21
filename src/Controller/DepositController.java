package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import Model.*;
import ap.javafx.*;

public class DepositController extends Controller<Account> {
	@FXML private TextField amountTf;
	@FXML private Button close;
	@FXML private Text feedback;
	
	@FXML public void initialize() {
		
	}
    
    public final Account getAccount(){
    	return model;
    }
    
    private double getAmount(){
    	return Double.parseDouble(amountTf.getText());
    }

    @FXML public void handleDeposit(ActionEvent event) throws Exception{    
    	
    	// Check if the field is not empty AND is greater or equal to 5
    	if(!amountTf.getText().isEmpty() && getAmount() >= 5){
	    	
	    	// Amount added
    		getAccount().deposit(getAmount());
	    	String amount = getAccount().formatted(getAmount());	
	    	
	    	amountTf.setText("0.0");
	    	feedback.setText("$" + amount + " has been added into your account");
	    
	    // If the amount entered is less than 5	
    	} else {
	    	amountTf.setText("0.0");
	    	feedback.setText("The minimum amount is $5");
    	}
    }

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
