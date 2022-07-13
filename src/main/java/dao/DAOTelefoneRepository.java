package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnecton;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection conn;
	private DAOUsuarioRepository dao = new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {
		
		conn = SingleConnecton.getConnection();
	}
	
	public void gravaTelefone(ModelTelefone model) throws SQLException
		{
			String sql = "INSERT INTO telefone(numero, usuario_pai_id, usuario_cad_id)	VALUES (?, ?, ?);";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, model.getNumero());
			st.setLong(2,model.getUsuario_id_pai().getId());
			st.setLong(3,model.getUsuario_cad_id().getId());
			st.execute();
			conn.commit();
			
					
		}
	public void deleteFone(Long id) throws SQLException 
		{
			String sql = "DELETE FROM public.telefone	WHERE id=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
			conn.commit();
		}
	
	public List<ModelTelefone> listaFone(Long idUserPai) throws Exception
		{
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
			String sql  ="select * from telefone where usuario_pai_id= ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, idUserPai);
			ResultSet rs  = st.executeQuery();
			while(rs.next())
				{
					ModelTelefone fone = new ModelTelefone();
					fone.setId(rs.getLong("id"));
					fone.setNumero(rs.getString("numero"));
					fone.setUsuario_id_pai(dao.consutalUsuarioId(rs.getLong("usuario_pai_id")));
					fone.setUsuario_id_pai(dao.consutalUsuarioId(rs.getLong("usuario_cad_id")));
					retorno.add(fone);
				}
			
			
			
			return retorno;
			
		}
	
	
	
	
	
	
	
	
}
