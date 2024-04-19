package oks08;

/**
 * Spousteni testu nezavisle na GUI
 * a jejich vyhodnoceni pres Listener
 *
 * @author Pavel Herout
 * @version 2019-04-07
 */

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import oks08.support.Listener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class Kontrola_Prj_08 {
  public static void main(String[] args) {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
               selectPackage("oks08.tests")
             )
            .filters(
//             includeTags(Tags.LINK),
//             includeTags(Tags.PASIVNI),
//             includeTags(Tags.GENEROVANI),
//                    excludeTags(Tags.NEGATIVNI),
//               includeClassNamePatterns(".*T.*")
            )
            .build();
    Launcher launcher = LauncherFactory.create();

    TestExecutionListener listener = new Listener();
    launcher.registerTestExecutionListeners(listener);
    launcher.execute(request);

    System.out.println(Listener.zaverecneHlaseni());
  }
}
