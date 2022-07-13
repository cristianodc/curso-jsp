package servelet;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServletUsuControler
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/ServletUsuControler"})
public class ServletUsuControler extends ServeletGenericUtil {
	private static final long serialVersionUID = 1L;
       
	DAOUsuarioRepository dao = new DAOUsuarioRepository();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuControler() {
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) 
				{
					String id = request.getParameter("id");
					dao.deletar(id);
					
					 request.setAttribute("msg","USUARIO EXCLUIDO COM SUCESSO!");
					 request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
					 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
					 List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
					 request.setAttribute("modelLogins", lista);
					
				}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) 
					{
						String id = request.getParameter("id");
						dao.deletar(id);
						
						response.getWriter().write("Excluido com sucesso");
					
				}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) 
						{
							String nome = request.getParameter("nomeBusca");
							
							List<ModelLogin>  dadosJsonUser = dao.consultaUsuarioList(nome,super.getUserlogado(request));
							ObjectMapper mapper = new ObjectMapper();
							String json = mapper.writeValueAsString(dadosJsonUser);
							response.addHeader("totalPagina", ""+dao.consultaUsuarioListTotalPaginaPaginacao(nome, super.getUserlogado(request)));
							response.getWriter().write(json);
							
						}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) 
						{
							String nome = request.getParameter("nomeBusca");
							String pagina = request.getParameter("pagina");
							List<ModelLogin>  dadosJsonUser = dao.consultaUsuarioListOffset(nome,super.getUserlogado(request), Integer.parseInt(pagina));
							ObjectMapper mapper = new ObjectMapper();
							String json = mapper.writeValueAsString(dadosJsonUser);
							response.addHeader("totalPagina", ""+dao.consultaUsuarioListTotalPaginaPaginacao(nome, super.getUserlogado(request)));
							response.getWriter().write(json);
						}
				else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) 
							{
								String id = request.getParameter("id");
								ModelLogin model = new ModelLogin();
								model = dao.consutalUsuarioId(id,super.getUserlogado(request));
								
								List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
								request.setAttribute("modelLogins", lista);
								 request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
								request.setAttribute("msg", "Usuário em edição");
								RequestDispatcher redireciona  = request.getRequestDispatcher("principal/usuario.jsp");
								request.setAttribute("model", model);
								redireciona.forward(request, response);
								
							}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser"))
								{
									List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
									
									request.setAttribute("msg", "Usuarios Cadastrados");
									
									RequestDispatcher redireciona  = request.getRequestDispatcher("principal/usuario.jsp");
									 request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
									request.setAttribute("modelLogins", lista);
									redireciona.forward(request, response);
									
								}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("dowloadFoto")) 
								{
									String idUser = request.getParameter("id");
									ModelLogin model = dao.consutalUsuarioId(idUser, super.getUserlogado(request));
									if(model.getFotoUser() != null && !model.getFotoUser().isEmpty())
										{
											response.setHeader("Content-Disposition", "attachment;filename=arquivo."+model.getExtensaoFoto());
											response.getOutputStream().write
												(new org.apache.tomcat.util.codec.binary.Base64().decodeBase64(model.getFotoUser().split("\\,")[1]));
											
										}
									
								}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
									
									Integer offset = Integer.parseInt( request.getParameter("pagina"));
									List<ModelLogin> lista = dao.consultaUsuarioListPaginado(this.getUserlogado(request), offset);
									
									RequestDispatcher redireciona  = request.getRequestDispatcher("principal/usuario.jsp");
								    request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
									request.setAttribute("modelLogins", lista);
									redireciona.forward(request, response);
 									
								}else 
								{
									List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
									request.setAttribute("modelLogins", lista);
									 request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
									request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);						
								}
			 
			
		}catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redireciona  = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
			{
				String msg    = "Operação realizada com sucesso!!";
				/*
				 * 
				 * String id     = request.getParameter("id");
					String nome   = request.getParameter("nome");
					String email  = request.getParameter("email");
					String login  = request.getParameter("login");
					String senha  = request.getParameter("senha");
					String perfil = request.getParameter("perfil");
					String sexo   = request.getParameter("sexo");
				 * */
			    String id     = request.getParameter("id");
				String nome   = request.getParameter("nome");
				String email  = request.getParameter("email");
				String login  = request.getParameter("login");
				String senha  = request.getParameter("senha");
				String perfil = request.getParameter("perfil");
				String sexo   = request.getParameter("sexo");
				
				String cep    = request.getParameter("cep");
				String logradouro =request.getParameter("logradouro");
				String bairro     = request.getParameter("bairro");
				String localidade = request.getParameter("localidade");
				String uf         = request.getParameter("uf");
				String nro        = request.getParameter("nro");
				String dataNasct  = request.getParameter("dtnasc");
				
				ModelLogin model  = new ModelLogin();
				/*
				 *  model.setId(id !=null && !id.isEmpty() ? Long.parseLong(id) : null);
					model.setNome(nome);
					model.setEmail(email);
					model.setLogin(login);
					model.setSenha(senha);
					model.setPerfil(perfil);
					model.setSexo(sexo);
				 * */
				model.setId(id !=null && !id.isEmpty() ? Long.parseLong(id) : null);
				model.setNome(nome);
				model.setEmail(email);
				model.setLogin(login);
				model.setSenha(senha);
				model.setPerfil(perfil);
				model.setSexo(sexo);
				
				model.setCep(cep);
				model.setLogradouro(logradouro);
				model.setBairro(bairro);
				model.setLocalidade(localidade);
				model.setUf(uf);
				model.setNumero(nro);
				model.setDataNascimento(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(dataNasct).getTime()));
				
				/*salvando imagem aula 22.56 ate 22.60*/
				 if (ServletFileUpload.isMultipartContent(request)) {
					
					  Part filePart = request.getPart("fileFoto");
					  
					  if(filePart.getSize() > 0)
					  	{
						  InputStream fileContent = filePart.getInputStream();
					      byte[] byteArray = getByteArray(fileContent);
					      String base64String ="data:image/"+filePart.getContentType().split("\\/")[1]+";base64,"+Base64.getEncoder().encodeToString(byteArray);
					        model.setFotoUser(base64String);
							model.setExtensaoFoto(filePart.getContentType().split("\\/")[1]);
					  	}
				
					 
				}
				
				if(dao.validaLogin(model.getLogin()) && model.getId() == null) 
					{
						msg = "JA EXISTE USUARIO COM ESTE LOGIN, INFORME OUTRO LOGIN";
					}else 
						{
							if(model.isNovo())
								{
								msg = "USUÁRIO GRAVADO COM SUCESSO!!!";
								}else 
									{
									msg = "USUÁRIO ATUALIZADO COM SUCESSO!!!";
									}
						 model=dao.gravarUser(model,super.getUserlogado(request));
						}
				
		    	
				List<ModelLogin> lista = dao.consultaUsuarioList(super.getUserlogado(request));
				request.setAttribute("modelLogins", lista);
				request.setAttribute("msg", msg);
				 request.setAttribute("totalPaginas", dao.totalPagina(this.getUserlogado(request)));
				RequestDispatcher redireciona  = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("model", model);
				redireciona.forward(request, response);
			
			}catch (Exception e) {
				
			e.printStackTrace();
			RequestDispatcher redireciona  = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
		
	}
	
	 private static byte[] getByteArray(InputStream is) throws Exception {
		    ByteArrayOutputStream b = new ByteArrayOutputStream();
		    BufferedOutputStream os = new BufferedOutputStream(b);
		    while (true) {
		      int i = is.read();
		      if (i == -1) break;
		      os.write(i);
		    }
		    os.flush();
		    os.close();
		    return b.toByteArray();
		  }

}
