package org.dash.ScheduleDashboard;

import org.dash.ScheduleDashboard.annotation.EnableScheduleDashboard;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Varsha
 *  
 *  Purpose of this class is to test all type of schedule
 *
 */
//@Component
//@EnableScheduling
//@EnableScheduleDashboard
public class ScheduleTest {

	@Scheduled(fixedDelay = 20000)
	public void test1() {
		System.out.println("test1 is running.....");
	}

	@Scheduled(fixedDelayString = "30000")
	public void test2() {
		System.out.println("test2 is running.....");
	}
	
	@Scheduled(cron=" 0/30 * * * * ? ",zone="Europe/Copenhagen")
	public void test3() {
		System.out.println("test3 is running.....");
	}
	
	@Scheduled(fixedRate=10000)
	public void test4() {
		System.out.println("test4 is running.......");
	}
	
	@Scheduled(fixedRate=50000)
	public void test5() {
		System.out.println("test5 is running.......");
	}

}
