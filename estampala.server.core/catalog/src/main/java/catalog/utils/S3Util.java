package catalog.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import catalog.exceptions.S3ImageUploadException;

@Service
public class S3Util {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${cloud.aws.s3.url}")
	private String s3Url;

	public String upload(byte[] image, String extensionFile, S3Folders folder) throws S3ImageUploadException {
		String imageUrl = s3Url;		
		try {
			if (extensionFile == null || extensionFile.isEmpty()){
				extensionFile = "png";
			}
			
			String fileName = folder.getFolderName() + "/" + UUID.randomUUID().toString() + "." + extensionFile;
			imageUrl = imageUrl + fileName;
			
            InputStream stream = new ByteArrayInputStream(image);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(image.length);	            
            amazonS3.putObject(bucket, fileName, stream, meta);
            amazonS3.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);
        } catch (Exception ex) {
            throw new S3ImageUploadException(ex.getMessage());
        }
		
		return imageUrl;
	}

	public ResponseEntity<byte[]> download(String key) throws IOException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

		S3Object s3Object = amazonS3.getObject(getObjectRequest);

		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
	}

	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = amazonS3.listObjects(new ListObjectsRequest().withBucketName(bucket));

		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

		return s3ObjectSummaries;
	}	
}
