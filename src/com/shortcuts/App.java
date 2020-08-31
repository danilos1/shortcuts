package com.shortcuts;

import com.shortcuts.table.Table;

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
            table.addRow(new Object[]{i+1, 5.415-i, new A("Danoon"),new A("Sfagaghads"), new A("Sfagaghads")});
        }

        // Error: Invalid column type
        //  table.addRow(new Object[]{"1", 5.415-2, new A("Danoon"),new A("Sfagaghads"), new A("Sfagaghads")}); // Error

        System.out.println(table);
    }
}
