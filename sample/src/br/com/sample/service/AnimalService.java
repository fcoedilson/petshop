package br.com.sample.service;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Cliente;


@Repository
@Transactional
public class AnimalService extends BaseService<Long, Animal> {

	@Transactional
	public List<Animal> findByCliente(Cliente cliente) throws NonUniqueResultException{
		try {
			Query query = em.createQuery("select p from Animal p where p.cliente.id = ?");
			query.setParameter(1, cliente.getId());
			return query.getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	@Transactional
	public Animal findByCliente(String cpf) throws NonUniqueResultException{
		try {
			Query query = em.createQuery("select p from Animal p where p.cliente.cpf = ?");
			query.setParameter(1, cpf);
			return (Animal) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

	@Transactional
	public List<Animal> findAnimaisByCliente(String cpf) throws NonUniqueResultException{
		try {
			Query query = em.createQuery("select p from Animal p where p.cliente.cpf = ?");
			query.setParameter(1, cpf);
			return (List<Animal>) query.getResultList();
		} catch (Exception e) {
		}
		return null;
	}	
	
	@Transactional
	public Animal findByNome(String nome) throws NonUniqueResultException{
		try {
			Query query = em.createQuery("select p from Animal p where p.nome = ?");
			query.setParameter(1, nome);
			return (Animal) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

}
