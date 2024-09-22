package com.sg.controller.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sg.entity.Student;
import com.sg.repo.IStudentRepo;

@RestController
@RequestMapping("/api/std")
public class StudentController {

	@Autowired
	private IStudentRepo studentRepo;

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student std) throws Exception {
		System.out.println("StudentController.createStudent()");
		studentRepo.save(std);
		return ResponseEntity.ok(std);
	}

	@PostMapping("/{stdId}/upload")
	public ResponseEntity<Student> uploadFiles(@PathVariable("stdId") Long stdId,
			@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("Dtd Id :: " + stdId);
		Student std = studentRepo.findById(stdId).get();
		try {
			System.out.println("StudentController.uploadFiles()");
			String fileName = "./files/" +stdId+"_"+file.getOriginalFilename();
			InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(new File(fileName));
			IOUtils.copy(is, os);

			std.setPhotoPath(fileName);
			studentRepo.save(std);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(std);
	}

	@GetMapping("/{stdId}")
	public ResponseEntity<byte[]> downLoadFiles(@PathVariable("stdId") Long stdId) throws Exception {

		Student std = studentRepo.findById(stdId).get();
		String path = std.getPhotoPath();
		File f = new File(path);
		InputStream is = new FileInputStream(new File(path));
		byte[] data = new byte[is.available()];
		is.read(data, 0, is.available());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"").body(data);

		/*
		 * byte[] data = new byte[is.available()]; is.read(data, 0, is.available());
		 * return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
		 */

	}

}
