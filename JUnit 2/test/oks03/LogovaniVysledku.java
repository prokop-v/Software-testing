package oks03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

public class LogovaniVysledku implements TestExecutionListener {

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if(testIdentifier.isTest())
            Kontrola_Prj_03.logger.info(testIdentifier.getDisplayName());
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if(testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED){
            String cut = testExecutionResult.toString();
            String vysledek = cut.substring(cut.indexOf("<"), cut.length()-1);
            Kontrola_Prj_03.logger.error("expected: " + vysledek);
        }
    }
}
