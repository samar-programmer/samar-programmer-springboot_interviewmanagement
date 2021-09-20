package com.revature.interviewmanagement.utils;

import java.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordHasherUtil {
	private static final Logger logger=LogManager.getLogger(PasswordHasherUtil.class.getName());
	private PasswordHasherUtil() {
		throw new IllegalStateException("Utility class");

	}
	
	 private static String getSecurePassword(String password, byte[] salt) {
		 	logger.trace("entering getSecurePassword method");
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt);
	            byte[] bytes = md.digest(password.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bytes.length; i++) {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	 
	 private static byte[] getSalt() {
		 logger.trace("entering getSalt method");
	        SecureRandom random = new SecureRandom();
	        byte[] salt = new byte[16];
	        random.nextBytes(salt);
	        return salt;
	    }
	 
	 public static String[] storePassword(String password) {
		 logger.trace("entering storePassword method");
	        byte[] salt = getSalt();
	        String[] passwordSaltArray=new String[2];
	        passwordSaltArray[0]=getSecurePassword(password, salt);
	        passwordSaltArray[1]=Base64.getEncoder().encodeToString(salt);
	        
	       return passwordSaltArray;
	        
	 }
	 public static boolean validatePassword(String hashedPassword,String password,String salt) {
		 logger.trace("entering recoverPassword method");
		 byte[] saltArray=Base64.getDecoder().decode(salt);
		 
		return (hashedPassword).equals(getSecurePassword(password, saltArray));
	 }

}
