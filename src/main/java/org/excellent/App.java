package org.excellent;

import org.excellent.ExcelReader;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World");
        ExcelReader reader = new ExcelReader();
        reader.readFile("Foo");
    }
}
