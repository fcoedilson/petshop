package br.com.sample.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import br.com.sample.type.StatusUsuario;

@Entity
@DiscriminatorValue(value="U")
@PrimaryKeyJoinColumn(name="pessoa_id")
@NamedQueries({
	@NamedQuery(name = "Usuario.findByLogin", query = "select o from Usuario o where o.login = ? and o.status = ?"),
	@NamedQuery(name = "Usuario.findByStatus", query = "select o from Usuario o where o.status = ?"),
	@NamedQuery(name = "Usuario.findByName", query = "select u from Usuario u where u.nome like ? and u.status = ?")
})
public class Usuario  extends Pessoa implements UserDetails{

	@NotNull(message="login não poder ser nullo")
	@Size(min=5, message="login deve ter pelos menos 5 caracteres")
	private String login;
	
	@NotNull(message="senha não poder ser nullo")
	@Size(min=6, message="senha deve ter pelos menos 6 caracteres")
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="status não pode ficar em branco")
	private StatusUsuario status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	@ManyToOne
	@JoinColumn(name="perfil_id")
	private Perfil role;
	
	
	@Transient
	private GrantedAuthority[] authorities;

	@Transient
	@Override
	public GrantedAuthority[] getAuthorities() {
		if(authorities == null){
			authorities = new Perfil[1];
		}
		GrantedAuthority authority = role;
		authorities[0] = authority;
		return authorities;
	}
	
	@Transient
	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	
	public Perfil getRole() {
		return role;
	}

	public void setRole(Perfil role) {
		this.role = role;
	}
	
	@Transient
	@Override
	public String getPassword() {
		return senha;
	}

	@Transient
	@Override
	public String getUsername() {
		return login;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
