package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class OmniDoc extends FileUtils {

    public List<String> CompareFiles(File file1, File file2, int pageNumber) throws Exception {
        System.out.println("Comparing Doc files (" + file1 + "," + file2 + ")");
        setColumnNames();
        String doc1 = getFileText(file1);
        String doc2 = getFileText(file2);
        String[] doc1Lines =  doc1.split("\n");
        String[] doc2Lines =  doc2.split("\n");
        for (int i=0; i<doc1Lines.length;i++) {
            if (!doc1Lines[i].equals(doc2Lines[i])) {
                errors.add("Line# : " + i + "," + doc1Lines[i] + "," + doc2Lines[i]);
            }
        }
        return errors;
    }


    public String getFileText(File file) throws Exception {
        return getFileText(file,0);
    }

    public String getFileText(String file, int pageNumber) throws Exception{
        return getFileText(new File(file),0);
    }

    public String getFileText(File file, int pageNumber) throws Exception{
        // Create a FileInputStream to read the DOC file
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        String text = "";

        if (file.getName().endsWith(".doc")){
            // Create a HWPFDocument object
            HWPFDocument document = new HWPFDocument(fis);
            // Create a WordExtractor to extract text from the document
            WordExtractor extractor = new WordExtractor(document);
            // Get the text from the document
            text = extractor.getText();
        } else {
            // Create a HWPFDocument object
            XWPFDocument  document = new XWPFDocument(fis);
            // Create a WordExtractor to extract text from the document
            XWPFWordExtractor extractor = new XWPFWordExtractor (document);
            // Get the text from the document
            text = extractor.getText();
        }

        // Close the FileInputStream
        fis.close();
        return text;
    }
}
