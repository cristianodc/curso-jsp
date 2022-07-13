package servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import dao.DaoLoginRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServeletLogin
 */
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) 
public class ServeletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private DaoLoginRepository dao = new DaoLoginRepository();  
    private DAOUsuarioRepository daoUso = new DAOUsuarioRepository();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletLogin() {
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout"))
			{
				request.getSession().invalidate(); /*Invalida os atributos da sessao atual*/
				RequestDispatcher redireciona = request.getRequestDispatcher("index.jsp");
				redireciona.forward(request, response);				
			}else 
				{
					doPost(request, response);
				}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome= request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		
			
				if((nome != null && !nome.isEmpty() && senha != null && !senha.isEmpty()))
				{
						ModelLogin model = new ModelLogin();
						model.setLogin(nome);
						model.setSenha(senha);
						
					if(dao.validarLogin(model))
						{	
							try {
								model = daoUso.consutalUsuarioLogado(model.getLogin());
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							request.getSession().setAttribute("usuario", model.getLogin());
							request.getSession().setAttribute("perfil", model.getPerfil());
							request.getSession().setAttribute("fotoUser", model.getFotoUser());
							
							if(url == null || url.equals("null"))
								{
									url="principal/principal.jsp";
								}
							
							RequestDispatcher redirecionar = request.getRequestDispatcher(url);
							redirecionar.forward(request, response);
							
						}else 
							{
							RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
							request.setAttribute("msg","Login ou senha corretamente  !");
							redirecionar.forward(request, response);
								
							}
				}else 
					{
						RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
						request.setAttribute("msg","Login ou senha vazios  !");
						redirecionar.forward(request, response);
						
					}
	}

}
