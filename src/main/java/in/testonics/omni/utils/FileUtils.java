package in.testonics.omni.utils;

import java.io.File;
import java.util.*;

public class FileUtils {

    public List<String> errors = new ArrayList<>();
    public String supportedFiles = "pdf,doc,docx,xls,xlsx,txt,csv,aspx,ascx,json,jpg,png,jpeg";

    public void setColumnNames(){
        this.errors.add("Mismatch,Expected,Actual");
    }

    public void CompareFiles(String fileOrFolderPath1, String fileOrFolderPath2) throws Exception {
        CompareFiles(fileOrFolderPath1,fileOrFolderPath2,0);
    }

    //Compares the files or all the files in 2 folders provided the file with the same names are present
    public List<String> CompareFiles(String fileOrFolderPath1, String fileOrFolderPath2, int pageOrSheetNumber) throws Exception {
        File file1 = new File(fileOrFolderPath1);
        File file2 = new File(fileOrFolderPath2);

        if (file1.isDirectory() && file2.isDirectory()) {
            File[] files = file1.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile()) {
                    if (!supportedFiles.contains(getFileExtension(file1))){
                        errors.add(getFileExtension(file1) + " comparison is ignored as not supported");
                    }else{
                        String fileName = file.getName();
                        System.out.println("Comparison started for the file : " + fileName);
                        errors = CompareFiles(file, new File(fileOrFolderPath2 + "//" + fileName),pageOrSheetNumber);
                    }
                }
            }
            return errors;
        }
        if (supportedFiles.contains(getFileExtension(file1)))
            return CompareFiles(file1, file2, pageOrSheetNumber);
        else{
            errors.add(getFileExtension(file1) + " comparison is ignored as not supported");
            return errors;
        }

    }

    public List<String> CompareFiles(File file1, File file2) throws Exception {
        return CompareFiles(file1,file2,0);
    }

    public List<String> CompareFiles(File file1, File file2, int pageOrSheetNumber) throws Exception {
            return null;
    }

    public String getFileText(String absoluteFilePath) throws Exception {
        return getFileText(new File(absoluteFilePath));
    }

    public String getFileText(File file) throws Exception {
        return "";
    }

    public Map<String,List<String>> getFileTextInMap(String absoluteFilePath) throws Exception {
        return getFileTextInMap(new File(absoluteFilePath));
    }

    public Map<String,List<String>> getFileTextInMap(File file) throws Exception {
        return new HashMap<>();
    }

    public void compareHashMap(Map<String,List<String>> map1, Map<String,List<String>> map2){
        setColumnNames();
        Set<String> headers = map1.keySet();
        for(String header : headers){
            int rowsCount = map1.get(header).size();
            for (int i=0;i<rowsCount;i++){
                if (!map1.get(header).get(i).equals(map2.get(header).get(i))){
                    errors.add("Column : " + header + " | Row : " + i + "," + map1.get(header).get(i) + "," + map2.get(header).get(i));
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
