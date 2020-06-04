/**
 * 
 */
package cn.tang.Marathon.pojo;

/**
 * @author Tang
 *
 */
public class PlayerList {
	private Integer player_id;
	private String player_username;
	private String player_password;
	private String player_name;
	private String player_sex;
	private String player_birthday;
	private String player_email;
	private String player_address;
	private String player_remake;
	
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
	public PlayerList combin(PlayerLogin pl) {
		PlayerList playerList = new PlayerList();
	playerList.setPlayer_id(pl.getPlayer_id());
	playerList.setPlayer_username(pl.getPlayer_username());
	playerList.setPlayer_password(pl.getPlayer_password());
	Player playerInf = pl.getPlayerInf();
	playerList.setPlayer_name(playerInf.getPlayer_name());
	playerList.setPlayer_sex(playerInf.getPlayer_sex());
	playerList.setPlayer_birthday(playerInf.getPlayer_birthday());
	playerList.setPlayer_email(playerInf.getPlayer_email());
	playerList.setPlayer_address(playerInf.getPlayer_address());
	playerList.setPlayer_remake(playerInf.getPlayer_remake());
		return playerList;
		
	}
}
