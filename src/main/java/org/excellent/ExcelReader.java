package org.excellent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class ExcelReader {

    public void readFile(String filename) throws IOException {
        System.out.println(filename);
        File file = new File(filename);
        FileInputStream fIP = new FileInputStream(file);

        // Get the workbook instance for XLSX file
        XSSFWorkbook workbook = new XSSFWorkbook(fIP);

        if (file.isFile() && file.exists()) {
            System.out.println("Excel file opened successfully");
        } else {
            System.out.println("Error opening file");
        }

        // now we have the workbook open, let's get just the first sheet
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();
        XSSFRow row;

        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;

                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println();
        }
        fIP.close();
    }
}
