/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONLoader;

/**
 *
 * @author utilizador
 */
import Enums.Position;
import Player.Player;
import Team.Club;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import com.ppstudios.footballmanager.api.contracts.player.*;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonManualLoader {

 public static IPlayer[] loadPlayersFromJson(String path) {
    int count = 0;

    // 1ª passagem: contar jogadores
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().startsWith("}")) {
                count++;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    IPlayer[] players = new IPlayer[count]; // array com tamanho certo
    int index = 0;

    // 2ª passagem: carregar jogadores
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        String name = "", nationality = "", photo = "", birthDate = "", preferredFoot = "";
        int number = 0, shooting = 0, passing = 0, stamina = 0, defense = 0, speed = 0;
        float height = 0f, weight = 0f;
        Position position = null;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("\"name\"")) name = extractString(line);
            else if (line.startsWith("\"birthDate\"")) birthDate = extractString(line);
            else if (line.startsWith("\"nationality\"")) nationality = extractString(line);
            else if (line.startsWith("\"number\"")) number = extractInt(line);
            else if (line.startsWith("\"shooting\"")) shooting = extractInt(line);
            else if (line.startsWith("\"passing\"")) passing = extractInt(line);
            else if (line.startsWith("\"stamina\"")) stamina = extractInt(line);
            else if (line.startsWith("\"defense\"")) defense = extractInt(line);
            else if (line.startsWith("\"speed\"")) speed = extractInt(line);
            else if (line.startsWith("\"position\""))
            try {
            position = Position.valueOf(extractString(line).toUpperCase());
            } catch (Exception e) {
            position = null; 
            }
            
            else if (line.startsWith("\"photo\"")) photo = extractString(line);
            else if (line.startsWith("\"height\"")) height = extractFloat(line);
            else if (line.startsWith("\"weight\"")) weight = extractFloat(line);
            else if (line.startsWith("\"preferredFoot\"")) preferredFoot = extractString(line);
            else if (line.startsWith("}")) {
                PreferredFoot foot = PreferredFoot.fromString(preferredFoot);
                
                if (position == null) {
        System.out.println("️ Jogador com posicao invalida: " + name);
        continue; // ignora este jogador
    }
                IPlayer p = new Player(
                    name,
                    LocalDate.parse(birthDate),
                    nationality,
                    number,
                    shooting,
                    passing,
                    stamina,
                    defense,
                    speed,
                    position,
                    photo,
                    height,
                    weight,
                    foot
                );
                players[index++] = p;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return players;
}

    
  
public static IClub[] loadClubsFromJson(String filePath) {
    IClub[] clubs = new IClub[100]; 
    int index = 0;

    try {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        for (Object obj : jsonArray) {
    JSONObject clubJson = (JSONObject) obj;

    String name = (String) clubJson.get("name");
    String code = (String) clubJson.get("code");
    String country = (String) clubJson.get("country");
    long founded = (long) clubJson.get("founded");
    String stadium = (String) clubJson.get("stadium");
    String logo = (String) clubJson.get("logo");
    String playerPosition = "";
    String targetPosition = "";
    IPlayer selected = null;
    IPlayer[] emptyPlayers = new IPlayer[100];

    IClub club = new Club(name, emptyPlayers, code, country, (int) founded, stadium, logo, playerPosition, targetPosition, selected);
    clubs[index++] = club;
}

    } catch (Exception e) {
        e.printStackTrace();
    }

    IClub[] result = new IClub[index];
    for (int i = 0; i < index; i++) {
        result[i] = clubs[i];
    }

    return result;
}

    private static String extractString(String line) {
        return line.split(":")[1].trim().replace("\"", "").replace(",", "");
    }

    private static int extractInt(String line) {
        return Integer.parseInt(line.split(":")[1].trim().replace(",", ""));
    }

    private static float extractFloat(String line) {
        return Float.parseFloat(line.split(":")[1].trim().replace(",", ""));
    }
    
    


}
