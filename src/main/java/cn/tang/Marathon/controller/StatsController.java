/**
 * 
 */
package cn.tang.Marathon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.pojo.Stats;
import cn.tang.Marathon.service.GameService;

/**
 * @author Tang
 *
 */
@RequestMapping("/stats")
@Controller
public class StatsController {
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("stats/stats_show");
		
		return model;

	}
	@RequestMapping(value = "/get_data", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(){
		Map<String, Object> ret=new HashMap<String, Object>();
		List<Integer> valueList = new ArrayList<Integer>();
		Stats stats = gameService.getStats();
		
		valueList.add(stats.getQuan());
		valueList.add(stats.getBan());
		valueList.add(stats.getTen());
		ret.put("valueList", valueList);
		
		return ret;
		
	}
}
