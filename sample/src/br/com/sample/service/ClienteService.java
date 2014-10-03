package br.com.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Usuario;
import br.com.sample.util.BeanUtil;

@Repository
@Transactional
public class ClienteService extends BaseService<Long, Cliente> {

	@Transactional(readOnly = true)
	public List<Cliente> retrieveAll() {

		Usuario user = BeanUtil.usuarioLogado();

		if (BeanUtil.isAdmin(user)) {
			return super.retrieveAll();
		} else {
			List<Cliente> result = new ArrayList<Cliente>();
			return result;
		}
	}
	
	@Transactional
	public List<Cliente> buscaPorNome(String nome){
		
		List<Cliente> result = executeResultListQuery("findByNome", "%"+nome+"%");
		return result;
	}

	@Transactional
	public Cliente buscaPorId(Long id){
		Query query = em.createQuery("select c from Cliente c where c.id = ?");
		query.setParameter(1, id);
		Cliente cliente = (Cliente) query.getSingleResult();
		return cliente;
	}
	
	@Transactional
	public Cliente buscaNome(String nome){
		
		Cliente cliente = executeSingleResultQuery("findByNome", nome);
		return cliente;
	}

}