import java.util.Scanner;

public class Launcher {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Bienvenue, que vouliez-vous me dire ? :");
        String input;
        while(!"quit".equals(input = s.nextLine())) {
                System.out.println("Unknown command");
        }
    }
}
