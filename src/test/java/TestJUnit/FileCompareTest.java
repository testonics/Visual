package TestJUnit;

import in.testonics.omni.models.OmniFiles;
import org.junit.Test;

import java.io.File;

public class FileCompareTest {

    @Test
    public void main() throws Exception {

        OmniFiles omniFiles = new OmniFiles();
        File file1 = new File("C:\\Users\\nikhi\\Downloads\\img1.png");
        File file2 = new File("C:\\Users\\nikhi\\Downloads\\img2.png");
//        File file1 = new File(".\\src\\test\\resources\\TestData\\Image3.jpg");
//        File file2 = new File(".\\src\\test\\resources\\TestData\\Image1.jpg");
        System.out.println(omniFiles.CompareFiles(file1,file2,0));
    }

}
