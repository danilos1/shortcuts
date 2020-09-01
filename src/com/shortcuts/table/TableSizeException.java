package com.shortcuts.table;

/**
 * <code>TableSizeException</code> is used to indicate if user have problems with row or column indexes definition or
 * definition of row and columns.
 * @see Table#addRow(Object[])
 * @see Table#addColumn(Object[])
 */
public class TableSizeException extends RuntimeException {
    public TableSizeException(String s) {
        super(s);
    }
}
