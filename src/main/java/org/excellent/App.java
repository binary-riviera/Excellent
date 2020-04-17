package org.excellent;

import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws IOException {
        out.println("Hello, World");
        ExcelReader reader = new ExcelReader();
        HashMap<String, Cell> cells;
        cells = reader.readFile("static_files/Example.xlsx");
        printCells(cells);
    }

    private static void printCells(HashMap<String, Cell> cells) {
        for (Map.Entry<String, Cell> entry : cells.entrySet()) {
            String k = entry.getKey();
            Cell v = entry.getValue();
            out.println("Key: " + k + " Value: " + v);
        }
    }
}
