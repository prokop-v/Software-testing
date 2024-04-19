package oks09.tests;

/**
 * Datovy zdroj pro parametrizovany test
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PripravaDatovehoZdroje {

  private static List<String> nacteniSouboru() {
    List<String> radky = null;
    try {
//      String soubor = System.getProperty("datovy.zdroj.oks09");
      String soubor = "priklady-oks-09.txt";
      radky = Files.readAllLines(Paths.get(soubor));
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Iterator<String> it = radky.iterator(); it.hasNext();) {
      String radka = it.next();
      if (radka.isBlank() || radka.startsWith("#")) {
        it.remove();
      }
    }
    return radky;
  }

  public static List<Object[]> listPrepravka() {
    List<Object[]> generovano = new ArrayList<>();
    List<String> radky = nacteniSouboru();
    for (String radka : radky) {
      String[] casti = radka.split(";");
      String tc = casti[0];
      String zahranicni = "";
      if (casti.length == 8) {
        zahranicni = casti[7];
      }
      Object[] dvojice = new Object[2];
      dvojice[0] = tc;
      dvojice[1] = new Prepravka(
              casti[1], casti[2],
              casti[3], casti[4], casti[5],
              casti[6], zahranicni);
      generovano.add(dvojice);
    }
    return generovano;
  }
}
