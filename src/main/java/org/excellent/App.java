package org.excellent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws IOException {
        out.println("Hello, World");
        ExcelReader reader = new ExcelReader();
        HashMap<CellAddress, Cell> cells;
        cells = reader.readFile("static_files/Example.xlsx");
        printCells(cells);

        HtmlCreator h = new HtmlCreator(cells);

    }

    /**
     * Prints the coordinates and values of the specified cells Hashmap.
     *
     * @param cells the cells hashmap to print
     */
    private static void printCells(HashMap<CellAddress, Cell> cells) {
        for (Map.Entry<CellAddress, Cell> entry : cells.entrySet()) {
            CellAddress k = entry.getKey();
            Cell v = entry.getValue();
            out.println("Key: " + k + " Value: " + v);
        }
    }

}
