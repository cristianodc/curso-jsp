package servelet;

import java.io.Serializable;
import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnecton;
import dao.DAOUsuarioRepository;
import model.ModelLogin;

public class ServeletGenericUtil extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository dao = new DAOUsuarioRepository();
	
	public Long getUserlogado(HttpServletRequest request) throws Exception
		{
			HttpSession session = request.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			return dao.consutalUsuarioLogado(usuarioLogado).getId();
		}
	
	public ModelLogin getUserlogadoObj(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return dao.consutalUsuarioLogado(usuarioLogado);
	}
}
