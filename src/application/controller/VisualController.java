package application.controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VisualController implements Initializable {
	
	@FXML
	private ToggleGroup chooseStudy; //Toggle Group to change the study methods
	@FXML
	private RadioButton audioMethod; //audio method
	@FXML
	private RadioButton picMethod; //visual method
	@FXML
	private ToggleGroup chooseVocab; //Toggle Group to change the vocabulary list
	@FXML
	private RadioButton list1; //vocab list 1 
	@FXML
	private RadioButton list2; //vocab list 2
	
	@FXML
	private ImageView studyWord;
	@FXML
	private TextField pinyin;
	@FXML
	private TextField english;
	@FXML
	private Button wordSubmit;
	@FXML
	private Button help;
	@FXML
	private Button drawPic;
	
	int imgNum = 0; //default value for image name
	Image imgCard; //image to load into ImageView
	String listName="";
	String passList="";
	String passMethod="";
	
	
	/***
	 * passedMethod() sets the study method radio button set
	 * @param listName
	 */
	public void passedMethod(String passMethod) {
        if(passMethod.equals("audioMethod")) {
        	audioMethod.setSelected(true);
        } else if(passMethod.equals("picMethod")) {
        	picMethod.setSelected(true);
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
		picMethod.setSelected(true);
		imgCard = new Image("default.png",true); // creating the image
		studyWord.setImage(imgCard);
		
	}
	
	/***
	 * drawImage() randomly chooses a picture to display
	 * using the randomName() method
	 */
	public void drawImage() {
		if(chooseVocab.getSelectedToggle() == list1){
			listName="list1"; //directory of photos for list one
		} else if(chooseVocab.getSelectedToggle()==list2){
			listName = "list2"; //directory of photos for list two
		}else {
			new Alert(Alert.AlertType.ERROR, "You must select a list").showAndWait();
			return;
		}
		 
		//load random image
		imgNum= randomName(); 
		String imgName = String.valueOf(imgNum);
		imgCard = new Image("/application/controller/"+listName+"/"+imgName+".jpg",true); // creating the image
		studyWord.setImage(imgCard);
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
	 * study method selection screen to the Audio Study
	 * Method screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void goToAudio(ActionEvent event) throws IOException {
		if(chooseStudy.getSelectedToggle() == audioMethod) {
			AnchorPane loader = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/view/Audio.fxml"));
			Stage stage = (Stage) audioMethod.getScene().getWindow();
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/visual.fxml"));
		AnchorPane aPane = loader.load();
		VisualController vc = loader.getController();
		RadioButton selected = (RadioButton) event.getSource();
		String listSelection = selected.getId();
		vc.passedList(listSelection);
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
	void goToValidationScreen(ActionEvent event) throws IOException{	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/validationScreen.fxml"));
		AnchorPane anchorPane = (AnchorPane)loader.load();
		//Getting controller from FXMLLoader
		ValidateController controller = loader.getController();
		//Setting data in the controller
		controller.setPinyin(pinyin.getText());
		controller.setEnglish(english.getText());
		
		if(chooseVocab.getSelectedToggle() == list1){
			passList="list1";
			controller.setList(passList);
		} else if(chooseVocab.getSelectedToggle()==list2){
			passList="list2";
			controller.setList(passList);
		} else {
			new Alert(Alert.AlertType.ERROR, "You must select a list").showAndWait();
			return;
		}
		if(chooseStudy.getSelectedToggle() == audioMethod) {
			passMethod = "audioMethod";
		}else if(chooseStudy.getSelectedToggle() == picMethod) {
			passMethod = "picMethod";
		}
		controller.setMethod(passMethod);
		controller.setIndex(imgNum);
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
