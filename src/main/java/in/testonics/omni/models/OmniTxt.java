package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class OmniTxt extends FileUtils {

    public List<String> CompareFiles(File file1, File file2) throws Exception {
        setColumnNames();
        System.out.println("Comparing Text files (" + file1 + "," + file2 + ")");
        try (BufferedReader bf1 = Files.newBufferedReader(file1.toPath());
             BufferedReader bf2 = Files.newBufferedReader(file2.toPath())) {

            long lineNumber = 1;
            String line1 = "", line2 = "";
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if (!line1.equals(line2)) {
                    errors.add("Line# : " + lineNumber + "," + line1 + "," + line2);
                }
                lineNumber++;
            }
        }
        return errors;
    }

    public String getFileText(File file) throws Exception{
        try (BufferedReader bf = Files.newBufferedReader(file.toPath())) {
            String text = "";
            String line = "";
            while ((line = bf.readLine()) != null) {
                text = text + line + "\n";
            }
            return text;
        }
    }
}
