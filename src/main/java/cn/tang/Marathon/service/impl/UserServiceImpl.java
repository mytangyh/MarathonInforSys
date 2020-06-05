/**
 * 
 */
package cn.tang.Marathon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tang.Marathon.dao.UserDao;
import cn.tang.Marathon.pojo.User;
import cn.tang.Marathon.service.UserService;

/**
 * @author Tang
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}
	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}
	@Override
	public List<User> findList(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.getTotal(queryMap);
	}
	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		return userDao.edit(user);
	}
	@Override
	public int delete(Long[] ids) {
		// TODO Auto-generated method stub
		return userDao.delete(ids);
	}
	@Override
	public int edit_pwd(User user) {
		// TODO Auto-generated method stub
		return userDao.edit_pwd(user);
	}

}
