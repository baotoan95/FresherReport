package com.fresher.report.constants;

import java.io.File;

public class PathContant {
	private static String rootPath = System.getProperty("catalina.home");
	public static final String UPLOAD_PATH = rootPath + File.separator + "tempFiles";
}
