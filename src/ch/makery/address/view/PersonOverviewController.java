package ch.makery.address.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import ch.makery.address.MainApp;

public class PersonOverviewController implements Initializable {
	
	/**
	 * MoviesPlaying Button handler
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void moviesplaying(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("MoviesPlayingBottomMenu.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Login Button handler
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void login(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * SignUp Button handler
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void signup(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
	
}

