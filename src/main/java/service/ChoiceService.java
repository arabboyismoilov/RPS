package service;

import model.Choice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoiceService {
    public List<Choice> choices = new ArrayList<>(Arrays.asList(
                new Choice(1, "rock"),
                new Choice(2, "paper"),
                new Choice(3, "scissors")
            )
    );

    public Choice getChoiceById(int choiceId){
        return choices.stream().filter(choice -> choice.getId()==choiceId).findFirst().orElse(null);
    }

    public Choice getComputerChoice(){
        // TODO add external API to get the random choice
        return getRandomChoice();
    }

    public Choice getRandomChoice(){
        int min = 0; // Minimum value of range
        int max = choices.size()-1; // Maximum value of range
        int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return choices.get(randomInt);
    }
}
