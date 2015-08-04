package com.lnet.tms.common;

public class ImportResult {

    private int rowIndex;

    private String importResult;

    private String failReason;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getImportResult() {
        return importResult;
    }

    public void setImportResult(String importResult) {
        this.importResult = importResult;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
