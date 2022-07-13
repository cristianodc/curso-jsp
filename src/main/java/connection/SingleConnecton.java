package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnecton {

	private static String banco="jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	
	private static Connection conn = null;
	
	 public static Connection getConnection() 
	 	{
		 	return conn;
	 	}
	static
		{
			conectar();
		}
	
	public SingleConnecton() {
		conectar();
	}
	
	private static void conectar() 
		{
			try {
				if(conn == null) 
					{
					Class.forName("org.postgresql.Driver");/*Carrega o drive de conecxao*/
						conn = DriverManager.getConnection(banco, user, senha);
						conn.setAutoCommit(false);
						
					}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
