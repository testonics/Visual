package in.testonics.omni.models;

import in.testonics.omni.image.VisualComparison;
import in.testonics.omni.image.VisualComparisonUtil;
import in.testonics.omni.image.model.Image;
import in.testonics.omni.image.model.VisualComparisonResult;

import java.awt.image.BufferedImage;
import java.io.File;

public class OmniImage {

    public boolean compare(File Image1, File Image2) throws Exception {
        compare(Image1.getAbsolutePath(), Image2.getAbsolutePath());
        return false;
    }

    public void compare(File Image1, File Image2, File resultImage) throws Exception {
        compare(Image1.getAbsolutePath(), Image2.getAbsolutePath(), resultImage.getAbsolutePath());
    }

    public void compare(String Image1, String Image2) throws Exception {
        compare(Image1, Image2,".\\target\\results.jpg");
    }

    public void compare(String Image1, String Image2, String resultFilePath) throws Exception {
        //load images to be compared:
        BufferedImage expectedImage = VisualComparisonUtil.readImageFromResources(Image1);
        BufferedImage actualImage = VisualComparisonUtil.readImageFromResources(Image2);
        // where to save the result (leave null if you want to see the result in the UI)
        File resultDestination = new File(resultFilePath);
        //Create ImageComparison object with result destination and compare the images.
        VisualComparisonResult visualComparisonResult = new VisualComparison(expectedImage, actualImage).setResizeImage(false).compareImages();
        System.out.println("Percentile Mismatch : " + visualComparisonResult.getDifferencePercent());
        System.out.println("Textual Mismatch : " + visualComparisonResult.getMismatch());
        //Image can be saved after comparison, using ImageComparisonUtil.
        VisualComparisonUtil.saveImage(resultDestination, visualComparisonResult.getResult());

    }
}
