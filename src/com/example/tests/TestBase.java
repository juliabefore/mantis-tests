package com.example.tests;

//import static com.example.tests.ContactDataGenerator.generateRandomContacts;
//import static com.example.tests.GroupDataGenerator.generateRandomGroups;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected static ApplicationManager app;
	private int checkCounter;
	private int checkFrequency;

	
	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
	    checkCounter = 0;
	    checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
	  }
	
	protected boolean wantToCheck(){
		checkCounter++;
		if(checkCounter > checkFrequency){
			checkCounter = 0;
			return true;
		}else{
			return false;
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
	    app.stop();
	
	  }
	
	
	
}
