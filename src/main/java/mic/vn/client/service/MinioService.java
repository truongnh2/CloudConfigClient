package mic.vn.client.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import mic.vn.client.model.Minio;
@Service
public class MinioService implements IMinioService{
	private MinioClient minioClient;
	@Override
	public Minio uploadFile(Minio minio) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, IOException {
		try {
		      // Create a minioClient with the MinIO server playground, its access key and secret key.
		      minioClient =
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
	@Override
	public void uploadFile2(Minio minio, MultipartFile file) throws IOException, InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException {
		minioClient =
		          MinioClient.builder()
		              .endpoint(minio.getUrlMinio())
		              .credentials(minio.getAccessKey(), minio.getSecretKey())
		              .build();
  
            minioClient.putObject(
            	     PutObjectArgs.builder().bucket(minio.getBucket()).object(file.getOriginalFilename()).stream(
            	    		 file.getInputStream(), file.getSize(), -1)
            	         .contentType(file.getContentType())
            	         .build());
            //(defaultBucketName, defaultBaseFolder + name, file.getAbsolutePath());
        

    }
	@Override
	public byte[] downloadFile(Minio minio, String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		 minioClient =
		          MinioClient.builder()
		              .endpoint(minio.getUrlMinio())
		              .credentials(minio.getAccessKey(), minio.getSecretKey())
		              .build();

		 InputStream stream =
			     minioClient.getObject(
			   GetObjectArgs.builder()
			     .bucket(minio.getBucket())
			     .object(fileName)
			     .build());

		byte[] content = IOUtils.toByteArray(stream);
		stream.close();
        return content;
	}
}
