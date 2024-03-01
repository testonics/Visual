package VisualComaprison;

import in.testonics.omni.image.VisualComparison;
import in.testonics.omni.image.model.VisualComparisonResult;
import org.junit.Test;

import java.io.File;

public class VisualCompareTest {

    @Test
    public void main() {
        //Sets the object of Visual Comparison
        VisualComparison visualComparison = new VisualComparison();

        //Optional : If not set, result file will be saved at root location with the name "results.png"
        visualComparison.setDestination(new File(".\\target\\results.png"));
        visualComparison.setResizeImage(true);

        //Set below parameters if textual comparison is required
        visualComparison.setExtractImageFlag(true);
        //Download the language file from the link and set the folder path
        visualComparison.setLanguagePath(".\\src\\main\\resources\\language");
        visualComparison.setPrintCoordinates(false);
        visualComparison.setCoordinatesExcludeFilePath(".\\src\\test\\resources\\TestData\\CoordinatesToExclude.csv");

        File file1 = new File(".\\src\\test\\resources\\TestData\\ImageExpected.png");
        File file2 = new File(".\\src\\test\\resources\\TestData\\ImageActual.png");
        VisualComparisonResult visualComparisonResult = visualComparison.compareImages(file1, file2);

        //Print the results as needed
        System.out.println("Percentile Mismatch : " + visualComparisonResult.getDifferencePercent());
        System.out.println("Textual Mismatch : " + visualComparisonResult.getMismatch());
    }

}
