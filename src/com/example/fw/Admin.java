package com.example.fw;

public class Admin {
	
	public String login;
	public String password;
	
	public Admin setLogin(String login) {
		this.login = login;
		return this;
	}

	public Admin setPassword(String password) {
		this.password = password;		
		return this;
	}

}
