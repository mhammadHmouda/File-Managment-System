package filedemo;

import filedemo.connection.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDemoApplication.class, args);
//		DBConnection dbConnection = new DBConnection();
//		dbConnection.getConnection();
	}

}
