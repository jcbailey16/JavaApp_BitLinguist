# BitLinguist

Program Developers: Jennifer Bailey, Kara Gustavus, Itzel Romero Sanchez

BitLinguist is a desktop application built with JavaFX in Eclipse that allows students to practice their vocabularly for a
foreign language class. For our first iteration, we choose to use Chinese as our test language. The application lets students 
study in two ways: through visual and audio "flashcards". If students choose the visual flashcard, a random picture of a Chinese 
word is displayed from a student-selected list (there are two uploaded in this version). The student will then provide the pinyin 
and the English translation of the word. Upon submitting, the user's responses will be evaluated. A message is then displayed, 
telling the student "congrats" or to "try again". The user's answers and correct answers are also displayed on the validation page. 
The audio flashcards are similar but the user clicks a button to generate a random audio clip to play. 

The lists, images, and audio files are included in the project.

Due to limitation of the MediaView controller, the audio file can only be played once. If the button is clicked again, a 
different word will be selected and played. As the files are randomly selected, the program does not keep count of which ones 
have been viewed or how many were correct/incorrect. This allows the user to practice their vocabularly in a stress-free environment.

Users are able to switch between the two methods and two lists on the Visual, Audio, and Validation Screen views. On the 
Audio and Visual views, doing so will switch to the indicated view and list. On the Validation Screen, the user will be taken 
to the indicated study method with the page set to the indicated list upon clicking selected. 

Validation: The user is not able to deselected both options for the study method, so that is always set to one of the two methods. 
On load of the Visual & Audio views, the list method is not selected. Messages are displayed to the user and an alert is displayed 
if the user tries to submit a response without a selected list. The user's responses are validated in the Validation Screen Controller 
for the Validation Screen view. Blank submissions are allowed.
