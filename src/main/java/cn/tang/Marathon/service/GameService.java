/**
 * 
 */
package cn.tang.Marathon.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.tang.Marathon.pojo.Game;
import cn.tang.Marathon.pojo.Stats;
import cn.tang.Marathon.pojo.User;

/**
 * @author Tang
 *
 */
@Service
public interface GameService {
	
	public int addGame(Game game);
	public int editGame(Game game);
	public int deleteGame(Long[] ids);
	public List<Game> findList(Map<String,Object> queryMap);
	public List<Game> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public Stats getStats();
}
