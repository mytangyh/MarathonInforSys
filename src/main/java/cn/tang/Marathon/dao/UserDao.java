/**
 * 
 */
package cn.tang.Marathon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tang.Marathon.pojo.User;

/**
 * @author Tang
 *
 */
@Repository
public interface UserDao {
	public User findByUserName(String username); 
	public int add(User user);
	public List<User> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public int edit(User user);
	public int delete(Long[] ids);
	public int edit_pwd(User user);
}
