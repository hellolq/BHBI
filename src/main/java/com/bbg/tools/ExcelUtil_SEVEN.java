package com.bbg.tools;


import com.bbg.controller.SaleAndClientAction;
import com.bbg.pojo.ClientTableDTO;
import com.bbg.pojo.IndexTable;
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

    /*
   * 生成行 有样式
   * */
    public static void  createSaleAndClient_body_createRow( SXSSFWorkbook wb,String[] head,int rowNum,Sheet sh,CellStyle cellStyle){

        XSSFFont font_red = (XSSFFont)wb.createFont();
        font_red.setFontName("Arial");
        font_red.setFontHeight((short)200);
        font_red.setColor(new XSSFColor(new byte[]{(byte)255, (byte)0, (byte)0}));


        Row row = sh.getRow(rowNum);
        if(row == null){
            row = sh.createRow(rowNum);
        }
        int start_cell = row.getLastCellNum();
        if(start_cell < 0){
            start_cell = 0;
        }
        for(int cellNum=0;cellNum<head.length;cellNum++){
            String tittle = head[cellNum];


            if(tittle.indexOf("-") > -1){
                CellStyle cell_temp =  wb.createCellStyle();
                cell_temp.cloneStyleFrom(cellStyle);
                cell_temp.setFont(font_red);
                Cell cell = row.createCell(cellNum+start_cell);
                cell.setCellStyle(cell_temp);
                cell.setCellValue(tittle);
            }else{
                Cell cell = row.createCell(cellNum+start_cell);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(tittle);
            }

        }
    }

     public static Cell createSaleAndClientTittle_tool_creCell(int index,Row row,String message,CellStyle cellStyle){
         Cell cell = row.createCell(index);
         cell.setCellStyle(cellStyle);
         cell.setCellValue(message);
         return cell;
    }


    /*
       使用连接：http://10.12.32.213:8066/exportClientTableThree
    *  selectSaleAndClientTableTwo 页面导出头部标题
    *  带门店列表
    * */
    public static void createSaleAndClient_title(SXSSFWorkbook wb,String title){
        Sheet sh = null;
        int sheet_num = wb.getNumberOfSheets();
        if(sheet_num > 0){
            sh = wb.getSheetAt(0);
        }

        if(sh == null){
            sh = wb.createSheet();
        }
        //单元格样式
        XSSFCellStyle cellStyle_tiile = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_tiile.setAlignment(ALIGN_CENTER);
        cellStyle_tiile.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_tiile.setFillForegroundColor(new XSSFColor(new byte[]{(byte)54, (byte)96, (byte)145}));

        cellStyle_tiile.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBottomBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setTopBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setLeftBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setRightBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));


        cellStyle_tiile.setFillPattern(CellStyle.SOLID_FOREGROUND);

        //字体
        XSSFFont font = (XSSFFont)wb.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)200);
        //font.setColor(new XSSFColor(new byte[]{(byte)255, (byte)255, (byte)255}));
        font.setColor(new XSSFColor(new byte[]{(byte)254, (byte)254, (byte)254}));
        cellStyle_tiile.setFont(font);
        //标题行
        //Row row_one = sh.createRow(0);
        Row row_one = sh.getRow(0);
        if(row_one == null){
            row_one = sh.createRow(0);
        }
        int cell_row_start = row_one.getLastCellNum();
        if(cell_row_start < 0){
            cell_row_start = cell_row_start + 1;
        }
        Cell md_cell = row_one.createCell(0+cell_row_start);
        md_cell.setCellStyle(cellStyle_tiile);
        md_cell.setCellValue("门店");
        sh.setColumnWidth(0+cell_row_start,3500);

        Cell title_cell = row_one.createCell(1+cell_row_start);
        title_cell.setCellStyle(cellStyle_tiile);
        title_cell.setCellValue(title);

        for(int i=0;i<11;i++){
            Cell temp_cell = createSaleAndClientTittle_tool_creCell(i+cell_row_start+2,row_one,"",cellStyle_tiile);
        }

        Row row_two = sh.getRow(1);
        if(row_two == null){
            row_two = sh.createRow(1);
        }
        //int cell_row_start_two = row_two.getLastCellNum()+1;
        //Row row_two = sh.createRow(1);
        String[] row_two_title = {"","销售","","","交易笔数","","","客流","","","客单","","",};
        for(int i=0;i<row_two_title.length;i++){
            Cell temp_cell = createSaleAndClientTittle_tool_creCell(i+cell_row_start,row_two,row_two_title[i],cellStyle_tiile);
            if(!row_two_title[i].equals("")){
                sh.addMergedRegion(new CellRangeAddress(row_two.getRowNum(),row_two.getRowNum(), temp_cell.getColumnIndex(), temp_cell.getColumnIndex()+2));
            }
        }

        Row row_three = sh.getRow(2);
        if(row_three == null){
            row_three = sh.createRow(2);
        }
        //Row row_three = sh.createRow(2);
        String[] row_three_title = {"","本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};
        for(int i=0;i<row_three_title.length;i++){
            createSaleAndClientTittle_tool_creCell(i+cell_row_start,row_three,row_three_title[i],cellStyle_tiile);
        }

        sh.addMergedRegion(new CellRangeAddress(row_one.getRowNum(),row_one.getRowNum(), title_cell.getColumnIndex(), title_cell.getColumnIndex()+11));
        sh.addMergedRegion(new CellRangeAddress(row_one.getRowNum(),row_one.getRowNum()+2, md_cell.getColumnIndex(), md_cell.getColumnIndex()));

    }

    /*
       使用连接：http://10.12.32.213:8066/exportClientTableThree
    *  selectSaleAndClientTableTwo 页面导出头部标题
    *  不带门店列表
    * */
    public static void createSaleAndClient_title_NO_shop(SXSSFWorkbook wb,String title){
        Sheet sh = null;
        int sheet_num = wb.getNumberOfSheets();
        if(sheet_num > 0){
            sh = wb.getSheetAt(0);
        }

        if(sh == null){
            sh = wb.createSheet();
        }
        //单元格样式
        XSSFCellStyle cellStyle_tiile = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_tiile.setAlignment(ALIGN_CENTER);
        cellStyle_tiile.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_tiile.setFillForegroundColor(new XSSFColor(new byte[]{(byte)54, (byte)96, (byte)145}));

        cellStyle_tiile.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_tiile.setBottomBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setTopBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setLeftBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));
        cellStyle_tiile.setRightBorderColor(new XSSFColor(new byte[]{(byte)165, (byte)165, (byte)165}));


        cellStyle_tiile.setFillPattern(CellStyle.SOLID_FOREGROUND);

        //字体
        XSSFFont font = (XSSFFont)wb.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)200);
        //font.setColor(new XSSFColor(new byte[]{(byte)255, (byte)255, (byte)255}));
        font.setColor(new XSSFColor(new byte[]{(byte)254, (byte)254, (byte)254}));
        cellStyle_tiile.setFont(font);
        //标题行
        //Row row_one = sh.createRow(0);
        Row row_one = sh.getRow(0);
        if(row_one == null){
            row_one = sh.createRow(0);
        }
        int cell_row_start = row_one.getLastCellNum();
        if(cell_row_start < 0){
            cell_row_start = cell_row_start + 1;
        }

        Cell title_cell = row_one.createCell(cell_row_start);
        title_cell.setCellStyle(cellStyle_tiile);
        title_cell.setCellValue(title);

        for(int i=0;i<10;i++){
            Cell temp_cell = createSaleAndClientTittle_tool_creCell(i+cell_row_start+2,row_one,"",cellStyle_tiile);
        }

        Row row_two = sh.getRow(1);
        if(row_two == null){
            row_two = sh.createRow(1);
        }
        //int cell_row_start_two = row_two.getLastCellNum()+1;
        //Row row_two = sh.createRow(1);
        String[] row_two_title = {"销售","","","交易笔数","","","客流","","","客单","",""};
        for(int i=0;i<row_two_title.length;i++){
            Cell temp_cell = createSaleAndClientTittle_tool_creCell(i+cell_row_start,row_two,row_two_title[i],cellStyle_tiile);
            if(!row_two_title[i].equals("")){
                sh.addMergedRegion(new CellRangeAddress(row_two.getRowNum(),row_two.getRowNum(), temp_cell.getColumnIndex(), temp_cell.getColumnIndex()+2));
            }
        }

        Row row_three = sh.getRow(2);
        if(row_three == null){
            row_three = sh.createRow(2);
        }
        //Row row_three = sh.createRow(2);
        String[] row_three_title = {"本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};
        for(int i=0;i<row_three_title.length;i++){
            createSaleAndClientTittle_tool_creCell(i+cell_row_start,row_three,row_three_title[i],cellStyle_tiile);
        }

        sh.addMergedRegion(new CellRangeAddress(row_one.getRowNum(),row_one.getRowNum(), title_cell.getColumnIndex(), title_cell.getColumnIndex()+11));
    }

    /*
       使用连接：http://10.12.32.213:8066/exportClientTableThree
    *  selectSaleAndClientTableTwo 页面导出门店详情
    * */
    public static void createSaleAndClient_body(SXSSFWorkbook wb,ClientTableDTO clientTableDTO){
        Sheet sh = wb.getSheetAt(0);
        XSSFColor color = new XSSFColor(new byte[]{(byte)54, (byte)96, (byte)145});
        XSSFColor color_white = new XSSFColor(new byte[]{(byte)254, (byte)254, (byte)254});
        XSSFColor color_red = new XSSFColor(new byte[]{(byte)253, (byte)213, (byte)181});
        XSSFColor color_green = new XSSFColor(new byte[]{(byte)217, (byte)217, (byte)217});
        //单元格样式  -  白色
        XSSFCellStyle cellStyle = (XSSFCellStyle)wb.createCellStyle();
        cellStyle.setAlignment(ALIGN_CENTER);
        cellStyle.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(color);
        cellStyle.setTopBorderColor(color);
        cellStyle.setLeftBorderColor(color);
        cellStyle.setRightBorderColor(color);
        cellStyle.setFillForegroundColor(color_white);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFFont font = (XSSFFont)wb.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)200);
        XSSFColor font_color = new XSSFColor(new byte[]{(byte)1, (byte)1, (byte)1});
        font.setColor(font_color);
        cellStyle.setFont(font);

        // 粉红色
        XSSFCellStyle cellStyle_pink = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_pink.setAlignment(ALIGN_CENTER);
        cellStyle_pink.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_pink.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_pink.setBottomBorderColor(color);
        cellStyle_pink.setTopBorderColor(color);
        cellStyle_pink.setLeftBorderColor(color);
        cellStyle_pink.setRightBorderColor(color);
        cellStyle_pink.setFillForegroundColor(color_red);
        cellStyle_pink.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle_pink.setFont(font);

        // 浅绿色
        XSSFCellStyle cellStyle_green = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_green.setAlignment(ALIGN_CENTER);
        cellStyle_green.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_green.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_green.setBottomBorderColor(color);
        cellStyle_green.setTopBorderColor(color);
        cellStyle_green.setLeftBorderColor(color);
        cellStyle_green.setRightBorderColor(color);
        cellStyle_green.setFillForegroundColor(color_green);
        cellStyle_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle_green.setFont(font);

        //内容主题
        int start_row =  3;
        Row row_temp = sh.getRow(start_row);
        if(row_temp == null){
            row_temp = sh.createRow(start_row);
        }
        List<IndexTable> res = SaleAndClientAction.transfromToIndexList(clientTableDTO.getRes());
        for(int i=0;i<res.size();i++){
            IndexTable temp_obj = res.get(i);
            String[] row_body = new String[13];
            row_body[0] = temp_obj.getShopName();
            row_body[1] = temp_obj.getXsje()+"";
            row_body[2] = temp_obj.getXsjeTq()+"";
            row_body[3] = temp_obj.getXszz()+"";
            row_body[4] = temp_obj.getJybs()+"";
            row_body[5] = temp_obj.getJybsTq()+"";
            row_body[6] = temp_obj.getJybsZz()+"";
            row_body[7] = temp_obj.getKll()+"";
            row_body[8] = temp_obj.getKllTq()+"";
            row_body[9] = temp_obj.getKllZz()+"";
            row_body[10] = temp_obj.getKdj()+"";
            row_body[11] = temp_obj.getKdjTq()+"";
            row_body[12] = temp_obj.getKdjZz()+"";

            if(temp_obj.getShopName().equals("广场可比店") || temp_obj.getShopName().equals("可比店")
                    || temp_obj.getShopName().equals("全比店") || temp_obj.getShopName().equals("百货可比店")){
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle_pink);
            }else if(temp_obj.getShopName().equals("广场业态合计") || temp_obj.getShopName().equals("百货业态合计")){
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle_green);
            }else{
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle);
            }

        }

    }

    /*
      使用连接：http://10.12.32.213:8066/exportClientTableThree
   *  selectSaleAndClientTableTwo 页面导出门店详情
   * */
    public static void createSaleAndClient_body_no_shop(SXSSFWorkbook wb,ClientTableDTO clientTableDTO){
        Sheet sh = wb.getSheetAt(0);
        XSSFColor color = new XSSFColor(new byte[]{(byte)54, (byte)96, (byte)145});
        XSSFColor color_white = new XSSFColor(new byte[]{(byte)254, (byte)254, (byte)254});
        XSSFColor color_red = new XSSFColor(new byte[]{(byte)253, (byte)213, (byte)181});
        XSSFColor color_green = new XSSFColor(new byte[]{(byte)217, (byte)217, (byte)217});
        //单元格样式  -  白色
        XSSFCellStyle cellStyle = (XSSFCellStyle)wb.createCellStyle();
        cellStyle.setAlignment(ALIGN_CENTER);
        cellStyle.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(color);
        cellStyle.setTopBorderColor(color);
        cellStyle.setLeftBorderColor(color);
        cellStyle.setRightBorderColor(color);
        cellStyle.setFillForegroundColor(color_white);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFFont font = (XSSFFont)wb.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)200);
        XSSFColor font_color = new XSSFColor(new byte[]{(byte)1, (byte)1, (byte)1});
        font.setColor(font_color);
        cellStyle.setFont(font);

        // 粉红色
        XSSFCellStyle cellStyle_pink = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_pink.setAlignment(ALIGN_CENTER);
        cellStyle_pink.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_pink.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_pink.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_pink.setBottomBorderColor(color);
        cellStyle_pink.setTopBorderColor(color);
        cellStyle_pink.setLeftBorderColor(color);
        cellStyle_pink.setRightBorderColor(color);
        cellStyle_pink.setFillForegroundColor(color_red);
        cellStyle_pink.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle_pink.setFont(font);

        // 浅绿色
        XSSFCellStyle cellStyle_green = (XSSFCellStyle)wb.createCellStyle();
        cellStyle_green.setAlignment(ALIGN_CENTER);
        cellStyle_green.setVerticalAlignment(VERTICAL_CENTER);
        cellStyle_green.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle_green.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle_green.setBottomBorderColor(color);
        cellStyle_green.setTopBorderColor(color);
        cellStyle_green.setLeftBorderColor(color);
        cellStyle_green.setRightBorderColor(color);
        cellStyle_green.setFillForegroundColor(color_green);
        cellStyle_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle_green.setFont(font);

        //内容主题
        int start_row =  3;
        Row row_temp = sh.getRow(start_row);
        if(row_temp == null){
            row_temp = sh.createRow(start_row);
        }

        List<IndexTable> res = SaleAndClientAction.transfromToIndexList(clientTableDTO.getRes());
        for(int i=0;i<res.size();i++){
            IndexTable temp_obj = res.get(i);
            String[] row_body = new String[12];
            //row_body[0] = temp_obj.getShopName();
            row_body[0] = temp_obj.getXsje()+"";
            row_body[1] = temp_obj.getXsjeTq()+"";
            row_body[2] = temp_obj.getXszz()+"";
            row_body[3] = temp_obj.getJybs()+"";
            row_body[4] = temp_obj.getJybsTq()+"";
            row_body[5] = temp_obj.getJybsZz()+"";
            row_body[6] = temp_obj.getKll()+"";
            row_body[7] = temp_obj.getKllTq()+"";
            row_body[8] = temp_obj.getKllZz()+"";
            row_body[9] = temp_obj.getKdj()+"";
            row_body[10] = temp_obj.getKdjTq()+"";
            row_body[11] = temp_obj.getKdjZz()+"";

            if(temp_obj.getShopName().equals("广场可比店") || temp_obj.getShopName().equals("可比店")
                    || temp_obj.getShopName().equals("全比店") || temp_obj.getShopName().equals("百货可比店")){
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle_pink);
            }else if(temp_obj.getShopName().equals("广场业态合计") || temp_obj.getShopName().equals("百货业态合计")){
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle_green);
            }else{
                createSaleAndClient_body_createRow(wb,row_body,(start_row+i),sh,cellStyle);
            }

        }

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
