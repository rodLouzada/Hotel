import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		System.out.println("Conectou no BD");
		return DriverManager.getConnection("jdbc:mysql://localhost/hotel","root","");
	}
}