package com.bbg.controller;

import com.bbg.mapper.ClientAndSaleMapper;
import com.bbg.mapper.FloorClientMapper;
import com.bbg.pojo.*;
import com.bbg.tools.ExcelUtil;
import com.bbg.tools.ExcelUtil_SEVEN;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H1N1 on 2018/9/28.
 */
@Controller
public class FloorClientAction {

    @Autowired
    private ClientAndSaleMapper clientAndSaleMapper;

    @Autowired
    private FloorClientMapper floorClientMapper;



   /*
   * 将List<FloorKllDTO> 转化为 List<String[]>
   * */
   public List<String[]> transfromFloorKllDTOToArray(List<FloorKllDTO> res){
       List<String[]> result = new ArrayList<>();
       for(int i=0;i<res.size();i++){
          String[] temp_array = new String[7];
          FloorKllDTO temp_obj = res.get(i);
          temp_array[0]=temp_obj.getShopName();//门店名称
           temp_array[1]=temp_obj.getFloorName();//楼层名称
           temp_array[2]=temp_obj.getDoorId();//点位ID
           temp_array[3]=temp_obj.getDoorName();//点位名称
           temp_array[4]=temp_obj.getIncount()+"";//进店人数
           temp_array[5]=temp_obj.getIncountCom()+"";//进店人数-同期
           temp_array[6]=temp_obj.getGrowth();//增长
           result.add(temp_array);
       }
       return result;
   }

    /*
    *查询结果导出
    * */
    @RequestMapping(value = "/exportFloorKll")
    @ResponseBody
    public void exportClientTableTwo(HttpServletRequest request, HttpServletResponse response){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileName = "客流"+sdf.format(new Date())+".xlsx";
            String[] heads = {"门店","楼层","点位ID","点位名称","进店人数","同期","增长"};
            short[] cellSize ={4000,3000,4000,8000,4000,4000,4000};
                    FloorClientParam floorKllParam = (FloorClientParam)request.getSession().getAttribute("floorKllParam");
            List<FloorKllDTO> floorKllDTOS = formateFloorKllDTO(floorClientMapper.selectFloorKll(floorKllParam));
            List<String[]> bodys = transfromFloorKllDTOToArray(floorKllDTOS);

            SXSSFWorkbook wb = ExcelUtil_SEVEN.wb07(fileName,heads,cellSize,bodys);
            ExcelUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/toFloorKllPage")
    public String selectSaleAndClientTable(ModelMap map){

        //获取省区列表
        List<String> sqList = clientAndSaleMapper.selectSqList();
        map.addAttribute("sqList",sqList);

        //获取所有门店
        List<ShopInfo> shops = clientAndSaleMapper.selectAllShop();
        map.addAttribute("page_shops",shops);
        return "floor_kll";
    }

   /*
   * 格式化 List<FloorKllDTO>
   * */
   public List<FloorKllDTO>  formateFloorKllDTO( List<FloorKllDTO> res){
       DecimalFormat df = new DecimalFormat("#0.##%");
       for(int i=0;i<res.size();i++){
           FloorKllDTO temp = res.get(i);
           double growth = 0;
           if(temp.getIncountCom() > 0){
               growth = (temp.getIncount() - temp.getIncountCom())/temp.getIncountCom();
           }
           temp.setGrowth(df.format(growth));

       }
       return res;
   }


    @ResponseBody
    @RequestMapping("/ajaxGetFloorClient")
    public ResponseObj ajaxGetFloorClient(FloorClientParam param,HttpServletRequest request){
        ResponseObj responseObj = new ResponseObj();
        responseObj.setStatus("200");
        responseObj.setObj("SUCCESS");
        List<FloorKllDTO> floorKllDTOS = formateFloorKllDTO(floorClientMapper.selectFloorKll(param));
        request.getSession().setAttribute("floorKllParam",param);
        responseObj.setObj(floorKllDTOS);
        return responseObj;
    }


}
