package in.testonics.omni.models;

import in.testonics.omni.image.ImageComparison;
import in.testonics.omni.image.ImageComparisonUtil;
import in.testonics.omni.image.model.ImageComparisonResult;

import java.awt.image.BufferedImage;
import java.io.File;

public class OmniImage {

    public void CompareFiles(File Image1, File Image2) throws Exception {
        CompareFiles(Image1.getAbsolutePath(), Image2.getAbsolutePath());
    }

    public void CompareFiles(File Image1, File Image2, File resultImage) throws Exception {
        CompareFiles(Image1.getAbsolutePath(), Image2.getAbsolutePath(), resultImage.getAbsolutePath());
    }

    public void CompareFiles(String Image1, String Image2) throws Exception {
        CompareFiles(Image1, Image2,".\\target\\results.jpg");
    }

    public void CompareFiles(String Image1, String Image2, String resultFilePath) throws Exception {
        //load images to be compared:
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(Image1);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(Image2);
        // where to save the result (leave null if you want to see the result in the UI)
        File resultDestination = new File(resultFilePath);
        //Create ImageComparison object with result destination and compare the images.
        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages(true);
        System.out.println("Percentile Mismatch : " + imageComparisonResult.getDifferencePercent());
        //Image can be saved after comparison, using ImageComparisonUtil.
        ImageComparisonUtil.saveImage(resultDestination, imageComparisonResult.getResult());
    }
}
