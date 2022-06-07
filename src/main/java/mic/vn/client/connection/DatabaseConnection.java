package mic.vn.client.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
	private static Connection con = null;

	private static String url;

	private static String username;

	private static String password;

	private static String driverClassName;

	public static Connection getConnection() {
		try {
			Properties prop = new Properties();
			//String propFileName = "C:\\Users\\truongnh\\Documents\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\CloudConfigClient\\src\\main\\resources\\application.properties";
			String propFileName = System.getProperty("user.dir") +"\\src\\main\\resources\\application.properties";
			InputStream input = new FileInputStream(propFileName);
			prop.load(input);
			url = prop.getProperty("spring.datasource.url");
			username = prop.getProperty("spring.datasource.username");
			password = prop.getProperty("spring.datasource.password");
			driverClassName = prop.getProperty("spring.datasource.driver-class-name");
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
