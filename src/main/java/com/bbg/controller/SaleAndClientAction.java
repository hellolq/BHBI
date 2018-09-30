package com.bbg.controller;

import com.bbg.mapper.ClientAndSaleMapper;
import com.bbg.pojo.*;
import com.bbg.tools.ExcelUtil;
import com.bbg.tools.ExcelUtil_SEVEN;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping("/selectSaleAndClientTableTwo")
    public String selectSaleAndClientTableTwo(ModelMap map,IndexWithConditionDTO param){

        if(param.getBtns() != null){
            map.addAttribute("preParam",param);
        }else{
            //获取省区列表
            List<String> sqList = clientAndSaleMapper.selectSqList();
            map.addAttribute("sqList",sqList);

        }
        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "indexTableTwo";
    }

    @RequestMapping("/selectSaleAndClientEchart")
    public String selectSaleAndClientEchart(ModelMap map,IndexWithConditionDTO param){
       if(param.getBtns() != null){
           map.addAttribute("preParam",param);
       }else{
           //获取省区列表
           List<String> sqList = clientAndSaleMapper.selectSqList();
           map.addAttribute("sqList",sqList);

       }
        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "indexEchart";
    }

    public List<IndexTable> getIndexTableList(SaleAndClientRequireParam param,List<IndexTable> sortIndexTable){
        String shops_gc_kb = "";
        String shops_gc = "";
        String shops_bh_kb = "";
        String shops_bh = "";
        String shops_sh_kb = "";
        String shops_sh = "";
        String shops_kb = "";
        String shops_qb = "";
        List<IndexTable> saleAndClient =  new ArrayList<>();
        List<IndexTable> saleAndClientTemp =  clientAndSaleMapper.selectIndexTable(param);

        for(int i=0;i<sortIndexTable.size();i++){
            IndexTable sort_temp = sortIndexTable.get(i);
            for(int j=0;j<saleAndClientTemp.size();j++){
                IndexTable temp = saleAndClientTemp.get(j);
                if(temp.getShopName().equals(sort_temp.getShopName())){
                    saleAndClient.add(temp);
                }
            }
        }

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

            if(temp.getShopYt().equals("生活广场")){
                shops_sh += ","+temp.getShopId();
                if(temp.getKbType().equals("1")){
                    shops_sh_kb += ","+temp.getShopId();
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
        //shops_sh_kb shops_sh  生活广场合计
        //shops_sh_kb 生活广场可比店
        if(!StringUtils.isEmpty(shops_sh_kb)){
            param.setShopId(shops_sh_kb.substring(1));
            IndexTable indexTable_shops_sh_kb= clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_sh_kb.setShopName("生活可比店");
            saleAndClient.add(indexTable_shops_sh_kb);
        }
        //shops_sh 生活广场业态合计
        if(!StringUtils.isEmpty(shops_sh)) {
            param.setShopId(shops_sh.substring(1));
            IndexTable indexTable_shops_sh = clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_sh.setShopName("生活广场合计");
            saleAndClient.add(indexTable_shops_sh);
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
        return saleAndClient;
    }

    public List<IndexTable> getIndexTableList(SaleAndClientRequireParam param){
        String shops_gc_kb = "";
        String shops_gc = "";
        String shops_bh_kb = "";
        String shops_bh = "";
        String shops_sh_kb = "";
        String shops_sh = "";
        String shops_kb = "";
        String shops_qb = "";
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

            if(temp.getShopYt().equals("生活广场")){
                shops_sh += ","+temp.getShopId();
                if(temp.getKbType().equals("1")){
                    shops_sh_kb += ","+temp.getShopId();
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
        //shops_sh_kb shops_sh  生活广场合计
        //shops_sh_kb 生活广场可比店
        if(!StringUtils.isEmpty(shops_sh_kb)){
            param.setShopId(shops_sh_kb.substring(1));
            IndexTable indexTable_shops_sh_kb= clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_sh_kb.setShopName("生活可比店");
            saleAndClient.add(indexTable_shops_sh_kb);
        }
          //shops_sh 生活广场业态合计
        if(!StringUtils.isEmpty(shops_sh)) {
            param.setShopId(shops_sh.substring(1));
            IndexTable indexTable_shops_sh = clientAndSaleMapper.selectIndexTableByShopId(param);
            indexTable_shops_sh.setShopName("生活广场合计");
            saleAndClient.add(indexTable_shops_sh);
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
        return saleAndClient;
    }

    public List<String> getSquTime(String startTime,String endTime)  {
        List<String> time = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date startTime_date = sdf.parse(startTime);
            Date endTime_date = sdf.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(startTime_date);
            while (startTime_date.getTime() <= endTime_date.getTime()) {
                time.add(sdf.format(startTime_date));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
                startTime_date = tempStart.getTime();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }

    public List<TransTime> getSquTimeWeek(String startTime,String endTime)  {
        List<TransTime> valueList = null;
        List<String> time = new ArrayList<>();
        List<String> time_res = new ArrayList<>();
        Date time_temp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
        Map<String,TransTime> timeRes = new LinkedHashMap<>();

        try {
            time = getSquTime(startTime,endTime);
            for(int i=0;i<time.size();i++){
                time_temp = sdf.parse(time.get(i));
                Calendar tempStart = Calendar.getInstance();
                tempStart.setFirstDayOfWeek(Calendar.MONDAY);
                tempStart.setTime(time_temp);
                String temp_key =sdf_year.format(time_temp)+"年第"+tempStart.get(Calendar.WEEK_OF_YEAR)+"周";
                if(timeRes.containsKey(temp_key)){
                    timeRes.get(temp_key).setEndTime(time.get(i));
                }else{
                    timeRes.put(temp_key,new TransTime(temp_key,time.get(i),time.get(i)));
                }
            }
            Collection<TransTime> valueCollection = timeRes.values();
            valueList = new ArrayList<TransTime>(valueCollection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return valueList;
    }

    public List<TransTime> getSquTimeMonth(String startTime,String endTime)  {
        List<TransTime> valueList = null;
        List<String> time = new ArrayList<>();
        List<String> time_res = new ArrayList<>();
        Date time_temp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
        Map<String,TransTime> timeRes = new LinkedHashMap<>();
        try {
            time = getSquTime(startTime,endTime);
            for(int i=0;i<time.size();i++){
                time_temp = sdf.parse(time.get(i));
                Calendar tempStart = Calendar.getInstance();
                tempStart.setFirstDayOfWeek(Calendar.MONDAY);
                tempStart.setTime(time_temp);
                String temp_key =sdf_year.format(time_temp)+"年"+(tempStart.get(Calendar.MONTH)+1)+"月";
                if(timeRes.containsKey(temp_key)){
                    timeRes.get(temp_key).setEndTime(time.get(i));
                }else{
                    timeRes.put(temp_key,new TransTime(temp_key,time.get(i),time.get(i)));
                }
            }
            Collection<TransTime> valueCollection = timeRes.values();
            valueList = new ArrayList<TransTime>(valueCollection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return valueList;
    }

    public String transfromTimeToSea(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String res = "";
        try {
            Date time_temp = sdf.parse(time);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setFirstDayOfWeek(Calendar.MONDAY);
            tempStart.setTime(time_temp);
            int  month = tempStart.get(Calendar.MONTH)+1;
            if(month>=1 && month<=3){
                res = "1";
            }
            if(month>=4 && month<=6){
                res = "2";
            }
            if(month>=7 && month<=9){
                res = "3";
            }if(month>=10 && month<=12){
                res = "4";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public List<TransTime> getSquTimeSea(String startTime,String endTime)  {
        List<TransTime> valueList = null;
        List<String> time = new ArrayList<>();
        List<String> time_res = new ArrayList<>();
        Date time_temp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
        Map<String,TransTime> timeRes = new LinkedHashMap<>();
        try {
            time = getSquTime(startTime,endTime);
            for(int i=0;i<time.size();i++){
                time_temp = sdf.parse(time.get(i));
                String temp_key =sdf_year.format(time_temp)+"年第"+(transfromTimeToSea(time.get(i)))+"季度";
                if(timeRes.containsKey(temp_key)){
                    timeRes.get(temp_key).setEndTime(time.get(i));
                }else{
                    timeRes.put(temp_key,new TransTime(temp_key,time.get(i),time.get(i)));
                }
            }
            Collection<TransTime> valueCollection = timeRes.values();
            valueList = new ArrayList<TransTime>(valueCollection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return valueList;
    }


    public List<SaleAndClientRequireParam> geneateSaleAndClientRequireParam(SaleAndClientRequireParam param){
        List<SaleAndClientRequireParam> res = new ArrayList<>();
        List<String> time = null;
        List<String> timedb = null;
        if(param.getTimeType().equals("yyyy-mm-dd")){
            time = getSquTime(param.getStartTime(),param.getEndTime());
            timedb = getSquTime(param.getStartTimedb(),param.getEndTimedb());
            int num = time.size();
            if(time.size() > timedb.size()){
                num = timedb.size();
            }
            for(int i=0;i<num;i++){
                SaleAndClientRequireParam temp_param = new SaleAndClientRequireParam();
                temp_param = param.clone();
                String title = time.get(i) + "对比" + timedb.get(i);
                temp_param.setStartTime(time.get(i));
                temp_param.setEndTime(time.get(i));
                temp_param.setStartTimedb(timedb.get(i));
                temp_param.setEndTimedb(timedb.get(i));
                temp_param.setDescTxt(title);
                res.add(temp_param);
            }
        }

        if(param.getTimeType().equals("yyyy-WW")){

            List<TransTime> time_ww =  getSquTimeWeek(param.getStartTime(),param.getEndTime());
            List<TransTime> timedb_ww =  getSquTimeWeek(param.getStartTimedb(),param.getEndTimedb());
            int num = time_ww.size();
            if(time_ww.size() > timedb_ww.size()){
                num = timedb_ww.size();
            }
            for(int i=0;i<num;i++){
                SaleAndClientRequireParam temp_param = new SaleAndClientRequireParam();
                temp_param = param.clone();

                temp_param.setStartTime(time_ww.get(i).getStartTime());
                temp_param.setEndTime(time_ww.get(i).getEndTime());
                temp_param.setStartTimedb(timedb_ww.get(i).getStartTime());
                temp_param.setEndTimedb(timedb_ww.get(i).getEndTime());
                String title = time_ww.get(i).getTitle()+"("+temp_param.getStartTime()+"-"+temp_param.getEndTime()+")"+ "对比"
                        + timedb_ww.get(i).getTitle()+"("+temp_param.getStartTimedb()+"-"+temp_param.getEndTimedb()+")";
                temp_param.setDescTxt(title);
                res.add(temp_param);
            }

        }
        if(param.getTimeType().equals("yyyy-mm")){

            List<TransTime> time_ww =  getSquTimeMonth(param.getStartTime(),param.getEndTime());
            List<TransTime> timedb_ww =  getSquTimeMonth(param.getStartTimedb(),param.getEndTimedb());
            int num = time_ww.size();
            if(time_ww.size() > timedb_ww.size()){
                num = timedb_ww.size();
            }
            for(int i=0;i<num;i++){
                SaleAndClientRequireParam temp_param = new SaleAndClientRequireParam();
                temp_param = param.clone();

                temp_param.setStartTime(time_ww.get(i).getStartTime());
                temp_param.setEndTime(time_ww.get(i).getEndTime());
                temp_param.setStartTimedb(timedb_ww.get(i).getStartTime());
                temp_param.setEndTimedb(timedb_ww.get(i).getEndTime());
                String title = time_ww.get(i).getTitle()+"("+temp_param.getStartTime()+"-"+temp_param.getEndTime()+")"+ "对比"
                        + timedb_ww.get(i).getTitle()+"("+temp_param.getStartTimedb()+"-"+temp_param.getEndTimedb()+")";
                temp_param.setDescTxt(title);
                res.add(temp_param);
            }

        }

        if(param.getTimeType().equals("yyyy-q")){

            List<TransTime> time_ww =  getSquTimeSea(param.getStartTime(),param.getEndTime());
            List<TransTime> timedb_ww =  getSquTimeSea(param.getStartTimedb(),param.getEndTimedb());
            int num = time_ww.size();
            if(time_ww.size() > timedb_ww.size()){
                num = timedb_ww.size();
            }
            for(int i=0;i<num;i++){
                SaleAndClientRequireParam temp_param = new SaleAndClientRequireParam();
                temp_param = param.clone();

                temp_param.setStartTime(time_ww.get(i).getStartTime());
                temp_param.setEndTime(time_ww.get(i).getEndTime());
                temp_param.setStartTimedb(timedb_ww.get(i).getStartTime());
                temp_param.setEndTimedb(timedb_ww.get(i).getEndTime());
                String title = time_ww.get(i).getTitle()+"("+temp_param.getStartTime()+"-"+temp_param.getEndTime()+")"+ "对比"
                        + timedb_ww.get(i).getTitle()+"("+temp_param.getStartTimedb()+"-"+temp_param.getEndTimedb()+")";
                temp_param.setDescTxt(title);
                res.add(temp_param);
            }

        }


        return res;
    }
    @ResponseBody
    @RequestMapping("/ajaxSelectSaleAndClientIndexTbale")

    public ResponseObj ajaxSelectSaleAndClientIndexTbale(HttpServletRequest request,SaleAndClientRequireParam param){
        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        request.getSession().setAttribute("paramIndexTbale",param);
        responseObj.setObj(getIndexTableList(param));
        return responseObj;
    }

    @ResponseBody
    @RequestMapping("/ajaxSelectSaleAndClientIndexTbaleTwo")
    public ResponseObj ajaxSelectSaleAndClientIndexTbaleTwo(HttpServletRequest request,SaleAndClientRequireParam param){
        ResponseObj responseObj = new ResponseObj();
        List<ClientTableDTO> res = new ArrayList<>();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        request.getSession().setAttribute("paramIndexTbale",param);
        ClientTableDTO clientTableDTO = new ClientTableDTO();
        param.setDescTxt(param.getStartTime()+"-"+param.getEndTime() + " 对比 "+param.getStartTimedb()+"-"+param.getEndTimedb());
        //区间汇总
        clientTableDTO.setTitle(param.getDescTxt());
        clientTableDTO.setRes(getIndexTableList(param));
        res.add(clientTableDTO);
        //分段汇总
        List<SaleAndClientRequireParam> saleAndClientRequireParams = geneateSaleAndClientRequireParam(param);
        List<IndexTable> index_sort = null;
        for(int i=0;i<saleAndClientRequireParams.size();i++){

            SaleAndClientRequireParam temp_param = saleAndClientRequireParams.get(i);
            ClientTableDTO clientTableDTO_temp = new ClientTableDTO();
            clientTableDTO_temp.setTitle(temp_param.getDescTxt());
            if(i > 0){
                clientTableDTO_temp.setRes(getIndexTableList(temp_param,index_sort));
            }else{
                clientTableDTO_temp.setRes(getIndexTableList(temp_param));
                index_sort = clientTableDTO_temp.getRes();
            }

            res.add(clientTableDTO_temp);
        }

        //封装处理结果
        responseObj.setObj(res);

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
        if(param.getDbType().equals("0")){

        }
        String[] titleOne = {"门店ID","门店名称","业态","省区","销售（万元）","交易笔数","同比销售增长","毛利率","会员销售（万元）","客流","租户销售(万元)","日期"};
        String[] titleTwo = {"门店ID","门店名称","业态","省区","销售（万元）","去年销售","同比销售","交易笔数","去年交易笔数",
                "同比交易笔数","同比销售增长",
                "毛利率","去年毛利率","同比毛利率","会员销售（万元）","客流","租户销售(万元)","达成"};
        String fileName = "客流销售分析"+sdf.format(new Date())+".xls";
        String sheetName = "客流销售";
        String[][] content = new String[saleAndClient.size()][];
        HSSFWorkbook wb = null;
        if(param.getDbType().equals("0")){
            for (int i = 0; i < saleAndClient.size(); i++) {
                content[i] = new String[titleOne.length];
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
            wb = ExcelUtil.getHSSFWorkbook(sheetName, titleOne, content, null);
        }else{
            for (int i = 0; i < saleAndClient.size(); i++) {
                content[i] = new String[titleTwo.length];
                SaleAndClient obj = saleAndClient.get(i);
                content[i][0] = obj.getShopId();
                content[i][1] = obj.getShopName();
                content[i][2] = obj.getYt();
                content[i][3] = obj.getSq();
                content[i][4] = obj.getXsje()+"";
                content[i][5] = obj.getXsbsQn()+"";
                content[i][6] = obj.getXsjeTb()+"";
                content[i][7] = obj.getXsbs()+"";
                content[i][8] = obj.getXsbsQn()+"";
                content[i][9] = obj.getXsbsTb()+"";
                content[i][10] = obj.getXsjekbRate()+"";
                content[i][11] = obj.getMll()+"";
                content[i][12] = obj.getMllQn()+"";
                content[i][13] = obj.getMllTb()+"";
                content[i][14] = obj.getHyxs()+"";
                content[i][15] = obj.getKll()+"";
                content[i][16] = obj.getGmv()+"";
                content[i][17] = obj.getDcRate()+"";
            }
            wb = ExcelUtil.getHSSFWorkbook(sheetName, titleTwo, content, null);
        }

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

    public List<IndexTable> transfromToIndexList(List<IndexTable> saleAndClient){
        List<IndexTable> res = new ArrayList<>();
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if (temp.getKbType() != null && temp.getKbType().equals("1") && temp.getShopYt().equals("广场")) {
                res.add(temp);
            }
        }

        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("广场可比店")){
                res.add(temp);
            }
        }

        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getKbType() != null && temp.getKbType().equals("0") && temp.getShopYt().equals("广场")){
                res.add(temp);
            }
        }

        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("广场业态合计")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getKbType() != null && temp.getKbType().equals("1") && temp.getShopYt().equals("百货")){
                res.add(temp);
            }
        }

        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("百货可比店")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getKbType() != null && temp.getKbType().equals("0") && temp.getShopYt().equals("百货")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("百货业态合计")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getKbType() != null && temp.getKbType().equals("1") && temp.getShopYt().equals("生活广场")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("生活可比店")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getKbType() != null && temp.getKbType().equals("0") && temp.getShopYt().equals("生活广场")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("生活广场合计")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("可比店")){
                res.add(temp);
            }
        }
        for(int i=0;i<saleAndClient.size();i++) {
            IndexTable temp = saleAndClient.get(i);
            if(temp.getShopName().equals("全比店")){
                res.add(temp);
            }
        }
        return res;
    }

    public HSSFWorkbook getHSSFWorkbookByParam(ClientTableDTO clientTableDTO,HSSFWorkbook wb,String fileName){

        List<IndexTable> saleAndClient = transfromToIndexList(clientTableDTO.getRes());
        String[] title = {"","本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};

        String sheetName = "客流销售";
        String[][] content = new String[saleAndClient.size()][];
        for (int i = 0; i < saleAndClient.size(); i++) {
            content[i] = new String[title.length];
            IndexTable obj = saleAndClient.get(i);
            content[i][0] = obj.getShopName();
            content[i][1] = obj.getXsje()+"";
            content[i][2] = obj.getXsjeTq()+"";
            content[i][3] = obj.getXszz()+"";
            content[i][4] = obj.getJybs()+"";
            content[i][5] = obj.getJybsTq()+"";
            content[i][6] = obj.getJybsZz()+"";
            content[i][7] = obj.getKll()+"";
            content[i][8] = obj.getKllTq()+"";
            content[i][9] = obj.getKllZz()+"";
            content[i][10] = obj.getKdj()+"";
            content[i][11] = obj.getKdjTq()+"";
            content[i][12] = obj.getKdjZz()+"";
        }

        HSSFWorkbook resWb = ExcelUtil.getHSSFWorkbookAuto(sheetName, title, content, wb,clientTableDTO.getTitle());
        return resWb;
    }

    public HSSFWorkbook getHSSFWorkbookByParamTwo(ClientTableDTO clientTableDTO,HSSFWorkbook wb,String fileName){

        List<IndexTable> saleAndClient = transfromToIndexList(clientTableDTO.getRes());
        String[] title = {"","本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};

        String sheetName = "客流销售";
        String[][] content = new String[saleAndClient.size()][];
        for (int i = 0; i < saleAndClient.size(); i++) {
            content[i] = new String[title.length];
            IndexTable obj = saleAndClient.get(i);
            content[i][0] = obj.getShopName();
            content[i][1] = obj.getXsje()+"";
            content[i][2] = obj.getXsjeTq()+"";
            content[i][3] = obj.getXszz()+"";
            content[i][4] = obj.getJybs()+"";
            content[i][5] = obj.getJybsTq()+"";
            content[i][6] = obj.getJybsZz()+"";
            content[i][7] = obj.getKll()+"";
            content[i][8] = obj.getKllTq()+"";
            content[i][9] = obj.getKllZz()+"";
            content[i][10] = obj.getKdj()+"";
            content[i][11] = obj.getKdjTq()+"";
            content[i][12] = obj.getKdjZz()+"";
        }

        HSSFWorkbook resWb = ExcelUtil.getHSSFWorkbookAutoTwo(sheetName, title, content, wb,clientTableDTO.getTitle());
        //HSSFWorkbook resWb = ExcelUtil.getHSSFWorkbookAuto(sheetName, title, content, wb,clientTableDTO.getTitle());
        return resWb;
    }

    public HSSFWorkbook getHSSFWorkbookByParamTwo_07(ClientTableDTO clientTableDTO,HSSFWorkbook wb,String fileName){

        List<IndexTable> saleAndClient = transfromToIndexList(clientTableDTO.getRes());
        String[] title = {"","本期","同期","增长","本期","同期","增长","本期","同期","增长","本期","同期","增长"};

        String sheetName = "客流销售";
        String[][] content = new String[saleAndClient.size()][];
        for (int i = 0; i < saleAndClient.size(); i++) {
            content[i] = new String[title.length];
            IndexTable obj = saleAndClient.get(i);
            content[i][0] = obj.getShopName();
            content[i][1] = obj.getXsje()+"";
            content[i][2] = obj.getXsjeTq()+"";
            content[i][3] = obj.getXszz()+"";
            content[i][4] = obj.getJybs()+"";
            content[i][5] = obj.getJybsTq()+"";
            content[i][6] = obj.getJybsZz()+"";
            content[i][7] = obj.getKll()+"";
            content[i][8] = obj.getKllTq()+"";
            content[i][9] = obj.getKllZz()+"";
            content[i][10] = obj.getKdj()+"";
            content[i][11] = obj.getKdjTq()+"";
            content[i][12] = obj.getKdjZz()+"";
        }

        HSSFWorkbook resWb = ExcelUtil.getHSSFWorkbookAutoTwo(sheetName, title, content, wb,clientTableDTO.getTitle());
        return resWb;
    }


    //发送响应流方法
    @RequestMapping(value = "/exportClientTable")
    @ResponseBody
    public void exportClientTable(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = "客流销售分析"+sdf.format(new Date())+".xls";
        SaleAndClientRequireParam param = (SaleAndClientRequireParam) request.getSession().getAttribute("paramIndexTbale");
        List<ClientTableDTO> res = new ArrayList<>();
        ClientTableDTO clientTableDTO = new ClientTableDTO();
        param.setDescTxt(param.getStartTime()+"-"+param.getEndTime() + " 对比 "+param.getStartTimedb()+"-"+param.getEndTimedb());
        //区间汇总
        clientTableDTO.setTitle(param.getDescTxt());
        clientTableDTO.setRes(getIndexTableList(param));
        res.add(clientTableDTO);
        //分段汇总
        List<SaleAndClientRequireParam> saleAndClientRequireParams = geneateSaleAndClientRequireParam(param);
        /*for(int i=0;i<saleAndClientRequireParams.size();i++){
            SaleAndClientRequireParam temp_param = saleAndClientRequireParams.get(i);
            ClientTableDTO clientTableDTO_temp = new ClientTableDTO();
            clientTableDTO_temp.setTitle(temp_param.getDescTxt());
            clientTableDTO_temp.setRes(getIndexTableList(temp_param));
            res.add(clientTableDTO_temp);
        }*/
        List<IndexTable> index_sort = null;
        for(int i=0;i<saleAndClientRequireParams.size();i++){
            SaleAndClientRequireParam temp_param = saleAndClientRequireParams.get(i);
            ClientTableDTO clientTableDTO_temp = new ClientTableDTO();
            clientTableDTO_temp.setTitle(temp_param.getDescTxt());
            if(i > 0){
                clientTableDTO_temp.setRes(getIndexTableList(temp_param,index_sort));
            }else{
                clientTableDTO_temp.setRes(getIndexTableList(temp_param));
                index_sort = clientTableDTO_temp.getRes();
            }
            res.add(clientTableDTO_temp);
        }
        HSSFWorkbook wb = null;
        for(int i=0;i<res.size();i++){
            wb = getHSSFWorkbookByParam(res.get(i),wb,fileName);
        }

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

    @RequestMapping(value = "/exportClientTableTwo")
    @ResponseBody
    public void exportClientTableTwo(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = "客流销售分析"+sdf.format(new Date())+".xls";
        SaleAndClientRequireParam param = (SaleAndClientRequireParam) request.getSession().getAttribute("paramIndexTbale");
        List<ClientTableDTO> res = new ArrayList<>();
        ClientTableDTO clientTableDTO = new ClientTableDTO();
        param.setDescTxt(param.getStartTime()+"-"+param.getEndTime() + " 对比 "+param.getStartTimedb()+"-"+param.getEndTimedb());
        //区间汇总
        clientTableDTO.setTitle(param.getDescTxt());
        clientTableDTO.setRes(getIndexTableList(param));
        res.add(clientTableDTO);
        //分段汇总
        List<SaleAndClientRequireParam> saleAndClientRequireParams = geneateSaleAndClientRequireParam(param);
        for(int i=0;i<saleAndClientRequireParams.size();i++){
            SaleAndClientRequireParam temp_param = saleAndClientRequireParams.get(i);
            ClientTableDTO clientTableDTO_temp = new ClientTableDTO();
            clientTableDTO_temp.setTitle(temp_param.getDescTxt());
            clientTableDTO_temp.setRes(getIndexTableList(temp_param));
            res.add(clientTableDTO_temp);
        }
        HSSFWorkbook wb = null;
        for(int i=0;i<res.size();i++){
            wb = getHSSFWorkbookByParamTwo(res.get(i),wb,fileName);
        }

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


    @RequestMapping(value = "/exportClientTableThree")
    @ResponseBody
    public void exportClientTableThree(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = sdf.format(new Date())+".xlsx";
        SaleAndClientRequireParam param = (SaleAndClientRequireParam) request.getSession().getAttribute("paramIndexTbale");

        ClientTableDTO clientTableDTO = new ClientTableDTO();
        param.setDescTxt(param.getStartTime()+"-"+param.getEndTime() + " 对比 "+param.getStartTimedb()+"-"+param.getEndTimedb());
        //区间汇总
        clientTableDTO.setTitle(param.getDescTxt());
        clientTableDTO.setRes(getIndexTableList(param));

        SXSSFWorkbook wb = null;
        wb = getExcel_07(clientTableDTO,wb,fileName);

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

    public SXSSFWorkbook getExcel_07(ClientTableDTO clientTableDTO,SXSSFWorkbook wb,String fileName){

        List<IndexTable> saleAndClient = transfromToIndexList(clientTableDTO.getRes());

        if(wb == null){
            wb = new SXSSFWorkbook();
        }
        ExcelUtil_SEVEN.createSaleAndClient(wb,clientTableDTO.getTitle());

        return wb;
    }

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
