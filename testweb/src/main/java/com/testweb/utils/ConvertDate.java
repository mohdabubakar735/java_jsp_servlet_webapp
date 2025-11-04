package com.testweb.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConvertDate {

	public ConvertDate() {
		// TODO Auto-generated constructor stub
	}
	
	public static Timestamp getTimestamp(String dateString) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);

            return  new Timestamp( date.getTime());
		} catch (Exception e) {
			System.err.println("Date formatting error :"+e.getMessage());
			return null;
		}
	}

	public static String convertTimestamp(String input) {
		String outputString = "";
		try {
			// Define the input and output formatters
			DateTimeFormatter inputFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// Parse the input string into a LocalDateTime object
			if (input.length() <= 21) {
				LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter1);
				// Format the LocalDateTime object into the desired output string
				outputString = dateTime.format(outputFormatter);
			} else {
				LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter2);
				// Format the LocalDateTime object into the desired output string
				outputString = dateTime.format(outputFormatter);
			}
			System.out.println(outputString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputString;
	}
	
    public static java.sql.Date convertDate(String dateString) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            System.out.println("date:"+date);
            return  new java.sql.Date(date.getTime());
		} catch (Exception e) {
			System.err.println("Date formatting error :"+e.getMessage());
			return null;
		}
	}
}
