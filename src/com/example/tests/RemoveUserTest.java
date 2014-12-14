package com.example.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.fw.AccountHelper;
import com.example.fw.Admin;
import com.example.fw.JdbcHelper;
import com.example.fw.User;

public class RemoveUserTest extends TestBase{
	
	private AccountHelper accHelper;
	
	public User user = new User()
	.setLogin("testUser1")
	.setPassword("123456")
	.setEmail("testUser1@localhost.localdomain");
	
	public Admin admin = new Admin()
		.setLogin("administrator")
		.setPassword("root");
	
	@BeforeClass
	public void createMailUser(){
		accHelper = app.getAccountHelper();
	}
	
	@Test
	public void removeUserTest() throws FileNotFoundException, SQLException, IOException{
		accHelper.removeUser(user, admin);
		accHelper.checkRemoveUser(user);
	}

}
