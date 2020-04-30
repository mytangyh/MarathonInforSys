/**
 * 
 */
package cn.tang.Marathon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tang.Marathon.pojo.Game;

/**
 * @author Tang
 *
 */
@Repository
public interface GameDao {

public int addGame(Game game);
public int editGame(Game game);
public int deleteGame(Long[] ids);
public List<Game> findList(Map<String,Object> queryMap);
public List<Game> findAll();
public int getTotal(Map<String,Object> queryMap);
}
