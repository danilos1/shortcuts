package com.shortcuts.table;

import java.util.NoSuchElementException;

/**
 * <code>Table</code> class is used as a data structure. It's kinda of plain database with
 * <i>CRUD</i> functionality. It allows you to store, add, insert, etc. rows and columns. Each <code>Table</code>
 * instance has table title, description and two-dimensional data array. Also, you can print out each table in a
 * good-looking form. Type of a column is defined when you add the first row, so the sequential types will be defined as
 * a previous ones.
 */
public class Table {
    private String tableTitle;
    private Object[][] data;
    private String[] titles;
    private String description;
    private int row, col;

    /**
     * Receives a brief description of a table.
     * @return description of a table
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes an old table description with a new <code>description</code>.
     * @param description new description for a table
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Receives all table data as two-dimensional array (column titles are not included to).
     * @return two-dimensional array underlying of a table
     */
    public Object[][] getData() {
        return data;
    }


    /**
     * Receives a quantity of table rows.
     * @return number of table rows
     */
    public int getRowDimensions() {
        return row;
    }


    /**
     * Receives a quantity of table columns.
     * @return number of table columns
     */
    public int getColumnDimensions() {
        return col;
    }


    /**
     * Constructs a new {@link Table} instance, receives a title(<code>tableTitle</code>) and <code>description</code>
     * of a table and an array of column titles as {@link String} objects. The initial capacity of data array 15 by 15,
     * next size will increase or decrease, according to adding or removing rows or columns.
     * @param tableTitle is a title of new table
     * @param description is a short description of a table
     * @param titles is an array of columns titles, also it's defined an initial number of columns
     */
    public Table(String tableTitle, String description, String[] titles) {
        this.tableTitle = tableTitle;
        this.titles = titles;
        this.description = description;
        this.col = titles.length;
        data = new Object[15][15];
    }

    public Table(String[] titles) {
        this.tableTitle = "No title for the table";
        this.titles = titles;
        this.description = "No description for the table";
        this.col = titles.length;
        data = new Object[15][15];
    }



    /**
     * Receives a table element by <code>rowIdx</code> and <code>colIdx</code> indexes.
     * @param rowIdx it is an index of a row
     * @param colIdx it is an index of a column
     * @return table element by <code>rowIdx</code> and <code>colIdx</code> indexes
     */
    public Object get(int rowIdx, int colIdx) {
        if (rowIdx >= row || colIdx >= col) {
            throw new IndexOutOfBoundsException();
        }

        return data[rowIdx][colIdx];
    }

    /**
     * Receives array of table elements by entire row by <code>rowIdx</code> position.
     * @param rowIdx it is an index of a row array
     * @return array of table elements by entire row by <code>rowIdx</code> position
     */
    public Object[] getRow(int rowIdx) {
        if (rowIdx >= row)
            throw new IndexOutOfBoundsException();

        Object[] rows = new Object[col];
        for (int i = 0; i < col; i++) {
            rows[i] = data[rowIdx][i];
        }

        return rows;
    }


    /**
     * Receives array of table elements by entire column by <code>colIdx</code> position.
     * @param colIdx it is an index of a column array
     * @return array of table elements by entire column by <code>colIdx</code> position
     */
    public Object[] getColumn(int colIdx) {
        if (colIdx >= col)
            throw new IndexOutOfBoundsException();

        Object[] column = new Object[row];
        for (int i = 0; i < row; i++) {
            column[i] = data[i][colIdx];
        }

        return column;
    }


    /**
     * Receives array of table elements by entire column by <code>title</code>.
     * @param title it is name of a column
     * @return array of table elements by entire column by <code>title</code>
     */
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

    /**
     * TODO
     * Receives {@link Table} element, searching by <code>title</code> and <code>pos</code>
     * @param title it's a column title
     * @param pos - position of a row
     * @return table element if it exists, else throws {@link NoSuchElementException} exception.
     * @see NoSuchElementException
     */
    public Object get(String title, int pos) {
        return null;
    }

    /**
     * Receives a name of a table.
     * @return name of table
     */
    public String getTableTitle() {
        return tableTitle;
    }

    /**
     * Receives array of columns titles.
     * @return array of all column titles of a table
     */
    public String[] getTitles() {
        return titles;
    }

    public String print(boolean isShowTitle, boolean isShowDescription, boolean isShowRecords) {
        StringBuilder sb = new StringBuilder().append("\n");

        if (isShowTitle) sb.append(getTableTitle()).append(" table").append('\n');
        if (isShowDescription) sb.append("Description: ").append(description).append("\n");
        if (isShowRecords) sb.append("Records: ").append(row).append("\n");

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

    public String print(boolean isShowAdditionalInfo) {
        return print(isShowAdditionalInfo, isShowAdditionalInfo, isShowAdditionalInfo);
    }

    public String print() {
        return print(true);
    }

    @Override
    public String toString() {
        return print();
    }

    /**
     * Added a new row to a table.
     * @param r array of new row, its length must be equaled to <code>col</code>
     * @throws TableSizeException if number of specified array <code>r</code> doesn't equals to column dimension
     * @throws TableTypeException if <code>r</code> array has elements with defined column types
     * @see TableSizeException
     */
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


    /**
     * Added a new column to a table.
     * @param objects array of new column, its length must be equaled to <code>row</code>
     */
    public void addColumn(Object[] objects) {

    }
}
