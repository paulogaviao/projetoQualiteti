package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
    
		private static String banco = "jdbc:mysql://localhost:3306/qualiteDB?autoReconnect=true";
		private static String password = "admin";
		private static String user = "admin";
		private static Connection connection = null;
		
		static {
			conectar();
		}
		/*ao chamar o construtor tem-se a conexão automática*/
		public SingleConnection() {
			conectar();
		}
		
		private static void conectar() {
			try {
				if(connection==null) {
					Class.forName("com.mysql.jdbc.Driver");
					connection=DriverManager.getConnection(banco, user, password);
					connection.setAutoCommit(false);
				}
				
			} catch (Exception e) {
			  throw new RuntimeException("erro ao conectar com o banco");
			  
			}
			 
		}
		public static Connection getConnection() {
			return connection;
		}
	}


