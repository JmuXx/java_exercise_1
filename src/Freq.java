import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;

public class Freq implements Command{
    public String name() {
        return "freq";
    }

    public boolean run(Scanner s) {
            System.out.println("Entrez un chemin de fichier : ");
            var path = s.nextLine();
            Path filepath = Paths.get(path);
            try {
                String content = readString(filepath);
                content = content.replaceAll("[^a-zA-Z ]","");
                content = content.toLowerCase(Locale.ROOT);
                var v = content.split(" ");
                List a = new ArrayList();
                for (int i = 0; i < v.length; i++) {
                    if(!v[i].isBlank()){
                        a.add(v[i]);
                    }
                }
                Map<String, Integer> map = new HashMap<String, Integer>();
                for (Object o : a) {
                    if (!map.containsKey((String) o)) {
                        map.put((String) o, 1);
                    } else {
                        map.put((String) o, map.get((String) o) + 1);
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
        return false;
    }
}
