package ch.makery.address.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	
	@FXML
	private TextField emailBox;
	
	@FXML
	private TextField usernameBox;
	
	@FXML
	private TextField passwordBox;
	
	@FXML
	private TextField confirmBox;
	
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
	 * Create Button handler
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void create(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("ExampleMoviePage.fxml"));
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		if (valid()) {
			stage.setScene(scene);
			stage.show();
		}
		else {
			emailBox.clear();
			usernameBox.clear();
			passwordBox.clear();
			confirmBox.clear();
		}
	}
	
	/**
	 * Match user's account to database
	 */
	private boolean valid() {
		boolean insertMatched = false;
		
		// Insert email, username and password
		System.out.println("INSERT INTO logininfo (num_ID, email_ID, username_ID, password_ID) VALUES (NULL, '" + emailBox.getText() + "', '" + usernameBox.getText() + "', '" + passwordBox.getText() + "');");
		
		String query = "INSERT INTO logininfo (num_ID, email_ID, username_ID, password_ID) VALUES (NULL, '" + emailBox.getText() + "', '" + usernameBox.getText() + "', '" + passwordBox.getText() + "');";
		
		// Create connection
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_info", "root", "");
			c.setAutoCommit(false);
			
			System.out.println("Opened database sucessfully");
			
			pstmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			c.commit();
			rs = pstmt.getGeneratedKeys();
			insertMatched = true;
			pstmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("valid operation done successfully");
		return insertMatched;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
