package com.ray.jnm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public static String rename(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
//		Long random = (long) (Math.random() * now);
		String fileName = now + "" + name;

//		if (name.indexOf(".") != -1) {
//			fileName += name.substring(name.lastIndexOf("."));
//		}

		return fileName;
	}
	
}
