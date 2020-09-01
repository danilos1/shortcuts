package com.shortcuts.table;

import java.util.NoSuchElementException;

public class Table {
    private String tableTitle;
    private Object[][] data;
    private String[] titles;
    private int row, col;


    public Table(String tableTitle, String[] titles) {
        this.tableTitle = tableTitle;
        this.titles = titles;
        this.col = titles.length;
        data = new Object[15][15];
    }

    public Object get(int rowIdx, int colIdx) {
        if (rowIdx >= row || colIdx >= col) {
            throw new IndexOutOfBoundsException();
        }

        return data[rowIdx][colIdx];
    }

    public Object[] getRow(int rowIdx) {
        if (rowIdx >= row)
            throw new IndexOutOfBoundsException();

        Object[] rows = new Object[col];
        for (int i = 0; i < col; i++) {
            rows[i] = data[rowIdx][i];
        }

        return rows;
    }

    public Object[] getColumn(int colIdx) {
        if (colIdx >= col)
            throw new IndexOutOfBoundsException();

        Object[] column = new Object[row];
        for (int i = 0; i < row; i++) {
            column[i] = data[i][colIdx];
        }

        return column;
    }

    public Object[] getColumn(String title) {
        int colIdx = -1;
        for (int i = 0; i < titles.length; i++) {
            if (title.equalsIgnoreCase(titles[i])) {
                colIdx = i;
                break;
            }
        }

        if (colIdx == -1)
            throw new NoSuchElementException("Cannot find a title '"+title+"'");

        Object[] column = new Object[row];
        for (int i = 0; i < row; i++) {
            column[i] = data[i][colIdx];
        }

        return column;
    }

    public Object get(String title, int pos) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Table: "+tableTitle+"\n");
        int[] columnLengths = new int[col];
        for (int i = 0; i < col; i++) {
            int maxLength = String.valueOf(data[0][i]).length();
            for (int j = 1; j < row; j++) {
                int cur = String.valueOf(data[j][i]).length();
                if (Math.abs(cur) > Math.abs(maxLength)) maxLength = cur;
            }
            columnLengths[i] = maxLength + 1;
        }

        for (int i = 0; i < columnLengths.length; i++) {
            int titleLen = titles[i].length();
            int colLen = columnLengths[i];
            columnLengths[i] = colLen & ((titleLen - colLen) >> 31) | titleLen & (~(titleLen - colLen) >> 31);
        }

        for (int i = 0; i < columnLengths.length; i++) {
            sb.append("|");
            for (int j = 0; j < columnLengths[i] + 2; j++) {
                sb.append("=");
            }
        }
        sb.append("|\n|");
        for (int i = 0; i < columnLengths.length; i++) {
            String format = " %-"+(columnLengths[i]+1)+"s|";
            sb.append(String.format(format, titles[i]));
        }
        sb.append("\n");
        for (int i = 0; i < columnLengths.length; i++) {
            sb.append("|");
            for (int j = 0; j < columnLengths[i] + 2; j++) {
                sb.append("-");
            }
        }
        sb.append("|\n");
        for (int i = 0; i < row; i++) {
            sb.append("| ");
            for (int j = 0; j < col; j++) {
                String format = "%-"+(columnLengths[j])+"s | ";
                sb.append(String.format(format, data[i][j]));
            }
            sb.append("\n");
        }
        for (int i = 0; i < columnLengths.length; i++) {
            sb.append("|");
            for (int j = 0; j < columnLengths[i] + 2; j++) {
                sb.append("=");
            }
        }
        sb.append("|\n");
        return sb.toString();
    }


    public void addRow(Object[] r) {
        if (r.length != col)
            throw new TableSizeException("Number of specified array is not equaled to column dimension. Expected: "
                    +col+", but founded: "+r.length);
        checkSize();

        if (row > 0) {
            for (int i = 0; i < r.length; i++) {
                if (r[i].getClass() != data[row - 1][i].getClass()) {
                    throw new TableTypeException("Invalid column type");
                }
            }
        }

        data[row++] = r;
    }

    private void checkSize() {
        int size = data.length;
        if (row == size || col == size) {
            Object[][] temp = new Object[size + (size >> 1)][size + (size >> 1)];
            for (int i = 0; i < size; i++) {
                System.arraycopy(data[i], 0, temp[i], 0, data[i].length);
            }
            data = temp;
        }
    }

    public void addColumn(Object[] objects) {

    }
}
