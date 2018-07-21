package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Model.*;
import ap.javafx.*;

public class AccountDetailsController extends Controller<Account> {
	@FXML private Button close;
	
	@FXML public void initialize() {
		
	}
    
    public final Account getAccount(){
    	return model;
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
