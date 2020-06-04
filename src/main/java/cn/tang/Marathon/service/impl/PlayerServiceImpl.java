/**
 * 
 */
package cn.tang.Marathon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tang.Marathon.dao.PlayerDao;
import cn.tang.Marathon.pojo.Player;
import cn.tang.Marathon.pojo.PlayerList;
import cn.tang.Marathon.pojo.PlayerLogin;
import cn.tang.Marathon.service.PlayerService;

/**
 * @author Tang
 *
 */
@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerDao playerDao;
	@Override
	public PlayerLogin findByUserName(String player_username) {
		// TODO Auto-generated method stub
		return playerDao.findByUserName(player_username);
	}

	@Override
	public int addPlayer(PlayerLogin playerl,Player player) {
		// TODO Auto-generated method stub
		int addPlayerLogin = playerDao.addPlayerLogin(playerl);
		
		player.setId(playerl.getPlayer_id());
		
		return playerDao.addPlayerInfo(player);
	}

	@Override
	public List<PlayerList> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		List<PlayerLogin> plist = playerDao.findList(queryMap);
		
		List<PlayerList> pl=new ArrayList<PlayerList>();
		for (PlayerLogin playerLogin : plist) {
			PlayerList playerList = new PlayerList();
			PlayerList combin = playerList.combin(playerLogin);
			pl.add(combin);
		}
		
		return pl;
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return playerDao.getTotal(queryMap);
	}

	

	@Override
	public int delete(Long[] ids) {
		// TODO Auto-generated method stub
		int deleteInf = playerDao.deleteInf(ids);
		int deleteLogin = playerDao.deleteLogin(ids);
		int ret=0;
		if (deleteInf==deleteLogin&&deleteInf>0) {
			ret=1;
		}
		return ret;
	}

	@Override
	public List<Player> findAll() {
		// TODO Auto-generated method stub
		return playerDao.findAll();
	}

	@Override
	public int edit(PlayerLogin playerl, Player player) {
		// TODO Auto-generated method stub
		int editPlayerLogin = playerDao.editPlayerLogin(playerl);
		System.out.println(playerl);
		player.setId(playerl.getPlayer_id());
		int editPlayerInf = playerDao.editPlayerInf(player);
		System.out.println(player+" "+editPlayerInf);
		int ed=editPlayerInf+editPlayerLogin;
		return ed;
	}

}
