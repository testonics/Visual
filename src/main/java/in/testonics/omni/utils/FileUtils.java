package in.testonics.omni.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileUtils {

    public static Map<String,List<String>> ConvertCsvToMap(String file) throws IOException{
        return ConvertCsvToMap(new File(file));
    }

    public static Map<String,List<String>> ConvertCsvToMap(File file) throws IOException {
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



    public void compareHashMap(Map<String,List<String>> map1, Map<String,List<String>> map2){
        Set<String> headers = map1.keySet();
        for(String header : headers){
            int rowsCount = map1.get(header).size();
            for (int i=0;i<rowsCount;i++){
                if (!map1.get(header).get(i).equals(map2.get(header).get(i))){
                    System.out.println("Column : " + header + " | Row : " + i + "," + map1.get(header).get(i) + "," + map2.get(header).get(i));
                }
            }
        }
    }

    //Returns the extension of the file
    public String getFileExtension(File file){
        String[] setOfFile = file.getName().split("\\.");
        return file.getName().split("\\.")[setOfFile.length-1];
    }
}
