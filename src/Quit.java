import java.util.Scanner;

public class Quit implements Command{

    public String name() {
        return "quit";
    }

    public boolean run(Scanner s) {
        return true;
    }
}
