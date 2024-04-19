package oks03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PripravaDatovehoZdroje {

    private static List<String> nacteniSouboru() throws FileNotFoundException {
        List<String> data = new ArrayList<>();
        Scanner sc;
        String soubor = System.getProperty("datovy.zdroj.oks03");
        String nacti = "priklady-oks-03.txt";
        File file = new File(nacti);
        sc = new Scanner(file);

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith("#") || line.equals("")){
                continue;
            }
            else{
                data.add(line);
            }
        }
        return data;
    }

    static List<Object[]> listDvojiceBooleanString() throws FileNotFoundException {
        List<String> data = nacteniSouboru();
        List<Object[]> generovano = new ArrayList<>();

        for (String line : data) {
            boolean booleanValue = Boolean.parseBoolean(line.substring(0, line.indexOf(";")));
            String rest = line.substring(line.indexOf(";")+1, line.length());
            Object[] pair = {booleanValue, rest};
            generovano.add(pair);
        }

        return generovano;
    }


}
