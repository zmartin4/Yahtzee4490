package yahtzee;

import java.io.Serializable;

public class User implements Serializable {
	
	private int id;
	private LoginData loginData;
	private int highscore;
	
	
	public LoginData getLoginData() {
		return loginData;
	}
	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User(int id, LoginData loginData) {
		this.loginData = loginData;
		this.id = id;
	}
	
	public String toString() {
		return "User [id=" + id +", username=" + loginData.getUsername() + ", password=" + loginData.getPassword() + ", highscore=" + highscore + "]";
	}
	
	public int getHighscore() {
		return highscore;
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	
}
