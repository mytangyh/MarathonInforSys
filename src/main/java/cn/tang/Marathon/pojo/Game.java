/**
 * 
 */
package cn.tang.Marathon.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Tang
 *
 */
@Component
public class Game {
	private int game_id;
	private String game_name;
	private String game_type;//类型
	private Date game_begintime;
	private Date game_endtime;
	private Integer game_num;//人数
	private String game_unit;
	private String game_location;//地点
	private String game_remakes;//备注
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getGame_type() {
		return game_type;
	}
	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}
	
//	public Date getGame_begintime() {
//		return game_begintime;
//	}
//	public void setGame_begintime(Date game_begintime) {
//		this.game_begintime = game_begintime;
//	}
//	public Date getGame_endtime() {
//		return game_endtime;
//	}
//	public void setGame_endtime(Date game_endtime) {
//		this.game_endtime = game_endtime;
//	}
	public String getGame_begintime() {
		return formatTime(game_begintime);
	}
	public void setGame_begintime(Date game_begintime) {
	this.game_begintime = game_begintime;
}
public String getGame_endtime() {
	return formatTime(game_endtime);
}
public void setGame_endtime(Date game_endtime) {
	this.game_endtime = game_endtime;
}
	
	public Integer getGame_num() {
	return game_num;
}
public void setGame_num(Integer game_num) {
	this.game_num = game_num;
}
	public String getGame_unit() {
		return game_unit;
	}
	public void setGame_unit(String game_unit) {
		this.game_unit = game_unit;
	}
	public String getGame_location() {
		return game_location;
	}
	public void setGame_location(String game_location) {
		this.game_location = game_location;
	}
	public String getGame_remakes() {
		return game_remakes;
	}
	public void setGame_remakes(String game_remakes) {
		this.game_remakes = game_remakes;
	}
	public String formatTime(Date date) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm" );
		String fmdate = ft.format(date);
		return fmdate;
	}
	public boolean IsAfter() {
		if (game_endtime.before(game_begintime)) {
			return false;
		}
		return true;	
	}
}
