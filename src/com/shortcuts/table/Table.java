package com.shortcuts.table;

public class Table {
    private String tableTitle;
    private Object[][] rows;
    private String[] titles;
    private int row, col;


    public Table(String tableTitle, String[] titles) {
        this.tableTitle = tableTitle;
        this.titles = titles;
        this.col = titles.length;
        rows = new Object[15][15];
    }

    public Object get(String title, int pos) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Table: "+tableTitle+"\n");
        int[] columnLengths = new int[col];
        for (int i = 0; i < col; i++) {
            int maxLength = String.valueOf(rows[0][i]).length();
            for (int j = 1; j < row; j++) {
                int cur = String.valueOf(rows[j][i]).length();
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
                sb.append(String.format(format, rows[i][j]));
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
                if (r[i].getClass() != rows[row - 1][i].getClass()) {
                    throw new TableTypeException("Invalid column type");
                }
            }
        }

        rows[row++] = r;
    }

    private void checkSize() {
        int size = rows.length;
        if (row == size || col == size) {
            Object[][] temp = new Object[size + (size >> 1)][size + (size >> 1)];
            for (int i = 0; i < size; i++) {
                System.arraycopy(rows[i], 0, temp[i], 0, rows[i].length);
            }
            rows = temp;
        }
    }

    public void addColumn(Object[] objects) {

    }
}
