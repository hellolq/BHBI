package com.bbg;

import com.bbg.controller.BkclAction;
import com.bbg.mapper.bhbi.BhSaleDcMapper;
import com.bbg.mapper.bkcl.BkclMapper;
import com.bbg.mapper.mobileoa.RkRiskInterfaceMapper;
import com.bbg.pojo.DataDealDTO;
import com.bbg.service.BhSaleDcService;
import com.bbg.tools.BbgSendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BhbiApplicationTests {


	@Autowired
	BhSaleDcService bhSaleDcService;

	@Autowired
	BhSaleDcMapper bhSaleDcMapper;

	@Test
	public void test3() {
		String body = "{\"data\":{\"bill\":{\"orderPrice\":3,\"wareTotal OrigPrice\":8},\"merchant_contribute\":0,\"netAmt\":3,\"orderId\":\"183392332007\",\"orderPmt\":[],\"orderType\":\"BH\",\"otherPayOrderNo\":\"4200000190201812056384173274\",\"other_contrib ute\":0,\"payChannel\":\"WX\",\"payId\":\"183392332007\",\"payOrderNo\":\"18120518032051\",\"payType\":\"1\",\"unitPayOrderNo\":\"201812052100000015230\",\"wares\":[{\"count\":1,\"discountAmount \":2,\"discountPrice\":2,\"lineno\":0,\"originalPrice\":2,\"proQty\":0,\"wareCode\":\"1036696\",\"warePrice\":2,\"weight\":0.0},{\"count\":3,\"discountAmount\":1,\"discountPrice\":0,\"lineno\":1,\"originalPrice\":6,\"proQty\":3,\"wareCode\":\"1024591\",\"warePrice\":2,\"weight\":0.0}]},\"member\":{\"birth\":\"\",\"birthFlag\":\"Y\",\"cardNo\":\"661500000008063969\",\"point\":\"10\",\"siebe lId\":\"1-9651VS4\",\"status\":\"Active\",\"tier\":\"银卡\"},\"storeCode\":\"012018\",\"traceId\":\"ff7d65f7-b3ec-4ad6-a377-e649c2a92588\"}";
		//bhSaleDcService.pushDcMessageToWx_send_message();
		bhSaleDcService.sendMessageToAdmin();
		//bhSaleDcService.sendMessageToEmp();
		//String shopId = bhSaleDcMapper.selectEmpIdByShopId("012018");
		//System.out.println(shopId);
		//bhSaleDcMapper.call_dc_month_check();
	}

}
