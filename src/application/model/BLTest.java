package application.model;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.Label;
    
public class BLTest {
	ArrayList<String> vocabList = new ArrayList<String>();
	
	/***
	* checkAnswers() compares the user's answers to the correct answers
	* @param correctResp1 Label correctResp1
	* @param correctResp2 Label correctResp2
	* @param userResp1 Label userResp1
	* @param userResp2 Label userResp2
	* @param successMsg Label successMsg
	* @param fileName String fileName
	* @param matchedIndex int matchedIndex
	**/
	public void checkAnswers(Label correctResp1, Label correctResp2, Label userResp1, Label userResp2, Label successMsg, String fileName, int matchedIndex) {
				
		try {
			//read data into an array
			FileInputStream input = new FileInputStream(fileName);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
			String line = buffer.readLine();
			while(line != null) {
				vocabList.add(line); 
				line = buffer.readLine();
			}
			buffer.close();	
			//validate input
			String tmp[] = vocabList.get(matchedIndex).split(",");
			//set labels
			correctResp1.setText(tmp[0].trim());
			correctResp2.setText(tmp[1].trim());
			//set text to lower and & trim
			String ur1=userResp1.getText().toLowerCase().trim();
			String ur2=userResp2.getText().toLowerCase().trim();
			String cr1=correctResp1.getText().toLowerCase();
			String cr2=correctResp2.getText().toLowerCase();
			//compare & set response
			if(ur1.equals(cr1)&& ur2.equals(cr2)) {
				successMsg.setText("Correct!");
			}else {
				successMsg.setText("Try Again!");
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
