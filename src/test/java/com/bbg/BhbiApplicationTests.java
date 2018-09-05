package com.bbg;

import com.bbg.mapper.ClientAndSaleMapper;
import com.bbg.pojo.IndexTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*@RunWith(SpringRunner.class)*/
@SpringBootTest
public class BhbiApplicationTests {

	@Autowired
	private ClientAndSaleMapper clientAndSaleMapper;

	@Test
	public void contextLoads() {
		List<IndexTable> result;
		result = clientAndSaleMapper.selectIndexTable(null);
		System.out.println(result);
	}

	@Test
	public void hello() throws Exception {
    String startTime = "20180901";
    String endTime = "20180904";
    List<String> time = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date startTime_date = sdf.parse(startTime);
		Date endTime_date = sdf.parse(endTime);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setFirstDayOfWeek(Calendar.MONDAY);
		tempStart.setTime(startTime_date);
		while (startTime_date.getTime() <= endTime_date.getTime()) {
			time.add(sdf.format(startTime_date));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			startTime_date = tempStart.getTime();
			System.out.println(tempStart.get(Calendar.WEEK_OF_YEAR));
		}
		//System.out.println(time.toString());
	}

}
