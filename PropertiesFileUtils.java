package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	
	private static String CONFIG_PATH_1 = "./configuration/configs.properties";
	private static String CONFIG_PATH_2 = "./configuration/token.properties";
	
	public static String getProperty(String key) {
		Properties properties= new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH_1);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
			} 
		catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của: " + key);
			ex.printStackTrace();
			}
		finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	public static String getToken(String key) {
		Properties properties= new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH_2);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
			} 
		catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của: " + key);
			ex.printStackTrace();
			}
		finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	public static void saveToken(String key, String value) {
		Properties properties= new Properties();
		FileOutputStream fileOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(CONFIG_PATH_2);
			properties.setProperty(key, value);
			properties.store(fileOutputStream, "Save token value");
			System.out.println("Save new token value in file properties success!");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
