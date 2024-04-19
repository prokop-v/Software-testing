package oks09.support.basic;

import oks09.support.tools.Drivers;

/**
 * Utility class
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

public class Const {
  public static final String BASE_URL = "http://oks.kiv.zcu.cz/OsobniCislo/";

  public static final String URL_UVOD = BASE_URL + "Uvod";
  public static final String URL_GENEROVANI = BASE_URL + "Generovani";
  public static final String URL_NAPOVEDA = BASE_URL + "Napoveda";
  
  public static final int TIMEOUT = 5; // sec

  public static final Drivers.Browsers WEB_BROWSER_TYPE = Drivers.Browsers.HTML_UNIT;

  public static final String CHROME_DRIVER_URI =
          "C:/Program Files/Google/Chrome/Application/chromedriver.exe";

  public static final String MOZILLA_DRIVER_URI =
          "C:/Program Files/Java/selenium/geckodriver0-24-0.exe";

  public static final String OPERA_DRIVER_URI =
          "C:/Program Files/Java/selenium/operadriver2-42.exe";
  public static final String OPERA_BINARY_URI =
          "C:/Users/herout/AppData/Local/Programs/Opera/58.0.3135.127/opera.exe";
}
