package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.BLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ValidateController implements Initializable {
	
	@FXML
	private ToggleGroup cardMethod; //Toggle Group to change the study methods
	@FXML
	private RadioButton listen; //audio method
	@FXML
	private RadioButton see; //visual method
	@FXML
	private ToggleGroup wordList; //Toggle Group to change the vocabulary list
	@FXML
	private RadioButton list1; //vocab list 1 
	@FXML
	private RadioButton list2; //vocab list 2  
	  
	@FXML
	private Label userResp1;
	@FXML 
	private Label userResp2;
	@FXML
	private Label correctResp1;
	@FXML 
	private Label correctResp2;     
	@FXML 
	private Label successMsg;
	
	@FXML
	private Button nextWord;
	@FXML
	private Button checkA;
	@FXML
	private Button help;
	
	ArrayList<String> vocabList = new ArrayList<String>();
	String fileName = ""; //name of text file based on chosen vocab list
	int matchedIndex;
	String passMethod;
	String passList;
	BLTest obj = new BLTest();
	
	/***
	 * setMethod() sets the study method passed from 
	 * the previous screen
	 * @param methodName
	 */
	public void setMethod(String methodName) {
        if(methodName.equals("audioMethod")) {
        	listen.setSelected(true);
        } else if(methodName.equals("picMethod")) {
        	see.setSelected(true);
        }
    }
	
	/***
	 * setList() sets the list method passed from 
	 * the previous screen
	 * @param listName
	 */
	public void setList(String listName) {
        if(listName.equals("list1")) {
        	list1.setSelected(true);
        } else if(listName.equals("list2")) {
        	list2.setSelected(true);
        }
    }
	
	/***
	 * Setter method for Pinyin User Response 1
	 * Sets the user response using a passed value
	 * @param pinyinString
	 */
	public void setPinyin(String pinyinString) {
		userResp1.setText(pinyinString);
	}
		
	/***
	 * Setter method for Englsih User Response 2
	 * Sets the user response using a passed value
	 * @param englishString
	 */
	public void setEnglish(String englishString) {
		userResp2.setText(englishString);
	}
	
	/***
	 * Setter method to pass the index value of the 
	 * selected image/audio file to match with the 
	 * correct text
	 * @param indexVal
	 */
	public void setIndex(int indexVal) {
		matchedIndex = indexVal;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//on load functions & settings go in here		
		
	}
	
	
	/***
	 * compare() compares the user-submitted response
	 * to the correct response and displays the appropriate message
	 * @param event
	 * @throws IOException
	 */
	public void compare(ActionEvent event) throws IOException {
		//set file		
		if(wordList.getSelectedToggle() == list1){
			fileName="list1.txt"; //directory of photos for list one
		} else {
			fileName = "list2.txt"; //directory of photos for list two
		}
				
		//check that input file exists
		try {
			checkFileExists(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		obj.checkAnswers(correctResp1, correctResp2, userResp1, userResp2, successMsg, fileName, matchedIndex);

	}
	
	/***
	 * checkFileExists() checks that the intended list file exists
	 * @param String fileNameL: name of file passed from initialize 
	 * @throws IOException
	 */
	static public void checkFileExists(String fileNameL) throws IOException {
		try {
			FileInputStream input = new FileInputStream(fileNameL);
			System.out.println("The file you want to read exists.");
			input.close();
		}catch(FileNotFoundException fileNotFoundException){//file wasn't found in expected location
			fileNotFoundException.printStackTrace(); //prints details of where exception occurred in the program
		}
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
	
	/***
	 * next() will move the user onto the next word
	 * and pass the list and study method values
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void next(ActionEvent event) throws IOException {
		if(wordList.getSelectedToggle() == list1) {
			passList = "list1";
		}else {
			passList = "list2";
		}
		
		FXMLLoader loader = null;
		if(cardMethod.getSelectedToggle() == listen) {
			passMethod = "audioMethod";
			loader = new FXMLLoader(getClass().getResource("/application/view/Audio.fxml"));
			AnchorPane aPane = loader.load();
			AudioController ac = loader.getController();
			ac.passedMethod(passMethod);
			ac.passedList(passList);
			Stage stage = (Stage) nextWord.getScene().getWindow();
			Scene scene = new Scene(aPane,600,400);
			stage.setScene(scene);
		}else if(cardMethod.getSelectedToggle() == see) {
			passMethod = "picMethod";
			loader = new FXMLLoader(getClass().getResource("/application/view/visual.fxml"));
			AnchorPane aPane = loader.load();
			VisualController vc = loader.getController();
			vc.passedMethod(passMethod);
			vc.passedList(passList);
			Stage stage = (Stage) nextWord.getScene().getWindow();
			Scene scene = new Scene(aPane,600,400);
			stage.setScene(scene);
		}
		
	}

}
