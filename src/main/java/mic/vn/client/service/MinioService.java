package mic.vn.client.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import mic.vn.client.model.Minio;
@Service
public class MinioService implements IMinioService{
	@Override
	public Minio uploadFile(Minio minio) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, IOException {
		try {
		      // Create a minioClient with the MinIO server playground, its access key and secret key.
		      MinioClient minioClient =
		          MinioClient.builder()
		              .endpoint(minio.getUrlMinio())
		              .credentials(minio.getAccessKey(), minio.getSecretKey())
		              .build();

		      // Make 'asiatrip' bucket if not exist.
		      boolean found =
		          minioClient.bucketExists(BucketExistsArgs.builder().bucket(minio.getBucket()).build());
		      if (!found) {
		        // Make a new bucket called 'bucket'.
		        minioClient.makeBucket(MakeBucketArgs.builder().bucket(minio.getBucket()).build());
		      } else {
		        System.out.println("Bucket "+minio.getBucket()+" already exists.");
		      }

		      // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
		      // 'asiatrip'.
		      minioClient.uploadObject(
		          UploadObjectArgs.builder()
		              .bucket(minio.getBucket())
		              .object(minio.getUrlFileUpload().substring(minio.getUrlFileUpload().lastIndexOf("/")+1))
		              .filename(minio.getUrlFileUpload())
		              .build());
		      System.out.println(
		    		  minio.getUrlFileUpload() +" is successfully uploaded as ");
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		      System.out.println("HTTP trace: " + e.httpTrace());
		    }
		return minio;
	}
	public void downloadFile() {}
}
