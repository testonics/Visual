package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OmniXls extends FileUtils {

    public List<String> CompareFiles(File file1, File file2, String sheetName) throws Exception {
        System.out.println("Comparing XLS files (" + file1 + "," + file2 + ")");
        Map<String,List<String>> xls1 = getFileTextInMap(file1,sheetName);
        Map<String,List<String>> xls2 = getFileTextInMap(file2,sheetName);
        compareHashMap(xls1,xls2);
        return errors;
    }

    public List<String> CompareFiles(File file1, File file2, int sheetNumber) throws Exception {
        System.out.println("Comparing XLS files (" + file1 + "," + file2 + ")");
        Map<String,List<String>> xls1 = getFileTextInMap(file1,sheetNumber);
        Map<String,List<String>> xls2 = getFileTextInMap(file2,sheetNumber);
        compareHashMap(xls1,xls2);
        return errors;
    }

    //Fetches the XLS Text
    public Map<String,List<String>> getFileTextInMap(String file) throws Exception {
        return getFileTextInMap(new File(file));
    }

    public Map<String,List<String>> getFileTextInMap(File file) throws Exception {
        return getFileTextInMap(file,0);
    }

    public Map<String,List<String>> getFileTextInMap(String file, String sheetName) throws Exception{
        return getFileTextInMap(new File(file),sheetName);
    }

    public Map<String,List<String>> getFileTextInMap(String file, int sheetNumber) throws Exception{
        return getFileTextInMap(new File(file),sheetNumber);
    }


    public Map<String,List<String>> getFileTextInMap(File file, String sheetName) throws Exception{
        InputStream fis = Files.newInputStream(Paths.get(file.getAbsolutePath()));
        if (file.getName().endsWith(".xlsx")){
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            return getFileTextInMap(file,wb.getSheetIndex(sheetName),true);
        } else{
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            return getFileTextInMap(file,wb.getSheetIndex(sheetName),true);
        }
    }

    public Map<String,List<String>> getFileTextInMap(File file, int sheetNumber) throws Exception{
        return getFileTextInMap(file,sheetNumber,true);
    }

    public Map<String,List<String>> getFileTextInMap(File file, int sheetNumber, boolean firstRowHeader) throws Exception{

        // Create a FileInputStream to read the XLS file
        InputStream fis = Files.newInputStream(Paths.get(file.getAbsolutePath()));
        Map<String, List<String>> Map = new HashMap<>();
        List<String> headers = new ArrayList<>();
        String cellValue = "";
        if (file.getName().endsWith(".xlsx")){
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(sheetNumber);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            int numberOfCells = sheet.getRow(0).getLastCellNum();
            //Setting up the headers
            for (int cellCount=0;cellCount<numberOfCells;cellCount++){
                String columnName;
                if (firstRowHeader){
                    columnName =  getCellValue(sheet.getRow(0).getCell(cellCount));
                }else{
                    columnName = String.valueOf(cellCount);
                }
                headers.add(columnName);
                Map.put(columnName,new ArrayList<>());
            }

            //Setting the data
            for (int rowCount=1;rowCount<numberOfRows; rowCount++){
                XSSFRow row = sheet.getRow(rowCount);
                for (int cellCount=0;cellCount<numberOfCells;cellCount++){
                    XSSFCell cell   = row.getCell(cellCount);
                    cellValue = getCellValue(cell);
                    Map.get(headers.get(cellCount)).add(cellValue);
                }
            }

        } else {

            //XLS Validations
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(sheetNumber);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            int numberOfCells = sheet.getRow(0).getLastCellNum();
            //Setting up the headers
            for (int cellCount=0;cellCount<numberOfCells;cellCount++){
                String columnName;
                if (firstRowHeader){
                    columnName =  getCellValue(sheet.getRow(0).getCell(cellCount));
                }else{
                    columnName = String.valueOf(cellCount);
                }
                headers.add(columnName);
                Map.put(columnName,new ArrayList<>());
            }

            //Setting the data
            for (int rowCount=1;rowCount<numberOfRows; rowCount++){
                HSSFRow row = sheet.getRow(rowCount);
                for (int cellCount=0;cellCount<numberOfCells;cellCount++){
                    HSSFCell cell   = row.getCell(cellCount);
                    cellValue = getCellValue(cell);
                    Map.get(headers.get(cellCount)).add(cellValue);
                }
            }

        }

        // Close the FileInputStream
        fis.close();
        return Map;
    }

    private String getCellValue(HSSFCell cell){
        if(cell==null) return "";
        CellType type = cell.getCellType();
        if (CellType.STRING == type)
            return cell.getStringCellValue();
        else if (CellType.NUMERIC == type)
            return cell.getNumericCellValue() + "";
        else if (CellType.FORMULA == type)
            return cell.getCellFormula();
        else if (CellType.BOOLEAN == type)
            return cell.getBooleanCellValue() + "";
        else if (CellType.ERROR == type)
            return cell.getErrorCellValue() + "";
        else return "";
    }

    private String getCellValue(XSSFCell cell){
        if(cell==null) return "";
        CellType type = cell.getCellType();
        if (CellType.STRING == type)
            return cell.getStringCellValue();
        else if (CellType.NUMERIC == type)
            return cell.getNumericCellValue() + "";
        else if (CellType.FORMULA == type)
            return cell.getCellFormula();
        else if (CellType.BOOLEAN == type)
            return cell.getBooleanCellValue() + "";
        else if (CellType.ERROR == type)
            return cell.getErrorCellValue() + "";
        else return "";
    }
}
