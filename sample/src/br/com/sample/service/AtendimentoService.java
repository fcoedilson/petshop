package br.com.sample.service;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Atendimento;


@Repository
@Transactional
public class AtendimentoService extends BaseService<Long, Atendimento> {

	public Atendimento buscaPorNome(String nome) {
		try {
			Query query = em.createQuery("select p from Animal p where p.cliente.id = ?");
			query.setParameter(1, nome);
			return (Atendimento) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

}
