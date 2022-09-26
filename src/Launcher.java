import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;

interface Command{
    String name();
    boolean run(Scanner s);
}

public class Launcher {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        List<Command> cmds = new ArrayList<Command> ();
        cmds.add(new Quit());
        cmds.add(new Freq());
        cmds.add(new Fibo());
        cmds.add(new Predict());
        var pointbreak = true;
        int count = 0;
        do {
            System.out.println("Bienvenue Que puis-je faire pour vous ? :");
            String line = s.nextLine();
            for (Command cmd : cmds) {
                if (Objects.equals(line, cmd.name())) {
                    var v = cmd.run(s);
                    if (v)
                        pointbreak = false;
                    break;
                } else
                    count += 1;
            }
            if (count == cmds.size())
                System.out.println("Unknown command");
            count = 0;

        } while (pointbreak);
    }
}
