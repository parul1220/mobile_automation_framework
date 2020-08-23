package com.project.utilities;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CommonUtilities {
	Random rand= new Random();
	public Properties prop;
	public InputStream input = null;
	String fileLocation= "/Users/PARUL/Desktop/MobileAutomation/other_files/Capabilities_Data.properties";
	String fileLocation1= "/Users/PARUL/Desktop/MobileAutomation/other_files/TestData.json";

	// ready data from Json file where testdata is stored
	public String readData(String key) 
	{
		String value="";
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(fileLocation1));
			JSONObject jsonObject = (JSONObject)obj;
			value = (String)jsonObject.get(key);
			System.out.println("Name: " + value);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	// ready data from properties file where capabilities and env setup related data are stored
	public String readfromFile(String name)
	{
		String a="";
		prop=new Properties();
		try {
			input=new FileInputStream(fileLocation);
			prop.load(input);
			a=prop.getProperty(name);
		} catch (IOException io) {
			io.printStackTrace();
		}
		return a;
	}

	// method to generate random number, will pass total number of items displayed on search result
	public int randomeNum(int num)
	{
		return rand.nextInt( num );	 
	}
}