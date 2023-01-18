package model;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
    This class stores two choices in list and winning choice Id.
    For example, choices may be rock and scissors and winningChoice will be rock as rock beats scissors.
    The main aim to create this class is to make dynamic application and new choices can be added
    by console without writing any if else code.
*/
@Getter
public class Combination {
    private List<Integer> choices;
    private Integer winingChoice;

    public Combination(List<Integer> choiceIds, Integer winningChoiceId){
        setCombination(choiceIds, winningChoiceId);
    }

    public void setCombination(List<Integer> choiceIds, Integer winningChoiceId){
        if (choiceIds.size()!=2){
            throw new RuntimeException("There should be only 2 choices in  list");
        }

        if (!choiceIds.contains(winningChoiceId)){
            throw new RuntimeException("One of the choices in list should be winning choice");
        }

        if (Objects.equals(choiceIds.get(0), choiceIds.get(1))){
            throw new RuntimeException("The choices shouldn't be same");
        }

        this.choices = choiceIds;
        this.winingChoice = winningChoiceId;
    }
}
