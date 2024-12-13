package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class HelperClass {

	 private static HelperClass helperClass;
     
	    private static WebDriver driver;
	    public final static int TIMEOUT = 10;
	      
	     private HelperClass() {

			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
			 driver = new ChromeDriver(options);
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
			 driver.manage().window().maximize();
	     }      
	              
	    public static void openPage(String url) {

			if (driver != null) {
				driver.get(url);
			} else {
				throw new IllegalStateException("Driver not initialized. Call setUpDriver() first.");
			}
	    }
	  	      
	    public static WebDriver getDriver() {
			if (helperClass == null) {
				setUpDriver();
			}
			return driver;
		}

	      
	    public static void setUpDriver() {
	          
	        if (helperClass==null) {
	              
	            helperClass = new HelperClass();
	        }
	    }

	public static void tearDown() {
		if (driver != null) {
			try {
				driver.close();
			} catch (Exception e) {
				System.err.println("Error closing driver: " + e.getMessage());
			} finally {
				driver.quit();
				driver = null;
				helperClass = null;
			}
		}
	}
	      
	}