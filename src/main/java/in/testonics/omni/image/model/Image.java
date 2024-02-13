package in.testonics.omni.image.model;

import in.testonics.omni.utils.DateUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Image {

    /**
     * Sets Image Resolution. By default, it's 70.
     */
    public static int imageResolution = 70;

    public static String getImageText(String imagePath){

        if(imagePath.equals("")){
            return "Invalid image path : " + imagePath;
        }

        String imageText = "";
        File imageFile = new File(imagePath);
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath(".\\src\\main\\resources\\language"); // replace with your tessdata path
        instance.setTessVariable("user_defined_dpi", String.valueOf(imageResolution)); //sets the resolution

        //Handling Multiple Language
        //instance.setLanguage("fra"); // for French
        //Download the language file
        // https://github.com/tesseract-ocr/tessdata/blob/main/eng.traineddata

        try {
            imageText = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return imageText;
    }


    /**
     * Gets the subimage from an image based on rectangle co-ordinates.
     *
     * @param rectangle : The rectangle objects.
     * @return result {@link BufferedImage} Sub Image extracted based on rectangle
     */
    public static String getSubImage(Rectangle rectangle, BufferedImage image){
        String subImagePath = "";
        int x = rectangle.getMinPoint().x;
        int y = rectangle.getMinPoint().y;
        int w = rectangle.getMaxPoint().x - rectangle.getMinPoint().x;
        int h = rectangle.getMaxPoint().y - rectangle.getMinPoint().y;
        if (w>0 && h>0){
            try{
                Random rand = new Random();
                subImagePath = ".\\target\\" + w + "-" + y + "_" + new DateUtils().getTimeStamp() + rand.nextInt(1000) + ".png";
                BufferedImage subImage = image.getSubimage(x,y,w,h);
                ImageIO.write(subImage, "png", new File(subImagePath));
            }catch (Exception e){
                subImagePath = "";
                System.err.println(e.getMessage());
            }
        }
        return subImagePath;
    }

}
