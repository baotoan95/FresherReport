package com.fresher.report.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

	public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";

	private DateUtils() {
	}

	public static Date getDate(String dateStr, String format) {
		final DateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}