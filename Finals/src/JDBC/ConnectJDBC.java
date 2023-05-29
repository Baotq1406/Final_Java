package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectJDBC {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			// Đăng ký MYSQL Driver với DriverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			// Các thông số
			String url ="jdbc:mySQL://localhost:3306/Finals";
			String username = "root";
			String password = "bao140624";
			
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c , PreparedStatement prst , ResultSet rs) {
		try	{
			if(c!=null) {
				c.close();
			}
			if(prst!=null) {
				prst.close();
			}
			if(rs!=null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

