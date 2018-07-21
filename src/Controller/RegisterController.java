package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import Model.*;
import ap.javafx.*;

public class RegisterController extends Controller<Bank> {
	@FXML private TextField nameTf;
	@FXML private TextField ageTf;
	@FXML private TextField genderTf;
	@FXML private TextField locationTf;
	
	@FXML private TextField usernameTf;
	@FXML private PasswordField passwordTf;
		
	@FXML private Button registerBtn;
	@FXML private Button close;
	@FXML private Text feedback;
	
	
	@FXML public void initialize() {
			
		//Enable the button only when all fields are not empty
		nameTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		ageTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		genderTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		locationTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		usernameTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		passwordTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());

	}
	
	public void updateButton(){
		registerBtn.setDisable(!isFieldNotEmpty());
    }
    
    public boolean isFieldNotEmpty(){
        return !nameTf.getText().isEmpty() && ageTf.getText().matches("[0-9]+") && !genderTf.getText().isEmpty() && !locationTf.getText().isEmpty() && !usernameTf.getText().isEmpty() && !passwordTf.getText().isEmpty();
    }
	
    public final Bank getBank() {
        return model;
    }

	public String getName(){
		return  getBank().nameCheck(nameTf.getText());
	}
	
	public int getAge(){
		return Integer.parseInt(ageTf.getText()); 
	}
	
	public String getGender(){
		return genderTf.getText().toUpperCase();
	}
	
	public String getLocation(){
		return  getBank().nameCheck(locationTf.getText());
	}
	
	public String getUsername(){
		return usernameTf.getText();
	}
	
	public String getPassword(){
		return passwordTf.getText();
	}
	
	
    @FXML public void handleRegister(ActionEvent event) throws Exception{    
    	
    	/*
    	 * Checking for errors/empty fields
    	 */
    	boolean usernameTaken = getBank().isUsernameTaken(getUsername());

    	if(getName().length() < 3 || getName().length() > 10)
			feedback.setText("Name must be between 3 to 10 characters");
			
		else if (getAge() < 16)
			feedback.setText("Age must be over 18");
			
		else if(getAge() > 99)
			feedback.setText("You're too old!");
		
		else if (!getGender().equals("M") && !getGender().equals("F"))
			feedback.setText("Gender must be set to 'M' or 'F'");
    	
		else if (usernameTaken)
    		feedback.setText("Username is already taken");
    	
    	else if (getPassword().length() < 4)
    		feedback.setText("Password must be 4 or more characters");
    	
    	else {
			
			/*
			 * Checks if customer details exists 
			 */
			
			boolean isNotACustomer = getBank().isNotACustomer(getName(), getAge(), getGender(), getLocation());

			//if customer details doesn't exist
	    	if(isNotACustomer){
	    		
	    		/*
	    		 * Adds the customer
	    		 */	    	
	    		
	    		Customer customer = new Customer(getName(), getAge(), getGender(), getLocation(), getUsername(), getPassword());
	    		
	    		getBank().addCustomer(customer);
	    		feedback.setText("");
	        	ViewLoader.showStage(model, "/View/Login.fxml", "Login", stage);
	    	
	    	} else
	    		feedback.setText("This customer is already available.");
		}
    }
    
    @FXML public void handleLogin(ActionEvent event) throws Exception{
    	ViewLoader.showStage(model, "/View/Login.fxml", "Login", stage);
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
