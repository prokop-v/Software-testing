package oks08.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static oks08.support.Const.BASE_URL;
import static oks08.support.Id.GEN_TEXT_VYSLEDEK;

public class Utils {
  public static Map<String, String> NAZVY_VYBERU_FAKULTA;

  static {
    nastavNazvyVyberu();
  }

  private static void nastavNazvyVyberu() {
    NAZVY_VYBERU_FAKULTA = new HashMap<>();
    NAZVY_VYBERU_FAKULTA.put("N", " --- neuvedeno ---");
    NAZVY_VYBERU_FAKULTA.put("FAV", "FAV - Fakulta aplikovaných věd");
    NAZVY_VYBERU_FAKULTA.put("FDU", "FDU - Fakulta designu a umění Ladislava Sutnara");
    NAZVY_VYBERU_FAKULTA.put("FEK", "FEK - Fakulta ekonomická");
    NAZVY_VYBERU_FAKULTA.put("FEL", "FEL - Fakulta elektrotechnická");
    NAZVY_VYBERU_FAKULTA.put("FF", "FF - Fakulta filosofická");
    NAZVY_VYBERU_FAKULTA.put("FPE", "FPE - Fakulta pedagogická");
    NAZVY_VYBERU_FAKULTA.put("FPR", "FPR - Fakulta právnická");
    NAZVY_VYBERU_FAKULTA.put("FST", "FST - Fakulta strojní");
    NAZVY_VYBERU_FAKULTA.put("FZS", "FZS - Fakulta zdravotnických studií");
  }

  // Typ studia
  public static final String TYP_B = "bakalářský";
  public static final String TYP_N = "navazující";
  public static final String TYP_P = "doktorský";
  public static final String TYP_M = "magisterský";

  /**
   * Natazeni stranky daneho URL a cekani na zobrazeni
   *
   * @param url URL pozadovane stranky
   */
  public static void getURLAndWait(String url) {
    Drivers.getDriver().get(url);
    (new WebDriverWait(Drivers.getDriver(), Const.TIMEOUT)).until(
            ExpectedConditions.urlToBe(url));
  }

  /**
   * Klik na element a cekani na zobrazeni prislusne stranky
   *
   * @param element element, na ktery se klika
   * @param expectedURL URL pozadovane stranky
   */
  public static void clickAndWaitURL(WebElement element, String expectedURL) {
    element.click();
    (new WebDriverWait(Drivers.getDriver(), Const.TIMEOUT)).until(
            ExpectedConditions.urlToBe(expectedURL));
  }

  /**
   * Kliknuti na tlacitko a cekani na obnoveni stranky Generovani
   *
   * @param id ID tlacitka
   */
  public static void stiskniTlacitkoACekej(String id) {
    WebElement we = Drivers.getDriver().findElement(By.id(id));
    clickAndWaitURL(we, BASE_URL + "Generovani");
  }

  /**
   * Nastaveni fakulty ve vyberovem seznamu
   * pouzije se NAZVY_VYBERU_FAKULTA ze Settings
   *
   * @param zkratkaFakulty tripismenna zkratka fakulty nebo "FF"
   */
  public static void setFakulta(String zkratkaFakulty) {
    WebElement seznam = Drivers.getDriver().findElement(By.name("fakulta"));
    List<WebElement> vsechnyVolby = seznam.findElements(By.tagName("option"));
    for (WebElement volba : vsechnyVolby) {
      if (volba.getAttribute("value").equals(zkratkaFakulty)) {
        volba.click();
        break;
      }
    }

  }

  /**
   * Nastaveni roku nastupu
   *
   * @param rok rok nastupu
   */
  public static void setRok(String rok) {
    WebElement we = Drivers.getDriver().findElement(By.id("rok_nastupu"));
    we.clear();
    we.sendKeys(rok);
  }

  /**
   * Nastaveni typu studia
   *
   * @param typ bude zadan pomoci konstant z Const - TYP_B apod.
   */
  public static void setTyp(String typ) {
    WebElement seznam = Drivers.getDriver().findElement(By.name("typ_studia"));
    List<WebElement> vsechnyVolby = seznam.findElements(By.tagName("option"));
    for (WebElement volba : vsechnyVolby) {
      if (volba.getText().equals(typ)) {
        volba.click();
        break;
      }
    }
  }

  /**
   * Nastveni poradoveho cisla
   *
   * @param poradi poradove cislo
   */
  public static void setPoradi(String poradi) {
    WebElement we = Drivers.getDriver().findElement(By.id("poradove_cislo"));
    we.clear();
    we.sendKeys(poradi);
  }

  /**
   * Nastaveni formy studia
   *
   * @param idForma ID konkretniho radiobuttonu
   */
  public static void setForma(String idForma) {
    WebElement we1 = Drivers.getDriver().findElement(By.id(idForma));
    we1.click();
  }

  /**
   * Generovani vysledneho osobniho cisla tlacitkem Generovani
   */
  public static void generujCislo() {
    stiskniTlacitkoACekej(Id.GEN_BUTTON_GENEROVANI);
  }

  /**
   * Vrati vygenerovane osobni cislo
   * @return
   */
  public static String getVysledek() {
    WebElement we = Drivers.getDriver().findElement(By.id("vysledek"));
    return we.getText();
  }

  /**
   * Vymazani celeho formulare tlacitkem Vymaz
   */
  public static void vymazFormular() {
    stiskniTlacitkoACekej(Id.GEN_BUTTON_MAZANI);
  }

  /**
   * Je zobrazeno generovane osobni cislo
   *
   * @return true - je zobrazeno, false - neni zobrazeno
   */
  public static boolean isVysledekZobrazen() {
   if(Drivers.getDriver().findElements(By.id("vysledek")).size() != 0)
      return true;
   return false;
  }

  /**
   * Generovani cisla a vraceni vysledku
   * predpoklada se, ze pri generovani nedojde k chybe
   *
   * @return vygenerovane osobni cislo
   */
  public static String generujCisloAVratVysledek() {
    generujCislo();
    return getVysledek();
  }

  /**
   * Nastaveni / shozeni priznaku zahranicniho studenta
   *
   * @param zahr true - nastavit, false - shodit
   */
  public static void setZahranicni(boolean zahr) {
    WebElement w = Drivers.getDriver().findElement(By.id("zahranicni"));
    if(zahr && !w.isSelected())
      w.click();
    else if (!zahr && w.isSelected())
      w.click();
  }

  /**
   * Je zobrazena chyba konkretniho ID
   *
   * @param id ID chyby
   * @return true - chyba je zobrazena, false - neni zobrazena
   */
  public static boolean isZobrazenaChyba(String id) {
    if(Drivers.getDriver().findElements(By.id(id)).size() == 0){
      return false;
    }
    else{
      return true;
    }
  }

  /**
   * Je nastaven priznak zahranicniho studenta
   *
   * @return true - je nastaven, false - neni nastaven
   */
  public static boolean isZahranicni() {
    WebElement w = Drivers.getDriver().findElement(By.id("zahranicni"));
    if(w.isSelected()){
      return true;
    }
    return false;
  }
}
