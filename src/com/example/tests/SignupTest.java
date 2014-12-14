package com.example.tests;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;




import com.example.fw.AccountHelper;
import com.example.fw.JamesHelper;
import com.example.fw.User;

public class SignupTest extends TestBase{
	
	public User user = new User()
	.setLogin("testUser1")
	.setPassword("123456")
	.setEmail("testUser1@localhost.localdomain");
	
	private JamesHelper james;
	private AccountHelper accHelper;
	
	@BeforeClass
	public void createMailUser(){
		james = app.getJamesHelper();
		accHelper = app.getAccountHelper();
		if(! james.doesUserExist(user.login)){
			james.createUser(user.login, user.password);
		}
	}
	
	
	
	//@Test
	public void newUserShouldSignup(){
		accHelper.signup(user);
		accHelper.login(user);
		assertThat(accHelper.loggedUser(user), equalTo(user.login));
	}
	
	@Test
	public void existingUserShouldNotSignup(){
		try{
			accHelper.signup(user);
		}catch(Exception e){
			assertThat(e.getMessage(), containsString("That username is already being used"));
			return;
		}
		fail("Exception expected");
	}
	
	@AfterClass
	public void deleteMailUser(){
		if(james.doesUserExist(user.login)){
			james.deleteUser(user.login);
			}
	}
}
