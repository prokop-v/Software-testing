package oks08.support;

/**
 * Vyhodnoceni prubehu testu spoustenych Runnerem
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

public class Listener implements TestExecutionListener {
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
      System.out.println("Start: " + testIdentifier.getDisplayName());
    }
  }

  @Override
  public void executionSkipped(TestIdentifier testIdentifier, String reason) {
    System.out.println("Skip: " + testIdentifier.getDisplayName()
            + ": " + reason);
  }

  @Override
  public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
    if (testIdentifier. isTest() == true) {
      System.out.print("End: " + testIdentifier.getDisplayName());
      switch (testExecutionResult.getStatus()) {
        case SUCCESSFUL:
          prosloTestu++;
          System.out.println(" -> OK");
          break;

        case FAILED:
          selhaloTestu++;
          System.out.println(" -> FAILED: "
                  + testExecutionResult.getThrowable().get().getMessage());
          break;

        case ABORTED:
          prerusenoTestu++;
          System.out.println(" -> ABORTED: "
                  + testExecutionResult.getThrowable().get().getMessage());
          break;
      }
    }
  }
}