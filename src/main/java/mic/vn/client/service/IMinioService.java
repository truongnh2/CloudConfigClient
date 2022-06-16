package mic.vn.client.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import mic.vn.client.model.Minio;

public interface IMinioService {
	Minio uploadFile(Minio minio) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, IOException;
	
	byte[] downloadFile(Minio minio, String urlOutput) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;
	
	void uploadFile2(Minio minio, MultipartFile file) throws FileNotFoundException, IOException, InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException;
	
}
