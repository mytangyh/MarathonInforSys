/**
 * 
 */
package cn.tang.Marathon.dao;

import org.springframework.stereotype.Component;

import cn.tang.Marathon.pojo.Admin;


/**
 * @author Tang
 *
 */
@Component
public interface AdminDao {
	public Admin findByUserName(String username); 
}
