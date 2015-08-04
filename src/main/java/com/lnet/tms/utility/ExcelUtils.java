package com.lnet.tms.utility;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static  Data readExcel(InputStream file,int sheetIndex) throws IOException {
        HSSFWorkbook workbook = null;
        Data data = new Data();

        try {
            workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            data.rowsNum=sheet.getLastRowNum();
            HSSFRow headRow = sheet.getRow(0);

            // 创建列
            for (int columnIndex = 0; columnIndex < headRow.getLastCellNum(); columnIndex ++){
                Cell cell = headRow.getCell(columnIndex);
                Column column = new Column();
                column.setTitle(cell.getStringCellValue());
                column.setIndex(columnIndex);
                data.getColumns().add(column);
            }

            // 创建行
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                try{
                    HSSFRow row = sheet.getRow(rowIndex);
                    for (Column column : data.getColumns()) {
                        HSSFCell cell = row.getCell((int) column.getIndex());
                        String itemTitle = column.getTitle();
                        Object itemValue = getCellValue(cell);
                        Map<String, Object> item = new HashMap<>();
                        item.put(itemTitle, itemValue);
                        item.put("rowIndex", rowIndex);
                        data.rows.add(item);
                    }
                }
                catch (Exception e){
                    logger.info("", e);
                }
            }

        } catch (Exception e) {
            logger.info("", e);

        } finally {
            if (workbook != null) workbook.close();
        }

        return data;
    }

    private static Object getCellValue(HSSFCell cell) {
        if(cell==null){
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                NumberFormat formatter = NumberFormat.getNumberInstance();
                //设置小数位数
                formatter.setMaximumFractionDigits(2);
                String str=formatter.format(cell.getNumericCellValue());
                StringBuffer sb=new StringBuffer();
                String[]strArr=str.split(",");
                if(strArr!=null&&strArr.length>0){
                    for(String s:strArr){
                        sb.append(s);
                    }
                }
                return sb.toString();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_ERROR:
                return null;
            default:
                return cell.getStringCellValue();
        }
    }

    public static class Column{
        private String title;
        private int index;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public static class Data {
        private List<Column> columns = new ArrayList<>();
        private List<Map<String, Object>> rows = new ArrayList<>();
        private Integer rowsNum;
        public List<Column> getColumns() {
            return columns;
        }
        public List<Map<String, Object>> getRows() {
            return rows;
        }
        public Integer getRowsNum(){return rowsNum;}
    }
}
