package utils;

import ar.com.teceng.excelReader.ExcelEntity;
import ar.com.teceng.excelReader.ExcelReader;
import ar.com.teceng.excelReader.impl.ExcelFactoryMethod;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
//import org.apache.poi.ss.usermodel.Row;

public class Excel {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    private static MissingCellPolicy xRow;

    public static ExcelReader excelReader;


    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

    public static void setExcelFile(String Path,String SheetName) throws Exception {

        try {

            // Abre el archivo Excel

            FileInputStream ExcelFile = new FileInputStream(Path);

            // Acceso al sheet del test data

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

        } catch (Exception e){

            throw (e);

        }

    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum){

        try{

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            String CellData = Cell.getStringCellValue();

            Log.doLogging("Consultando datos en "+Constantes.File_TestData);

            return CellData;

        }catch (Exception e){

            return"";

        }

    }

    //This method is to write in the Excel cell, Row num and Col num are the parameters

    public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

        try{

            Row  = ExcelWSheet.getRow(RowNum);

            Cell = Row.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
            //Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

            if (Cell == null) {

                Cell = Row.createCell(ColNum);

                Cell.setCellValue(Result);

            } else {

                Cell.setCellValue(Result);

            }

            // Constantes variables Test Data path and Test Data file name

            FileOutputStream fileOut = new FileOutputStream(Constantes.Path_TestData + Constantes.File_TestData);

            ExcelWBook.write(fileOut);

            fileOut.flush();

            fileOut.close();

        }catch(Exception e){

            throw (e);

        }

    }

    public void abrirExcel(String nombre_del_archivo) throws Exception {

        ExcelFactoryMethod factory = new ExcelFactoryMethod();
        excelReader = factory.createExcelReader(Constantes.File_TestData, Constantes.Path_TestData, nombre_del_archivo);
        Object entity = Class.forName("test.creationals.entities." + nombre_del_archivo).newInstance();
        excelReader.parseTable((ExcelEntity) entity);
        Log.doLogging("ingresando al Excel: "+nombre_del_archivo+".xlsx para consultar los datos para los test");
    }

}