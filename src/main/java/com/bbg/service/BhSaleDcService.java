package com.bbg.service;

import com.bbg.mapper.bhbi.BhSaleDcMapper;
import com.bbg.pojo.DataDealDTO;
import com.bbg.tools.BbgSendMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by H1N1 on 2018/11/28.
 */
@Service
public class BhSaleDcService {

    @Autowired
    private BhSaleDcMapper bhSaleDcMapper;

    public void execute_find_message(){
       //pushDcMessageToWx_send_message();
        bhSaleDcMapper.call_dc_month_check();
        sendMessageToAdmin();
       //sendMessageToEmp();

    }

    public void pushDcMessageToWx_send_message(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        List<DataDealDTO> res = bhSaleDcMapper.getXsdcJkOne();
        for(int i=0;i<res.size();i++){
            DataDealDTO result_temp = new DataDealDTO();
            DataDealDTO temp = res.get(i);
            result_temp.setMessage_id(temp.getJk_id());
            result_temp.setMessage_type(temp.getJk_type());
            String message_content = "";
            if(temp.getJk_type().equals("MONTH_SHOP")){
                DataDealDTO shopName = bhSaleDcMapper.getShopByShopId(temp.getJk_val());
                String receive_empId = bhSaleDcMapper.selectEmpIdByShopId(temp.getJk_val());
                String msg_time = sdf.format(new Date());
                int msg_num =  bhSaleDcMapper.getDcNum(temp.getJk_type());
                String url_view = "http://ct.bbg.com.cn/OAWSSMS/bhTwo/ajaxAdminPushMessage.action?message_id="+temp.getJk_id();
                String msg_content = "【月度计划达成】"
                        +shopName.getShop_name()+"于"
                        +msg_time
                        +"完成月度销售计划，全司共有"
                        +msg_num
                        +"家门店完成月度销售计划\n"
                        +"<a href='http://wx.bbg.com.cn/wxent/acct/reception/getGamsMeunsUrls.do?key=BH_SS'>点击查看详情</a>";
                result_temp.setMessage_content(msg_content);
                String msg_content_admin = "【月度计划达成-数据校对】"
                        +shopName.getShop_name()+"于"
                        +msg_time
                        +"完成月度销售计划，全司共有"
                        +msg_num
                        +"家门店完成月度销售计划\n"
                        //+url_view;
                        +"<a href='"+url_view+"'>确认群发</a>";
                result_temp.setMessage_content_admin(msg_content_admin);
                //T8507020019
                result_temp.setAdmin_empid("T8507020019");
                result_temp.setReceive_empid(receive_empId);
                result_temp.setSend_status("0");
                result_temp.setTime_one(msg_time);
                result_temp.setTime_two("");
                result_temp.setTime_three("");
                result_temp.setSend_message("");
                result_temp.setNote("");

                int result_a = bhSaleDcMapper.updateXsdc_jkById(result_temp.getMessage_id());
                if(result_a > 0){
                    bhSaleDcMapper.insertIntoMessagePush(result_temp);
                }
            }
        }
    }




    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true,value="bhbiTransactionManager" )
    public void sendMessageByList(List<DataDealDTO> wxSendMessage,String statusOne,String statusTwo){
        ObjectMapper mapper = new ObjectMapper();
        for(int i=0;i<wxSendMessage.size();i++){
            DataDealDTO temp = wxSendMessage.get(i);

            DataDealDTO model = new DataDealDTO();
            model.setMessage_id(temp.getMessage_id());
            model.setSend_status(statusOne);
            int  send_num = bhSaleDcMapper.updateWxSendMessage(model);

            String return_message = "";
            if(send_num > 0){
                try {
                    if(temp.getSend_status().equals("0")){
                        return_message = BbgSendMessage.sendMessage(temp.getAdmin_empid(),temp.getMessage_content_admin());
                    }

                    if(temp.getSend_status().equals("1")){
                        return_message = BbgSendMessage.sendMessage(temp.getReceive_empid(),temp.getMessage_content());
                    }

                    if(!StringUtils.isEmpty(return_message)){
                        JsonNode rootNode = mapper.readTree(return_message);
                        String status = rootNode.path("status").asText();
                        if(status.equals("0")){
                            model.setSend_status(statusTwo);
                            model.setSend_message(return_message);
                            bhSaleDcMapper.updateWxSendMessage(model);
                        }else{
                            model.setSend_status("fail");
                            model.setSend_message(return_message);
                            bhSaleDcMapper.updateWxSendMessage(model);
                        }
                    }


                } catch (Exception e) {
                    DataDealDTO model_e = new DataDealDTO();
                    model_e.setMessage_id(temp.getMessage_id());
                    model_e.setNote(e.getMessage());
                    model_e.setSend_message(e.getMessage());
                    model_e.setSend_status("error");
                    bhSaleDcMapper.updateWxSendMessage(model_e);
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    * 向管理员推送信息
    * */
    public void sendMessageToAdmin(){
        DataDealDTO model = new DataDealDTO();
        model.setSend_status("0");
        List<DataDealDTO> wxSendMessage = bhSaleDcMapper.getWxSendMessage(model);
        String statusOne = "pushToAdmin";
        String statusTwo = "admin_trail";
        sendMessageByList(wxSendMessage,statusOne,statusTwo);

    }

    /*
   * 向有权限员工推送信息
   * */
    public void sendMessageToEmp(){
        DataDealDTO model = new DataDealDTO();
        model.setSend_status("1");
        List<DataDealDTO> wxSendMessage = bhSaleDcMapper.getWxSendMessage(model);
        String statusOne = "pushToEmp";
        String statusTwo = "success";
        sendMessageByList(wxSendMessage,statusOne,statusTwo);

    }

}
