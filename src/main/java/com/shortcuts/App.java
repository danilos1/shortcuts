package com.shortcuts;

import com.shortcuts.table.Table;
import com.shortcuts.table.TablePrinter;

import java.io.File;
import java.util.Arrays;

/**
 * Hello world!
 *
 */

class A {
    private String name;

    public A(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class App 
{
    public static void main( String[] args )
    {
        Table table = new Table("Employees",
                "Table represents a marketplace with goods and their prices",
                new String[]{"№","Goods", "Price"}
        );

        table.addRow(new Object[]{1, "Korg Minilogue", 546.});
        table.addRow(new Object[]{2, "iPhone 11 128GB", 965.79});
        table.addRow(new Object[]{3, "Canon EOS 77D EF-S 18-135mm", 902.13});


        TablePrinter tablePrinter = new TablePrinter(table);
        tablePrinter.printToMarkdownFile(new File("C:\\Users\\Admin\\IdeaProjects\\shortcuts\\src\\main\\java\\com\\shortcuts\\table\\tableExample.md"));


        System.out.println(table);
    }
}
