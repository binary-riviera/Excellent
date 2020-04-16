package org.excellent;

import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


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

    }
}
