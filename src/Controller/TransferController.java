package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import Model.*;
import ap.javafx.*;

public class TransferController extends Controller<Account> {
	@FXML private TextField nameTf;
	@FXML private TextField accountNumberTf;
	@FXML private TextField BSBTf;
	
	@FXML private TextField amountTf;
	@FXML private TextField fromNameTf;
	
	@FXML private Button transferBtn;
	@FXML private Text feedback;

	@FXML private Button close;
	
	private Bank bank;
	
	@FXML public void initialize() {
		
		nameTf.textProperty().addListener(
	            (observable, oldText, newText) -> 
	            	updateButton());
		
		accountNumberTf.textProperty().addListener(
	            (observable, oldText, newText) -> 
	            	updateButton());
		
		BSBTf.textProperty().addListener(
	            (observable, oldText, newText) -> 
	            	updateButton());
		
		fromNameTf.textProperty().addListener(
	            (observable, oldText, newText) -> 
	            	updateButton());
		
		amountTf.textProperty().addListener(
	            (observable, oldText, newText) -> 
	            	updateButton());
		
	}
		
	
	public void updateButton(){
		transferBtn.setDisable(!isFieldNotEmpty());
	}
	
	public boolean isFieldNotEmpty(){
	    return !nameTf.getText().isEmpty() && accountNumberTf.getText().matches("[0-9]+") && BSBTf.getText().matches("[0-9]+") && !fromNameTf.getText().isEmpty() && !amountTf.getText().isEmpty();
	}
	
	
    public final Account getAccount(){
    	return model;
    }
    
    private String getName(){
    	return nameTf.getText();
    }
    
    
    private int getAccountNumber(){
    	return Integer.parseInt(accountNumberTf.getText());
    }
    
    private int getBSB(){
    	return Integer.parseInt(BSBTf.getText()); 
    }
    
    
    
    private double getAmount(){
    	return Double.parseDouble(amountTf.getText());
    }
    
    private String getFromCustomer(){
    	return fromNameTf.getText();
    }

    @FXML public void handleTransfer(ActionEvent event) throws Exception{   
    	
    	Customer toCustomer = bank.customerAvailable(getName());
		Account accountFound;

    	// if customer found
    	if(toCustomer != null){
    		System.out.println("Customer found.");	
    		accountFound = toCustomer.accountAvailable(getAccountNumber(), getBSB());

    		// if account found
    		if(accountFound != null){
    			System.out.println("Account found!");
    			
    			// Check if the field is not empty AND is greater or equal to 5
    	    	if(getAmount() >= 5){
    		    	
    	    		Boolean hasEnoughFunds = getAccount().hasEnoughFunds(getAmount());
    	    		
    	    		//check if the customer has enough balance first to withdraw
    	    		if(hasEnoughFunds){
    	    			getAccount().transfer(getFromCustomer(), getName(), accountFound, getAmount());
    	    			String amount = getAccount().formatted(getAmount());	
    	    			amountTf.setText("0.0");
    	    	    	feedback.setText("Transfer successful.\n$" + amount + " has been transferred to " + getName() + "'s account.");
    	    		
    	    		} else
    	    			feedback.setText("You don't have enough funds!");
    		    	
    		    
    		    // If the amount entered is less than 5	
    	    	} else {
    		    	amountTf.setText("0.0");
    		    	feedback.setText("The minimum amount is $5");
    	    	}
    			
    		} else
    			System.out.println("Customer was found but not the correct accouunt number/BSB.");
    		
    	} else
    		System.out.println("Customer not found.");
    }

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
