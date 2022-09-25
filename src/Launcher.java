import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;

public class Launcher {
    static Scanner s = new Scanner(System.in);

    public static int fibo(int n)
    {
        if(n == 0 || n == 1){
            return n;
        }
        return fibo(n-1) + fibo(n-2);
    }

    public static void freq(){
        System.out.println("Entrez un chemin de fichier : ");
        var path = s.nextLine();
        Path filepath = Paths.get(path);
        try {
            String content = readString(filepath);
            content = content.replace("!", "").replace("?","").replace(".","").replace(",","").replace("\n","");
            content = content.toLowerCase(Locale.ROOT);
            var v = content.split(" ");
            List a = new ArrayList();
            for (int i = 0; i < v.length; i++) {
                if(!v[i].isBlank()){
                    a.add(v[i]);
                }
            }
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(int i = 0; i < a.size(); i++) {
                if(!map.containsKey((String)a.get(i))){
                   map.put((String) a.get(i),1);
                }
                else{
                    map.put((String) a.get(i),map.get((String)a.get(i)) + 1);
                }
            }
            String reskey = "";
            int resvalue = 0;
            for (int i = 0; i < 3; i++) {
                reskey = "";
                resvalue = 0;
                for (String name : map.keySet()) {
                    String key = name.toString();
                    String value = map.get(name).toString();
                    if (Integer.parseInt(value) > resvalue) {
                        resvalue = Integer.parseInt(value);
                        reskey = key;
                    }
                }
                if (i < 2) {
                    System.out.print(reskey + " ");
                    map.remove(reskey);
                }
                else{
                    System.out.println(reskey);
                }
            }
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e.getClass().getName() + e);
        }
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
                else if("freq".equals(input)){
                    freq();
                }
                else{
                    System.out.println("Unknown command");
                }
        }
    }
}
