package oks09.support.pageobjects;

import oks09.support.basic.Const;
import oks09.support.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ZalozkyPage extends PageObject {
  public static final String UVOD_LT = "Úvod";
  public static final String GENEROVANI_LT = "Generování";
  public static final String NAPOVEDA_LT = "Nápověda";

////////
  @FindBy(linkText=GENEROVANI_LT)
  private List<WebElement> generovani;

  /**
   * Prejde na stranku Generovani
   */
  public GenerovaniPage clickZalozkaGenerovani(){
    Utils.clickAndWaitURL(getElement(generovani, GENEROVANI_LT), Const.URL_GENEROVANI);
    return new GenerovaniPage();
  }

}
