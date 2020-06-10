package org.dash.ScheduleDashboard;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.dash.ScheduleDashboard.aspect.ScheduleAspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Varsha
 *
 */
@Controller
@RequestMapping(value = "/schedule")
public class ApplicationController {

	/**
	 * @param model
	 * @return Dashboard html
	 */
	@GetMapping
	public String dashBoard(Model model) {
		model.addAttribute("ScheduleInfo", ScheduleAspect.dashboardData);
		return "dashboard";
	}

	/**
	 * 
	 * @param info
	 * @param model
	 * @return String
	 * 
	 *         This API will run selected schedule before next runtime
	 */
	@RequestMapping(value = "/run", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> runSchedule(@RequestBody HashMap<String, Object> info, Model model) {
		String response = null;
		HashMap<String, String> resp = new HashMap<>();
		try {
			Method[] methods = ScheduleAspect.scheduleClassObj.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (info.get("methodName").toString().equals(methods[i].getName())) {
					methods[i].invoke(ScheduleAspect.scheduleClassObj);
					response = "success";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = "Failed";
		}
		resp.put("msg", response);
		return resp;
	}

}
