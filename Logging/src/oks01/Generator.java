package oks01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Třída aplikační logiky generátoru osobních čísel
 * 
 * @author P.Herout
 *
 */
public class Generator {
  public static final Logger generatorLogger = LogManager.getLogger();
  
  /** seznam dle typů studia seznamů jednotlivých fakultních osobních čísel */
  private List<List<OsobniCislo>> generovanaOsobniCisla;
  
  /** seznam osobních čísel, která měla nějakou část v chybném formátu;<br/> 
   * fakulta ale musí být správně 
   */
  private List<OsobniCislo> chybnyFormatOsobniCisla;

  /**
   * Metoda řídící zpracování jednoho vstupního souboru<br/>
   * včetně zápisu výsledků do výstupního souboru 
   * 
   * @param jmenoVstupnihoSouboru jméno vstupního souboru
   * @param fakulta zkratka fakulty
   */
  public void zpracovani(String jmenoVstupnihoSouboru, String fakulta) {
    List<OsobniCislo> nactenaOsobniCisla = nactiVsechnaOsobniCisla(jmenoVstupnihoSouboru);
    generatorLogger.info("nacteno osobnich cisel: " + nactenaOsobniCisla.size());
    pripravSkupinyARoztridDoNich(nactenaOsobniCisla, fakulta);
    generatorLogger.info("chybnych osobnich cisel: " + chybnyFormatOsobniCisla.size());
    generujVyslednaOsobniCisla();    
    zapisVysledky();
  }

  /**
   * Vytvoří seznam seznamů a seznam neplatných formátů.<br />
   * Do těchto seznamů pak roztřídí předvyplněná osobní čísla.
   * 
   * @param nactenaOsobniCisla seznam všech ze souboru načtených osobních čísel
   * @param fakulta zkratka fakulty
   */
  private void pripravSkupinyARoztridDoNich(List<OsobniCislo> nactenaOsobniCisla, String fakulta) {
    generovanaOsobniCisla = new ArrayList<List<OsobniCislo>>();
    for (int i = 0; i < TypStudia.values().length; i++) {
      generovanaOsobniCisla.add(new ArrayList<OsobniCislo>());
    }
    
    chybnyFormatOsobniCisla = new ArrayList<OsobniCislo>();
    
    String znakFakulty = Konstanty.najdiZnakFakulty(fakulta);
    generatorLogger.info("nalezeny znak fakulty: " + znakFakulty);
    
    // roztřídění podle typů - jen osobní čísla s platným formátem a z dané fakulty
    for (OsobniCislo oc : nactenaOsobniCisla) {
      generatorLogger.debug("zpracovavany student: " + oc.toString());
      if (oc.getFakulta().equals(znakFakulty)) {
        if (oc.isPlatnyFormat() == true) {
          int skupina = oc.getTypStudia().ordinal();
          generovanaOsobniCisla.get(skupina).add(oc);
          generatorLogger.trace("prirazen do skupiny: " + oc.getTypStudia());
        }
        else {
          chybnyFormatOsobniCisla.add(oc);
          generatorLogger.trace("prirazen do skupiny: NEPLATNY");
        }
      }
    }
  }
  
  /**
   * Seřadí skupiny typů studia studentů dle abecedy.<br />
   * Podle pořadí jim pak přidělí finální pořadové číslo, 
   * což je druhý a závěrečný krok v generování osobního čísla.
   */
  private void generujVyslednaOsobniCisla() {
    // seřazení platných podle abecedy
    generatorLogger.info("razeni podle abecedy");
    for (int i = 0; i < TypStudia.values().length - 1; i++) {
      Collections.sort(generovanaOsobniCisla.get(i));
    }
    
    // generování výsledných osobních čísel
    for (int i = 0; i < TypStudia.values().length - 1; i++) {
      generatorLogger.info("generovani poradovych cisel pro skupinu: " + TypStudia.values()[i]);
      List<OsobniCislo> seznam = generovanaOsobniCisla.get(i);
      for (int j = 0; j < seznam.size(); j++) {
        String poradoveCislo = String.format("%04d", (j + 1));
        seznam.get(j).generujOsobniCislo(poradoveCislo);
        generatorLogger.trace("prirazeno poradove cislo: " + poradoveCislo);
      }
    }    
  }

  
  /**
   * Načte vstupní soubor, vynechává prázdné řádky a komentáře.<br/>
   * Vstupní soubor musí být v UTF-8.
   * 
   * @param jmenoVstupnihoSouboru jméno vstupního souboru
   * @return seznam předvyplněných osobních čísel
   */
  private List<OsobniCislo> nactiVsechnaOsobniCisla(String jmenoVstupnihoSouboru) {
    List<OsobniCislo> osobniCisla = new ArrayList<OsobniCislo>();
    try {
      BufferedReader bfr = new BufferedReader(
                           new InputStreamReader(
                           new FileInputStream(
                           new File(jmenoVstupnihoSouboru)),
                           Konstanty.KODOVANI));
      
      String radka;
      while ((radka = bfr.readLine()) != null) {
        radka = radka.trim();
        generatorLogger.trace("radka souboru: " + radka);
        if (isPlatnaRadka(radka) == true) {
          osobniCisla.add(new OsobniCislo(radka));
          generatorLogger.debug("platna radka souboru: " + radka);
        }
      }
      bfr.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
        generatorLogger.error("nenalezen vstupni soubor: " + jmenoVstupnihoSouboru);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return osobniCisla;
  }
  
  /**
   * Označí prázdné řádky a řádky komentářů.
   * 
   * @param radka načtená řádka po <code>trim()</code>
   * @return <code>true</code> pokud je na řádce osobní číslo, <code>false</code> pro prázdné řádky a komentáře
   */
  private boolean isPlatnaRadka(String radka) {
    if (radka.length() == 0) {
      return false;
    }
    else if (radka.startsWith(Konstanty.ZNAK_KOMENTARE) == true) {
      return false;
    }
    return true;
  }

  /**
   * Do výstupního souboru zapisuje příslušnou skupinu typu studia.<br/>
   * Název skupiny je vypsán jako komentář na začátku.
   * 
   * @param pw soubor, kam se zapisuje
   * @param seznam seznam všech osobních čísel patřících do daného typu studia
   * @param nazevSkupiny název skupiny
   */
  private void zapisSkupinu(PrintWriter pw, List<OsobniCislo> seznam, String nazevSkupiny) {
    pw.println(Konstanty.ZNAK_KOMENTARE + " " + nazevSkupiny);
    generatorLogger.info("zapisuji skupinu: " + nazevSkupiny);
    for (OsobniCislo oc : seznam) {
      pw.println(oc);
      generatorLogger.trace(oc.toString());
    }
    pw.println();
  }
  
  
  /**
   * Pro jednu fakultu zapíše všechny výsledky po skupinách podle typu studia do souboru.<br/>
   * Na závěr vypíše stručnou statistiku.
   *    
   * <p>výstupní soubor je v UTF-8 a jeho jméno je konstantní - <code>Konstanty.VYSLEDKOVY_SOUBOR</code>
   */
  private void zapisVysledky() {
    try {
      PrintWriter pw = new PrintWriter(
                       new OutputStreamWriter(
                       new FileOutputStream(
                       new File(Konstanty.VYSLEDKOVY_SOUBOR)), 
                       Konstanty.KODOVANI));
 
      int pocet = 0;
      for (int i = 0; i < TypStudia.values().length - 1; i++) {
        zapisSkupinu(pw, generovanaOsobniCisla.get(i), TypStudia.values()[i].getNazev());
        generatorLogger.info("pocet ve skupine '"+ TypStudia.values()[i].getNazev() + "' : " +
                +generovanaOsobniCisla.get(i).size());
        pocet += generovanaOsobniCisla.get(i).size();
      }
      
      zapisSkupinu(pw, chybnyFormatOsobniCisla, Konstanty.TEXT_CHYBNY_FORMAT);
      
      pw.println("---------------------------");
      pw.println("Z celkově zadaných " + (pocet + chybnyFormatOsobniCisla.size()) +
          " jich bylo zadáno:");
      pw.println("správně " + pocet + 
          " a " + chybnyFormatOsobniCisla.size() + " jich bylo chybně zapsáno");

      generatorLogger.info("pocet ve skupine 'chybně zadáno' : " + chybnyFormatOsobniCisla.size());
      pw.close();
    } catch (UnsupportedEncodingException e) {
       e.printStackTrace();
    } catch (FileNotFoundException e) {
       e.printStackTrace();
    }
  }

}
