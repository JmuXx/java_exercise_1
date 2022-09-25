import java.util.Scanner;

public class Fibo implements Command{

    public String name() {
        return "fibo";
    }

    public int fibo(int n)
    {
        if(n == 0 || n == 1){
            return n;
        }
        return fibo(n-1) + fibo(n-2);
    }

    public boolean run(Scanner s){
        System.out.println("Entrez un entier : ");
        try {
            var l = s.nextInt();
            int res = fibo(l);
            System.out.println(res);
            s.nextLine();
        }
        catch (NumberFormatException e){
            System.out.println("ENTREZ UN NOMBRE SVP ! :");
        }
        return false;
    }
}
