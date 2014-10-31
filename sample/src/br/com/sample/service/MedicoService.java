package br.com.sample.service;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Medico;

@Repository
@Transactional
public class MedicoService extends BaseService<Long, Medico>{

	@Transactional
	public Medico findByCpf(String cpf){

		Medico medico = null;

		try {
			Query query = em.createQuery("select f from Medico f where f.pessoa.cpf = ?");
			query.setParameter(1, cpf);
			medico = (Medico) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return medico;
	}
}
