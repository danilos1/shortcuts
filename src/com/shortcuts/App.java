package com.shortcuts;

import com.shortcuts.table.Table;

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
                new String[]{"№","NSP", "Rate", "Mob. telephone", "City"}
        );

        for (int i = 0; i < 20; i++) {
            table.addRow(new Object[]{i+1, 5.415-i, new A("Danoon"),5888115, new A("Sfagaghads")});
        }

        Object[] column0 = table.getColumn(2);
        System.out.println(Arrays.toString(column0));

        Object[] column1 = table.getColumn("Rate");
        System.out.println(Arrays.toString(column1));


        // Error: Invalid column type
        //  table.addRow(new Object[]{"1", 5.415-2, new A("Danoon"),new A("Sfagaghads"), new A("Sfagaghads")}); // Error

        System.out.println(table);
    }
}
