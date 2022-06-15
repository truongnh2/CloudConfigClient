package mic.vn.client.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mic.vn.client.exception.AppException;
import mic.vn.client.model.Minio;
import mic.vn.client.service.IMinioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/minio")
public class MinioController {
	@Autowired
	IMinioService minioService;
	
	@PostMapping(path= "/upload", consumes = "application/json", produces = "application/json")
	public void uploadFileToMinio(@RequestBody Minio minio) {
		System.out.println("aaa");
		try {
			
			minioService.uploadFile(minio);
		} catch ( InvalidKeyException | NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");

		}
	}
	
	@GetMapping(path= "/upload")
	public void uploadFileToMinio2() {
		System.out.println("aaa");
		try {
			Minio minio = new Minio();
			minio.setAccessKey("minioadmin");
			minio.setSecretKey("minioadmin");
			minio.setBucket("test-bucket");
			minio.setUrlFileUpload("D:/Test_1.txt");
			minio.setUrlMinio("http://10.4.1.100:9000");
			minioService.uploadFile(minio);
		} catch ( InvalidKeyException | NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");

		}
	}

}
