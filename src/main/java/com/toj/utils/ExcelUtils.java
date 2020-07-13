package com.toj.utils;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

public class ExcelUtils {
    public void exportExcel(XSSFWorkbook workbook, int sheetNum,
                            String sheetTitle, String[] headers, Map<Integer, Map<Integer, Object>> result,
                            OutputStream out) throws Exception {
        // creat table
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // default width
        sheet.setDefaultColumnWidth((short) 20);
        // creat style
        XSSFCellStyle style = workbook.createCellStyle();
        // set style
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // creat font
        XSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.AUTOMATIC.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // use font
        style.setFont(font);

        // wrap
        style.setWrapText(true);

        // creat cell style
        XSSFCellStyle stylecell = workbook.createCellStyle();
        // set style
        stylecell.setAlignment(HorizontalAlignment.CENTER);
        stylecell.setVerticalAlignment(VerticalAlignment.CENTER);

        // wrap
        stylecell.setWrapText(true);

        // creat title row
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);

            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }


        // insert data
        Iterator<Integer> rowIterator = result.keySet().iterator();
        Iterator<Integer> cellIterator;
        int rowNum;
        int cellNum;
        int index = 1;
        Object cellValue;
        Map<Integer, Object> cellMap;
        while(rowIterator.hasNext()){
            row = sheet.createRow(index);
            rowNum = rowIterator.next();
            cellMap = result.get(rowNum);
            cellIterator = cellMap.keySet().iterator();
            int cellIndex = 0;
            while (cellIterator.hasNext()){
                XSSFCell cell = row.createCell((short) cellIndex);
                cellNum = cellIterator.next();
                cellValue = cellMap.get(cellNum);
                if(String.valueOf(cellValue)!="null"&&String.valueOf(cellValue)!=null){
                cell.setCellValue(String.valueOf(cellValue));
                }else{
                    cell.setCellValue("  ");
                }
                cell.setCellStyle(stylecell);
                cellIndex++;
            }
            index++;
        }
    }

}