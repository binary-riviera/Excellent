package org.excellent;

import org.excellent.ExcelReader;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World");
        ExcelReader reader = new ExcelReader();
        reader.readFile("static_files/Example.xlsx");
    }
}
