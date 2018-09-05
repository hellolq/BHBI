package com.bbg.tools;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class ExcelUtil {

    public static void createRow(HSSFSheet sheet,int rowNum,int colNum,String[] col_arry,HSSFCellStyle style){
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.createRow(rowNum);
        for(int i=0;i<col_arry.length;i++){
            cell = row.createCell(i+colNum);
            cell.setCellValue(col_arry[i]);
            cell.setCellStyle(style);
        }
    }

    public static void createRow_body(HSSFSheet sheet,int rowNum,int colNum,String[] col_arry,HSSFWorkbook wb){
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFCellStyle style = wb.createCellStyle();
        //内容字体样式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
        //内容边框颜色
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        row = sheet.createRow(rowNum);
        if(col_arry[0].equals("百货可比店") ||col_arry[0].equals("广场可比店") || col_arry[0].equals("可比店") || col_arry[0].equals("全比店")|| col_arry[0].equals("生活可比店") ){
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.ROSE.index);
        }else if(col_arry[0].equals("百货业态合计") || col_arry[0].equals("生活广场合计") || col_arry[0].equals("广场业态合计")){
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        }
        for(int i=0;i<col_arry.length;i++){
            cell = row.createCell(i+colNum);
            cell.setCellValue(col_arry[i]);
            cell.setCellStyle(style);
        }
    }




    public static HSSFWorkbook getHSSFWorkbookAuto(String sheetName, String []title, String [][]values, HSSFWorkbook wb,String descTxt){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFSheet sheet = null;
        int indexRow = 0;
        if(wb == null){
            wb = new HSSFWorkbook();
            sheet = wb.createSheet(sheetName);
        }else{
            sheet = wb.getSheetAt(0);
            indexRow = sheet.getLastRowNum()+2;
        }
        HSSFCellStyle style_title = wb.createCellStyle();
        //第一列宽度
        sheet.setDefaultColumnWidth(10);
        sheet.setDefaultRowHeight((short)500);
        sheet.setColumnWidth(0+indexRow,3500);
        //头部背景颜色
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex((short)8, (byte) 54, (byte) 96, (byte) 145);
        palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte) 165, (byte) 165, (byte) 165);
        palette.setColorAtIndex(HSSFColor.ROSE.index, (byte) 253, (byte) 213, (byte) 181);//广场可比店背景颜色
        palette.setColorAtIndex(HSSFColor.WHITE.index, (byte) 255, (byte) 255, (byte) 255);//白色背景色
        palette.setColorAtIndex(HSSFColor.BLUE_GREY.index, (byte) 217, (byte) 217, (byte) 217);//白色背景色
        style_title.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        style_title.setFillForegroundColor((short)8);
        style_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平

        //头部边框颜色
        style_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_title.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
        style_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_title.setLeftBorderColor(HSSFColor.LIGHT_GREEN.index);
        style_title.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style_title.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
        style_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_title.setTopBorderColor(HSSFColor.LIGHT_GREEN.index);

        //字体颜色
        HSSFFont font=wb.createFont();
        font.setColor(HSSFColor.WHITE.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short)10);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        style_title.setFont(font);
        String[] temp_arry =  {"门店",descTxt,"","","","","","","","","","",""};
        String[] xs_arry =  {"销售","","","交易笔数","","","客流","","","客单","",""};
        createRow(sheet,0+indexRow,0,temp_arry,style_title);
        createRow(sheet,1+indexRow,1,xs_arry,style_title);
        sheet.addMergedRegion(new CellRangeAddress(0+indexRow,0+indexRow,1,12));
        sheet.addMergedRegion(new CellRangeAddress(0+indexRow,2+indexRow,0,0));
        sheet.addMergedRegion(new CellRangeAddress(1+indexRow,1+indexRow,1,3));
        sheet.addMergedRegion(new CellRangeAddress(1+indexRow,1+indexRow,4,6));
        sheet.addMergedRegion(new CellRangeAddress(1+indexRow,1+indexRow,7,9));
        sheet.addMergedRegion(new CellRangeAddress(1+indexRow,1+indexRow,10,12));

        createRow(sheet,2+indexRow,0,title,style_title);
        for(int i=0;i<values.length;i++){
            createRow_body(sheet,(i+3+indexRow),0,values[i], wb);
        }
        return wb;
    }

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

}
