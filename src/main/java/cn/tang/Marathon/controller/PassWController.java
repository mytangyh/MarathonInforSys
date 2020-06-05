
package cn.tang.Marathon.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.pojo.Admin;
import cn.tang.Marathon.pojo.User;
import cn.tang.Marathon.service.AdminService;
import cn.tang.Marathon.service.UserService;





@RequestMapping("/editpw")
@Controller
public class PassWController {
@Autowired
private UserService userService;
	@Autowired AdminService adminService;
	@RequestMapping(value = "/show",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("editpw/editpw");
		return model;	
	}
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(newpassword)){
			ret.put("type", "error");
			ret.put("msg", "请填写新密码 ！");
			return ret;
		}
		User user = (User) request.getSession().getAttribute("user");
		int usertype = (int) request.getSession().getAttribute("userType");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误");
			return ret;
		}
		if (usertype==1) {
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			admin.setPassword(newpassword);
			if(adminService.edit_pwd(admin)<= 0){
				ret.put("type", "error");
				ret.put("msg", "修改失败");
				return ret;
			}
		}else {
			user.setPassword(newpassword);
			if (userService.edit_pwd(user)<=0) {
				ret.put("type", "error");
				ret.put("msg", "修改失败");
				return ret;
			}
		}
		
		
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		
		return ret;
	} 
}
