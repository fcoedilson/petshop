package br.com.sample.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@DiscriminatorValue(value="C")
@PrimaryKeyJoinColumn(name="pessoa_id")
@NamedQueries( {
	@NamedQuery(name = "Cliente.findByNome", query = "SELECT p FROM Cliente p WHERE p.nome LIKE ?"),
	@NamedQuery(name = "Cliente.findById", query = "SELECT p FROM Cliente p WHERE p.id = ?") 
})
public class Cliente extends Pessoa{
	
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

}
