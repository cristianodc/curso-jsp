package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.SingleConnecton;
import model.ModelLogin;

public class DAOUsuarioRepository {

	Connection conn;
	
	public DAOUsuarioRepository() {

		conn = SingleConnecton.getConnection();
	}
	
	public ModelLogin gravarUser(ModelLogin model,Long userLogado) throws Exception
	{
		if(model.isNovo())
			{
				String sql = "INSERT INTO public.model_login(login, senha, nome, email, perfil, sexo,  cep, logradouro, bairro,"
						+ " localidade, uf, numero,  usuario_id,datanscimento)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, model.getLogin());
				prepare.setString(2, model.getSenha());
				prepare.setString(3, model.getNome());
				prepare.setString(4, model.getEmail());
				prepare.setString(5, model.getPerfil());
				prepare.setString(6, model.getSexo());
				prepare.setString(7,model.getCep() );
				prepare.setString(8,model.getLogradouro());
				prepare.setString(9,model.getBairro());
				prepare.setString(10, model.getLocalidade());
				prepare.setString(11,model.getUf());
				prepare.setString(12,model.getNumero());
				prepare.setLong(13, userLogado);
				prepare.setDate(14, model.getDataNascimento());
				
				prepare.execute();
				conn.commit();
				
				if(model.getExtensaoFoto() != null && !model.getFotoUser().isEmpty())
					{
							sql = "UPDATE model_login SET fotouser =? , extensaoFoto=? where login=?";
							 prepare = conn.prepareStatement(sql);
						 
						    prepare.setString(1, model.getFotoUser());
							prepare.setString(2, model.getExtensaoFoto());
							prepare.setString(3, model.getLogin());
							prepare.execute();
							conn.commit();
					}
			
			
			}else 
				{
				   String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?,"
				   		+ "cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?, datanascimento=?	WHERE id = "+model.getId()+";";
				   PreparedStatement prepare = conn.prepareStatement(sql);
				    prepare.setString(1, model.getLogin());
					prepare.setString(2, model.getSenha());
					prepare.setString(3, model.getNome());
					prepare.setString(4, model.getEmail());
					prepare.setString(5, model.getPerfil());
					prepare.setString(6, model.getSexo());
					prepare.setString(7,model.getCep() );
					prepare.setString(8,model.getLogradouro());
					prepare.setString(9,model.getBairro());
					prepare.setString(10, model.getLocalidade());
					prepare.setString(11,model.getUf());
					prepare.setString(12,model.getNumero());
					prepare.setDate(13, model.getDataNascimento());
					prepare.executeUpdate();
					conn.commit();
					
					if(model.getFotoUser() != null && !model.getFotoUser().isEmpty()) 
					{
						sql = "UPDATE model_login SET fotouser =? , extensaoFoto=? where id=?";
						 prepare = conn.prepareStatement(sql);
						 
						    prepare.setString(1, model.getFotoUser());
							prepare.setString(2, model.getExtensaoFoto());
							prepare.setLong(3, model.getId());
							prepare.executeUpdate();
							conn.commit();
					}
				}
		
		
		return this.consutalUsuario(model.getLogin());
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome,Long userLogado) throws SQLException
		{
			List<ModelLogin> retorno = new ArrayList<>();
			
			String sql= "SELECT * FROM model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,"%"+ nome + "%" );
			statement.setLong(2,userLogado);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next())
				{
				ModelLogin model = new ModelLogin();
				model.setId   (resultado.getLong("id"));
				model.setNome (resultado.getString("nome"));
				model.setEmail(resultado.getString("email"));
				model.setLogin(resultado.getString("login"));
				model.setPerfil(resultado.getString("perfil"));
				model.setSexo(resultado.getString("sexo"));
							
				retorno.add(model);
				}
			
			return retorno;
		}
	public List<ModelLogin> consultaUsuarioListOffset(String nome,Long userLogado,int offset) throws SQLException
	{
		List<ModelLogin> retorno = new ArrayList<>();
		
		String sql= "SELECT * FROM model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset "+offset+" limit 5";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1,"%"+ nome + "%" );
		statement.setLong(2,userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next())
			{
			ModelLogin model = new ModelLogin();
			model.setId   (resultado.getLong("id"));
			model.setNome (resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setLogin(resultado.getString("login"));
			model.setPerfil(resultado.getString("perfil"));
			model.setSexo(resultado.getString("sexo"));
						
			retorno.add(model);
			}
		
		return retorno;
	}
	public int consultaUsuarioListTotalPaginaPaginacao(String nome,Long userLogado) throws SQLException
	{
		Double cadastros = 0.0;
		
		String sql= "SELECT count(1) as total FROM model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1,"%"+ nome + "%" );
		statement.setLong(2,userLogado);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next())
		{
		 cadastros = resultado.getDouble("total");
		}
	
		
		Double porpagina= 5.0;
		Double pagina= cadastros / porpagina;
		
		Double resto = pagina % 2;
		if(resto > 0)
			{
				pagina ++;
			}
		return pagina.intValue();
		}
	
	public List<ModelLogin> consultaUsuarioListPaginado(Long userLogado,Integer offset) throws SQLException
	{
		List<ModelLogin> retorno = new ArrayList<>();
		
		String sql= "SELECT * FROM model_login where useradmin is false and usuario_id="+userLogado +" order by id offset " + offset + "limit 5";
		PreparedStatement statement = conn.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next())
			{
			ModelLogin model = new ModelLogin();
			model.setId   (resultado.getLong("id"));
			model.setNome (resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setLogin(resultado.getString("login"));
			model.setPerfil(resultado.getString("perfil"));
			model.setSexo(resultado.getString("sexo"));			
			retorno.add(model);
			}
		
		return retorno;
	}
	
		public List<ModelLogin> consultaUsuarioList(Long userLogado) throws SQLException
		{
			List<ModelLogin> retorno = new ArrayList<>();
			
			String sql= "SELECT * FROM model_login where useradmin is false and usuario_id="+userLogado +" limit 5";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next())
				{
				ModelLogin model = new ModelLogin();
				model.setId   (resultado.getLong("id"));
				model.setNome (resultado.getString("nome"));
				model.setEmail(resultado.getString("email"));
				model.setLogin(resultado.getString("login"));
				model.setPerfil(resultado.getString("perfil"));
				model.setSexo(resultado.getString("sexo"));			
				retorno.add(model);
				}
			
			return retorno;
		}
	public ModelLogin consutalUsuario(String login, long userLogado)throws Exception {
		
		String sql = "Select * from model_login where upper(login) = upper('"+login+"') and useradmin is false and ususario_id+"+userLogado ;
		ModelLogin model = new ModelLogin();
		PreparedStatement prepareSql = conn.prepareStatement(sql);
		//prepareSql.setString(1, login);
		ResultSet rs  = prepareSql.executeQuery();
		if(rs.next())
			{
				
				model.setId(rs.getLong("id"));
				model.setNome(rs.getString("nome"));
				model.setEmail(rs.getString("email"));
				model.setLogin(rs.getString("login"));
				model.setSenha(rs.getString("senha"));
				model.setPerfil(rs.getString("perfil"));
				model.setSexo(rs.getString("sexo"));
				model.setFotoUser(rs.getString("fotouser"));
			}
		
		return model;
	}
	
public ModelLogin consutalUsuario(String login)throws Exception {
		
		String sql = "Select * from model_login where upper(login) = upper('"+login+"') and useradmin is false";
		ModelLogin model = new ModelLogin();
		PreparedStatement prepareSql = conn.prepareStatement(sql);
		
		ResultSet rs  = prepareSql.executeQuery();
		if(rs.next())
			{
				/*****************************************/
				model.setId(rs.getLong("id"));
				model.setNome(rs.getString("nome"));
				model.setEmail(rs.getString("email"));
				model.setLogin(rs.getString("login"));
				model.setSenha(rs.getString("senha"));
				model.setPerfil(rs.getString("perfil"));
				model.setSexo(rs.getString("sexo"));
				model.setFotoUser(rs.getString("fotouser"));
				model.setCep(rs.getString("cep"));
				model.setLogradouro(rs.getString("logradouro"));
				model.setBairro(rs.getString("bairro"));
				model.setLocalidade(rs.getString("localidade"));
				model.setUf(rs.getString("uf"));
				model.setNumero(rs.getString("numero"));
				model.setDataNascimento(rs.getDate("datanascimento"));
				
				/*
				 * sem endereco
				 * model.setId(rs.getLong("id"));
				model.setNome(rs.getString("nome"));
				model.setEmail(rs.getString("email"));
				model.setLogin(rs.getString("login"));
				model.setSenha(rs.getString("senha"));
				model.setPerfil(rs.getString("perfil"));
				model.setSexo(rs.getString("sexo"));
				model.setFotoUser(rs.getString("fotouser"));
				 * */
				
			}
		
		return model;
	}
public ModelLogin consutalUsuarioLogado(String login)throws Exception {
	
	String sql = "Select * from model_login where upper(login) = upper('"+login+"')";
	ModelLogin model = new ModelLogin();
	PreparedStatement prepareSql = conn.prepareStatement(sql);
	
	ResultSet rs  = prepareSql.executeQuery();
	if(rs.next())
		{
			
			model.setId(rs.getLong("id"));
			model.setNome(rs.getString("nome"));
			model.setEmail(rs.getString("email"));
			model.setLogin(rs.getString("login"));
			model.setSenha(rs.getString("senha"));
			model.setUserAdmin(rs.getBoolean("useradmin"));
			model.setPerfil(rs.getString("perfil"));
			model.setSexo(rs.getString("sexo"));
		    model.setFotoUser(rs.getString("fotouser"));
		}
	
	return model;
}
	
public ModelLogin consutalUsuarioId(String id,Long userLogado)throws Exception {
		
		String sql = "Select * from model_login where id = ? and useradmin is false and usuario_id=?";
		ModelLogin model = new ModelLogin();
		PreparedStatement prepareSql = conn.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(id));
		prepareSql.setLong(2,userLogado);
		ResultSet rs  = prepareSql.executeQuery();
		if(rs.next())
			{
				
				model.setId(rs.getLong("id"));
				model.setNome(rs.getString("nome"));
				model.setEmail(rs.getString("email"));
				model.setLogin(rs.getString("login"));
				model.setSenha(rs.getString("senha"));
				model.setPerfil(rs.getString("perfil"));
				model.setSexo(rs.getString("sexo"));
				model.setFotoUser(rs.getString("fotouser"));
				model.setExtensaoFoto(rs.getString("extensaofoto"));
				model.setCep(rs.getString("cep"));
				model.setLogradouro(rs.getString("logradouro"));
				model.setBairro(rs.getString("bairro"));
				model.setLocalidade(rs.getString("localidade"));
				model.setUf(rs.getString("uf"));
				model.setNumero(rs.getString("numero"));
				model.setDataNascimento(rs.getDate("datanascimento"));
			}
		
		return model;
	}
public ModelLogin consutalUsuarioId(Long id)throws Exception {
	
	String sql = "Select * from model_login where id = ? and useradmin is false ";
	ModelLogin model = new ModelLogin();
	PreparedStatement prepareSql = conn.prepareStatement(sql);
	prepareSql.setLong(1, id);
	
	ResultSet rs  = prepareSql.executeQuery();
	if(rs.next())
		{
			
			model.setId(rs.getLong("id"));
			model.setNome(rs.getString("nome"));
			model.setEmail(rs.getString("email"));
			model.setLogin(rs.getString("login"));
			model.setSenha(rs.getString("senha"));
			model.setPerfil(rs.getString("perfil"));
			model.setSexo(rs.getString("sexo"));
			model.setFotoUser(rs.getString("fotouser"));
			model.setExtensaoFoto(rs.getString("extensaofoto"));
			model.setCep(rs.getString("cep"));
			model.setLogradouro(rs.getString("logradouro"));
			model.setBairro(rs.getString("bairro"));
			model.setLocalidade(rs.getString("localidade"));
			model.setUf(rs.getString("uf"));
			model.setNumero(rs.getString("numero"));
		}
	
	return model;
}
	
	public boolean validaLogin(String login) throws Exception 
		{
			String sql= "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
			PreparedStatement prepareSql = conn.prepareStatement(sql);
			//prepareSql.setString(1, login);
			ResultSet rs  = prepareSql.executeQuery();
			
			rs.next();				
			return rs.getBoolean("existe");
				
			
		}
	
	public void deletar(String idUser) throws SQLException
	{
		String sql = "delete from model_login where id = ? and useradmin is false";
		
		PreparedStatement prepareSql = conn.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUser));
		prepareSql.executeUpdate();
		conn.commit();
	}
	
	public int totalPagina(Long userLogado) throws Exception 
		{
			String sql = "select count(1) as total from model_login where usuario_id = "+userLogado;
			Double cadastros = 0.0;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			if(resultado.next())
				{
				 cadastros = resultado.getDouble("total");
				}
			
			
			Double porpagina= 5.0;
			Double pagina= cadastros / porpagina;
			
			Double resto = pagina % 2;
			if(resto > 0)
				{
					pagina ++;
				}
			return pagina.intValue();
		}
	
}
