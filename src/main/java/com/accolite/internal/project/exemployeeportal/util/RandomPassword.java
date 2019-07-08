package com.accolite.internal.project.exemployeeportal.util;

import java.util.Random;

public class RandomPassword {
	private String emailId;
	private Random rndmMethod;
	
	public RandomPassword() {
		rndmMethod = new Random();
	}

	public String getEmailId() {
		return emailId;
	}

	public void setId(String emailId) {
		this.emailId = emailId;
	}


	public String generateRandomPassword() {

		
		int len=12;
		String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		String smallChars = "abcdefghijklmnopqrstuvwxyz"; 
		String numbers = "0123456789"; 
		String symbols = "!@#$%^&*_=+-/?<>)[]";  

		char[] password = new char[len];

		for (int i = 0; i < 3; i++) {  
			password[i] = capitalChars.charAt(rndmMethod.nextInt(capitalChars.length()));
			password[i+3] = smallChars.charAt(rndmMethod.nextInt(smallChars.length()));
			password[i+6] =  numbers.charAt(rndmMethod.nextInt(numbers.length()));
			password[i+9] = symbols.charAt(rndmMethod.nextInt(symbols.length()));

		} 
		
		
		for (int i = 11; i > 0; i--) {
	      int index = rndmMethod.nextInt(i + 1);
	      char a = password[index];
	      password[index] = password[i];
	      password[i] = a;
	    }
		
		return new String(password);
	} 
}
