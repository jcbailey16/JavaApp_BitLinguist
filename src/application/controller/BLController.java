package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BLController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//on load functions & settings go here
		
	}
	
	@FXML 
	private Button startBtn;
	@FXML
	private Button switchAudio;
	@FXML
	private Button switchPic;
	@FXML 
	private Button help;
	@FXML
	private Button closeHelp;
	@FXML
	Stage popupwindow=new Stage();
	
	
	/***
	 * startPgm() is the method that will switch screens from 
	 * the startup window to the Choose Your Study method window.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void startPgm(ActionEvent event) throws IOException {
		AnchorPane loader = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/CardType2.fxml"));
		Stage stage = (Stage) startBtn.getScene().getWindow();
		Scene scene = new Scene(loader,500,300);
		stage.setScene(scene);
	}
	
	/***
	 * goToAudio() is the method that will switch from the 
	 * study method selection screen to the Audio Study
	 * Method screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void goToAudio(ActionEvent event) throws IOException {
		AnchorPane loader = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/Audio.fxml"));
		Stage stage = (Stage) switchAudio.getScene().getWindow();
		Scene scene = new Scene(loader,600,400);
		stage.setScene(scene);
	}

	/***
	 * goToPicture() is the method that will switch from the 
	 * study method selection screen to the Visual Study
	 * Method screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void goToPicture(ActionEvent event) throws IOException {
		AnchorPane loader = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/visual.fxml"));
		Stage stage = (Stage) switchPic.getScene().getWindow();
		Scene scene = new Scene(loader,600,400);
		stage.setScene(scene);
	}
	
	/***
	 * closeHelp() is the method that closes the pop-up window.
	 * Method screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void closeHelp(ActionEvent event) throws IOException {
		Stage stage = (Stage) closeHelp.getScene().getWindow();
	    stage.close();
	}
	
	
}
