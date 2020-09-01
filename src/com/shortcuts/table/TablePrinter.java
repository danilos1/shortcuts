package com.shortcuts.table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <code>TablePrinter</code> is used to print out a {@link Table} instance via a type of files: .md or .txt
 * Using this class you can save and store your table output to a different types of file.
 * @see Table
 */
public class TablePrinter {
    private Table table;

    /**
     * Receives a {@link Table} object.
     * @return a Table object
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets a new {@link Table} object.
     * @param table is a new {@link Table} object
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Constructs a {@link TablePrinter} instance, receives a {@link Table} object.
     * @param table is a {@link Table} object
     */
    public TablePrinter(Table table) {
        this.table = table;
    }

    /**
     * Method for printing out an output of {@link Table} object to a .txt file.
     * @param file specified file for storing table output
     * @return boolean true if <code>file</code> was successfully written, and false elsewhere
     */
    public boolean printToFile(File file) {
        return printToFile(file, false);
    }

    /**
     * An override version of <code>printToFIle</code> method for printing out an output of {@link Table} object
     * to a .txt file. Boolean <code>append</code> parameter is used to overwrite a file with a new table output if its
     * false, and true if you want to add new table output to a previous one.
     * @param file specified file for storing table output
     * @param append is a switcher, if <code>append</code> is true, then table will be printed to a <code>file</code>
     * with old records, and if <code>append</code> is false, then the method overwrites a <code>file</code>with a new
     * table output.
     * @return boolean true if <code>file</code> was successfully written, and false elsewhere
     */
    public boolean printToFile(File file, boolean append) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            fileWriter.write("Table: "+table.getTableTitle()+"\n");
            fileWriter.write("Records: "+table.getRowDimensions()+"\n");
            fileWriter.write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Method for printing out an output of {@link Table} object to a .md file.
     * @param mdFile specified .md file for storing table output
     * @return boolean true if <code>file</code> was successfully written, and false elsewhere
     */
    public boolean printToMarkdownFile(File mdFile) {
        return this.printToMarkdownFile(mdFile, false);
    }


    /**
     * An override version of <code>printToFIle</code> method for printing out an output of {@link Table} object
     * to a .md file. Boolean <code>append</code> parameter is used to overwrite a <code>mdFile</code> with a new table
     * output if its false, and true if you want to add new table output to a previous one.
     * @param mdFile specified .md file for storing table output
     * @param append is a switcher, if <code>append</code> is true, then table will be printed to a <code>mdFile</code>
     * with old records, and if <code>append</code> is false, then the method overwrites a <code>mdFile</code>with a new
     * table output.
     * @return boolean true if <code>mdFile</code> was successfully written, and false elsewhere
     */
    public boolean printToMarkdownFile(File mdFile, boolean append) {
        try (FileWriter fileWriter = new FileWriter(mdFile, append)) {
            StringBuilder sb = new StringBuilder("## "+table.getTableTitle()+" table<hr>\n")
                    .append("**Description: ")
                    .append(table.getDescription())
                    .append("**<br>\n")
                    .append("**Records: ")
                    .append(table.getRowDimensions())
                    .append("**\n\n");

            int row = table.getRowDimensions();
            int col = table.getColumnDimensions();
            int[] columnLengths = new int[col];
            for (int i = 0; i < col; i++) {
                int maxLength = String.valueOf(table.getData()[0][i]).length();
                for (int j = 1; j < row; j++) {
                    int cur = String.valueOf(table.getData()[j][i]).length();
                    if (Math.abs(cur) > Math.abs(maxLength)) maxLength = cur;
                }
                columnLengths[i] = maxLength + 1;
            }

            for (int i = 0; i < columnLengths.length; i++) {
                int titleLen = table.getTitles()[i].length();
                int colLen = columnLengths[i];
                columnLengths[i] = colLen & ((titleLen - colLen) >> 31) | titleLen & (~(titleLen - colLen) >> 31);
            }

            sb.append("\n|");
            for (int i = 0; i < columnLengths.length; i++) {
                String format = " %-"+(columnLengths[i]+1)+"s|";
                sb.append(String.format(format, table.getTitles()[i]));
            }

            sb.append("\n");
            for (int i = 0; i < columnLengths.length; i++) {
                sb.append("|:");
                for (int j = 0; j < columnLengths[i] + 2; j++) {
                    sb.append("-");
                }
            }
            sb.append("|\n");
            for (int i = 0; i < row; i++) {
                sb.append("| ");
                for (int j = 0; j < col; j++) {
                    String format = "%-"+(columnLengths[j])+"s | ";
                    sb.append(String.format(format, table.getData()[i][j]));
                }
                sb.append("\n");
            }
            fileWriter.write(sb.toString());
            fileWriter.write("<hr>File was saved to "+mdFile.getAbsolutePath()+"<br>");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
