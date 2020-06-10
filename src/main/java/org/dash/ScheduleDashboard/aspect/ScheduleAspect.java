package org.dash.ScheduleDashboard.aspect;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Varsha
 * 
 * This Aspect class will be called whenever any method has annotation @Scheduled within a 
 * class has annotation @EnableScheduleDashboard starts running. 
 *
 */
@Aspect
@Component
public class ScheduleAspect {

	static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static List<HashMap<String, Object>> dashboardData = new ArrayList<>();
	public static Object scheduleClassObj = null;

	@Around("@within(org.dash.ScheduleDashboard.annotation.EnableScheduleDashboard) &&  @annotation(org.springframework.scheduling.annotation.Scheduled)")
	public void getAllSchedules(ProceedingJoinPoint joinPoint) throws Throwable {
		long scheduleTime = 0;
		HashMap<String, Object> scheduleInfo = new HashMap<>();
		scheduleClassObj = joinPoint.getThis();

		Method[] methods = joinPoint.getThis().getClass().getSuperclass().getMethods();
		for (Method method : methods) {
			if (joinPoint.getSignature().getName().equals(method.getName())) {
				Scheduled schedule = method.getAnnotation(Scheduled.class);
				Date cronDate = null;
				if (!schedule.cron().equals("")) {
					CronSequenceGenerator generator = null;
					if (!schedule.zone().equals("")) {
						generator = new CronSequenceGenerator(schedule.cron(), TimeZone.getTimeZone(schedule.zone()));
					} else
						generator = new CronSequenceGenerator(schedule.cron());
					cronDate = generator.next(new Date());
				} else if (schedule.fixedDelay() != -1L)
					scheduleTime = schedule.fixedDelay();
				else if (!schedule.fixedDelayString().equals(""))
					scheduleTime = Long.parseLong(schedule.fixedDelayString());
				else if (schedule.fixedRate() != -1L)
					scheduleTime = schedule.fixedRate();
				else if (!schedule.fixedRateString().equals(""))
					scheduleTime = Long.parseLong(schedule.fixedRateString());

				String currentTime = pattern.format(LocalDateTime.now());
				String nextRunTime = null;
				if (null != cronDate)
					nextRunTime = formatter.format(cronDate);
				else
					nextRunTime = pattern.format(LocalDateTime.now().plusSeconds(scheduleTime / 1000));

				for (int i = 0; i < dashboardData.size(); i++) {
					if (dashboardData.get(i).containsValue(joinPoint.getSignature().getName())) {
						dashboardData.remove(dashboardData.get(i));
						break;
					}
				}

				scheduleInfo.put("methodName", joinPoint.getSignature().getName());
				scheduleInfo.put("status", "Running...");
				scheduleInfo.put("lastRunTime", currentTime);
				scheduleInfo.put("nextRunTime", nextRunTime);

				dashboardData.add(scheduleInfo);
				break;

			}
		}
		joinPoint.proceed();
		for (int i = 0; i < dashboardData.size(); i++) {
			if (dashboardData.get(i).containsValue(joinPoint.getSignature().getName())) {
				dashboardData.get(i).computeIfPresent("status", (k, v) -> "Stopped");
				break;
			}
		}

	}
}
