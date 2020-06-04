package cn.tang.Marathon.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.pojo.Admin;
import cn.tang.Marathon.pojo.PlayerLogin;
import cn.tang.Marathon.pojo.User;
import cn.tang.Marathon.service.AdminService;
import cn.tang.Marathon.service.PlayerService;
import cn.tang.Marathon.service.UserService;
import cn.tang.Marathon.util.CpachaUtil;

@RequestMapping("/system")
@Controller
public class SystemController {
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private AdminService adminService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/main");
		
		return model;

	}

	/**
	 * 登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");

		return model;
	}
	@RequestMapping(value = "/login_out", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request) {
		request.getSession().setAttribute("user",null);

		return "redirect:login";
	}
	/**
	 * 登录表单提交
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(

			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "vcode", required = true) String vcode,
			@RequestParam(value = "type", required = true) int type,HttpServletRequest request) {

		Map<String, String> ret = new HashMap<String, String>();

		if (StringUtils.isEmpty(username)) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(password)) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(vcode)) {
			ret.put("type", "error");
			ret.put("msg", "验证码不能为空");
			return ret;
		}
		String loginCpacha = (String) request.getSession().getAttribute("loginCpacha");
		if (StringUtils.isEmpty(loginCpacha)) {
			ret.put("type", "error");
			ret.put("msg", "长时间未操作，会话已失效，请刷新后重试！");
			return ret;
		}
		if (!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {//转为大写判断是否相同
			ret.put("type", "error");
			ret.put("msg", "验证码错误");
			return ret;
		}
		request.getSession().setAttribute("loginCpacha", null);
		//操作数据库
		if (type==1) {//管理员
			Admin admin = adminService.findByUserName(username);
			if (admin==null) {
				ret.put("type", "error");
				ret.put("msg", "用户不存在");
				return ret;
			}
			if (!password.equals(admin.getPassword())) {
				ret.put("type", "error");
				ret.put("msg", "密码错误");
				return ret;
			}
			User user = new User();
			user.setUsername(admin.getUsername());
			user.setPassword(admin.getPassword());
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("admin",admin);
			
			
		}
		if (type==3) {//主办方
			User user = userService.findByUserName(username);
			if (user==null) {
				ret.put("type", "error");
				ret.put("msg", "用户不存在");
				return ret;
			}
			if (!password.equals(user.getPassword())) {
				ret.put("type", "error");
				ret.put("msg", "密码错误");
				return ret;
			}
			request.getSession().setAttribute("user", user);
		}
		
		//System.out.println(username);
		if (type==2) {
			//选手
			PlayerLogin login = playerService.findByUserName(username);
			if (login==null) {
				ret.put("type", "error");
				ret.put("msg", "用户不存在");
				return ret;
			}
			if (!password.equals(login.getPlayer_password())) {
				ret.put("type", "error");
				ret.put("msg", "密码错误");
				return ret;
			}
			
			User user = new User();
			user.setUsername(login.getPlayer_username());
			user.setPassword(login.getPlayer_password());
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("play", login);
		}
		request.getSession().setAttribute("userType", type);
		ret.put("type", "success");
		ret.put("msg", "登陆成功");
		return ret;

	}

	/**
	 * 验证码
	 * 
	 * @param request
	 * @param vl
	 * @param w
	 * @param h
	 * @param response
	 */
	@RequestMapping(value = "/get_cpacha", method = RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,
			@RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
			@RequestParam(value = "w", defaultValue = "140", required = false) Integer w,
			@RequestParam(value = "h", defaultValue = "40", required = false) Integer h, HttpServletResponse response) {
		// System.out.println("获取验证码");
		CpachaUtil cpachautil = new CpachaUtil(vl, w, h);
		String code = cpachautil.generatorVCode();
		request.getSession().setAttribute("loginCpacha", code);
		BufferedImage generatorVCodeImage = cpachautil.generatorVCodeImage(code, true);
		try {
			ImageIO.write(generatorVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}