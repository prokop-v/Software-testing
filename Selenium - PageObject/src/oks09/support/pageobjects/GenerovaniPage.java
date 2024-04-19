package oks09.support.pageobjects;

import oks09.support.basic.Const;
import oks09.support.tools.Drivers;
import oks09.support.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class GenerovaniPage extends PageObject {
  public static final String LABEL_FAKULTA = "label_fakulta";
  public static final String SELECT_FAKULTA = "fakulta";
  public static final String CHYBA_FAKULTA = "chyba_fakulta";

  public static final String LABEL_ROK = "label_rok_nastupu";
  public static final String INPUT_ROK = "rok_nastupu";
  public static final String CHYBA_ROK = "chyba_rok_nastupu";

  public static final String LABEL_TYP = "label_typ_studia";
  public static final String SELECT_TYP = "typ_studia";

  public static final String LABEL_PORADI = "label_poradove_cislo";
  public static final String INPUT_PORADI = "poradove_cislo";
  public static final String CHYBA_PORADI = "chyba_poradove_cislo";

  public static final String LABEL_FORMA = "label_forma_studia";
  public static final String RADBTN_FORM_PREZ = "forma_prezencni";
  public static final String LABEL_FORM_PREZ = "label_forma_prezencni";
  public static final String RADBTN_FORM_KOMB = "forma_kombinovana";
  public static final String LABEL_FORM_KOMB = "label_forma_kombinovana";
  public static final String RADBTN_FORM_DIST = "forma_distancni";
  public static final String LABEL_FORM_DIST = "label_forma_distancni";
  public static final String CHYBA_FORMA = "chyba_forma_studia";

  public static final String LABEL_ZAHR = "label_zahranicni";
  public static final String CHKBOX_ZAHR = "zahranicni";

  public static final String BUTTON_GENEROVANI = "generovani";
  public static final String BUTTON_MAZANI = "mazani";

  public static final String TEXT_VYSLEDEK = "vysledek";

  public static final String CHYBA_CELKOVA = "chyba_celkova";

  public ZalozkyPage zalozky = new ZalozkyPage();

  /////////////////////////
  @FindBy(id=INPUT_ROK)
  private List<WebElement> inputRok;

  public void setRokNastupu(String rok) {
    WebElement vstup = getElement(inputRok, INPUT_ROK);
    vstup.clear();
    vstup.sendKeys(rok);
  }

  //////////////
  @FindBy(id=BUTTON_GENEROVANI)
  private List<WebElement> btnGenerovani;

  public GenerovaniPage clickGenerovani() {
    Utils.clickAndWaitURL(getElement(btnGenerovani, BUTTON_GENEROVANI), Const.URL_GENEROVANI);
    return new GenerovaniPage();
  }

  ///////////////////

  @FindBy(id=SELECT_FAKULTA)
  private List<WebElement> fakulta;

  public void setFakulta(String zkratkaFakulty) {
    WebElement seznam = getElement(fakulta, LABEL_FAKULTA);
    List<WebElement> vsechnyVolby = seznam.findElements(By.tagName("option"));
    for (WebElement volba : vsechnyVolby) {
      if (volba.getAttribute("value").equals(zkratkaFakulty)) {
        volba.click();
        break;
      }
    }
    }

  ///////////////////////////

  @FindBy(id=SELECT_TYP)
  private List<WebElement> typStudia;
  public void setTyp(String typ) {
    if(typ.equals("d"))
      typ = "p";
    WebElement seznam = getElement(typStudia, SELECT_TYP);
    List<WebElement> vsechnyVolby = seznam.findElements(By.tagName("option"));
    for (WebElement volba : vsechnyVolby) {
      if (volba.getAttribute("value").toLowerCase().equals(typ)) {
        volba.click();
        break;
      }
    }
  }

  /////////////////////////

  @FindBy(id=INPUT_PORADI)
  private List<WebElement> inputPoradi;
  public void setPoradi(String poradi) {
    WebElement vstup = getElement(inputPoradi, INPUT_PORADI);
    vstup.clear();
    vstup.sendKeys(poradi);
  }

  ///////////////////////////

  @FindBy(id = "forma_prezencni")
  private WebElement radioButtonPrezencni;

  @FindBy(id = "forma_kombinovana")
  private WebElement radioButtonKombinovana;

  @FindBy(id = "forma_distancni")
  private WebElement radioButtonDistancni;

  // Constructor and WebDriver initialization if needed

  public void setForma(String formaStudia) {
    WebElement radioButton = null;
    switch (formaStudia.toUpperCase()) {
      case "P":
        radioButton = radioButtonPrezencni;
        break;
      case "K":
        radioButton = radioButtonKombinovana;
        break;
      case "D":
        radioButton = radioButtonDistancni;
        break;
    }
    if (radioButton != null) {
      radioButton.click();
    } else {
      System.out.println("Forma studia not found: " + formaStudia);
    }
  }

  /////////////////////////

  @FindBy(id=TEXT_VYSLEDEK)
  private List<WebElement> vysledek;
  public String getGenerovaneCislo() {
    WebElement we = getElement(vysledek, TEXT_VYSLEDEK);
    return we.getText();
  }

  /////////////////////////
  @FindBy(id=CHKBOX_ZAHR)
  private List<WebElement> zahranicni;

  public void setZahranicni(String zahr) {
    WebElement w = getElement(zahranicni, CHKBOX_ZAHR);
    if(!zahr.isEmpty() && ! w.isSelected())
      w.click();
    else if (zahr.isEmpty() && w.isSelected())
      w.click();
  }
}
