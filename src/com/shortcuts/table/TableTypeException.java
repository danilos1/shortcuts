package com.shortcuts.table;

/**
 * <code>TableTypeException</code> is used to indicate if user have problems with column types definition.
 * @see Table#addRow(Object[])
 * @see Table#addColumn(Object[])
 */
public class TableTypeException extends RuntimeException{
    public TableTypeException(String message) {
        super(message);
    }
}
