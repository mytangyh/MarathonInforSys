/**
 * 
 */
package cn.tang.Marathon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tang.Marathon.dao.GameDao;
import cn.tang.Marathon.pojo.Game;
import cn.tang.Marathon.pojo.Stats;
import cn.tang.Marathon.service.GameService;

/**
 * @author Tang
 *
 */
@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
	

	@Override
	public int addGame(Game game) {
		// TODO Auto-generated method stub
		return gameDao.addGame(game);
	}

	@Override
	public List<Game> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gameDao.findList(queryMap);
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return gameDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gameDao.getTotal(queryMap);
	}

	@Override
	public int editGame(Game game) {
		// TODO Auto-generated method stub
		return gameDao.editGame(game);
	}

	@Override
	public int deleteGame(Long[] ids) {
		// TODO Auto-generated method stub
		return gameDao.deleteGame(ids);
	}

	@Override
	public Stats getStats() {
		// TODO Auto-generated method stub
		return gameDao.getStats();
	}

}
