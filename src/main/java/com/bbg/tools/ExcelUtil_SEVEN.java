package com.bbg.tools;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.apache.poi.hssf.record.ExtendedFormatRecord.VERTICAL_CENTER;
import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class ExcelUtil_SEVEN {
    /*
    * 生成行 无样式
    * */
    public static void  createRow( String[] head,int rowNum,Sheet sh){
        Row row = sh.createRow(rowNum);
        for(int cellNum=0;cellNum<head.length;cellNum++){
            String tittle = head[cellNum];
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(tittle);
        }
    }

    /*
  * 生成行 头部样式 有样式
  * */
    public static void  createHeaderRow( String[] head,short[] cellSize,int rowNum,Sheet sh,CellStyle cellStyle){
        Row row = sh.createRow(rowNum);
        for(int cellNum=0;cellNum<head.length;cellNum++){
            String tittle = head[cellNum];
            Cell cell = row.createCell(cellNum);
            sh.setColumnWidth(cell.getColumnIndex(),cellSize[cellNum]);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(tittle);
        }
    }

    /*
   * 生成行 有样式
   * */
    public static void  createRow( String[] head,int rowNum,Sheet sh,CellStyle cellStyle){
        Row row = sh.createRow(rowNum);
        for(int cellNum=0;cellNum<head.length;cellNum++){
            String tittle = head[cellNum];
            Cell cell = row.createCell(cellNum);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(tittle);
        }
    }

     public static Cell createSaleAndClientTittle_tool_creCell(int index,Row row,String message,CellStyle cellStyle){
         Cell cell = row.createCell(index);
         cell.setCellStyle(cellStyle);
         cell.setCellValue(message);
         return cell;
    }


    /*
    *  selectSaleAndClientTableTwo 页面导出头部标题
    * */
    public static void createSaleAndClient(SXSSFWorkbook wb,String title){
        Sheet sh = wb.createSheet();
        //单元格样式
        XSSFCellStyle cellStyle_tiile = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_tiile.setAlignment(ALIGN_CENTER);
        cellStyle_tiile.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_tiile.setFillForegroundColor(new XSSFColor(new byte[]{(byte)54, (byte)96, (byte)145}));
        cellStyle_tiile.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //字体
        XSSFFont font = (XSSFFont)wb.createFont();
        font.setFontName("楷体");
        font.setFontHeight((short)260);
        //font.setColor(new XSSFColor(new byte[]{(byte)255, (byte)255, (byte)255}));
        font.setColor(new XSSFColor(new byte[]{(byte)254, (byte)254, (byte)254}));
        cellStyle_tiile.setFont(font);
        //标题行
        Row row_one = sh.createRow(0);
        Cell md_cell = row_one.createCell(0);
        md_cell.setCellStyle(cellStyle_tiile);
        md_cell.setCellValue("门店");

        Cell title_cell = row_one.createCell(1);
        title_cell.setCellStyle(cellStyle_tiile);
        title_cell.setCellValue(title);



         Row row_two = sh.createRow(1);
         String[] row_two_title = {"销售","交易笔数","客流","客单"};
         int startCell = 1;
         for(int i=0;i<row_two_title.length;i++){
             int  index = 1 + (i)*3;
             Cell temp_cell = createSaleAndClientTittle_tool_creCell(index,row_two,row_two_title[i],cellStyle_tiile);
             sh.addMergedRegion(new CellRangeAddress(row_two.getRowNum(),row_two.getRowNum(), temp_cell.getColumnIndex(), temp_cell.getColumnIndex()+2));
         }

        Row row_three = sh.createRow(2);
        String[] row_three_title = {"","本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};
        for(int i=0;i<row_three_title.length;i++){
            createSaleAndClientTittle_tool_creCell(i,row_three,row_three_title[i],cellStyle_tiile);
        }



        sh.addMergedRegion(new CellRangeAddress(row_one.getRowNum(),row_one.getRowNum(), title_cell.getColumnIndex(), title_cell.getColumnIndex()+11));
        sh.addMergedRegion(new CellRangeAddress(row_one.getRowNum(),row_one.getRowNum()+2, md_cell.getColumnIndex(), md_cell.getColumnIndex()));

    }

    /*
    *  07 excel
    * */
    public static SXSSFWorkbook wb07(String fileName, String[] head,short[] cellSize,List<String[]> bodys ) throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        Sheet sh = wb.createSheet();
        //样式设置
        CellStyle cellStyle = wb.createCellStyle();
        // 字体
        Font font = wb.createFont();
        font.setFontName("楷体");
        font.setFontHeight((short)260);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle.setAlignment(ALIGN_CENTER);




        //表头标题
        int rowNum = 0;
        createHeaderRow(head,cellSize,rowNum,sh,cellStyle);
        //表格内容
        for(int i=0;i<bodys.size();i++){
            String[] temp = bodys.get(i);
            createRow(temp,i+1,sh,cellStyle);
        }
        return wb;
    }



    /*
    * 设置Excel导出消息头
    * */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
