package br.com.sample.service;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Usuario;
import br.com.sample.type.StatusUsuario;

@Repository
@Transactional
public class UsuarioService extends BaseService<Long, Usuario> {


	@Transactional
	public Usuario findByCpf(String cpf){

		Usuario Usuario = null;

		try {
			Query query = em.createQuery("select f from Usuario f where f.pessoa.cpf = ?");
			query.setParameter(1, cpf);
			Usuario = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Usuario;
	}

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
	public Usuario findByLogin(String login) throws NonUniqueResultException{
		return executeSingleResultQuery("findByLogin", login, "FALSE", "false");
	}

	@Transactional
	public List<Usuario> findByStatus(String status) {
		return executeResultListQuery("findByStatus", status, status.toLowerCase());
	}



	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> findByLogin(String login, String status) {
		Query query = em.createQuery("select o from Usuario o where o.login = ? and o.status = ?");
		query.setParameter(1, login);
		query.setParameter(2, status);
		return query.getResultList();
	}


	@Transactional(readOnly = true)
	public List<Usuario> retriveByMail(String mail){
		return executeResultListQuery("Usuario.findByMail", mail, true);
	}

}