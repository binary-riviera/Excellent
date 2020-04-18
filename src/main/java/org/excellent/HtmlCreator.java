package org.excellent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;

import java.util.ArrayList;
import java.util.HashMap;

public class HtmlCreator {
    HashMap<CellAddress, Cell> cells;

    public HtmlCreator(HashMap<CellAddress, Cell> cells) {
        this.cells = cells;
    }

    private static ArrayList<ArrayList<Cell>> createGrid(HashMap<CellAddress, Cell>) {

    }
}
/*
 * TODO: write a function that takes a hashmap of cells and turns it into a 2d array of cell values, this means we
 *  can feed this easily to j2html.
 *
 */