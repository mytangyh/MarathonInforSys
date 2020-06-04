/**
 * 
 */
package cn.tang.Marathon.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.tang.Marathon.pojo.Player;
import cn.tang.Marathon.pojo.PlayerList;
import cn.tang.Marathon.pojo.PlayerLogin;

/**
 * @author Tang
 *
 */
@Service
public interface PlayerService {
	public PlayerLogin findByUserName(String player_username); 
	public int addPlayer(PlayerLogin playerl, Player player);
	public List<PlayerList> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public int edit(PlayerLogin playerl, Player player);
	public int delete(Long[] ids);
	public List<Player> findAll();
}
