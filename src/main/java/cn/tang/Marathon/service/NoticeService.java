/**
 * 
 */
package cn.tang.Marathon.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.tang.Marathon.pojo.Notice;

/**
 * @author Tang
 *
 */
@Service
public interface NoticeService {
	public List<Notice> findAll();
	public List<Notice> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public int add(Notice notice);
	public int delete(Long[] ids);
}
