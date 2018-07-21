package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;
import Model.*;
import ap.javafx.*;

public class AllCustomersController extends Controller<Bank> {
	@FXML private TableView <Customer> customersTv;
	
	@FXML private Button viewAccountsBtn;
	@FXML private Button removeCustomerBtn;

	@FXML private Text feedback;
	@FXML private Button close;
	
	@FXML public void initialize() {
		customersTv.setItems(getBank().getCustomers());
		
		customersTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldCustomer, newCustomer) -> 
		        removeCustomerBtn.setDisable(newCustomer == null));
		
		customersTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldCustomer, newCustomer) -> 
		        viewAccountsBtn.setDisable(newCustomer == null));
		
		customersTv.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldCustomer, newCustomer) -> 
		        viewAccountsBtn.setText("View " + getSelectedCustomer().getName() + "'s Accounts"));
	}
    
    public final Bank getBank(){
    	return model;
    }
    
    private Customer getSelectedCustomer(){
    	return customersTv.getSelectionModel().getSelectedItem();
    }
    
    @FXML public void handleRemoveCustomer(ActionEvent event) throws Exception{
    	Customer customer = getSelectedCustomer();
    	
    	if(customer != null){
    		getBank().removeCustomer(customer);
    		feedback.setText("Customer removed!");
    	}
    }
    
    @FXML public void handleViewAccounts(ActionEvent event) throws Exception{
    	Customer customer = getSelectedCustomer();
    	
    	if(customer != null){
        	Stage stage2 = new Stage();
    		
    		stage2.getIcons().add(new Image("/View/stage-icon.png"));
    		ViewLoader.showStage(customer, "/View/AllAccounts.fxml", "All Accounts", stage2);  
    	}
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
