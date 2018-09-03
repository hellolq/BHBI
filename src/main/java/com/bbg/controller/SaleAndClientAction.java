package com.bbg.controller;

import com.bbg.mapper.ClientAndSaleMapper;
import com.bbg.pojo.*;
import com.bbg.tools.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H1N1 on 2018/8/24.
 */
@Controller
public class SaleAndClientAction {

    @Autowired
    private ClientAndSaleMapper clientAndSaleMapper;



    @RequestMapping("/selectSaleAndClient")
    public String selectSaleAndClient(ModelMap map){

        //获取省区列表
        List<String> sqList = clientAndSaleMapper.selectSqList();
        map.addAttribute("sqList",sqList);

        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "index";
    }

    @RequestMapping("/selectSaleAndClientTable")
    public String selectSaleAndClientTable(ModelMap map){

        //获取省区列表
        List<String> sqList = clientAndSaleMapper.selectSqList();
        map.addAttribute("sqList",sqList);

        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "indexTable";
    }

    @RequestMapping("/selectSaleAndClientEchart")
    public String selectSaleAndClientEchart(ModelMap map){

        //获取省区列表
        List<String> sqList = clientAndSaleMapper.selectSqList();
        map.addAttribute("sqList",sqList);

        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "indexEchart";
    }

    @ResponseBody
    @RequestMapping("/ajaxSelectSaleAndClientIndexTbale")
    public ResponseObj ajaxSelectSaleAndClientIndexTbale(HttpServletRequest request,SaleAndClientRequireParam param){
        String shops_gc_kb = "";
        String shops_gc = "";
        String shops_bh_kb = "";
        String shops_bh = "";
        String shops_kb = "";
        String shops_qb = "";

        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        request.getSession().setAttribute("paramIndexTbale",param);
        List<IndexTable> saleAndClient =  clientAndSaleMapper.selectIndexTable(param);
        for(int i=0;i<saleAndClient.size();i++){
            IndexTable temp = saleAndClient.get(i);
            shops_qb  += ","+temp.getShopId();
            if(temp.getShopYt().equals("广场")){
                shops_gc +=","+ temp.getShopId();
                if(temp.getKbType().equals("1")){
                    shops_gc_kb += ","+temp.getShopId();
                    shops_kb  += ","+temp.getShopId();
                }
            }

            if(temp.getShopYt().equals("百货")){
                shops_bh += ","+temp.getShopId();
                if(temp.getKbType().equals("1")){
                    shops_bh_kb += ","+temp.getShopId();
                    shops_kb  += ","+temp.getShopId();
                }
            }
        }
        //shops_gc_kb 广场可比店
        if(!StringUtils.isEmpty(shops_gc_kb)){
            param.setShopId(shops_gc_kb.substring(1));
            IndexTable indexTable_shops_gc_kb= clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_gc_kb.setShopName("广场可比店");
            saleAndClient.add(indexTable_shops_gc_kb);
        }

        //shops_gc 广场业态合计
        if(!StringUtils.isEmpty(shops_gc)){
            param.setShopId(shops_gc.substring(1));
            IndexTable indexTable_shops_gc= clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_gc.setShopName("广场业态合计");
            saleAndClient.add(indexTable_shops_gc);
        }
        //shops_bh_kb 百货可比店
        if(!StringUtils.isEmpty(shops_bh_kb)){
            param.setShopId(shops_bh_kb.substring(1));
            IndexTable indexTable_shops_bh_kb= clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_bh_kb.setShopName("百货可比店");
            saleAndClient.add(indexTable_shops_bh_kb);
        }


        //shops_bh 百货业态合计
        if(!StringUtils.isEmpty(shops_bh)) {
            param.setShopId(shops_bh.substring(1));
            IndexTable indexTable_shops_bh = clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_bh.setShopName("百货业态合计");
            saleAndClient.add(indexTable_shops_bh);
        }
        //shops_kb 可比店
        if(!StringUtils.isEmpty(shops_kb)) {
            param.setShopId(shops_kb.substring(1));
            IndexTable indexTable_shops_kb = clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_kb.setShopName("可比店");
            saleAndClient.add(indexTable_shops_kb);
        }
        //shops_qb 全比店
        if(!StringUtils.isEmpty(shops_qb)) {
            param.setShopId(shops_qb.substring(1));
            IndexTable indexTable_shops_qb = clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_qb.setShopName("全比店");
            saleAndClient.add(indexTable_shops_qb);
        }
        responseObj.setObj(saleAndClient);
        return responseObj;
    }


    @ResponseBody
    @RequestMapping("/ajaxSelectSaleAndClient")
    public ResponseObj ajaxSelectSaleAndClient(HttpServletRequest request,SaleAndClientRequireParam param){
        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        request.getSession().setAttribute("param",param);
        PageHelper.startPage(param.getPn(),param.getPnSize());
        List<SaleAndClient> saleAndClient =  clientAndSaleMapper.selectSaleAndClient(param);
        PageInfo pageInfo = new PageInfo(saleAndClient);
        responseObj.setObj(pageInfo);

        return responseObj;
    }

    @ResponseBody
    @RequestMapping("/ajaxSelectSaleAndClientParse")
    public ResponseObj ajaxSelectSaleAndClientParse(HttpServletRequest request,SaleAndClientRequireParam param){
        List<String> time = new ArrayList<>();//时间轴
        List<String> timedb = new ArrayList<>();//时间轴
        List<Double> xsje = new ArrayList<>();//销售金额
        List<Double> xsjedb = new ArrayList<>();//销售金额
        List<Double> hyxs = new ArrayList<>();//会员销售
        List<Double> hyxsdb = new ArrayList<>();//会员销售
        List<Integer> xsbs = new ArrayList<>();//交易笔数
        List<Integer> xsbsdb = new ArrayList<>();//交易笔数
        List<Integer> kll = new ArrayList<>();//客流量
        List<Integer> klldb = new ArrayList<>();//客流量
        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        request.getSession().setAttribute("param",param);
        List<SaleAndClient> saleAndClient =  clientAndSaleMapper.selectSaleAndClientParse(param);
        param.setStartTime(param.getStartTimedb());
        param.setEndTime(param.getEndTimedb());
        List<SaleAndClient> saleAndClientdb =  clientAndSaleMapper.selectSaleAndClientParse(param);
        for(int i=0;i<saleAndClient.size();i++){
            SaleAndClient temp = saleAndClient.get(i);
            time.add(temp.getZfrq());
            xsje.add(temp.getXsje());
            hyxs.add(temp.getHyxs());
            xsbs.add(temp.getXsbs());
            kll.add(temp.getKll());
        }
        for(int i=0;i<saleAndClientdb.size();i++){
            SaleAndClient temp = saleAndClientdb.get(i);
            timedb.add(temp.getZfrq());
            xsjedb.add(temp.getXsje());
            hyxsdb.add(temp.getHyxs());
            xsbsdb.add(temp.getXsbs());
            klldb.add(temp.getKll());
        }

        int ce_num = saleAndClientdb.size() - saleAndClient.size();
        if(ce_num > 0){
            for(int i=0;i<ce_num;i++){
                time.add("-");
            }
        }else if(ce_num < 0){
            for(int i=0;i<Math.abs(ce_num);i++){
                timedb.add("-");
            }
        }


        SaleAndClientResponse saleAndClientResponse = new SaleAndClientResponse();
        saleAndClientResponse.setHyxs(hyxs);
        saleAndClientResponse.setKll(kll);
        saleAndClientResponse.setTime(time);
        saleAndClientResponse.setXsje(xsje);
        saleAndClientResponse.setXsbs(xsbs);

        saleAndClientResponse.setHyxsdb(hyxsdb);
        saleAndClientResponse.setKlldb(klldb);
        saleAndClientResponse.setTimedb(timedb);
        saleAndClientResponse.setXsjedb(xsjedb);
        saleAndClientResponse.setXsbsdb(xsbsdb);
        responseObj.setObj(saleAndClientResponse);

        return responseObj;
    }

    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SaleAndClientRequireParam param = (SaleAndClientRequireParam) request.getSession().getAttribute("param");
        List<SaleAndClient> saleAndClient =  clientAndSaleMapper.selectSaleAndClient(param);
        String[] title = {"门店ID","门店名称","业态","省区","销售（万元）","交易笔数","同比销售增长","毛利率","会员销售（万元）","客流","租户销售(万元)","日期"};
        String fileName = "客流销售分析"+sdf.format(new Date())+".xls";
        String sheetName = "客流销售";
        String[][] content = new String[saleAndClient.size()][];
        for (int i = 0; i < saleAndClient.size(); i++) {
            content[i] = new String[title.length];
            SaleAndClient obj = saleAndClient.get(i);
            content[i][0] = obj.getShopId();
            content[i][1] = obj.getShopName();
            content[i][2] = obj.getYt();
            content[i][3] = obj.getSq();
            content[i][4] = obj.getXsje()+"";
            content[i][5] = obj.getXsbs()+"";
            content[i][6] = obj.getXsjekbRate()+"";
            content[i][7] = obj.getMll()+"";
            content[i][8] = obj.getHyxs()+"";
            content[i][9] = obj.getKll()+"";
            content[i][10] = obj.getGmv()+"";
            content[i][11] = obj.getZfrq()+"";
        }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        try {
             this.setResponseHeader(response, fileName);
             OutputStream os = response.getOutputStream();
      wb.write(os);
       os.flush();
       os.close();
} catch (Exception e) {
       e.printStackTrace();
 }


    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
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
