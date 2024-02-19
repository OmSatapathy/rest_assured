package com.sonicjobs.base;

import java.io.FileInputStream;
import java.util.Properties;


public class FileReader {
	
	static Properties prop;

	public static void readFile() {
		
		
		try (FileInputStream input = new FileInputStream("src/test/java/com/sonicjobs/base/credentials.properties")) 
		{

	         prop = new Properties();


	        prop.load(input);
	        
	        System.out.println(prop.getProperty("uk_email1"));
            System.out.println(prop.getProperty("password"));
         

		

	} catch(Exception e){
		
	}
		
	}
	

}