package com.bbg;

import com.bbg.mapper.bhbi.ClientAndSaleMapper;
import com.bbg.mapper.bhbi.FloorClientMapper;
import com.bbg.mapper.mobileoa.RkRiskInterfaceMapper;
import com.bbg.pojo.IndexTable;
import com.bbg.pojo.RK_RISK_INTERFACE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BhbiApplicationTests {

	@Autowired
	private RkRiskInterfaceMapper rkRiskInterfaceMapper;

	@Autowired
	private FloorClientMapper floorClientMapper;



	@Test
	public void contextLoads() {
		List<RK_RISK_INTERFACE> result;
		result = rkRiskInterfaceMapper.selectAll();
		System.out.println(result.size());
	}



}
