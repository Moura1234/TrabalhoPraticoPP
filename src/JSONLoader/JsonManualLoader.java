/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package JSONLoader;


import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import Enums.EPosition;
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

/**
 * Utility class responsible for loading player and club data from JSON files.
 * It reads and parses JSON-formatted files to instantiate IPlayer and IClub
 * objects. This manual loader uses BufferedReader and simple parsing
 * techniques.
 */
public class JsonManualLoader {

    /**
     * Loads players from a JSON file located at the given path.
     *
     * @param path the path to the JSON file containing player data
     * @return an array of IPlayer objects created from the JSON data
     * @throws RuntimeException if an error occurs during reading or parsing
     */
    public static IPlayer[] loadPlayersFromJson(String path) {
        int count = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("}")) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        IPlayer[] players = new IPlayer[count];
        int index = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            String name = "", nationality = "", photo = "", birthDate = "", preferredFoot = "";
            int number = 0, shooting = 0, passing = 0, stamina = 0, defense = 0, speed = 0, reflexes = 0;
            float height = 0f, weight = 0f;
            EPosition position = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("\"name\"")) {
                    name = extractString(line);
                } else if (line.startsWith("\"birthDate\"")) {
                    birthDate = extractString(line);
                } else if (line.startsWith("\"nationality\"")) {
                    nationality = extractString(line);
                } else if (line.startsWith("\"number\"")) {
                    number = extractInt(line);
                } else if (line.startsWith("\"shooting\"")) {
                    shooting = extractInt(line);
                } else if (line.startsWith("\"passing\"")) {
                    passing = extractInt(line);
                } else if (line.startsWith("\"stamina\"")) {
                    stamina = extractInt(line);
                } else if (line.startsWith("\"defense\"")) {
                    defense = extractInt(line);
                } else if (line.startsWith("\"speed\"")) {
                    speed = extractInt(line);
                } else if (line.startsWith("\"reflexes\"")) {
                    reflexes = extractInt(line);
                } else if (line.startsWith("\"position\""))
                
            try {
                    position = EPosition.valueOf(extractString(line).toUpperCase());
                } catch (Exception e) {
                    position = null;
                } else if (line.startsWith("\"photo\"")) {
                    photo = extractString(line);
                } else if (line.startsWith("\"height\"")) {
                    height = extractFloat(line);
                } else if (line.startsWith("\"weight\"")) {
                    weight = extractFloat(line);
                } else if (line.startsWith("\"preferredFoot\"")) {
                    preferredFoot = extractString(line);
                } else if (line.startsWith("}")) {
                    PreferredFoot foot = PreferredFoot.fromString(preferredFoot);

                    if (position == null) {
                        System.out.println("️ Jogador com posicao invalida: " + name);
                        continue;
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
                            reflexes,
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

    /**
     * Loads clubs from a JSON file at the specified path. Each club is
     * constructed with basic metadata and an empty player array.
     *
     * @param filePath the path to the JSON file containing clubs
     * @return an array of IClub objects parsed from the JSON file
     * @throws RuntimeException if the file can't be read or parsed
     */
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

    /**
     * Loads the correct JSON file of players for each club by matching the club
     * name to a known file name. Populates each club with its respective
     * players.
     *
     * @param clubs array of IClub objects to which players will be assigned
     */
    public static void loadClubName(IClub[] clubs) {

        for (IClub club : clubs) {
            String fileName = null;

            switch (club.getName()) {
                case "Sport Lisboa e Benfica":
                    fileName = "Benfica";
                    break;
                case "Futebol Clube do Porto":
                    fileName = "Porto";
                    break;
                case "Sporting Clube de Portugal":
                    fileName = "Sporting";
                    break;
                case "Sporting Clube de Braga":
                    fileName = "Braga";
                    break;
                case "Vitoria Sport Clube":
                    fileName = "Vitoria";
                    break;
                case "Boavista Futebol Clube":
                    fileName = "Boavista";
                    break;
                case "Casa Pia Atletico Clube":
                    fileName = "CasaPia";
                    break;
                case "Grupo Desportivo Estoril Praia":
                    fileName = "Estoril";
                    break;
                case "C. Futebol Estrela da Amadora":
                    fileName = "EstrelaAmadora";
                    break;
                case "Futebol Clube de Famalicao":
                    fileName = "Famalicao";
                    break;
                case "Sport Clube Farense":
                    fileName = "Farense";
                    break;
                case "Gil Vicente Futebol Clube":
                    fileName = "GilVicente";
                    break;
                case "Moreirense Futebol Clube":
                    fileName = "Moreirense";
                    break;
                case "Clube Desportivo Nacional":
                    fileName = "Nacional";
                    break;
                case "Rio Ave Futebol Clube":
                    fileName = "RioAve";
                    break;
                case "Clube Desportivo Santa Clara":
                    fileName = "SantaClara";
                    break;
                case "AVS Futebol SAD":
                    fileName = "AFS";
                    break;
                case "Futebol Clube de Arouca":
                    fileName = "Arouca";
                    break;
                default:
                    fileName = null;
                    break;
            }

            if (fileName != null) {
                String path = "JSONfiles/files/players/" + fileName + ".json";
                IPlayer[] players = JsonManualLoader.loadPlayersFromJson(path);
                ((Club) club).setPlayers(players);
            }
        }
    }

    /**
     * Extracts a String value from a JSON line.
     *
     * @param line the JSON-formatted line
     * @return the extracted String value
     */
    private static String extractString(String line) {
        int start = line.indexOf(":") + 1;
        String value = line.substring(start).trim();

        if (value.startsWith("\"")) {
            value = value.substring(1);
        }
        if (value.endsWith(",")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.endsWith("\"")) {
            value = value.substring(0, value.length() - 1);
        }

        return value;
    }

    /**
     * Extracts an integer value from a JSON line.
     *
     * @param line the JSON-formatted line
     * @return the extracted integer
     */
    private static int extractInt(String line) {
        return Integer.parseInt(line.split(":")[1].trim().replace(",", ""));
    }

    /**
     * Extracts a float value from a JSON line.
     *
     * @param line the JSON-formatted line
     * @return the extracted float
     */
    private static float extractFloat(String line) {
        return Float.parseFloat(line.split(":")[1].trim().replace(",", ""));
    }

}
