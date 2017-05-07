package catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import catalog.exceptions.S3KeysNotFoundException;

@Configuration
public class AWSConfiguration {

	@Value("${cloud.aws.region}")
	private String region;

	@Value("${estampala.s3.accesskey}")
	private String accessKey;

	@Value("${estampala.s3.secretkey}")
	private String secretKey;

  @Bean
	public BasicAWSCredentials basicAWSCredentials() throws S3KeysNotFoundException {
		if (accessKey == null || secretKey == null || accessKey.isEmpty() || secretKey.isEmpty()){
			throw new S3KeysNotFoundException();
		}

		return new BasicAWSCredentials(accessKey, secretKey);
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentials awsCredentials) {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

		return s3Client;
	}
}
