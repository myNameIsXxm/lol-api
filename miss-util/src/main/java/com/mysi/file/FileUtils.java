package com.mysi.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
	public static String getUploadRoot(){
		Properties p = new Properties();   
		try {
			p.load(FileUtils.class.getResourceAsStream("/property/upload.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "c://upload";
		} catch (IOException e) {
			e.printStackTrace();
			return "c://upload";
		} 
		return p.getProperty("uploadfile");
	}
}