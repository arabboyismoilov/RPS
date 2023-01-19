import model.Choice;
import service.ChoiceService;
import service.CombinationService;
import service.MessageConstants;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChoiceService choiceService = new ChoiceService();
        CombinationService combinationService = new CombinationService();
        Scanner scanner = new Scanner(System.in);

        System.out.println(MessageConstants.WELCOME_TEXT);

        int playerCommand = 1;
        while (playerCommand != 0){
            // showing main menu
            // 1. Play the game  2. Settings  3. Exit
            System.out.println(MessageConstants.MAIN_MENU);

            // Getting input from the user
            playerCommand = getInput();

            // Getting all choices from the list
            String choiceStr = "";
            for (Choice choice : choiceService.choices) {
                choiceStr += choice.getId() + " - " + choice.getName() + "\n";
            }

            // if player input 1, he oe she will go play menu
            if (playerCommand == 1){
                System.out.println(MessageConstants.PLAY_MENU);

                //showing available choices in console
                System.out.println(choiceStr);

                //variable to store player choice
                int playerChoiceId = 1;

                // player can play again and again until he enters 0.
                // If he enters 0, he will back to main menu
                while (playerChoiceId != 0){
                    // Player is choosing one of the choices
                    playerChoiceId = getInput();

                    // Checking user choice is correct number or not
                    Choice playerChoice = choiceService.getChoiceById(playerChoiceId);
                    if (playerChoice == null){
                        System.out.println(MessageConstants.WRONG_NUMBER_MESSAGE + choiceStr);
                        continue;
                    }

                    // Computer is choosing random choice
                    Choice computerChoice = choiceService.getComputerChoice();

                    // Showing selected answers in console
                    System.out.println(MessageConstants.USER_CHOICE + playerChoice.getName());
                    System.out.println(MessageConstants.COMPUTER_CHOICE + computerChoice.getName());

                    // checking the combinations and finding the answer
                    int result = combinationService.checkTheWinner(playerChoiceId, computerChoice.getId());
                    if (result == 0){
                        System.out.println(MessageConstants.TIE);
                    }
                    else if (result == playerChoiceId){
                        System.out.println(MessageConstants.USER_WIN);
                    }
                    else if (result == computerChoice.getId()){
                        System.out.println(MessageConstants.COMPUTER_WIN);
                    }
                    else {
                        System.out.println(MessageConstants.ERROR_MESSAGE);
                    }

                    // Displaying play menu again
                    System.out.println(MessageConstants.TRY_AGAIN + choiceStr);
                }
            }
            // if player enters 2 while he is in main menu, he will go there which is settings page
            else if (playerCommand == 2){
                // showing settings menu
                System.out.println(MessageConstants.SETTINGS_MENU);

                // variable to store player move in settings page
                int settingsStage = 1;

                // player will be in this stage until he enters 0
                while (settingsStage != 0){
                    settingsStage = getInput();

                    // if player enters 1 he will go add choice window
                    if (settingsStage == 1){
                        // showing instructions about how to add new choice to user
                        System.out.println(MessageConstants.NEW_CHOICE_ADD_MESSAGE);

                        // getting new choice name
                        String newChoiceName = scanner.nextLine();
                        int newChoiceId = choiceService.addNewChoice(newChoiceName);

                        // asking about entering new choice combinations with others
                        System.out.println(MessageConstants.NEW_CHOICE_COMBINATION_ASK);

                        // iterating all other choices to create combination with new choice
                        for (int i=0; i < choiceService.choices.size(); i++) {
                            Choice choice = choiceService.choices.get(i);
                            if (choice.getId() == newChoiceId){
                                continue;
                            }
                            // asking who beats the other choice
                            System.out.println(choice.getName() + MessageConstants.ASK_COMBINATION_TYPE);

                            // getting right combination
                            int isBeat = getInput();

                            //if the user writes string instead of number it will ask again
                            if (isBeat == 400){
                                i--;
                                continue;
                            }

                            // finding who beats the other choice
                            int winningChoiceId = isBeat == 1? choice.getId() : newChoiceId;

                            // adding combination to list
                            combinationService.addCombination(Arrays.asList(choice.getId(), newChoiceId), winningChoiceId);
                        }

                        System.out.println(MessageConstants.SUCCESS_MESSAGE);
                    }
                    // if user enters 2, he will go delete choice menu
                    else if (settingsStage == 2){
                        System.out.println(MessageConstants.DELETE_CHOICE_QUESTION + choiceStr);
                        int deleteChoiceId = getInput();

                        // deleting choice from choice list
                        choiceService.deleteChoice(deleteChoiceId);

                        // deleting its combinations
                        combinationService.deleteCombinations(deleteChoiceId);

                        System.out.println(MessageConstants.DELETE_SUCCESS_MESSAGE);
                    }

                    // showing settings menu again
                    System.out.println("\n" + MessageConstants.SETTINGS_MENU);
                }
            }
        }
    }

    private static int getInput(){
        // Getting the input from user. If it is not int type ot will catch the exception
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
        catch (Exception e){
            System.out.println("You should enter only number");
            return 400;
        }
    }
}
