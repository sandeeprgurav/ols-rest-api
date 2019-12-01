package in.ols.rest.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "config")
public class Config{

	public static final String SESSION_INCREMENT_TIME_SECONDS = "SESSION_INCREMENT_TIME_SECONDS";
	public static final String AUTH_ENABLED = "AUTH_ENABLED";
	public static final String FILE_UPLOAD_PATH = "FILE_UPLOAD_PATH";
	public static final String SERVER_ROOT = "SERVER_ROOT";
	public static final String BUFFER_HOUR_BEFORE = "BUFFER_HOUR_BEFORE";
	public static final String BUFFER_HOUR_AHEAD = "BUFFER_HOUR_AHEAD";
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_SUBJECT = "mail.subject";
	public static final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketfactory.port";
	public static final String MAIL_SMTP_SOCKETFACTORY_CLASS = "mail.smtp.socketfactory.class";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_SIGNATURE = "mail.signature";
	public static final String MAIL_FROM_USER = "mail.from.user";
	public static final String MAIL_FROM_PASSWORD = "mail.from.password";
	public static final String MAIL_AUTH = "mail.smtp.auth";
	public static final String SECRET_KEY = "secret.key";
	public static final String OTP_WINDOW = "otp.window";
	public static final String S3_KEY = "s3.key";
	public static final String S3_SECRET = "s3.secret";
	public static final String S3_ENDPOINT = "s3.endpoint";
	public static final String S3_REGION = "s3.region";
	public static final String S3_BUCKET = "s3.bucket";
	
	public static final String FROM_EMAIL = "from.email";
	public static final String FROMNAME = "from.name";
	public static final String AWS_SES_HOST = "aws.ses.host";
	public static final String AWS_SES_SMTP_USERNAME = "aws.ses.smtp.username";
	public static final String AWS_SES_SMTP_PASSWORD = "aws.ses.smtp.password";
	
	public static final String PREPEND_ZEROS = "%1$06d";
	

	@Indexed
	private String property;
	private Object value;
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
