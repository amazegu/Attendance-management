package com.toj.utils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * 通过excel模板导出excel
 * 共分为六部完成根据模板导出excel操作：<br/>
 * 第一步、设置excel模板路径（setSrcPath）<br/>
 * 第二步、设置要生成excel文件路径（setDesPath）<br/>
 * 第三步、设置模板中哪个Sheet列（setSheetName）<br/>
 * 第四步、获取所读取excel模板的对象（getSheet）<br/>
 * 第五步、设置数据（分为6种类型数据：setCellStrValue、setCellDateValue、setCellDoubleValue、setCellBoolValue、setCellCalendarValue、setCellRichTextStrValue）<br/>
 * 第六步、完成导出 （exportToNewFile）<br/>
 *
 * @author liulixi
 * @date 2016年12月19日21:37:50
 */
public class ExcelUtil {
    private static String srcXlsPath = "";// // excel模板路径
    private static String sheetName = "";
    private static XSSFWorkbook wb = null;
    private static XSSFSheet sheet = null;
    private static Map<Integer, XSSFCellStyle> cellStyleMap;
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 第一步、设置excel模板路径
     * @param srcXlsPath
     */
    @SuppressWarnings("unused")
    private static void setSrcPath(String srcXlsPath) {
        ExcelUtil.srcXlsPath = srcXlsPath;
    }

    /**
     * 第三步、设置模板中哪个Sheet列
     * @param sheetName
     */
    @SuppressWarnings("unused")
    private static void setSheetName(String sheetName) {
        ExcelUtil.sheetName = sheetName;
    }

    /**
     * 第四步、获取所读取excel模板的对象
     */
    private static void getSheet() {
        try {
            File fi = new File(srcXlsPath);
            if(!fi.exists()){
                System.out.println("模板文件:"+srcXlsPath+"不存在!");
                return;
            }
            wb = new XSSFWorkbook(fi);
            sheet = wb.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 第五步、设置字符串类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--字符串类型的数据
     */
    private static XSSFCell setCellStrValue(int rowIndex, int cellnum, String value) {
        if (sheet.getRow(rowIndex) == null) {
            sheet.createRow(rowIndex);
        }

        if (sheet.getRow(rowIndex).getCell(cellnum) == null) {
            sheet.getRow(rowIndex).createCell(cellnum);
        }

        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
        return cell;
    }

    /**
     * 第五步、设置日期/时间类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日期/时间类型的数据
     */
    @SuppressWarnings("unused")
    private void setCellDateValue(int rowIndex, int cellnum, Date value) {
        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置浮点类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--浮点类型的数据
     */
    @SuppressWarnings("unused")
    private void setCellDoubleValue(int rowIndex, int cellnum, double value) {
        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置Bool类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--Bool类型的数据
     */
    @SuppressWarnings("unused")
    private void setCellBoolValue(int rowIndex, int cellnum, boolean value) {
        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置日历类型的数据
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日历类型的数据
     */
    @SuppressWarnings("unused")
    private void setCellCalendarValue(int rowIndex, int cellnum, Calendar value) {
        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置富文本字符串类型的数据。可以为同一个单元格内的字符串的不同部分设置不同的字体、颜色、下划线
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--富文本字符串类型的数据
     */
    @SuppressWarnings("unused")
    private void setCellRichTextStrValue(int rowIndex, int cellnum,
                                         RichTextString value) {
        XSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第六步、完成导出
     */
    private static void exportToNewFile(HttpServletResponse response) {
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过模板导出 excel<br/>
     * 在参数 rowMap 中，最外层的map的key 表示行号（excel中第一行为0），key 对应的value表示excel中的一行数据<br/>
     * value 是个map，map 中时 key 表示 列号（第一列为0），map中value表示excel中对应的cell中的值。<br/>
     * excel模板文件可参考项目doc 下的 template.xls文件
     * @param templateFilePath 模板文件路径
     * @param setSheetName excel 文档中 sheet 名称
     * @param rowMap 数据
     * @param rowStyleLike 行样式，如果不需要行样式  传null，在插入数据时，参考样式所在行。比如excel表中，第0行是总标题行，<br/>
     * 第1行是列表头行，为了使得插入的数据有样式，我们可以在excel模板的第2行设置每个单元格的样式，这样在插入数据时，会使用此样式。<br/>
     * 特别注意：比如说一行有8个单元格，如果有某个单元格没有设置样式，那么该单元格后面的单元格的样式不会被使用。所以如果设置单元格样式的话，<br/>
     * 最好把一行所有的单元格都设置样式。
     */
    public static void exportExcelByTemplate(String templateFilePath, String setSheetName,
                                             Map<Integer, Map<Integer, Object>> rowMap, Integer rowStyleLike,HttpServletResponse response) {
        srcXlsPath = templateFilePath;
        sheetName = setSheetName;
        getSheet();

        if (rowStyleLike != null && sheet.getRow(rowStyleLike) != null) {
            cellStyleMap = new HashMap<Integer, XSSFCellStyle>();
            XSSFRow rowCellStyle = sheet.getRow(rowStyleLike);
            XSSFCell cell;
            XSSFCellStyle cellStyle;
            int i = 0;
            while (true) {
                cell = rowCellStyle.getCell(i);
                if (cell != null) {
                    cellStyle = cell.getCellStyle();
                    cellStyleMap.put(i, cellStyle);
                    ++i;
                } else {
                    break;
                }

            }
        }

        Iterator<Integer> rowIterator = rowMap.keySet().iterator();
        Iterator<Integer> cellIterator;
        int rowNum;
        int cellNum;
        Object cellValue;
        Map<Integer, Object> cellMap;
        while (rowIterator.hasNext()) {
            rowNum = rowIterator.next();
            cellMap = rowMap.get(rowNum);
            cellIterator = cellMap.keySet().iterator();
            XSSFCell cell;
            while (cellIterator.hasNext()) {
                cellNum = cellIterator.next();
                cellValue = cellMap.get(cellNum);
                cell = setCellStrValue(rowNum, cellNum, String.valueOf(cellValue));
                if(String.valueOf(cellValue)!="null"&&String.valueOf(cellValue)!=null){
                    cell.setCellValue(String.valueOf(cellValue));
                }else{
                    cell.setCellValue("  ");
                }
                if (cell != null && cellStyleMap != null && cellStyleMap.get(cellNum) != null) {
                    cell.setCellStyle(cellStyleMap.get(cellNum));
                }
            }
        }
        exportToNewFile(response);
    }

    /**
     * 创建单个文件
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            logger.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            logger.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                logger.debug("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                logger.debug(descFileName + " 文件创建成功!");
                return true;
            } else {
                logger.debug(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(descFileName + " 文件创建失败!");
            return false;
        }

    }


    public static void main(String[] args) {
        Map<Integer, Map<Integer, Object>> rowMap = new HashMap<Integer, Map<Integer,Object>>();

        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(0, "你好1");
        map.put(1, "大家好2");
        map.put(2, "你好3");
        map.put(3, "大家好4");
        map.put(4, "大家好5");
        rowMap.put(2, map);

        map = new HashMap<Integer, Object>();
        map.put(0, "你好5");
        map.put(1, "大家好6");
        map.put(2, "你好");
        map.put(3, "大家好7");
        rowMap.put(3, map);

        map = new HashMap<Integer, Object>();
        map.put(0, "你这是最大的标题内容，呵呵哈哈哈或或");

        rowMap.put(0, map);

        map = new HashMap<Integer, Object>();
        map.put(0, "你好1");
        map.put(1, "大家好2");
        map.put(2, "你好3");
        map.put(3, "大家好4");
        map.put(4, "大家好5");
        rowMap.put(4, map);

        map = new HashMap<Integer, Object>();
        map.put(0, "你好1");
        map.put(1, "大家好2");
        map.put(2, "你好3");
        map.put(3, "大家好4");
        map.put(4, "大家好5");
        rowMap.put(5, map);

    }
}