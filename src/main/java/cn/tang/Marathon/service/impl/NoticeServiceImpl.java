/**
 * 
 */
package cn.tang.Marathon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tang.Marathon.dao.NoticeDao;
import cn.tang.Marathon.pojo.Notice;
import cn.tang.Marathon.service.NoticeService;

/**
 * @author Tang
 *
 */
@Service
public class NoticeServiceImpl implements NoticeService {
@Autowired
private NoticeDao noticeDao;
	
	@Override
	public List<Notice> findAll() {
		// TODO Auto-generated method stub
		return noticeDao.findAll();
	}

	@Override
	public List<Notice> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return noticeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return noticeDao.getTotal(queryMap);
	}

}
