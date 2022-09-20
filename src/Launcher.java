import java.util.Scanner;

public class Launcher {
    static Scanner s = new Scanner(System.in);

    public static int fibo(int n)
    {
        if(n == 0 || n == 1){
            return n;
        }
        return fibo(n-1) + fibo(n-2);
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue, que vouliez-vous me dire ? :");
        String input;
        while(!"quit".equals(input = s.nextLine())) {
                if("fibo".equals(input)){
                    System.out.println("Entrez un entier : ");
                    try {
                        var l = s.nextInt();
                        int res = fibo(l);
                        System.out.println(res);
                    }
                    catch (NumberFormatException e){
                        System.out.println("ENTREZ UN NOMBRE SVP ! :");
                    }

                }
                else{
                    System.out.println("Unknown command");
                }
        }
    }
}
