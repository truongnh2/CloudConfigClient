package mic.vn.client.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import mic.vn.client.exception.AppException;
import mic.vn.client.model.Minio;
import mic.vn.client.service.IMinioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/minio")
public class MinioController {
	@Autowired
	IMinioService minioService;

	@PostMapping(path = "/upload", consumes = "application/json", produces = "application/json")
	public void uploadFileToMinio(@RequestBody Minio minio) {
		System.out.println("aaa");
		try {

			minioService.uploadFile(minio);
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");

		}
	}
	
	@PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
		Minio minio = new Minio();
		minio.setAccessKey("minioadmin");
		minio.setSecretKey("minioadmin");
		minio.setBucket("test-bucket");
		minio.setUrlFileUpload("D:/Test_1.txt");
		minio.setUrlMinio("http://10.4.1.100:9000");
		
		try {
			minioService.uploadFile2(minio,files);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());
        return result;
    }

	@GetMapping(path = "/upload")
	public void uploadFileToMinio2() {
		try {
			Minio minio = new Minio();
			minio.setAccessKey("minioadmin");
			minio.setSecretKey("minioadmin");
			minio.setBucket("test-bucket");
			minio.setUrlFileUpload("D:/Test_1.txt");
			minio.setUrlMinio("http://10.4.1.100:9000");
			minioService.uploadFile(minio);
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");

		}
	}

	@GetMapping(path = "/download")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadFileFromMinio(@RequestParam(value = "AccessKey") String accessKey,
			@RequestParam(value = "SecretKey") String secretKey, @RequestParam(value = "Bucket") String bucket,
			@RequestParam(value = "UrlServer") String urlMinio, @RequestParam(value = "ObjectName") String objectName) {
		try {
			Minio minio = new Minio();
			minio.setAccessKey(accessKey);
			minio.setSecretKey(secretKey);
			minio.setBucket(bucket);
			minio.setUrlMinio(urlMinio);
			//minio.setUrlMinio("http://10.4.1.100:9000");
			minio.setObjectName(objectName);
			byte[] data = minioService.downloadFile(minio, objectName);
			ByteArrayResource resource = new ByteArrayResource(data);
			return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
					.header("Content-disposition", "attachment; filename=\"" + objectName + "\"").body(resource);
		} catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException
				| ServerException | XmlParserException | InvalidKeyException | NoSuchAlgorithmException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");

		}
	}

}
