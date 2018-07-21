import javafx.stage.*;
import ap.javafx.*;
import javafx.application.Application;
import javafx.scene.image.Image;

import Model.*;

public class BankApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
		
    	Bank bank = new Bank();	
		bank.addingCustomerToList();
		
		stage.getIcons().add(new Image("/View/stage-icon.png"));
		ViewLoader.showStage(bank, "/View/Login.fxml", "Login", stage);
    }
}
