package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable {

	private Long id;
	private String numero;
	private ModelLogin usuario_id_pai;
	private ModelLogin usuario_cad_id;
	
	public ModelTelefone() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public ModelLogin getUsuario_id_pai() {
		return usuario_id_pai;
	}

	public void setUsuario_id_pai(ModelLogin usuario_id_pai) {
		this.usuario_id_pai = usuario_id_pai;
	}

	public ModelLogin getUsuario_cad_id() {
		return usuario_cad_id;
	}

	public void setUsuario_cad_id(ModelLogin usuario_cad_id) {
		this.usuario_cad_id = usuario_cad_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
