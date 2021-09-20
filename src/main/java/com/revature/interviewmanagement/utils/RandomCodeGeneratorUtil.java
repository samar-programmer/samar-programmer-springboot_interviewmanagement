package com.revature.interviewmanagement.utils;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomCodeGeneratorUtil {
	private static final Logger logger=LogManager.getLogger(RandomCodeGeneratorUtil.class.getName());
	private RandomCodeGeneratorUtil() {
		throw new IllegalStateException("Utility class");

	}
	public static String randomCode()
	{
			logger.trace("entering randomCode method");
			String str = "1234567890";
	        StringBuilder salt = new StringBuilder();
	        while (salt.length() <= 5) { 
	            int index = (new Random().nextInt(10));
	            salt.append(str.charAt(index));
	        }
	     
	        return  salt.toString();
		
	}
}
