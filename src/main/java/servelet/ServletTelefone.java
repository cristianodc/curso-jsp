package servelet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;
import model.ModelTelefone;

/**
 * Servlet implementation class ServletTelefone
 */
@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServeletGenericUtil {
	private static final long serialVersionUID = 1L;
     private DAOUsuarioRepository dao = new DAOUsuarioRepository();
     private DAOTelefoneRepository daoFone = new  DAOTelefoneRepository();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTelefone() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// String acao = request.getParameter("excluir");
		
		try {
			String acao = request.getParameter("acao");
			if(acao != null && !acao.isEmpty() && acao.equals("excluir"))
				{
				    String idFone  = request.getParameter("id");
					
					daoFone.deleteFone(Long.parseLong(idFone));
					String userPai  = request.getParameter("userPai");
					
					ModelLogin model  = dao.consutalUsuarioId(Long.parseLong(userPai));
					List<ModelTelefone> lista = daoFone.listaFone(Long.parseLong(userPai));
					request.setAttribute("model", model);
					request.setAttribute("lista", lista);
					request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
					return;
				}
			
		
		    String idUser = request.getParameter("idUser");
		
		    if(idUser !=null && !idUser.isEmpty())
		    	{
		    
		    	List<ModelTelefone> lista = daoFone.listaFone(Long.parseLong(idUser));
				
				request.setAttribute("lista", lista);

					ModelLogin model  = dao.consutalUsuarioId(Long.parseLong(idUser));
					request.setAttribute("model", model);
					request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		    	
		    	}else 
		    		{

			    		List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
						request.setAttribute("modelLogins", lista);
					    request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
						request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);		
		    		}
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String usuarioPaiId = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			ModelTelefone modelF = new ModelTelefone();
			modelF.setNumero(numero);
			modelF.setUsuario_id_pai(dao.consutalUsuarioId(Long.parseLong(usuarioPaiId)));
			modelF.setUsuario_cad_id(super.getUserlogadoObj(request));
			
			daoFone.gravaTelefone(modelF);
			List<ModelTelefone> lista = daoFone.listaFone(Long.parseLong(usuarioPaiId));
			
			ModelLogin model  = dao.consutalUsuarioId(Long.parseLong(usuarioPaiId));
			request.setAttribute("model", model);
			
			request.setAttribute("lista", lista);
			request.setAttribute("msg", "Telefone Salvo com sucesso");
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
		}
		
	}

}
