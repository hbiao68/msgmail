package adtec.util.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzDemo {

	Logger log = Logger.getLogger(QuartzDemo.class);
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info(("开始执行报表的业务逻辑了----现在的时间是--"+new Date()));		
	}
	
	public void aa() throws JobExecutionException {
		log.info(("开始执行报表的业务逻辑了----现在的时间是--"+new Date()));
		System.out.println("sssssssssssssssssssssssssssssssssssss");
	}

}
