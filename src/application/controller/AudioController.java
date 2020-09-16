package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AudioController implements Initializable {
	
	@FXML
	private ToggleGroup studyMethod; //Toggle Group to change the study methods
	@FXML
	private RadioButton audioMthd; //audio method
	@FXML
	private RadioButton picMthd; //visual method
	@FXML
	private ToggleGroup vocabPick; //Toggle Group to change the vocabulary list
	@FXML
	private RadioButton list1; //vocab list 1 
	@FXML
	private RadioButton list2; //vocab list 2
	
	//@FXML
	//private MediaPlayer playWord;
	@FXML
	private TextField pinyin;
	@FXML
	private TextField english;
	@FXML
	private Button wordSubmit;
	@FXML
	private Button help;
	
	@FXML
	Media media;
	@FXML
	MediaPlayer mediaPlayer;
	@FXML
	MediaView playWord;
	
	int audioNum = 0; //default value for audio name
	String setName="";
	String passList="";
	String passMethod="";
	
	
	/***
	 * passedMethod() sets the study method radio button set
	 * @param listName
	 */
	public void passedMethod(String passMethod) {
        if(passMethod.equals("audioMethod")) {
        	audioMthd.setSelected(true);
        } else if(passMethod.equals("picMethod")) {
        	picMthd.setSelected(true);
        }
    }
	
	/***
	 * passedList() sets the list radio button set
	 * @param listName
	 */
	public void passedList(String listName) {
        if(listName.equals("list1")) {
        	list1.setSelected(true);
        } else if(listName.equals("list2")) {
        	list2.setSelected(true);
        }
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//on load functions & settings go here
		audioMthd.setSelected(true);
	}
	
	/***
	 * setAudio() randomly chooses an audio file to use
	 * using the randomName() method
	 */
	public void setAudio() {
		if(vocabPick.getSelectedToggle() == list1){
			setName="list1a"; //directory of photos for list one
		} else if(vocabPick.getSelectedToggle()==list2){
			setName = "list2a"; //directory of photos for list two
		} else {
			new Alert(Alert.AlertType.ERROR, "You must select a list").showAndWait();
			return;
		}
		
		//load random audio file
		audioNum= randomName(); 		
		media = new Media(new File(setName+"/"+audioNum+".m4a").toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		playWord= new MediaView(mediaPlayer);
	
	}
	
	/***
	 * randomName() generates a random number between 0 & 4
	 * to display a random image on-page load
	 * @return int result: randomly generated number
	 */
	public int randomName(){
		Random rdm=new Random();
		int low = 0;
		int high = 4;
		int result = rdm.nextInt((high-low)+1) + low;
		return result;
	}
	
	/***
	 * goToAudio() is the method that will switch from the 
	 * Audio study method selection screen to the Visual Study
	 * Method screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void goToPicture(ActionEvent event) throws IOException {
		if(studyMethod.getSelectedToggle() == picMthd) {
			AnchorPane loader = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/visual.fxml"));
			Stage stage = (Stage) picMthd.getScene().getWindow();
			Scene scene = new Scene(loader,600,400);
			stage.setScene(scene);	
		}
		
	}
	
	/***
	 * switchList() will refresh the current screen but reset
	 * which list is used. 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void switchList(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Audio.fxml"));
		AnchorPane aPane = loader.load();
		AudioController ac = loader.getController();
		RadioButton selected = (RadioButton) event.getSource();
		String listSelection = selected.getId();
		ac.passedList(listSelection);
		Stage stage = (Stage) selected.getScene().getWindow();
		Scene scene = new Scene(aPane,600,400);
		stage.setScene(scene);
	}
	
	/***
	 * goToValidationScreen() is the method that switches from the visual screen to the validation screen 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void goToValidateScreen(ActionEvent event) throws IOException{	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/validationScreen.fxml"));
		AnchorPane anchorPane = (AnchorPane)loader.load();
		//Getting controller from FXMLLoader
		ValidateController controller = loader.getController();
		//Setting data in the controller
		controller.setPinyin(pinyin.getText());
		controller.setEnglish(english.getText());
		
		if(vocabPick.getSelectedToggle() == list1){
			passList="list1";
			controller.setList(passList);
		} else if(vocabPick.getSelectedToggle()==list2){
			passList="list2";
			controller.setList(passList);
		} else {
			new Alert(Alert.AlertType.ERROR, "You must select a list").showAndWait();
			return;
		}
		if(studyMethod.getSelectedToggle() == audioMthd) {
			passMethod = "audioMethod";
		}else if(studyMethod.getSelectedToggle() == picMthd) {
			passMethod = "picMethod";
		}
		controller.setMethod(passMethod);
		controller.setIndex(audioNum);
		// Setting the stage and scene
		Stage stage = (Stage)wordSubmit.getScene().getWindow();
		Scene scene = new Scene(anchorPane, 600,400);
		stage.setScene(scene);
	   }
	
	/***
	 * openHelp() is the method that opens the pop-up help screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openHelp(ActionEvent event) throws IOException {
		AnchorPane loader2 = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/help.fxml"));
		Stage popupwindow=new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("BitLinguist");
		Scene scene2 = new Scene(loader2,500,350);
		scene2.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		popupwindow.setScene(scene2);
		popupwindow.showAndWait();
	}

}
