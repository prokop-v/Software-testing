package oks09.support.main;

import oks09.support.basic.Const;
import oks09.support.pageobjects.GenerovaniPage;
import oks09.support.pageobjects.ZalozkyPage;
import oks09.support.tools.Drivers;
import oks09.support.utils.Utils;

public class Hlavni {
  public static void main(String[] args) {
    Drivers.setWebDriver();
    Utils.openUrlAndWait(Const.URL_GENEROVANI);
    GenerovaniPage generovaniPage = new GenerovaniPage();
    ZalozkyPage zalozkyPage = generovaniPage.zalozky;
    generovaniPage = zalozkyPage.clickZalozkaGenerovani();
    generovaniPage.setForma("P");
    generovaniPage.setRokNastupu("20");
    generovaniPage.setTyp("b");
    generovaniPage.setFakulta("FAV");
    generovaniPage.setPoradi("1000");
    generovaniPage.setZahranicni("");
    generovaniPage = generovaniPage.clickGenerovani();
//    Drivers.getDriver().quit();
  }
}
