package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import Model.*;
import ap.javafx.*;
import javafx.stage.Stage;

public class LoginController extends Controller<Bank> {
	@FXML private TextField usernameTf;
	@FXML private PasswordField passwordTf;
	@FXML private Button loginBtn;
	@FXML private Button adminBtn;
	@FXML private Button close;
	@FXML private Text feedback;
	
		
	@FXML public void initialize() {
		
		//Enable the button only when there is text in both fields
		usernameTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		passwordTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	updateButton());
		
		
		//admin button
		usernameTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                	adminBtn.setDisable(!getUsername().equals("admin")));
                	
	}

    public void updateButton(){
    	loginBtn.setDisable(!isFieldNotEmpty());
    }
    
    public boolean isFieldNotEmpty(){
        return !usernameTf.getText().isEmpty() && !passwordTf.getText().isEmpty();
    }
    
	
    public final Bank getBank() {
        return model;
    }
    
    
	public String getUsername(){
		return usernameTf.getText();
	}
	
	public String getPassword(){
		return passwordTf.getText();
	}
	
	
    @FXML public void handleLogin(ActionEvent event) throws Exception{    
    	
		// look up username and password
    	Customer customer = getBank().lookUpCustomer(getUsername(), getPassword());
    	
    	// if customer was found
    	if(customer != null){
			feedback.setText("Correct username and password!");
			
			Stage stage2 = new Stage();
			
			stage2.getIcons().add(new Image("/View/stage-icon.png"));
			ViewLoader.showStage(customer, "/View/SelectAccount.fxml", customer.getName() + "'s Accounts", stage2);

    	} else // customer not found
    		feedback.setText("Incorrect username or password. Try again");
    }
    
	 // opens a sign-up window
    @FXML public void handleSignUp(ActionEvent event) throws Exception{   
         ViewLoader.showStage(model, "/View/Register.fxml", "Register", stage);
    }
    
    // opens admin window
    @FXML public void OpenAdminWindow(ActionEvent event) throws Exception{
    	
		feedback.setText("Admin access!");
		
		Stage stage2 = new Stage();
		
		stage2.getIcons().add(new Image("/View/stage-icon.png"));
		ViewLoader.showStage(model, "/View/AllCustomers.fxml", "Admin access", stage2); 

    }	
    

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
