package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnecton;
import model.ModelLogin;

public class DaoLoginRepository {

	private Connection conn;
	
	public DaoLoginRepository() {
		conn = SingleConnecton.getConnection();
	}
	
	public boolean validarLogin(ModelLogin model)
		{
			String sql = "select * from model_login where login=? and senha=? ";
			boolean teste = false;
			
			try {
				PreparedStatement statement;
				statement = conn.prepareStatement(sql);
				statement.setString(1, model.getLogin());
				statement.setString(2, model.getSenha());
				
				ResultSet rset = statement.executeQuery();
				
				if(rset.next())
					{
						teste= true;
					}else 
						{
							teste= false;
						}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			return teste;
		}
}
