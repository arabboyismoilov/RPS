import model.Choice;
import service.ChoiceService;
import service.CombinationService;
import service.MessageConstants;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChoiceService choiceService = new ChoiceService();
        CombinationService combinationService = new CombinationService();

        System.out.println(MessageConstants.WELCOME_TEXT);

        int playerCommand = 1;
        while (playerCommand != 0){
            System.out.println(MessageConstants.MAIN_MENU);

            // Getting input from the user
            playerCommand = getInput();

            if (playerCommand == 1){
                System.out.println(MessageConstants.PLAY_MENU);

                // Getting all choices from the list
                String choiceStr = "";
                for (Choice choice : choiceService.choices) {
                    choiceStr += choice.getId() + " - " + choice.getName() + "\n";
                }

                //showing available choices in console
                System.out.println(choiceStr);

                int playerChoiceId = 1;
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

                    //checking the combinations and finding the answer
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
            else if (playerCommand == 2){
                System.out.println(MessageConstants.SETTINGS_MENU);
            }
        }
    }

    private static int getInput(){
        //Getting the input from user. If it is not int type ot will catch the exception
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
