package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Choice;
import model.Response;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceService {
    private int lastId = 3;
    public List<Choice> choices = new ArrayList<>(Arrays.asList(
                new Choice(1, "rock"),
                new Choice(2, "paper"),
                new Choice(3, "scissors")
            )
    );

    public int addNewChoice(String name){
        choices.add(new Choice(++lastId, name));
        return lastId;
    }

    public Choice getChoiceById(int choiceId){
        return choices.stream()
                .filter(choice -> choice.getId()==choiceId).findFirst().orElse(null);
    }

    public Choice getChoiceByName(String name){
        return choices.stream()
                .filter(choice -> choice.getName().equals(name)).findFirst().orElse(null);
    }

    public Choice getComputerChoice(){
        try {
            URL url = new URL("https://private-anon-b21807813f-curbrockpaperscissors.apiary-proxy.com/rps-stage/throw");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            InputStream responseStream = con.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Response response = mapper.readValue(responseStream, Response.class);

            Choice apiChoice = getChoiceByName(response.getBody());
            if (apiChoice == null){
                return getRandomChoice();
            }
            System.out.println("INFO app has used third party to get random choice");
            return apiChoice;
        } catch (Exception e) {
            System.out.println("INFO getting random choice from local host as server is responding 500" +
                    "+");
            return getRandomChoice();
        }
    }

    public Choice getRandomChoice(){
        int min = 0; // Minimum value of range
        int max = choices.size()-1; // Maximum value of range
        int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return choices.get(randomInt);
    }

    public void deleteChoice(int choiceId){
        choices = choices.stream()
                .filter(choice -> choice.getId()!=choiceId).collect(Collectors.toList());
    }
}
