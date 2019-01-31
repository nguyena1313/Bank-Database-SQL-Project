import java.sql.*;


public class Connect {

	private static Connection con;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
			 

		//get connection
		 con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/bankdata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root","password"); 
		 Class.forName("com.mysql.jdbc.Driver");

	}

	public Connection getCon(){
		return con;
	}
}
