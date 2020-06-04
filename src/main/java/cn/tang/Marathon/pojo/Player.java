/**
 * 
 */
package cn.tang.Marathon.pojo;

import java.sql.Date;

import org.springframework.stereotype.Component;

/**
 * @author Tang
 *
 */
@Component
public class Player {
	private Integer player_id;
	private String player_name;
	private String player_sex;
	private String player_birthday;
	private String player_email;
	private String player_address;
	private String player_remake;
	public Integer getId() {
		return player_id;
	}
	public void setId(Integer id) {
		this.player_id = id;
	}
	
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public String getPlayer_sex() {
		return player_sex;
	}
	public void setPlayer_sex(String player_sex) {
		this.player_sex = player_sex;
	}
	public String getPlayer_birthday() {
		return player_birthday;
	}
	public void setPlayer_birthday(String player_birthday) {
		this.player_birthday = player_birthday;
	}
	public String getPlayer_email() {
		return player_email;
	}
	public void setPlayer_email(String player_email) {
		this.player_email = player_email;
	}
	public String getPlayer_address() {
		return player_address;
	}
	public void setPlayer_address(String player_address) {
		this.player_address = player_address;
	}
	public String getPlayer_remake() {
		return player_remake;
	}
	public void setPlayer_remake(String player_remake) {
		this.player_remake = player_remake;
	}
	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", player_name=" + player_name + ", player_sex=" + player_sex
				+ ", player_birthday=" + player_birthday + ", player_email=" + player_email + ", player_address="
				+ player_address + ", player_remake=" + player_remake + "]";
	}
	
	
}
