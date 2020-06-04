/**
 * 
 */
package cn.tang.Marathon.pojo;

import org.springframework.stereotype.Component;

/**
 * @author Tang
 *
 */
@Component
public class PlayerLogin {
	private Integer player_id;
	private String player_username;
	private String player_password;
	private Player playerInf;
	
	public Player getPlayerInf() {
		return playerInf;
	}
	public void setPlayerInf(Player playerInf) {
		this.playerInf = playerInf;
	}
	public Integer getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(Integer player_id) {
		this.player_id = player_id;
	}
	public String getPlayer_username() {
		return player_username;
	}
	public void setPlayer_username(String player_username) {
		this.player_username = player_username;
	}
	public String getPlayer_password() {
		return player_password;
	}
	public void setPlayer_password(String player_password) {
		this.player_password = player_password;
	}
	@Override
	public String toString() {
		return "PlayerLogin [player_id=" + player_id + ", player_username=" + player_username + ", player_password="
				+ player_password + ", playerInf=" + playerInf + "]";
	}
	
}
