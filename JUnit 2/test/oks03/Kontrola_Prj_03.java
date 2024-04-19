package oks03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class Kontrola_Prj_03 {
  static {
    System.setProperty("log4j.configurationFile",
            "oks-03-konfigurace.xml");
    System.setProperty("datovy.zdroj.oks03",
            "priklady-oks-03.txt");
  }

  /** log4j logger */
  public static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
               selectClass(oks03.OsobniCisloTest.class)
            )
            .build();
    Launcher launcher = LauncherFactory.create();
    launcher.registerTestExecutionListeners(new LogovaniVysledku());
    launcher.execute(request);
  }
}
