
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
import cn.tang.Marathon.service.UserService;

/**
 * 管理员
 * @author Tang
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	/**
	 * 用戶列表
	 * @param model
	 * @return
	 */
	@Autowired
	public UserService userService;
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("user/user_list");
		return model;
		
		
	}
	/**
	 * 获取用户列表
	 * @param username
	 * @param page1
	 * @return
	 */
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value = "username",required = false,defaultValue = "") String username,
			Page page1) {
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("username", "%"+username+"%");
		queryMap.put("offset", page1.getOffset());
		queryMap.put("pageSize", page1.getRows());
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", userService.getTotal(queryMap));
		return ret;
		
	}
	/**
	 * 添加用户
	 * @param aduser
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User aduser) {
		Map<String, String> ret=new HashMap<String, String>();
		if (aduser==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		if (StringUtils.isEmpty(aduser.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(aduser.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		User existUser = userService.findByUserName(aduser.getUsername());
		if (existUser!=null) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在!");
			return ret;
		}
		if (userService.add(aduser)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		//System.out.println(aduser.getUsername());
		return ret;
		
	}
	/**
	 * 编辑用户
	 * @param aduser
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user) {
		Map<String, String> ret=new HashMap<String, String>();
		if (user==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		User existUser = userService.findByUserName(user.getUsername());
		if (existUser!=null) {
			if (user.getId()!=existUser.getId()) {
				ret.put("type", "error");
				ret.put("msg", "该用户名已存在!");
				return ret;
			}
			
		}
		if (userService.edit(user)<=0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		//System.out.println(aduser.getUsername());
		return ret;
		
	}
	/**
	 * 删除
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value = "ids[]",required = true) Long[] ids) {
		Map<String, String> ret=new HashMap<String, String>();
		
		if (ids ==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据！");
			return ret;
		}
		if (userService.delete(ids)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
		
	}
	
}
