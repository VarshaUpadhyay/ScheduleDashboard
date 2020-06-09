[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.varshaupadhyay/schedule-dashboard/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.varshaupadhyay/schedule-dashboard/)

# ScheduleDashboard
This repository will create a dashboard for schedules in your java application.
From the schedule dashboard you can monitor your schedule and also you can run a particular schedule before its next run time.

# Working Example

```
@Component
@EnableScheduling
@EnableScheduleDashboard
@ComponentScan(basePackageClasses = ApplicationController.class)
public class ScheduleTest{
	@Scheduled(fixedDelay = 20000)
	public void test1() {
		System.out.println("test1 is running.....");
	}
	}
  ```
  Go to (http://your_host:your_port/schedule) to access dashboard.
  
  
## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
