package br.com.sample.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Perfil;



@Repository
@Transactional
public class PerfilService extends BaseService<Long, Perfil>{

	public Perfil findById(Integer id){
		return executeSingleResultQuery("findById", id);
	}

	@SuppressWarnings("unchecked")
	public List<Perfil> findAll(){

		Query query = em.createQuery("SELECT p FROM Perfil p");
		return query.getResultList();
	}
}
