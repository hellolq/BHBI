package com.bbg;

import com.bbg.mapper.ClientAndSaleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BhbiApplicationTests {

	@Autowired
	private ClientAndSaleMapper clientAndSaleMapper;

	@Test
	public void contextLoads() {
		List<String> result = clientAndSaleMapper.selectSqList();
		System.out.println(result);
	}

}
