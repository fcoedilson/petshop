package br.com.sample.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
//@DiscriminatorValue(value="C")
//@PrimaryKeyJoinColumn(name="pessoa_id")
@NamedQueries( {
	@NamedQuery(name = "Cliente.findByNome", query = "SELECT p FROM Cliente p WHERE p.pessoa.nome LIKE ?"),
	@NamedQuery(name = "Cliente.findById", query = "SELECT p FROM Cliente p WHERE p.id = ?") 
})
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cliente_id")
	private Long id;

	@OneToOne
	@JoinColumn(name="pessoa_id", nullable=false)
	private Pessoa pessoa;

	@NotNull(message="login não poder ser nullo")
	@Size(min=5, message="login deve ter pelos menos 5 caracteres")
	private String login;

	@NotNull(message="senha não poder ser nullo")
	@Size(min=6, message="senha deve ter pelos menos 6 caracteres")
	private String senha;


	@OneToMany(mappedBy="cliente")
	@Basic(fetch=FetchType.LAZY)
	private List<Pedido> pedidos;


	@OneToMany(mappedBy="cliente")
	@Basic(fetch=FetchType.LAZY)
	private List<Animal> animais;


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


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public List<Animal> getAnimais() {
		return animais;
	}


	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
	

}
