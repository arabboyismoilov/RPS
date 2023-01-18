package service;

public interface MessageConstants {
    String WELCOME_TEXT = "Welcome to the game Rock-Paper-Scissors";
    String MAIN_MENU = "Menu (please enter only numbers):\n1 - Play the game\n2 - Settings\n0 - Exit\n";
    String PLAY_MENU = "Please choose one of the following choices: (0 - to exit the game)";
    String SETTINGS_MENU = "Settings page, menu:\n1 - Add new choice\n2 - Delete choice\n0 - Exit\n";
    String WRONG_NUMBER_MESSAGE = "You wrote wrong number! Try again:\n";
    String USER_CHOICE = "Your choice: ";
    String COMPUTER_CHOICE = "Computer's choice: ";
    String TIE = "=== Your are tie ===";
    String USER_WIN = "=== You win ===";
    String COMPUTER_WIN = "=== Computer wins ===";
    String ERROR_MESSAGE = "Something went wrong!";
    String TRY_AGAIN = "\nTry again:(0 - to exit)\n";
    String NEW_CHOICE_ADD_MESSAGE = "Please enter new choice name (ex: gun, hammer)";
    String NEW_CHOICE_COMBINATION_ASK = "You created new choice, now you should define its combinations with other choices";
    String ASK_COMBINATION_TYPE = " choice beats it or not? \n1 - Beats \n2 - Not";
    String SUCCESS_MESSAGE = "All done, now you can play the game";
    String DELETE_CHOICE_QUESTION = "Enter the choice number you want to delete:\n";
    String DELETE_SUCCESS_MESSAGE = "The choice you selected removed";
}
