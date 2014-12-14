package com.example.fw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends WebDriverHelperBase{

	public AccountHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void signup(User user) {
		openUrl("/");
		click(By.cssSelector("span.bracket-link"));
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.cssSelector("input.button"));
		
		WebElement errorMessage = findElement(By.cssSelector("table.width50 tbody tr td p"));
		if(errorMessage != null){
			throw new RuntimeException(errorMessage.getText());
		}
		
		pause(3000);
		String msg = manager.getMailHelper().getNewMail(user.login, user.password);
		String confirmationLink = getConfirmationLink(msg);
		openAbsoluteUrl(confirmationLink);
		type(By.name("password"), user.password);
		type(By.name("password_confirm"), user.password);
		click(By.cssSelector("input.button"));
	}

	public String getConfirmationLink(String text) {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}
	
	public String loggedUser(User user) {
		WebElement element = findElement(By.cssSelector("td.login-info-left span"));
		return element.getText();
	}

	public void login(User user) {
		openUrl("/");
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.cssSelector("input.button"));
	}

	public void removeUser(User user, Admin admin) {
		openUrl("/");
		type(By.name("username"), admin.login);
		type(By.name("password"), admin.password);
		click(By.cssSelector("input.button"));
		click(By.linkText("Manage Users"));
		click(By.linkText(user.login));
		click(By.xpath("//input[@value='Delete User']"));
		click(By.cssSelector("input.button"));
		click(By.linkText("Proceed"));
	}

	public boolean checkRemoveUser(User user) throws SQLException, FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader(new File("application.properties")));
		ApplicationManager app = new ApplicationManager(properties);
		JdbcHelper jdbc = new JdbcHelper(app, "jdbc:mysql://localhost/bugtracker?user=root&password=");
		if(jdbc.countUsers(user).equals("0")){
			System.out.println("User was successfully removed");
			return true;
		}else{
			System.out.println("User wasn't removed");
			return false;
		}
		
	}

}
