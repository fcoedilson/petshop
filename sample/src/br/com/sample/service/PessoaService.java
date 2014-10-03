package br.com.sample.service;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Pessoa;


@Repository
@Transactional
public class PessoaService extends BaseService<Long, Pessoa> {


	@Transactional
	public Pessoa findByCpf(String cpf) throws NonUniqueResultException{
		try {
			Query query = em.createQuery("select p from Pessoa p where p.cpf = ?");
			query.setParameter(1, cpf);
			return (Pessoa) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}


}
