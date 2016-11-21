import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		Connection conectou =  DriverManager.getConnection("jdbc:mysql://localhost/hotel","root","");
			System.out.println("Conectou ao banco");
		return conectou;
	}
}