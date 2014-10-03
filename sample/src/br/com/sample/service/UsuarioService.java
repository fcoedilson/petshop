package br.com.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Usuario;
import br.com.sample.type.StatusUsuario;

@Repository
@Transactional
public class UsuarioService extends BaseService<Long, Usuario> {


	@Transactional
	public Usuario save(Usuario user) {
		user.setStatus(StatusUsuario.ATIVO);
		return update(user);
	}

	@Transactional
	public Usuario bloquear(Long id) {
		Usuario user = retrieve(id);
		user.setStatus(StatusUsuario.BLOQUEADO);
		update(user);
		return user;
	}

	@Transactional
	public Usuario desbloquear(Long id) {
		Usuario user = retrieve(id);
		user.setStatus(StatusUsuario.ATIVO);
		update(user);
		return user;
	}
	
	@Transactional
	public Usuario alterarSenha(Long id, String senha){
		Usuario user = retrieve(id);
		user.setSenha(senha);
		update(user);
		return user;
	}

	@Transactional
	public List<Usuario> findByCliente(Integer clienteId) {
		return (List<Usuario>) executeResultListQuery("findByCliente", clienteId);
	}

	@Transactional
	public Usuario findByLogin(String login) throws NonUniqueResultException{
		return executeSingleResultQuery("findByLogin", login, "FALSE", "false");
	}

	@Transactional
	public List<Usuario> findByStatus(String status) {
		return executeResultListQuery("findByStatus", status, status.toLowerCase());
	}

	@Transactional
	public List<Usuario> findByClienteStatus(Integer cliId, String status) {
		return executeResultListQuery("findByCliente", cliId, status);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> findByLogin(String login, String status) {
		Query query = em.createQuery("select o from Usuario o where o.login = ? and o.status = ?");
		query.setParameter(1, login);
		query.setParameter(2, status);
		return query.getResultList();
	}

	public List<Usuario> findUsuariosBloqueados() {
		List<Usuario> lockedUsers = executeResultListQuery("Usuario.findByStatus", StatusUsuario.ATIVO);
		return lockedUsers;
	}

	@Transactional(readOnly = true)
	public List<Usuario> retriveByMail(String mail){
		return executeResultListQuery("Usuario.findByMail", mail, true);
	}
	
	@Transactional
	public List<Usuario> findByClienteLogin(Integer cliId, String filter, String status){
		List<Usuario> users = new ArrayList<Usuario>();
		if(cliId == null){
			if(filter != null && !filter.equals("")){
				users = findByLogin(filter, status);
			} else {
				users = findByStatus(status);
			}
		} else {
			if(filter != null && !filter.equals("")){
				users = executeResultListQuery("findByClienteLogin", cliId, filter, status);
			} else {
				users = findByClienteStatus(cliId, status);
			}
		}
		return users;
	}
	
	public Boolean findByCpf(String cpf, Cliente cliente){
		try {
			if(cliente != null){
				return executeSingleResultQuery("findByClienteCpf",  cliente.getId(), cpf) != null;
			} else {
				return executeSingleResultQuery("findByCpf", cpf) != null;
			}
		} catch (NoResultException e) {
		}
		return null;
	}

}