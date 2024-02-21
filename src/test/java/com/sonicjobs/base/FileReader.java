package com.sonicjobs.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.JSONObject;

public class FileReader {

	public static Properties readFile() {

		Properties prop = null;

		try (FileInputStream input = new FileInputStream("src/test/java/com/sonicjobs/base/emails.properties")) {

			prop = new Properties();

			prop.load(input);

		} catch (Exception e) {

		}
		return prop;

	}

	public static JSONObject getUserByEmail(String email, String deviceType) {

		Properties prop = FileReader.readFile();

		JSONObject login = new JSONObject();
		login.put("email", email);
		login.put("password", prop.getProperty("password"));
		login.put("deviceType", deviceType);

		return login;

	}
}