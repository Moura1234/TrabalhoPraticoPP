/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONLoader;

/**
 *
 * @author utilizador
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import com.ppstudios.footballmanager.api.contracts.player.*;

public class JsonManualLoader {

    public static IPlayer[] loadPlayersFromJson(String path) {
        IPlayer[] players = new IPlayer[18]; // Assumindo 18 jogadores por equipa
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String name = "", nationality = "", photo = "", birthDate = "", preferredFoot = "";
            int number = 0, shooting = 0, passing = 0, stamina = 0, speed = 0;
            float height = 0f, weight = 0f;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("\"name\"")) {
                    name = extractString(line);
                } else if (line.startsWith("\"birthDate\"")) {
                    birthDate = extractString(line);
                } else if (line.startsWith("\"nationality\"")) {
                    nationality = extractString(line);
                } else if (line.startsWith("\"photo\"")) {
                    photo = extractString(line);
                } else if (line.startsWith("\"number\"")) {
                    number = extractInt(line);
                } else if (line.startsWith("\"shooting\"")) {
                    shooting = extractInt(line);
                } else if (line.startsWith("\"passing\"")) {
                    passing = extractInt(line);
                } else if (line.startsWith("\"stamina\"")) {
                    stamina = extractInt(line);
                } else if (line.startsWith("\"speed\"")) {
                    speed = extractInt(line);
                } else if (line.startsWith("\"height\"")) {
                    height = extractFloat(line);
                } else if (line.startsWith("\"weight\"")) {
                    weight = extractFloat(line);
                } else if (line.startsWith("\"preferredFoot\"")) {
                    preferredFoot = extractString(line);
                } else if (line.startsWith("}")) { // fim de um jogador
                    IPlayer p = new Player(
                        name,
                        LocalDate.parse(birthDate),
                        nationality,
                        photo,
                        number,
                        shooting,
                        passing,
                        stamina,
                        speed,
                        height,
                        weight,
                        PreferredFoot.valueOf(preferredFoot)
                    );
                    players[index++] = p;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
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
