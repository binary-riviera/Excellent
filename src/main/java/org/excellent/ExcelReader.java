package org.excellent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;


public class ExcelReader {

    /**
     * Returns all the non-empty cells from a specified Excel .xlsx file.
     *
     * @param filename the filepath of the XLSX file to open
     * @return a map from the cell coordinates to a non-empty Cell object
     * @throws IOException unable to open file
     */
    public HashMap<String, Cell> readFile(String filename) throws IOException {
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

        HashMap<String, Cell> cells = new HashMap<>(); // this is what we'll store the cell values in

        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cells.put(cell.getAddress().toString(), cell);
                // TODO: cell.getCachedFormulaResultType())
                // TODO: cell.getRichStringCellValue()
            }
            System.out.println();
        }
        fIP.close();
        return cells;
    }
}
