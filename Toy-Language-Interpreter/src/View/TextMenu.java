package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by vladc on 08.11.2016.
 */
public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command cmd : commands.values()) {
            String line = String.format("%4s. %s", cmd.getKey(), cmd.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Your option: ");
            String key = scanner.nextLine();
            Command cmd = commands.get(key);
            if (cmd == null) {
                System.out.println("Wrong command!");
                continue;
            }
            cmd.execute();
        }
    }
}
