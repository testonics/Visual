package report;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Logger {
    ExtentTest logger;

    public void pass(String message){
        logger.log(LogStatus.PASS, message);
        System.out.println(message);
    }

    public void fail(String message){
        logger.log(LogStatus.FAIL, message);
        System.out.println(message);
    }

    public void skip(String message){
        logger.log(LogStatus.SKIP, message);
        System.out.println(message);
    }

    public void info(String message){
        logger.log(LogStatus.INFO, message);
        System.out.println(message);
    }

    public void setLogger(ExtentTest logger) {
        this.logger = logger;
    }

    public ExtentTest getLogger() {
        return logger;
    }
}
