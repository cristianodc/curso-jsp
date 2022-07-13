package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnecton;

/**
 * Servlet Filter implementation class FilterAutentifcacao
 */
@WebFilter(urlPatterns = {"/principal/*"})/*Index não pode passar por aqui*/
public class FilterAutentifcacao  implements Filter {
       private static Connection conn;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterAutentifcacao() {
       
    }

	/**
	 * @see Filter#destroy()
	 * Termina dos os porcessos
	 */
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * Intercepata todas as requisiçoes e as respostas no sistema
	 * tudo o que fro feito passa por aqui;
	 * validaçoes 
	 * commit e roolback de transações 
	 * redirecionalmentos especificos 
	 * validações em geral
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
	try {
		
			HttpServletRequest req  = (HttpServletRequest) request;
			
			HttpSession session = req.getSession();
			
			String usuario = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath();/*url que esta endo acessada*/
			
			/*Validar se esta logado se nao redireciona apra a tela de login*/
			
			if(usuario == null  && 	!urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) 
				{
					RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
					request.setAttribute("msg", "Realize o login ");
					redireciona.forward(request, response);
					return; /*Para a execuçao e redirecinal para o login*/
				}else 
					{
						chain.doFilter(request, response);
					}
			conn.commit();
	}catch (Exception e)
		{
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
			
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * Inicia os processos ou recuros quando o servidor sobe o projeto
	 * Ex: Inicia a conexao com o banco 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		conn = SingleConnecton.getConnection();
		
	}

}
