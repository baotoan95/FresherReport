package com.fresher.report.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fresher.report.constants.PathContant;

public class UploadFileService {
    public static boolean uploadSingleFile(MultipartFile file, String name) {
	if (!file.isEmpty()) {
	    BufferedOutputStream stream = null;
	    try {
		byte[] bytes = file.getBytes();
		File dir = new File(PathContant.UPLOAD_PATH);

		if (!dir.exists()) {
		    dir.mkdir();
		}

		File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		System.out.println("Uploaded to: " + serverFile.getAbsolutePath());
		return true;
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
		try {
		    stream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return false;
    }

    public static List<String> uploadMultipleFile(MultipartHttpServletRequest request) {
	List<String> pathFilesUploaded = new ArrayList<>();

	Iterator<String> itr = request.getFileNames();
	MultipartFile mpf = null;
	File dir = new File(PathContant.UPLOAD_PATH);

	while (itr.hasNext()) {
	    mpf = request.getFile(itr.next());
	    System.out.println(mpf.getOriginalFilename());
	    try {
		String pathFile = dir.getAbsolutePath() + File.separator + mpf.getOriginalFilename();
		File serverFile = new File(pathFile);
		FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(serverFile));
		System.out.println(mpf.getOriginalFilename() + " uploaded! ");
		pathFilesUploaded.add(mpf.getOriginalFilename());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return pathFilesUploaded;
    }
}
