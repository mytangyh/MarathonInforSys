/**
 * 
 */
package cn.tang.Marathon.service;

import org.springframework.stereotype.Service;

import cn.tang.Marathon.pojo.Admin;

/**
 * @author Tang
 *
 */
@Service
public interface AdminService {
	public Admin findByUserName(String username); 

}
