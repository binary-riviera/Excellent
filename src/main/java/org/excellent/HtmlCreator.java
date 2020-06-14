package org.excellent;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
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
        writeHTMLFile(foo, "zumba.html", true);
    }

    /**
     * Converts a Hashmap of Cells to a 2d array of strings.
     *
     * @param cells the grid of Cells to convert
     * @return a 2d array of strings, each one the text contents of the cell
     */
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

    /**
     * Prints the cell grid out
     *
     * @param grid the cell content strings to print
     */
    private void printGrid(String[][] grid) {
        for (String[] strings : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("'" + strings[j] + "'\t\t");
            }
            System.out.println();
        }
    }


    /**
     * Converts a grid of cells to a HTML table
     *
     * @param grid the grid of cell contents to convert
     * @return the generated HTML, represented as a String
     */
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


    /**
     * Writes a HTML string to a specified file
     *
     * @param html      the HTML string to write
     * @param filename  the file to write to
     * @param openAfter whether to open a browser with the file afterwards
     */
    public void writeHTMLFile(String html, String filename, Boolean openAfter) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(html);

            // open file in browser
            if (openAfter == true && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(Paths.get(filename).toAbsolutePath().toUri());
            }
        } catch (FileNotFoundException e) {
            System.out.println("A file error existed");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO error existed");
            e.printStackTrace();
        }
    }
}