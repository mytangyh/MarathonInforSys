
package cn.tang.Marathon.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.page.Page;
import cn.tang.Marathon.pojo.User;
import cn.tang.Marathon.service.NoticeService;
import cn.tang.Marathon.service.UserService;


@RequestMapping("/notice")
@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("notice/notice_list");
		return model;
		
		
	}
	
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page) {
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows",noticeService.findList(queryMap) );
		ret.put("total", noticeService.getTotal(queryMap));		
		return ret;
		
	}
	
	
}
