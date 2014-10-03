package br.com.sample.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.GrantedAuthority;

@Entity
public class Perfil implements Serializable, GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="perfil_id")
	private long id;
	
	
	@NotNull(message="nome do perfil é obrigatório")
	@Size(min=4, message="deve ter pelos menos 4 caracteres")
	private String nome;
	
	
	@NotNull
	@Size(min=4, message="deve ter pelos menos 4 caracteres")
	private String authority;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="perfil_permissao", 
			joinColumns=@JoinColumn(name="perfil_id"), 
			inverseJoinColumns=@JoinColumn(name="permissao_id"))
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Transient
	@Override
	public int compareTo(Object o) {
		if (o != null) {
			Perfil other = (Perfil) o;
			if (this.authority == null && other.authority != null){
				return -1;
			} else if (this.authority != null && other.authority == null) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return getAuthority();
	}
	
}
