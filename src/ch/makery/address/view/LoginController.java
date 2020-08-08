package ch.makery.address.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public class LoginController implements Initializable {
	
	@FXML
	private Label invalidLabel;
	
	@FXML
	private TextField usernameBox;
	
	@FXML
	private TextField passwordBox;

	/**
	 * Login Button handler
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void login(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("ExampleMoviePage.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		if (valid()) {
			stage.setScene(scene);
			stage.show();
		}
		else {
			usernameBox.clear();
			passwordBox.clear();
			invalidLabel.setText("Sorry, incorrect username or password. Try again.");
		}
	}
	
	/**
	 * Match user's account to database
	 */
	private boolean valid() {
		boolean logInMatched = false;
		
		// Search matched username and password
		System.out.println("SELECT * FROM logininfo WHERE username_ID= " + "'" + usernameBox.getText() + "'" + " AND password_ID= " + "'" + passwordBox.getText() + "'");
		
		// Create connection
		Connection c = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_info", "root", "");
			c.setAutoCommit(false);
			
			System.out.println("Opened database sucessfully");
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM logininfo WHERE username_ID= " + "'" + usernameBox.getText() + "'" + " AND password_ID= " + "'" + passwordBox.getText() + "'");
			
			while (rs.next()) {
				if (rs.getString("username_ID") != null && rs.getString("password_ID") != null) {
					String username = rs.getString("username_ID");
					System.out.println("username_ID= " + username);
					
					String password = rs.getString("password_ID");
					System.out.println("password_ID= " + password);
					
					logInMatched = true;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("valid operation done successfully");
		return logInMatched;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
