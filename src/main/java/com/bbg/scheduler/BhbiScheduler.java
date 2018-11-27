package com.bbg.scheduler;


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
	private final static Logger logger = (Logger) LoggerFactory.getLogger(BhbiScheduler.class);

	public void update_LP_REAL_BFXS_HZ(){  
		try{
			long start_time = System.currentTimeMillis();
			realTimeSaleDataSycService.beginDeal();
			long end_time =  (System.currentTimeMillis() - start_time)/1000;
	 	  logger.info("execute time : {} 秒",end_time);
		}catch(Exception e){
			logger.error("Exception Message :{}",e);
		}
    } 

}
