/**
 * 
 */
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
import cn.tang.Marathon.pojo.Game;
import cn.tang.Marathon.pojo.Game;
import cn.tang.Marathon.service.GameService;

/**赛事管理
 * @author Tang
 *
 */
@RequestMapping("/game")
@Controller
public class GameController {
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("game/game_list");
		return model;	
	}
	/**
	 * 获取赛事列表
	 * @param game_name
	 * @param game_type
	 * @param game_num
	 * @param game_unit
	 * @param game_location
	 * @param page1
	 * @return
	 */
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value = "game_name",required = false,defaultValue = "") String game_name,
			@RequestParam(value = "game_type",required = false,defaultValue = "") String game_type,
			@RequestParam(value = "game_num",required = false) Integer game_num,
			@RequestParam(value = "game_unit",required = false,defaultValue = "") String game_unit,
			@RequestParam(value = "game_location",required = false,defaultValue = "") String game_location,
			Page page1) {
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("game_name", "%"+game_name+"%");
		
		if (game_type!=null&&!"".equals(game_type)) {
			queryMap.put("game_type", game_type);
		}
		if (game_num !=null) {
			queryMap.put("game_num", game_num);
		}
		if (game_unit!=null) {
			queryMap.put("game_unit", "%"+game_unit+"%");
		}
		if (game_location!=null) {
			queryMap.put("game_location", "%"+game_location+"%");
		}
		queryMap.put("offset", page1.getOffset());
		queryMap.put("pageSize", page1.getRows());
		ret.put("rows", gameService.findList(queryMap));
		ret.put("total", gameService.getTotal(queryMap));
		return ret;
		
	}
	/**
	 * 添加赛事
	 * @param game
	 * @return
	 */
	@RequestMapping(value = "/addgame",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addGame(Game game) {
		Map<String, String> ret=new HashMap<String, String>();
		if (game==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_name())) {
			ret.put("type", "error");
			ret.put("msg", "赛事名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_unit())) {
			ret.put("type", "error");
			ret.put("msg", "主办单位不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_location())){
			ret.put("type", "error");
			ret.put("msg", "比赛地点不能为空");
			return ret;
		}
		if (!game.IsAfter()) {
			ret.put("type", "error");
			ret.put("msg", "关门时间不能早于起跑时间");
			return ret;
		}
		if (game.getGame_num()==0){
			ret.put("type", "error");
			ret.put("msg", "赛事规模不能为空");
			return ret;
		}
		
		if (gameService.addGame(game)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		
		return ret;
		
	}
	/**
	 * 修改赛事
	 * @param game
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Game game) {
		Map<String, String> ret=new HashMap<String, String>();
		if (game==null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错，请联系开发人员！");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_name())) {
			ret.put("type", "error");
			ret.put("msg", "赛事名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_unit())) {
			ret.put("type", "error");
			ret.put("msg", "主办单位不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(game.getGame_location())){
			ret.put("type", "error");
			ret.put("msg", "比赛地点不能为空");
			return ret;
		}
		if (!game.IsAfter()) {
			ret.put("type", "error");
			ret.put("msg", "关门时间不能早于起跑时间");
			return ret;
		}
		if (game.getGame_num()==0){
			ret.put("type", "error");
			ret.put("msg", "赛事规模不能为空");
			return ret;
		}
		
		if (gameService.editGame(game)<=0) {
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
		if (gameService.deleteGame(ids)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
		
	}
}
