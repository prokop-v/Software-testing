package oks09.support.tools;

import oks09.support.basic.Const;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;

import static oks09.support.basic.Const.CHROME_DRIVER_URI;

public class Drivers {
  public enum Browsers {
    CHROME, CHROME_HEADLESS, OPERA, MOZILLA, HTML_UNIT;
  }

  private static WebDriver driver;

  public static WebDriver getDriver() {
    return driver;
  }

  public static WebDriver setChromeDriver() {
    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_URI);
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--disable-features=HttpsUpgrades");
    return new ChromeDriver(options);
  }

  public static WebDriver setChromeDriverHeadless() {
    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_URI);
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    return new ChromeDriver(chromeOptions);
  }

  public static WebDriver setMozillaDriver() {
    System.setProperty("webdriver.gecko.driver", Const.MOZILLA_DRIVER_URI);
    return new FirefoxDriver();
  }

  public static WebDriver setOperaDriver() {
    System.setProperty("webdriver.opera.driver", Const.OPERA_DRIVER_URI);
    OperaOptions options = new OperaOptions();
    options.setBinary(new File(Const.OPERA_BINARY_URI));
    return new OperaDriver(options);
  }

  public static WebDriver setHtmlUnitDriver() {
    return new HtmlUnitDriver();
  }

  public static void setWebDriver() {
    switch (Const.WEB_BROWSER_TYPE) {
      case CHROME :
        driver = Drivers.setChromeDriver();
        break;

      case CHROME_HEADLESS:
        driver = Drivers.setChromeDriverHeadless();
        break;

      case OPERA :
        driver = Drivers.setOperaDriver();
        break;

      case MOZILLA :
        driver = Drivers.setMozillaDriver();
        break;

      case HTML_UNIT :
        driver = Drivers.setHtmlUnitDriver();
        break;

      default:
        throw new UnsupportedOperationException("No such browser!");
    }
  }
}
