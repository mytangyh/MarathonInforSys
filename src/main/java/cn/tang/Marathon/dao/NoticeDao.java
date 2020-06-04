/**
 * 
 */
package cn.tang.Marathon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tang.Marathon.pojo.Notice;


/**
 * @author Tang
 *
 */
@Repository
public interface NoticeDao {
public List<Notice> findAll();
public List<Notice> findList(Map<String,Object> queryMap);
public int getTotal(Map<String,Object> queryMap);
}
