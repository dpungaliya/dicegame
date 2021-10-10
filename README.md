# Dice Games

A simple game app for a mobile app development course. Stuedents will be able to demonstrate their understanding of:

* Activity lifecycle
* Intents
* Persisting the UI state
* Developing UI for landscape and portrait mode


A.
-Project name- DiceGames 
-Name-Dhaval Pungaliya 
-BITS ID-2018B2A70662G 
-Email-f20180662@goa.bits-pilani.ac.in 

B.
As the name says,it's an app(game) that uses dice,for gambling. There are 2 games in this app. On opening the app,we see a roll-die game.The user gets five coins every time he rolls a six. User carries forward these total coins to the next state to play the next gambling game.Here the user can bet a certian amount of coins such that,if 4 die are rolled then either 2 or 3 or 4 will be same. Each of them has a reward and probability of winning/losing.
Known Bugs:
If a very large number is provided as input for the wager, the app is not able to parse it as an integer and gives an error - "Wager not set" instead of "Set a valid Wager".
Though i had changed the String name in the xml file and given it the value "Two or More" as was expected in the question,it was showing the previous name(Launch BlackJack). I figured out that it had to changed in the String.xml(night) file.


C.
Task1-WalletActivity:
I first designed the ViewModel class file based on the test cases.Then created the UI element fields.Set the increment value, win value and the die right after linking the WalletViewModel to the WalletActivity.I even set an onClickListener for the die. It checks for exceptions as well as displays Toast messages. It also takes us to the next game,if button is pressed.
Used the ActivityResultLauncher to launch the TwoOrMoreActivity as we want the balance as a result after the control comes back to WalletActivity.

Task2-TwoOrMoreViewModel
Wrote the constructor and all the getters and setters in the TwoOrMoreViewModel class.This class takes care of all the logic behind the TwoOrMore game.
Check the isvalidWager function:
wager > 0 
for TwoAlike : balance >= 2 * wager. 
for ThreeAlike: balance >= 3 * wager. 
for FourAlike: balance >= 4 * wager. 
Wrote the exception handling in the Play function.I kept checking for the test cases as and when I implemented the methods to check if what I implemented was correct.

Task3-Connect ViewModel with Activity for the TwoOrMoreGame
Set the game type and add the die right after linking the TwoOrMoreViewModel to the TwoOrMoreActivity.Extract the balance passed by the WalletActivity and update the UI.Setthe Game type and play the game.Used the ViewModel's play() function to get the game result.Fetch the die values and update the UI.Display any error message that may have occured during the process.

Task4-Back button press :
Launch the WalletActivity by passing the updated balance.Extract the returned balance in WalletActivity and update the UI. Override the onBackPressed for adding the same functionality to the phone's back button(Uses an explicit intent to do the same).

Task 5: Implementing Info Button-
It takes us to the document having the rules of the game-https://docs.google.com/document/d/1qasddO8VpxAPOcilwboPxdMJ6IFKCigXPHg8n3eS-8g/edit?usp=sharing

Task6-
Used LinearLayout to create the landscape layouts for WalletActivity and TwoOrMoreActivity.I created activity layouts for both the activities. I added appropriate ids for the same.I rearranged and resized a few elements to fit to the screen properly.I also ensured that the activities persist the UI state over rotation(possible due to intents.)

Task7-Accessibility-
I ran my app with my eyes closed and tested the TalkBack feature. It was easy to navigate around,but relatively tougher than prevoius assignment.
I also ran Accessibility Scanner on my app. 

D.
I used a Test-driven approach for building my app.
Testing-
Viewed the test cases and wrote the code for passing the cases 2-3 at a time.I went on to fix the test cases one by one until all my test cases passed. Checked all the test cases at the end.I did the UI testing as well.I even used the Monkey tool


E.40 hours,or maybe more.

F.9/10-Difficulty