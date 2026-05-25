[![Maven Central](https://img.shields.io/maven-central/v/com.github.varshaupadhyay/schedule-dashboard.svg)](https://central.sonatype.com/artifact/com.github.varshaupadhyay/schedule-dashboard)

# ScheduleDashboard

ScheduleDashboard is a lightweight Spring Boot dashboard for monitoring and managing Spring Batch scheduled jobs in Java applications.

It uses AspectJ for tracking and intercepting scheduled job executions and Thymeleaf for rendering the dashboard UI. The dashboard provides real-time visibility into scheduled tasks, execution monitoring, and manual triggering capabilities directly from the interface.

---

## Features

* Monitor scheduled jobs in real time
* View job execution details
* Manually trigger scheduled jobs
* Lightweight and easy to integrate
* Built for Spring Boot applications

---

## Maven Dependency

```xml
<dependency>
    <groupId>com.github.varshaupadhyay</groupId>
    <artifactId>schedule-dashboard</artifactId>
    <version>1.0.2</version>
</dependency>
```

---

## Usage

```java
@Component
@EnableScheduling
@EnableScheduleDashboard
@ComponentScan(basePackageClasses = ApplicationController.class)
public class ScheduleTest {

    @Scheduled(fixedDelay = 20000)
    public void test1() {
        System.out.println("test1 is running...");
    }
}
```

---

## Access Dashboard

After starting your application, open:

```text
http://your_host:your_port/schedule
```

## Dashboard Preview

<img width="800" alt="Schedule Dashboard Preview" src="https://github.com/user-attachments/assets/57270a0c-438b-448a-8ca3-b2bb68dc494b" />

---

## Tech Stack

* Java
* Spring Boot
* Spring Scheduling
* AspectJ
* Thymeleaf
* Maven

--- 
  
## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
