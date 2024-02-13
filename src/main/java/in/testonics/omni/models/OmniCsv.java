package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class OmniCsv extends FileUtils {

    public List<String> CompareFiles(File file1, File file2) throws Exception {
        System.out.println("Comparing CSV files (" + file1 + "," + file2 + ")");
        Map<String,List<String>> csv1 = getFileTextInMap(file1);
        Map<String,List<String>> csv2 = getFileTextInMap(file2);
        compareHashMap(csv1,csv2);
        return errors;
    }

    public Map<String,List<String>> getFileTextInMap(File file) throws Exception{
        Map<String, List<String>> Map = new HashMap<>();
        boolean setHeadersFlag = true;
        int counter = 0;
        try (BufferedReader bf = Files.newBufferedReader(file.toPath())) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] data = line.split(",");
                for (String value: data){
                    if (setHeadersFlag){
                        Map.put(String.valueOf(counter),new ArrayList<>());
                    } else {
                        Map.get(String.valueOf(counter)).add(value);
                    }
                    counter++;
                }
                setHeadersFlag = false;
                counter = 0;
            }
        }
        return Map;
    }
}
