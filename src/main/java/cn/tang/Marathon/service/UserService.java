package cn.tang.Marathon.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.tang.Marathon.pojo.User;
@Service
public interface UserService {
	public User findByUserName(String username); 
	public int add(User user);
	public List<User> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public int edit(User user);
	public int delete(Long[] ids);
}
