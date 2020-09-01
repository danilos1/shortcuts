package com.shortcuts.table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TablePrinter {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public TablePrinter(Table table) {
        this.table = table;
    }

    public boolean printToFile(File file) {
        return printToFile(file, false);
    }

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

    public boolean printToMarkdownFile(File mdFile) {
        return this.printToMarkdownFile(mdFile, false);
    }

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
