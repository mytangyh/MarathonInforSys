/**
 * 
 */
package cn.tang.Marathon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tang.Marathon.page.Page;
import cn.tang.Marathon.pojo.Game;
import cn.tang.Marathon.pojo.Player;
import cn.tang.Marathon.pojo.PlayerList;
import cn.tang.Marathon.pojo.PlayerLogin;
import cn.tang.Marathon.service.GameService;
import cn.tang.Marathon.service.PlayerService;

/**赛事管理
 * @author Tang
 *
 */
@RequestMapping("/player")
@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("player/player_list");
		return model;	
	}
	
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value = "player_username",required = false,defaultValue = "") String username,
			Page page,HttpServletRequest request){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("username", "%"+username+"%");
		Object attribute = request.getSession().getAttribute("userType");
		PlayerLogin playerLogin = (PlayerLogin) request.getSession().getAttribute("play");
		if ("2".equals(attribute.toString())) {
			queryMap.put("username", "%"+playerLogin.getPlayer_username()+"%");

		}
		
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		List<PlayerList> plist = playerService.findList(queryMap);
		
		ret.put("rows", plist);
		ret.put("total", playerService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addplayer(PlayerLogin player,Player p) {
		Map<String, String> ret=new HashMap<String, String>();
		if (player==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		
		if (StringUtils.isEmpty(player.getPlayer_username())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(player.getPlayer_password())){
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		if (playerService.findByUserName(player.getPlayer_username()) != null) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在!");
			return ret;
		}
		
		if (playerService.addPlayer(player,p)<0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		
		return ret;
		
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(PlayerLogin player,Player p){
		Map<String, String> ret=new HashMap<String, String>();
		if (player==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		
		if (StringUtils.isEmpty(player.getPlayer_username())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(player.getPlayer_password())){
			ret.put("type", "error");
			ret.put("msg", "密码不能为空");
			return ret;
		}
		if (playerService.edit(player, p)<1) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
		
	}
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
		if (playerService.delete(ids)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
		
	}
}
