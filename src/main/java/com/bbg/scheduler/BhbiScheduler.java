package com.bbg.scheduler;


import com.bbg.service.BhSaleDcService;
import com.bbg.service.RealTimeSaleDataSycService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@Component 
@EnableScheduling 
public class BhbiScheduler {

	@Autowired
	private RealTimeSaleDataSycService realTimeSaleDataSycService;
	@Autowired
	private BhSaleDcService bhSaleDcService;
	private final static Logger logger = (Logger) LoggerFactory.getLogger(BhbiScheduler.class);

	public void update_LP_REAL_BFXS_HZ(){  
		try{
			long start_time = System.currentTimeMillis();
			realTimeSaleDataSycService.beginDeal();
			long end_time =  (System.currentTimeMillis() - start_time)/1000;
	 	  logger.info("execute time : {} 秒",end_time);
			//logger.info("此为实时报表同步程序，发布正式环境记得打开注释");
		}catch(Exception e){
			logger.error("Exception Message :{}",e);
		}
    }

	public void ckeck_message(){
		try{

			//bhSaleDcService.execute_find_message();
			logger.info("执行execute_find_message,没5分钟执行一次");

		}catch(Exception e){
			logger.error("ckeck_dc_message :{}",e);
		}
	}

}
