package org.excellent;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;

import java.util.Arrays;
import java.util.HashMap;

import static j2html.TagCreator.*;

public class HtmlCreator {
    String[][] cells;

    public HtmlCreator(HashMap<CellAddress, Cell> cells) {
        this.cells = createGrid(cells);
        printGrid(this.cells);
        String foo = generateHTML(this.cells);
        System.out.println(foo);
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


    public String generateHTML(String[][] grid) {
        // TODO: add a filename field to put in the title of the html document.
        return html(
                table(attrs("#table-excel"), tbody(
                        Arrays.stream(grid)
                                .map(row ->
                                        tr(Arrays.stream(row).map(TagCreator::td).toArray(ContainerTag[]::new))
                                ).toArray(ContainerTag[]::new)
                ))).render();
    }
}