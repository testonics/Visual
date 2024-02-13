package VisualComaprison;

import in.testonics.omni.models.OmniImage;
import org.junit.Test;

import java.io.File;

public class VisualCompareTest {

    @Test
    public void main() throws Exception {

        OmniImage omniImage = new OmniImage();
        File file1 = new File(".\\src\\test\\resources\\TestData\\ImageActual.jpg");
        File file2 = new File(".\\src\\test\\resources\\TestData\\ImageExpected.jpg");
        System.out.println(omniImage.compare(file1,file2));
    }

}
