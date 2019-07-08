package com.accolite.internal.project.exemployeeportal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;
import com.accolite.internal.project.exemployeeportal.exception.ResourceNotFoundException;
import com.accolite.internal.project.exemployeeportal.security.CurrentUser;
import com.accolite.internal.project.exemployeeportal.security.ExEmployeePrincipal;
import com.accolite.internal.project.exemployeeportal.security.UserPrincipal;

public class GenericUtil {

	public static String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return null;
		}
		String userName = auth.getName().toString();
		if (userName == null) {
			return "None";
		}
		return userName;
	}

	public static String getCurrentExEmployeeUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return null;
	}

	public static String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return null;
	}

	public static String formatDateDayDMY(Date date) {
		SimpleDateFormat sdt = new SimpleDateFormat("E dd-MM-yyyy 'at' hh:mm:ss a");
		return sdt.format(date);
	}

	public static String formatDateDayMpnthY(Date date) {
		SimpleDateFormat sdt = new SimpleDateFormat("E dd-MM-yyyy 'at' hh:mm:ss a");
		return sdt.format(date);
	}

	// DD Month, YYYY
	public static String today() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		return dateFormat.format(new Date());
	}

	// DD Month, YYYY
	public static String tomorrow(String format) {
		return nthDayDate(new Date(), 1, format);
	}

	public static String yesterday(String format) {
		return nthDayDate(new Date(), -1, format);
	}

	public static String nextMonth(Date date, String format) {
		return nthMonthDate(date, 1, format);
	}

	public static String nextYear(Date date, String format) {
		return nthYearDate(date, 1, format);
	}

	public static String nthDayDate(Date date, int n, String format) {
		// Date currentDate = new Date();
		DateFormat dateFormat = null;
		if (format.equals("dmy")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		} else {
			// format 30 June, 2017
			dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
			;
		}

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// manipulate date
		c.add(Calendar.DATE, n);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();

		return dateFormat.format(currentDatePlusOne);
	}

	public static String nthMonthDate(Date date, int n, String format) {
		DateFormat dateFormat = null;
		if (format.equals("dmy")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		} else {
			// format 30 June, 2017
			dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
			;
		}

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// manipulate date
		c.add(Calendar.MONTH, 1);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();

		return dateFormat.format(currentDatePlusOne);
	}

	public static String nthYearDate(Date date, int n, String format) {
		DateFormat dateFormat = null;
		if (format.equals("dmy")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		} else {
			// format 30 June, 2017
			dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
			;
		}

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// manipulate date
		c.add(Calendar.YEAR, 1);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();

		return dateFormat.format(currentDatePlusOne);
	}

	public static long generateId() {
		return new Date().getTime();
	}

	public static String generateId(String type) {
		return type + "-" + String.valueOf(new Date().getTime());
	}

	// Validate Email
	public static GenericUtilResponse isValidEmail(String email) {
		GenericUtilResponse genericUtilResp = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(email);
		email = email.trim();
		if (!isNullOrEmpty.isValid()) {
			genericUtilResp.setValid(false);
			genericUtilResp.setMessage(isNullOrEmpty.getMessage());
		} else {
			Pattern emailNamePtrn = Pattern
					.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mtch = emailNamePtrn.matcher(email);
			if (mtch.matches()) {
				genericUtilResp.setValid(true);
			} else {
				genericUtilResp.setValid(false);
				genericUtilResp.setMessage("Invalid email address");
			}

		}
		return genericUtilResp;
	}

	// Validate Password for contraint
	public static GenericUtilResponse validatePassword(String password) {
		GenericUtilResponse genericUtilResp = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(password);
		if (!isNullOrEmpty.isValid()) {
			genericUtilResp.setValid(false);
			genericUtilResp.setMessage(isNullOrEmpty.getMessage());
		} else {
			Pattern passwordPtrn = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
			Matcher mtch = passwordPtrn.matcher(password);
			if (mtch.matches()) {
				genericUtilResp.setValid(true);
			} else {
				genericUtilResp.setValid(false);
				// check this properly
				genericUtilResp.setMessage(
						"Invalid password. Please provide a valid password of atleast 6 digit alpha numeric");
			}

		}
		return genericUtilResp;
	}

	// File Validation foe extensions txt, doc, csv, pdf
	public static GenericUtilResponse validateFileExtension(String fileName) {
		GenericUtilResponse genericUtilResp = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(fileName);
		if (!isNullOrEmpty.isValid()) {
			genericUtilResp.setValid(false);
			genericUtilResp.setMessage(isNullOrEmpty.getMessage());
		} else {
			Pattern fileNamePtrn = Pattern.compile("([^\\s]+(\\.(?i)(txt|doc|csv|pdf))$)");
			Matcher mtch = fileNamePtrn.matcher(fileName);
			if (mtch.matches()) {
				genericUtilResp.setValid(true);
			} else {
				genericUtilResp.setValid(false);
				genericUtilResp.setMessage("Invalid file name");
			}

		}
		return genericUtilResp;
	}

	// Date validation
	public static GenericUtilResponse validateDateFormat(String date) {
		GenericUtilResponse genericUtilResp = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(date);
		if (!isNullOrEmpty.isValid()) {
			genericUtilResp.setValid(false);
			genericUtilResp.setMessage(isNullOrEmpty.getMessage());
		} else {
			Pattern datePtrn = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
			Matcher mtch = datePtrn.matcher(date);
			if (mtch.matches()) {
				genericUtilResp.setValid(true);
			} else {
				genericUtilResp.setValid(false);
				genericUtilResp.setMessage("Invalid date format");
			}

		}
		return genericUtilResp;
	}

	// Image validation for extensions jpg, png, gif, bmp
	public static GenericUtilResponse validateImage(String image) {
		GenericUtilResponse genericUtilResp = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(image);
		if (!isNullOrEmpty.isValid()) {
			genericUtilResp.setValid(false);
			genericUtilResp.setMessage(isNullOrEmpty.getMessage());
		} else {
			Pattern datePtrn = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)");
			Matcher mtch = datePtrn.matcher(image);
			if (mtch.matches()) {
				genericUtilResp.setValid(true);
			} else {
				genericUtilResp.setValid(false);
				genericUtilResp.setMessage("Invalid image format");
			}

		}
		return genericUtilResp;
	}

	// extract Ip's from text log
	public static List<String> extractIpsFromText(String largeText) {
		String pattern = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
		return extractValueFromText(largeText, pattern);
	}

	// extract Values from text
	public static List<String> extractValueFromText(String largeText, String pattern) {
		List<String> values = new ArrayList<String>();
		Pattern ptn = Pattern.compile(pattern);
		Matcher mtch = ptn.matcher(largeText);

		while (mtch.find()) {
			values.add(mtch.group());
		}
		return values;
	}

	// split a string using regular expression
	public static List<String> splitStringUsingPattern(String largeText, String pattern) {
		List<String> values = new ArrayList<String>();
		Pattern ptn = Pattern.compile(pattern);
		String[] parts = ptn.split(pattern);
		for (String p : parts) {
			System.out.println(p);
			values.add(p);
		}
		return values;
	}

	// validate Object
	public static GenericUtilResponse isValid(Object object) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();
		if (object == null) {
			genericUtil.setValid(false);
			genericUtil.setMessage("Input is null");
		} else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				genericUtil.setValid(false);
				genericUtil.setMessage("Input is Empty");
			} else {
				genericUtil.setValid(true);
			}
		} else {
			genericUtil.setValid(true);
		}

		return genericUtil;
	}

	// validate Object
	public static boolean isValidObject(Object object) {
		if (object == null) {
			return false;
		} else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	// validate input string contains Number only
	public static GenericUtilResponse isNumeric(String number) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();

		String regex = "\\d+";

		GenericUtilResponse isNullOrEmpty = isValid(number);
		if (!isNullOrEmpty.isValid()) {
			genericUtil.setValid(false);
			genericUtil.setMessage(isNullOrEmpty.getMessage());
		} else if (number.matches(regex)) {
			genericUtil.setValid(true);
		} else {
			genericUtil.setValid(false);
			genericUtil.setMessage("String is not numeric");
		}
		return genericUtil;
	}

	// validate input string contains No Number
	public static GenericUtilResponse hasNoNumber(String noNumber) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();

		String regex = "\\d+";

		GenericUtilResponse isNullOrEmpty = isValid(noNumber);
		if (!isNullOrEmpty.isValid()) {
			genericUtil.setValid(false);
			genericUtil.setMessage(isNullOrEmpty.getMessage());
		} else if (noNumber.matches(regex)) {
			genericUtil.setValid(false);
			genericUtil.setMessage("String contain numeric digits");

		} else {
			genericUtil.setValid(true);

		}
		return genericUtil;
	}

	// validate input string contains only Alphabets
	public static GenericUtilResponse hasOnlyAlphabets(String alphabets) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();

		String regex = "^[a-zA-Z][a-zA-Z\\s]*$";

		GenericUtilResponse isNullOrEmpty = isValid(alphabets);
		if (!isNullOrEmpty.isValid()) {
			genericUtil.setValid(false);
			genericUtil.setMessage(isNullOrEmpty.getMessage());
		} else if (alphabets.matches(regex)) {
			genericUtil.setValid(true);

		} else {
			genericUtil.setValid(false);
			genericUtil.setMessage("Please provide correct input with no number and no special character");
		}
		return genericUtil;
	}

	// validate input string contains Special Character only
	public static GenericUtilResponse isSpecialChar(String specialChar) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();

		String regex = "[" + "-/@#!*$%^&.'_+={}()" + "]+";

		GenericUtilResponse isNullOrEmpty = isValid(specialChar);
		if (!isNullOrEmpty.isValid()) {
			genericUtil.setValid(false);
			genericUtil.setMessage(isNullOrEmpty.getMessage());
		} else if (specialChar.matches(regex)) {
			genericUtil.setValid(true);

		} else {
			genericUtil.setValid(false);
			genericUtil.setMessage("Please provide correct input with only special character");
		}
		return genericUtil;
	}

	// validate input string for Mobile Number
	public static GenericUtilResponse isValidMobileNumber(String mobileNumber) {
		GenericUtilResponse genericUtil = new GenericUtilResponse();

		GenericUtilResponse isNullOrEmpty = isValid(mobileNumber);
		mobileNumber = mobileNumber.trim();
		if (!isNullOrEmpty.isValid()) {
			genericUtil.setValid(false);
			genericUtil.setMessage(isNullOrEmpty.getMessage());
		} else if (mobileNumber.trim().length() >= 10) {

			Pattern mobPtrn = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");
			Matcher mtch = mobPtrn.matcher(mobileNumber);
			if (mtch.matches()) {

				genericUtil.setValid(true);
			}
		}
		/*
		 * else if ((checkMob.isValid()==true) && (mobileNumber.trim().length()==10)) {
		 * genericUtil.setValid(true);
		 * 
		 * 
		 * }
		 */
		else {
			genericUtil.setValid(false);
			genericUtil.setMessage("Please provide valid 10 digits mobile number");
		}
		return genericUtil;
	}

	public static void setResponseParameters(Response response, long startTime, long endTime) {
		response.setTime(endTime - startTime);
	}
}
