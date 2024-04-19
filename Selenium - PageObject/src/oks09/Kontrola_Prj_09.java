package oks09;

/**
 * Spousteni testu a jejich vyhodnoceni pres Listener
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

import apisquash.ConnectToSquash;
import apisquash.SquashTestExecutionListener;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class Kontrola_Prj_09 {

  public static final String VYSTUPNI_SOUBOR = "vysledky-testu-09.txt";
  private static PrintWriter pw;

  public static void main(String[] args) {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
               selectPackage("oks09.tests")
             )
            .build();
    Launcher launcher = LauncherFactory.create();

    TestExecutionListener listener = new SledovaniPrubehu();
    launcher.registerTestExecutionListeners(listener);

    //  vysledky do Squash
//    ConnectToSquash connectToSquash = new ConnectToSquash();
//    connectToSquash.init("OKS-07");
//    TestExecutionListener listenerSquash = new SquashTestExecutionListener();
//    launcher.registerTestExecutionListeners(listenerSquash);

    try {
       pw = new PrintWriter(
              new FileWriter(
                      new File(VYSTUPNI_SOUBOR)));

      launcher.execute(request);

      pw.println(SledovaniPrubehu.zaverecneHlaseni());
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class SledovaniPrubehu implements TestExecutionListener {
    private static int celkemTestu = 0;
    private static int prosloTestu = 0;
    private static int selhaloTestu = 0;
    private static int prerusenoTestu = 0;

    public static String zaverecneHlaseni() {
      return "Celkem spusteno: " + celkemTestu + "\n" +
             "Proslo:          " + prosloTestu + "\n" +
             "Selhalo:         " + selhaloTestu + "\n" +
             "Preruseno:       " + prerusenoTestu;
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
      if (testIdentifier.isTest() == true) {
        celkemTestu++;
//        pw.println("Start: " + testIdentifier.getDisplayName());
//        System.out.println("Start: " + testIdentifier.getDisplayName());
      }
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
      pw.println("Skip: " + testIdentifier.getDisplayName()
              + ": " + reason);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
      if (testIdentifier.isTest() == true) {
        pw.print(testIdentifier.getDisplayName() + "  ");
        switch (testExecutionResult.getStatus()) {
          case SUCCESSFUL:
            prosloTestu++;
            pw.println(" -> OK");
            break;

          case FAILED:
            selhaloTestu++;
            pw.println(" -> FAILED: "
                    + testExecutionResult.getThrowable().get().getMessage());
            break;

          case ABORTED:
            prerusenoTestu++;
            pw.println(" -> ABORTED: "
                    + testExecutionResult.getThrowable().get().getMessage());
            break;
        }
      }
    }
  }
}
