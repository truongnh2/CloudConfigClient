package mic.vn.client.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import mic.vn.client.model.Minio;

public interface IMinioService {
	Minio uploadFile(Minio minio) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, IOException;
	
}
