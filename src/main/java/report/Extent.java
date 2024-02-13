package report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import utils.dateUtils;

import java.io.File;

public class Extent {

    public ExtentReports extent;
    public dateUtils date = new dateUtils();

    public void createReport(){

        System.out.println("Creating Extent Report");

        String timestamp = date.getTimeStamp();
        extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport" + timestamp + ".html", true);
        //extent.addSystemInfo("Environment","Environment Name")
        extent
                .addSystemInfo("Host Name", "Test Automation Framework")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Rest Assured");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
    }

    public void endReport() throws Exception{
        // writing everything to document
        //flush() - to write or update test information to your report.
        extent.flush();
        //Call close() at the very end of your session to clear all resources.
        //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
        //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream.
        //Once this method is called, calling any Extent method will throw an error.
        //close() - To close all the operation
//        extent.close();
    }

    public ExtentTest startTest(String testCaseName){
        return extent.startTest(testCaseName);
    }

    public void endTest(ExtentTest logger){
        extent.endTest(logger);
    }
}
