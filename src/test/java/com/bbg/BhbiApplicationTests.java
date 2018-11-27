package com.bbg;

import com.bbg.controller.BhSaleDcAction;
import com.bbg.controller.BkclAction;
import com.bbg.mapper.bhbi.BhSaleDcMapper;
import com.bbg.mapper.bkcl.BkclMapper;
import com.bbg.mapper.mobileoa.RkRiskInterfaceMapper;
import com.bbg.pojo.BfclDTO;
import com.bbg.pojo.DataDealDTO;
import com.bbg.pojo.RK_RISK_INTERFACE;
import com.bbg.tools.BbgSendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BhbiApplicationTests {

	@Autowired
	private BhSaleDcMapper bhSaleDcMapper;

	@Autowired
	BkclAction bkclAction;

	@Autowired
	BhSaleDcAction bhSaleDcAction;

	@Autowired
	RkRiskInterfaceMapper rkRiskInterfaceMapper;

	@Autowired
	private JdbcTemplate bhbiJdbcTemplate;

	@Autowired
	BkclMapper bkclMapper;


	@Test
	public void test4() {
		String source = "{ \"data\": { \"bill\": { \"orderPrice\": 2900, \"wareTotalOrigPrice\": 3990 }, \"merchant_contribute\": 0, \"netAmt\": 2900, \"orderId\": \"183302202504\", \"orderPmt\": [ { \"dmno\": \"0\", \"lineno\": 0, \"pmtamt\": 1090, \"pmtbill\": \"223374036510241\", \"pmtbill2\": \"22336106\", \"pmtmemo\": \"siebel券\", \"pmttype\": \"50\", \"pmtuomamt\": 1090, \"skuCount\": 1, \"wareCode\": \"107340312\" } ], \"orderType\": \"BH\", \"otherPayOrderNo\": \"4200000208201811268072325691\", \"other_contribute\": 0, \"payChannel\": \"WX\", \"payId\": \"183302202504\", \"payOrderNo\": \"18112617002429\", \"payType\": \"1\", \"unitPayOrderNo\": \"201811262100004629063\", \"wares\": [ { \"count\": 1, \"discountAmount\": 2900, \"discountPrice\": 2900, \"lineno\": 0, \"originalPrice\": 3990, \"proQty\": 1, \"wareCode\": \"107340312\", \"warePrice\": 3990, \"weight\": 0 } ] }, \"member\": { \"birth\": \"1979-06-22\", \"birthFlag\": \"Y\", \"cardNo\": \"881100000000257440\", \"point\": \"141\", \"siebelId\": \"1-3EJER13\", \"status\": \"Active\", \"tier\": \"金卡\" }, \"storeCode\": \"120236\", \"traceId\": \"1f02f430-a996-47c6-8a7e-a326823962a2\" }";

	}

	@Test
	public void test3() {
		bhSaleDcAction.pushDcMessageToWx_send_message();
		bhSaleDcAction.sendMessageToAdmin();
		bhSaleDcAction.sendMessageToEmp();
	}

	@Test
	public void test2() throws UnsupportedEncodingException {
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
						+"<a href='"+url_view+"'>确认推送</a>";
				result_temp.setMessage_content_admin(msg_content_admin);
				result_temp.setAdmin_empid("1000063573");
				result_temp.setReceive_empid("1000063573");
				result_temp.setSend_status("0");
				result_temp.setTime_one(msg_time);
				result_temp.setTime_two("");
				result_temp.setTime_three("");
				result_temp.setSend_message("");
				result_temp.setNote("");
				bhSaleDcMapper.insertIntoMessagePush(result_temp);
			}
		}
		System.out.println(res.size());
	}

	@Test
	public void test_bhSaleDcAction() {
		//bhSaleDcAction.sendMessageToAdmin();
		bhSaleDcAction.sendMessageToEmp();
	}

	@Test
	public void test_Update() {
		DataDealDTO model = new DataDealDTO();
		model.setMessage_id("month_201811_012018");
		model.setSend_message("12312312");
		bhSaleDcMapper.updateWxSendMessage(model);
	}


	@Test
	public void test() {
		DataDealDTO model = new DataDealDTO();
		model.setMessage_id("month_201811_012018");
		model.setSend_status("PUSHING");
		int  result = bhSaleDcMapper.updateWxSendMessage(model);
		System.out.println(result);
	}
	@Test
	public void contextLoads() {
		Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld1");
		logger.debug("Hello world.");
		DataDealDTO model = new DataDealDTO();
		model.setSend_status("1");
		List<DataDealDTO> result = bhSaleDcMapper.getWxSendMessage(model);
		DataDealDTO one = result.get(0);
		String message = one.getMessage_content();
		String return_message = "";
		try {
			return_message = BbgSendMessage.sendMessage("1000063573",message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(return_message);
		System.out.println(message);
	}



}
