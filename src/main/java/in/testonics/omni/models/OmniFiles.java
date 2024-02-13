package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OmniFiles extends FileUtils {

    private final List<String> errors = new ArrayList<>();

    //Compares the file
    public List<String> CompareFiles(File file1, File file2, String sheetName) throws Exception {
        String fileExtension = getFileExtension(file1);
        if (!fileExtension.startsWith("xls")){
            errors.add("Files to be compared should be xls and xlsx");
            return errors;
        }

        return new OmniXls().CompareFiles(file1, file2, sheetName);
    }


        //Compares the file
    public List<String> CompareFiles(File file1, File file2, int pageOrSheetNumber) throws Exception {

        String fileExtension = getFileExtension(file1);

        switch (fileExtension) {
            case "pdf":
                return new OmniPdf().CompareFiles(file1, file2, pageOrSheetNumber);
            case "txt":
            case "aspx":
            case "ascx":
            case "json":
                return new OmniTxt().CompareFiles(file1, file2);
            case "docx":
            case "doc":
                return new OmniDoc().CompareFiles(file1, file2, pageOrSheetNumber);
            case "xlsx":
            case "xls":
                return new OmniXls().CompareFiles(file1, file2, pageOrSheetNumber);
            case "csv":
                return new OmniCsv().CompareFiles(file1, file2);
            case "jpg":
            case "png":
            case "jpeg":
                new OmniImage().CompareFiles(file1, file2);
                return null;
            default:
                errors.add(fileExtension + " file is not supported.");
                return errors;
        }
    }

    //Returns the text of the file
    public String getFileText(File file) throws Exception {
        String fileExtension = getFileExtension(file);

        switch (fileExtension) {
            case "pdf":
                return new OmniPdf().getFileText(file);
            case "txt":
            case "aspx":
            case "ascx":
            case "json":
                return new OmniTxt().getFileText(file);
            case "docx":
            case "doc":
                return new OmniDoc().getFileText(file);
            case "csv":
                return new OmniCsv().getFileText(file);
            default:
                return fileExtension + " file is not supported.";
        }

    }
    //Returns the text of the file
    public Map<String,List<String>> getFileTextInMap(File file) throws Exception {
        return getFileTextInMap(file,0);
    }

    public Map<String,List<String>> getFileTextInMap(File file, int sheetNumber) throws Exception {
        String fileExtension = getFileExtension(file);

        switch (fileExtension) {
            case "xlsx":
            case "xls":
                return new OmniXls().getFileTextInMap(file);
            case "csv":
                return new OmniCsv().getFileTextInMap(file);
            default:
                return null;
        }
    }
}
