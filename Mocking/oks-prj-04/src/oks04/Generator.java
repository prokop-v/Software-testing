package oks04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
  
  /** přístup k datovému zdroji */
  private ICteniDat cteniDat;
  
  /**
   * vytvoří spojení na instanci Cteni dat
   */
  public Generator(ICteniDat cteniDat) {
    this.cteniDat = cteniDat;
  }

  
  /**
   * Metoda řídící zpracování jednoho vstupního souboru<br/>
   * včetně zápisu výsledků do výstupního souboru</p>
   * <p>Pro lepší testovatelnost vrací seznam seznamů jednotlivých typů studia
   * 
   * @param jmenoVstupnihoSouboru jméno vstupního souboru
   * @param fakulta zkratka fakulty
   * @return seznam seznamů jednotlivých typů studia
   */
  public List<List<OsobniCislo>> zpracovani(String jmenoVstupnihoSouboru, String fakulta) {
    List<OsobniCislo> nactenaOsobniCisla = cteniDat.nactiVsechnaOsobniCisla(jmenoVstupnihoSouboru);
    String znakFakulty = Konstanty.najdiZnakFakulty(fakulta);
    
    // seznam dle typů studia seznamů jednotlivých fakultních osobních čísel
    List<List<OsobniCislo>> seznamyTypuStudia = new ArrayList<List<OsobniCislo>>();
    
    // roztřídí do jednotlivých seznamů
    for (TypStudia ts : TypStudia.values()) {
      List<OsobniCislo> typStudiaSeznam = vytvorSeznamTypuStudia(nactenaOsobniCisla, znakFakulty, ts);
      int skupina = ts.ordinal();
      seznamyTypuStudia.add(skupina, typStudiaSeznam);
    }
    
    // seznam osobních čísel, která měla nějakou část v chybném formátu; fakulta ale musí být správně 
    List<OsobniCislo> chybnyFormatOsobniCisla = vytvorSeznamChybnychFormatu(nactenaOsobniCisla, znakFakulty);
    
    // pro jednotlivé seznamy generuje výsledná osobní čísla
    for (TypStudia ts : TypStudia.values()) {
      List<OsobniCislo> typStudiaSeznam = getSeznamTypuStudia(seznamyTypuStudia, ts);
      generujVyslednaOsobniCisla(typStudiaSeznam);
    }

    zapisVysledky(seznamyTypuStudia, chybnyFormatOsobniCisla);
    
    return seznamyTypuStudia;
  }

  /**
   * Vrací jeden konkrétní seznam ze seznamu seznamů typů studia
   * 
   * @param seznamyTypuStudia seznamu seznamů typů studia
   * @param typStudia konkrétní typ studia
   * @return jeden konkrétní seznam
   */
  public List<OsobniCislo> getSeznamTypuStudia(List<List<OsobniCislo>> seznamyTypuStudia, 
                                                    TypStudia typStudia) {
    int skupina = typStudia.ordinal();
    return seznamyTypuStudia.get(skupina);
  }
  
  /**
   * Ze seznamu všech předvyplněných osobních čísel vybere jen ta, 
   * která patří do stejné fakulty, do stejného typu studia a jsou v platném formátu<br />
   * tato osobní čísla uloží do vytvořeného seznamu
   * 
   * @param nactenaOsobniCisla seznam všech načtených osobních čísel
   * @param znakFakulty jednopísmenný znak fakulty
   * @param typStudia hledaný typ studia
   * @return seznam platných osobních čísel patřících do jednoho typu studia
   */
  public List<OsobniCislo> vytvorSeznamTypuStudia(List<OsobniCislo> nactenaOsobniCisla, 
                                                      String znakFakulty, TypStudia typStudia) {
    List<OsobniCislo> typStudiaSeznam = new ArrayList<>();
    
    for (OsobniCislo oc : nactenaOsobniCisla) {
      if (oc.getFakulta().equals(znakFakulty)) {
        if (oc.getTypStudia().equals(typStudia)) {
          if (oc.isPlatnyFormat() == true) {
            typStudiaSeznam.add(oc);
          }
        }
      }
    }
    return typStudiaSeznam;
  }
  
  /**
   * Ze seznamu všech předvyplněných osobních čísel vybere jen ta, 
   * která patří do stejné fakulty a jsou v <strong>neplatném</strong> formátu<br />
   * tato osobní čísla uloží do vytvořeného seznamu
   * 
   * @param nactenaOsobniCisla seznam všech načtených osobních čísel
   * @param znakFakulty jednopísmenný znak fakulty
   * @return seznam <strong>neplatných</strong> osobních čísel patřících k fakultě
   */
  public List<OsobniCislo> vytvorSeznamChybnychFormatu(List<OsobniCislo> nactenaOsobniCisla, 
                                                      String znakFakulty) {
    List<OsobniCislo> chybnyFormatSeznam = new ArrayList<>();
    
    for (OsobniCislo oc : nactenaOsobniCisla) {
      if (oc.getFakulta().equals(znakFakulty)) {
        if (oc.isPlatnyFormat() == false) {
          chybnyFormatSeznam.add(oc);
        }
      }
    }
    return chybnyFormatSeznam;
  }
  
  /**
   * Seřadí seznam osobních čísel dle přirozeného řazení.<br />
   * Podle pořadí jim pak přidělí finální pořadové číslo, 
   * což je druhý a závěrečný krok v generování osobního čísla.
   * 
   * @param typStudiaSeznam seznam všech osobních čísel patřících do daného typu studia
   */
  public void generujVyslednaOsobniCisla(List<OsobniCislo> typStudiaSeznam) {
    Collections.sort(typStudiaSeznam);

    // generování výsledných osobních čísel
    for (int i = 0; i < typStudiaSeznam.size(); i++) {
      String poradoveCislo = String.format("%04d", (i + 1));
      typStudiaSeznam.get(i).generujOsobniCislo(poradoveCislo);
    }    
  }

  /**
   * Do výstupního souboru zapisuje příslušnou skupinu typu studia.<br/>
   * Název skupiny je vypsán jako komentář na začátku.
   * 
   * @param pw soubor, kam se zapisuje
   * @param seznam seznam všech osobních čísel patřících do daného typu studia
   * @param nazevSkupiny název skupiny
   * @return počet zapsaných osobních čísel
   */
  private int zapisSkupinu(PrintWriter pw, List<OsobniCislo> seznam, String nazevSkupiny) {
    pw.println(Konstanty.ZNAK_KOMENTARE + " " + nazevSkupiny);
    int pocet = 0;
    for (OsobniCislo oc : seznam) {
      pw.println(oc);
      pocet++;
    }
    pw.println();
    
    return pocet;
  }
  
  
  /**
   * Pro jednu fakultu zapíše všechny výsledky po skupinách podle typu studia do souboru.<br/>
   * Na závěr vypíše stručnou statistiku.
   *    
   * <p>výstupní soubor je v UTF-8 a jeho jméno je konstantní - <code>Konstanty.VYSLEDKOVY_SOUBOR</code>
   */
  public void zapisVysledky(List<List<OsobniCislo>> seznamyTypuStudia, List<OsobniCislo> chybnyFormatOsobniCisla) {
    try {
      PrintWriter pw = new PrintWriter(
                       new OutputStreamWriter(
                       new FileOutputStream(
                       new File(Konstanty.VYSLEDKOVY_SOUBOR)), 
                       Konstanty.KODOVANI));
 
      int pocet = 0;
      for (TypStudia ts : TypStudia.values()) {
        if (ts.equals(TypStudia.NEPLATNY) == false) {
          List<OsobniCislo> typStudiaSeznam = getSeznamTypuStudia(seznamyTypuStudia, ts);
          pocet += zapisSkupinu(pw, typStudiaSeznam, ts.getNazev());
        }
      }
      
      int chybne = zapisSkupinu(pw, chybnyFormatOsobniCisla, Konstanty.TEXT_CHYBNY_FORMAT);
      
      pw.println("---------------------------");
      pw.println("Z celkově zadaných " + (pocet + chybne) +
          " jich bylo zadáno:");
      pw.println("správně " + pocet + 
          " a " + chybne + " jich bylo chybně zapsáno");
      
      pw.close();
    } catch (UnsupportedEncodingException e) {
       e.printStackTrace();
    } catch (FileNotFoundException e) {
       e.printStackTrace();
    }
  }

}
