package br.com.sample.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Funcionario;

@Repository
@Transactional
public class FuncionarioService extends BaseService<Long, Funcionario> {
	
	
	@Transactional
	public Funcionario findByCpf(String cpf){
		
		Funcionario funcionario = null;
		
		try {
			Query query = em.createQuery("select f from Funcionario f where f.cpf = ?");
			query.setParameter(1, cpf);
			funcionario = (Funcionario) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return funcionario;
	}
	
	@Transactional
	public List<Funcionario> buscaPorNome(String nome){
		
		List<Funcionario> result = null;
		try {
			Query query = em.createQuery("select f from Funcionario f where f.name like ?");
			query.setParameter(1, "%"+nome+"%");
			result = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}