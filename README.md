# ScheduleDashboard
This repository will create a dashboard for schedules in your java application.
From the schedule dashboard you can monitor your schedule and also you can run a particular schedule before its next run time.

# Working Example

@Component
@EnableScheduling
@EnableScheduleDashboard
public class ScheduleTest {

	@Scheduled(fixedDelay = 20000)
	public void test1() {
		System.out.println("test1 is running.....");
	}
  }
  
  
 # Go to (http://<host>:<port>/schedule) to access dashboard.
  
  
