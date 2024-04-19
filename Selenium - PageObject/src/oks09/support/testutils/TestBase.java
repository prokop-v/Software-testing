package oks09.support.testutils;

/**
 * Zaklad vsech testu
 *
 * Zajistuje jednorazovou inicializaci driveru na pred prvnim testem
 * a jeho ukonceni po skonceni vsech testu
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

import oks09.support.tools.Drivers;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class TestBase implements BeforeAllCallback,
        ExtensionContext.Store.CloseableResource {

  private static boolean started = false;

  /**
   * Jednorazova inicializaci driveru na pred prvnim testem
   * uz se nebude nikdy opakovat
   *
   * @param context parametr, ktery doda Selenium
   */
  @Override
  public void beforeAll(ExtensionContext context) {
    if (!started) {
      started = true;
      context.getRoot().getStore(GLOBAL).put("ahoj", this);
      Drivers.setWebDriver();
//      System.out.println("ZACATEK: " + context.getTestClass().get());
    }
  }

  /**
   * Ukonceni driveru po skonceni vsech testu
   */
  @Override
  public void close() {
    Drivers.getDriver().quit();
    System.out.println("KONEC");
  }
}