/**
 * 
 */
package cn.tang.Marathon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tang.Marathon.dao.AdminDao;
import cn.tang.Marathon.pojo.Admin;
import cn.tang.Marathon.service.AdminService;

/**
 * @author Tang
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
@Autowired
private AdminDao admindao;
	@Override
	public Admin findByUserName(String username) {
		// TODO Auto-generated method stub
		return admindao.findByUserName(username);
	}
	@Override
	public int edit_pwd(Admin admin) {
		// TODO Auto-generated method stub
		return admindao.edit_pwd(admin);
	}

}
