package com.fresher.report.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class RestDownloadController {
	@Autowired
	private ServletContext context;

	@RequestMapping(value="/list-templates")
	public ResponseEntity<List<String>> listTemplate(){
		String templatesFolder = context.getRealPath("/WEB-INF/download-files");
		File folder = new File(templatesFolder);
		File[] files = folder.listFiles();
		List<String> result = new ArrayList<>();
		for(File file : files) {
			result.add(file.getName());
		}
		if(result.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping("/{fileName:.+}")
	public void downloadTemplates(HttpServletResponse response, @PathVariable("fileName") String fileName) {
		String downloadFolder = context.getRealPath("/WEB-INF/download-files/");
		Path file = Paths.get(downloadFolder, fileName);
		
		// Check if file exists
		if (Files.exists(file)) {
			// set content type
			response.setContentType("application/vnd.ms-excel");
			// add response header
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			try {
				// copies all bytes from a file to an output stream
				Files.copy(file, response.getOutputStream());
				// flushes output stream
				response.getOutputStream().flush();
			} catch (IOException e) {
				System.out.println("Error :- " + e.getMessage());
			}
		} else {
			System.out.println("Sorry File not found!!!!");
		}
	}

}
