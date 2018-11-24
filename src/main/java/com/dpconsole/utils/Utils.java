package com.dpconsole.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.web.multipart.MultipartFile;

public class Utils {

	public static boolean isEmpty(String param) {
		if(param == null) {
			return true;
		} else if("".equals(param.trim())) {
			return true;
		}
		return false;
	}

	public static void createDirectoryIfNotExists(File file) throws IOException {
		if(!file.exists()) {
			if(!file.mkdirs()) {
				throw new IOException("Failed to create directory");
			}
		}
	}

	public static void createDirectoryIfNotExists(String file) throws IOException {
		createDirectoryIfNotExists(new File(file));
	}

	/*	public static File saveMultipartFileInTemp(MultipartFile file,String fileName) throws IllegalStateException, IOException {
		String tempPath = Configuration.Temp_Dir_Path+ "/" + RandomString.getNext() + "/";
		Utils.createDirectoryIfNotExists(tempPath);
		File tempFile = new File(tempPath, fileName);
		String path = tempFile.getPath();
		file.transferTo(tempFile);
		File tfile = new File(path);
		return tfile;
	}*/

	public static String saveFileToFilesDir(MultipartFile file, String destDir) throws IOException {
		if(file == null || isEmpty(file.getName())) {
			return null;
		}
		String fileNameWithExt = file.getOriginalFilename();
		String[] fileNameArray = fileNameWithExt.split("\\.",2);
		String fileName = null;
		String ext = null;
		if(fileNameArray == null || fileNameArray.length <= 0) {
			return null;
		} else if(fileNameArray.length == 1) {
			fileName = fileNameArray[0];
			ext = ".file";
		} else {
			fileName = fileNameArray[0];
			ext = fileNameArray[1];
		}
		Utils.createDirectoryIfNotExists(destDir);
		// Do no overwrite existing file
		File tempFile = new File(destDir + File.separator +fileNameWithExt);
		for (int i = 0; tempFile.exists(); i++) {
			tempFile = new File(destDir + File.separator + fileName + i + "." + ext);
		}
		file.transferTo(tempFile);

		return tempFile.getName();
	}

	public static Date getSystemTimeInGMT() {
		return convertDateToGMT(new Date(), TimeZone.getDefault());
	}

	public static final Date convertDateToGMT(Date date, TimeZone tz) {
		if (date == null || tz == null) {
			return date;
		}
		long time = date.getTime();
		GregorianCalendar cal = toGregorianCalendar(date, tz);
		long offset = cal.get(Calendar.ZONE_OFFSET)
				+ cal.get(Calendar.DST_OFFSET);
		long gmtTime = time - offset;
		return new Date(gmtTime);
	}

	private static GregorianCalendar toGregorianCalendar(Date date, TimeZone tz) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.setTimeZone(tz);
		return cal;
	}

	public static final Date convertStringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
	}

}