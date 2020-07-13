package com.toj.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelImport {
    /*public static List<List<String>> readXlsx(String path) throws IOException {
        InputStream input = new FileInputStream(path);
        return readXlsx(input);
    }

    public static List<List<String>> readXls(String path) throws IOException {
        InputStream input = new FileInputStream(path);
        return readXls(input);
    }

    public static List<List<String>> readXlsx(InputStream input) throws IOException {
        List<List<String>> result = new ArrayList<List<String>>();
        XSSFWorkbook wb = new XSSFWorkbook(input);
        for (Sheet xssfSheet : wb) {
            if (xssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                Row row = xssfSheet.getRow(rowNum);
                int minCellNum = row.getFirstCellNum();
                int maxCellNum = row.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                for (int i = minCellNum; i < maxCellNum; i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        return result;
    }

    public static List<List<String>> readXls(InputStream input) throws IOException {
        List<List<String>> result = new ArrayList<List<String>>();
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                int minCellNum = row.getFirstCellNum();
                int maxCellNum = row.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                for (int i = minCellNum; i < maxCellNum; i++) {
                    HSSFCell cell = row.getCell(i);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal(cell));
                }
                result.add(rowList);
            }
        }
        return result;
    }

    private static String getStringVal(HSSFCell cell) {
        String value = null;
        if(cell==null||cell.equals(null)||cell.getCellType()==CellType.BLANK){
            value="null";
        }else {
            switch (cell.getCellType()) {
                case FORMULA:value = "" + cell.getCellFormula();
                    break;
                case NUMERIC:value = "" + cell.getNumericCellValue();
                    break;
                case STRING:value = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
        }
        return value;
        }
*/
    private String getValue(Cell cell) {
        String result = "";

        switch (cell.getCellType()) {
            case BOOLEAN:
                result = cell.getBooleanCellValue() + "";
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case NUMERIC:
                // 可能是普通数字，也可能是日期
                /*if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    result = DateUtil.getJavaDate(cell.getNumericCellValue())
                            .toString();
                } else {
                    result = cell.getNumericCellValue() + "";
                }
                break;*/
                short format = cell.getCellStyle().getDataFormat();
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (format == 20 || format == 32) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else if (format == 14 || format == 31 || format == 57 || format == 58 || format == 177) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        result = sdf.format(date);
                    }else {// 日期
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    }
                    try {
                        result = sdf.format(cell.getDateCellValue());// 日期
                    } catch (Exception e) {
                        try {
                            throw new Exception("exception on get date data !".concat(e.toString()));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }finally{
                        sdf = null;
                    }
                }  else {
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    result = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
                }
                break;
        }
        return result;
    }

    /***
     * 这种方法支持03，和07版本的excel读取
     * 但是对于合并的单元格，除了第一行第一列之外，其他部分读取的值为空
     * @param is
     */
    public List<List<String>> importXlsx(InputStream is) {
        try {
            List<List<String>> result = new ArrayList<List<String>>();
            Workbook workbook = WorkbookFactory.create(is);
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (rowNum >= 1) {
                        if (row == null) {
                            continue;
                        } else if (StringUtils.isEmpty(getValue(row.getCell(0))) && StringUtils.isEmpty(getValue(row.getCell(1)))){
                            continue;
                        }
                    }
                    int minCellNum = row.getFirstCellNum();
                    int maxCellNum = row.getLastCellNum();
                    List<String> rowList = new ArrayList<String>();
                    for (int i = minCellNum; i < maxCellNum; i++) {
                        Cell cell = row.getCell(i);
                        if (cell == null) {
                            continue;
                        }
                        rowList.add(getValue(cell));
                    }
                    result.add(rowList);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}