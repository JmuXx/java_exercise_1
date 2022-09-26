import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;

public class Predict implements Command{
    public String name() {
        return "predict";
    }

    public boolean run(Scanner s) {
        System.out.println("Entrez un chemin de fichier : ");
        var path = s.nextLine();
        Path filepath = Paths.get(path);
        try {
            String content = readString(filepath);
            content = content.replaceAll("[^a-zA-Zç\\- ]","");

            content = content.toLowerCase(Locale.ROOT);
            var v = content.split(" ");
            List<String> a = new ArrayList<String>();
            for (String item : v) {
                if (!item.isBlank())
                    a.add(item);
            }
            Set<String> charSet = new LinkedHashSet<String>(a);
            Map<String, Map<String,Integer>> res = new HashMap<String, Map<String,Integer>>();
            Map<String, Integer> map; // = new HashMap<String, Integer>();
            for (String stt : charSet) {
                //System.out.println((stt));
                map = new HashMap<String, Integer>();
                for (int i = 0; i < a.size() - 1; i++) {
                    if(Objects.equals(stt, a.get(i))) {
                        if (!map.containsKey(a.get(i + 1))) {
                            map.put(a.get(i + 1), 1);
                        } else {
                            map.put(a.get(i + 1), map.get(a.get(i + 1)) + 1);
                        }
                    }
                }
                res.put(stt, map);
            }
            Map<String, String> finres = new HashMap<String, String>();
            for (String wrds : charSet) {
                if(res.get(wrds).size() > 1){
                    int maxval = 0;
                    String maxword = "";
                    for (Map.Entry<String, Integer> stringIntegerEntry : res.get(wrds).entrySet()) {
                        Map.Entry mapentry = (Map.Entry) stringIntegerEntry;
                        if ((int) mapentry.getValue() > maxval) {
                            maxval = (int) mapentry.getValue();
                            maxword = (String) mapentry.getKey();
                        }
                    }
                    finres.put(wrds,maxword);
                }
                else{
                    List keys = new ArrayList(res.get(wrds).keySet());
                    if(keys.size() != 0) {
                        finres.put(wrds, (String) keys.get(0));
                    }
                }
            }
            /*for(Map.Entry<String, String> entry : finres.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + " " + value);
            }*/
            System.out.println("Entrez pour reconstruire une phrase à partir du text :");
            String word = s.nextLine();
            word = word.replaceAll("[^a-zA-Zç\\- ]","");

            word = word.toLowerCase(Locale.ROOT);
            boolean isin = false;
            for (String sss: charSet) {
                if(Objects.equals(word, sss)) {
                    isin = true;
                    break;
                }
            }
            if(!isin){
                System.out.println("Le mot donné n'est pas dans le text.");
            }
            else{
                StringBuilder sentence = new StringBuilder(word);
                for (int i = 0; i < 20; i++) {
                    if(finres.containsKey(word) && finres.get(word) != null){
                        sentence.append(" ").append(finres.get(word));
                        word = finres.get(word);
                    }
                }
                System.out.println("The sentence is : " + sentence);
            }

        }
        catch (Exception e){
            System.out.println("Unreadable file: " + e.getClass().getName() + e);
        }
        return false;
    }
}
