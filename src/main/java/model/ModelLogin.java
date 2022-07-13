package model;

import java.io.Serializable;
import java.sql.Date;

public class ModelLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private boolean userAdmin;
	private String perfil;
	private String sexo;
	private String fotoUser;
	private String extensaoFoto;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;
	private Date dataNascimento;
	
	public ModelLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isNovo() 
		{
		 boolean teste = false ;
		 
			if(this.id == null)
				{
					teste =  true;
				}else if(this.id != null && this.id > 0)
					{
						teste= false;
					}
			return teste;
		}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getExtensaoFoto() {
		return extensaoFoto;
	}

	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
