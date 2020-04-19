package org.excellent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;

import java.util.HashMap;

public class HtmlCreator {
    HashMap<CellAddress, Cell> cells;

    public HtmlCreator(HashMap<CellAddress, Cell> cells) {
        this.cells = cells;

        String[][] grid = createGrid(this.cells);


    }

    private static String[][] createGrid(HashMap<CellAddress, Cell> cells) {
        int maxColumn = 0;
        int maxRow = 0;

        for (CellAddress pos : cells.keySet()) {
            if (pos.getColumn() > maxColumn) {
                maxColumn = pos.getColumn();
            }
            if (pos.getRow() > maxRow) {
                maxRow = pos.getRow();
            }
        }

        String[][] grid = new String[maxRow][maxColumn];

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                if (cells.containsKey(new CellAddress(i, j))) {
                    grid[i][j] = cells.get(new CellAddress(i, j)).toString();
                } else {
                    grid[i][j] = "";
                }
            }
        }

        return grid;
    }

    private void printGrid(String[][] grid) {
        for (String[] strings : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("'" + strings[j] + "'\t\t");
            }
            System.out.println();
        }
    }
}
/*
 * TODO: write a function that takes a hashmap of cells and turns it into a 2d array of cell values, this means we
 *  can feed this easily to j2html.
 *
 */