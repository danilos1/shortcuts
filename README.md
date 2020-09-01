# ShortcutUtils

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/danilos1/shortcuts/blob/master/LICENSE)

## An expandable library of delicious and convenient shortcuts utils, making development more easier and good-looking!

There are some of library features:

- ```table``` package is used for representing some table of data. It allows you to add, *insert*, *remove* rows and columns, it's kinda of small database in a pretty output.
    For example, let's create a simple table, representing a merketplace with goods and their prices:
    ```
     Table table = new Table("Employees",
                    "Table represents a marketplace with goods and their prices",
                    new String[]{"№","Goods", "Price"}
     );
    
     table.addRow(new Object[]{1, "Korg Minilogue", 546.});
     table.addRow(new Object[]{2, "iPhone 11 128GB", 965.79});
     table.addRow(new Object[]{3, "Canon EOS 77D EF-S 18-135mm", 902.13});
  
     System.out.println(table);
    ```
    The following output:
    
    ```
    Employees table
    Description: Table represents a marketplace with goods and their prices
    Records: 3
    |====|==============================|=========|
    | №  | Goods                        | Price   |
    |----|------------------------------|---------|
    | 1  | Korg Minilogue               | 546.0   | 
    | 2  | iPhone 11 128GB              | 965.79  | 
    | 3  | Canon EOS 77D EF-S 18-135mm  | 902.13  | 
    |====|==============================|=========|
   ```

# License
Code is under [MIT License](https://github.com/danilos1/shortcuts/blob/master/LICENSE)
