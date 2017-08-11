package com.db.dto;

import java.sql.Timestamp;

public class LoginTrail {
	private int loginId;
	private String loggedInUuserId;
	private String loggedInAUuserId;
	private Timestamp loginDateAndTime;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoggedInUuserId() {
		return loggedInUuserId;
	}

	public void setLoggedInUuserId(String loggedInUuserId) {
		this.loggedInUuserId = loggedInUuserId;
	}

	public String getLoggedInAUuserId() {
		return loggedInAUuserId;
	}

	public void setLoggedInAUuserId(String loggedInAUuserId) {
		this.loggedInAUuserId = loggedInAUuserId;
	}

	public Timestamp getLoginDateAndTime() {
		return loginDateAndTime;
	}

	public void setLoginDateAndTime(Timestamp loginDateAndTime) {
		this.loginDateAndTime = loginDateAndTime;
	}

}
