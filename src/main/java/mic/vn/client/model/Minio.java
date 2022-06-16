package mic.vn.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Minio {
	private String accessKey;
	private String secretKey;
	private String urlMinio;
	private String bucket;
	private String urlFileUpload;
	private String objectName;
}
