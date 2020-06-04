/**
 * 
 */
package cn.tang.Marathon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tang.Marathon.pojo.Player;
import cn.tang.Marathon.pojo.PlayerList;
import cn.tang.Marathon.pojo.PlayerLogin;

/**
 * @author Tang
 *
 */
@Repository
public interface PlayerDao {
	public PlayerLogin findByUserName(String player_username); 
	public int addPlayerLogin(PlayerLogin player);
	public int addPlayerInfo(Player player);
	public List<PlayerLogin> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public int editPlayerLogin(PlayerLogin player);
	public int editPlayerInf(Player player);
	public int deleteLogin(Long[] ids);
	public int deleteInf(Long[] ids);
	public List<Player> findAll();
}
