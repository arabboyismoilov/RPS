package service;

import model.Combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CombinationService {
    List<Combination> combinations = new ArrayList<>(Arrays.asList(
            new Combination(Arrays.asList(1, 2), 2),
            new Combination(Arrays.asList(1, 3), 1),
            new Combination(Arrays.asList(2, 3), 3)
    ));

    public int checkTheWinner(int choice1, int choice2){
        if (choice1 == choice2){
            return 0;
        }

        Combination com = combinations.stream()
                .filter(combination -> {
                    List<Integer> choices = combination.getChoices();
                    return choices.contains(choice1) && choices.contains(choice2);
                })
                .findFirst()
                .orElse(null);

        return com == null? -1 : com.getWiningChoice();
    }

    public void addCombination(List<Integer> choiceIds, int winningChoiceId){
        combinations.add(new Combination(choiceIds, winningChoiceId));
    }

    public void deleteCombinations(int choiceId){
        combinations = combinations.stream()
                .filter(combination -> !combination.getChoices().contains(choiceId))
                .collect(Collectors.toList());
    }
}
