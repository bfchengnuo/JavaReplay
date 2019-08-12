package com.bfchengnuo.uselibraries.apache.poi.excel.old;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * 简单的基础操作
 *
 * 使用的是老版本的 POI，在 3.17 中部分 API 已经废弃，代码中使用注释标注
 *
 * @author Created by 冰封承諾Andy on 2019/7/23.
 */
public class SimpleExample {
    /**
     * 生成 Excel
     * @param title 导出 Excel 的首行标题
     * @param headers 表头
     * @param countMap 统计信息（自定义）
     * @param dataMap 主体数据
     * @param out 输出流
     */
    public void exportExcel(String title, String[] headers, Map<String, Object> countMap,
                             Map<String, Object> dataMap, OutputStream out) {
        final int WIDE_WIDTH = 20 * 256;
        final int FIT_WIDTH = 15 * 256;

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 第一个参数是指哪个单元格，第二个参数是单元格的宽度
        sheet.setColumnWidth((short)0, WIDE_WIDTH);
        sheet.setColumnWidth((short)1, WIDE_WIDTH);
        sheet.setColumnWidth((short)2, WIDE_WIDTH);
        sheet.setColumnWidth((short)3, FIT_WIDTH);
        sheet.setColumnWidth((short)4, FIT_WIDTH);
        sheet.setColumnWidth((short)5, FIT_WIDTH);
        sheet.setColumnWidth((short)6, FIT_WIDTH);
        sheet.setColumnWidth((short)7, FIT_WIDTH);
        sheet.setColumnWidth((short)8, FIT_WIDTH);
        sheet.setColumnWidth((short)9, FIT_WIDTH);
        sheet.setColumnWidth((short)10, FIT_WIDTH);
        sheet.setColumnWidth((short)11, FIT_WIDTH);
        sheet.setColumnWidth((short)12, FIT_WIDTH);
        sheet.setColumnWidth((short)13, FIT_WIDTH);
        sheet.setColumnWidth((short)14, FIT_WIDTH);
        sheet.setColumnWidth((short)15, FIT_WIDTH);
        sheet.setColumnWidth((short)16, FIT_WIDTH);
        sheet.setColumnWidth((short)17, FIT_WIDTH);
        sheet.setColumnWidth((short)18, FIT_WIDTH);
        sheet.setColumnWidth((short)19, FIT_WIDTH);
        sheet.setColumnWidth((short)20, FIT_WIDTH);
        sheet.setColumnWidth((short)21, FIT_WIDTH);
        sheet.setColumnWidth((short)22, FIT_WIDTH);
        sheet.setColumnWidth((short)23, FIT_WIDTH);
        sheet.setColumnWidth((short)24, FIT_WIDTH);
        sheet.setColumnWidth((short)25, FIT_WIDTH);
        // 设置列默认高度
        sheet.setDefaultRowHeight((short)400);
        // 标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 字体居中 (3.17 已废弃)
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 标题字体
        HSSFFont font = workbook.createFont();
        // font.setColor(HSSFColor.VIOLET.index);
        font.setColor(HSSFColor.HSSFColorPredefined.VIOLET.getIndex());
        font.setFontHeightInPoints((short) 16);
        // 设置黑体 (3.17 已废弃)
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 第二行样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 靠左居中
        // style2.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
        style2.setAlignment(HorizontalAlignment.LEFT);
        // 第二行字体
        HSSFFont font2 = workbook.createFont();
        // 正常字体
        font2.setBold(false);
        font2.setFontHeightInPoints((short) 14);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 表格内容
        HSSFCellStyle style3 = workbook.createCellStyle();
        // 设置边框
        // style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // style3.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style3.setBorderBottom(BorderStyle.THIN);
        style3.setBorderLeft(BorderStyle.THIN);
        style3.setBorderTop(BorderStyle.THIN);
        style3.setBorderRight(BorderStyle.THIN);

        // 水平居中
        style3.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style3.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置是否能够换行，能够换行为true
        style3.setWrapText(true);
        // 表格和底部字体
        HSSFFont font3 = workbook.createFont();
        font3.setBold(false);
        font3.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        style3.setFont(font3);

        // 底部备注
        HSSFCellStyle style4 = workbook.createCellStyle();
        style4.setAlignment(HorizontalAlignment.LEFT);
        style4.setWrapText(true);
        // 表格和底部字体
        HSSFFont font4 = workbook.createFont();
        font4.setBold(false);
        font4.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        style4.setFont(font4);

        // 表格中内容的字体
        HSSFFont font5 = workbook.createFont();
        font5.setColor(HSSFColor.BLACK.index);

        // 表格第一行(标题)
        HSSFRow row1 = sheet.createRow(0);
        int length = headers.length;
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (length - 1)));
        row1.setHeightInPoints(30);
        // 创建一个单元格
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(style);
        cell1.setCellValue(title);

        // 表格内容的标题
        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style3);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 首行统计信息
        HSSFRow countRow = sheet.createRow(2);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
        HSSFCell cell = countRow.createCell(0);
        cell.setCellStyle(style3);
        cell.setCellValue(new HSSFRichTextString("县（市）区合计"));
        for (int i = 1; i < headers.length; i++) {
            double text;
            if (i == 3) {
                text = Double.parseDouble(String.valueOf(countMap.get("typeCount")));
            } else {
                text = countMap.get(headers[i]) == null ? 0 : Double.parseDouble(String.valueOf(countMap.get(headers[i])));
            }
            HSSFCell cel = countRow.createCell(i);
            cel.setCellStyle(style3);
            cel.setCellValue(text);
        }

        // 遍历集合数据，产生数据行

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
