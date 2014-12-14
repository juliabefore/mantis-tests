package com.example.fw;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {
	
	private WebDriver driver;
	public String baseUrl;
			

	private Properties properties;
	private HibernateHelper hibernateHelper;
	private AccountHelper accountHelper;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;

	
	public ApplicationManager(Properties properties){
		this.properties = properties;
		//model = new ApplicationModel();
		//model.setGroups(getHibernateHelper().listGroups());
		//model.setContacts(getHibernateHelper().listContacts());
	}
	


	
	
	

	public void stop() {
		driver.quit();
	    		
	}
	
	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}

	public WebDriver getDriver() {
		if (driver == null) {
			String browser = properties.getProperty("browser");
			if("firefox".equals(browser)){
				driver = new FirefoxDriver();
			}else if("ie".equals(browser)){
				File file = new File("C:/JULIA/Education/Trainings/одр20/Soft/IEDriverServer_Win32_2.44.0/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
			}else{
				throw new Error("Unsupported browser " + browser);
			}
			
		    baseUrl = properties.getProperty("baseUrl");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.get(baseUrl);
		}
		return driver;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}







	public MailHelper getMailHelper() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}







	public JamesHelper getJamesHelper() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}
}
