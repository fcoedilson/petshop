package br.com.sample.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import br.com.sample.entity.Endereco;
import br.com.sample.entity.Pessoa;

public class EntityTest {
	
	
	@PersistenceContext(unitName="sample")
	static EntityManager em;
	
	
	public static void main(String[] args){
		

		
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("sample");
		//EntityManager em = factory.createEntityManager();
		EntityTransaction tm = em.getTransaction();
		tm.begin();

		
		Pessoa p = new Pessoa();
		p.setEndereco(new Endereco());
		
		p.setNome("Francisco Edilson");
		p.setCpf("71322914320");
		p.setDataNascimento(new Date("1975-08-07"));
		
		p.getEndereco().setLogradouro("Rua João Sorongo");
		p.getEndereco().setNumero("1320");
		p.getEndereco().setComplemento("Apto 202A");
		p.getEndereco().setBairro("Rodolfo");
		
		
		em.persist(p);
		
		tm.commit();
	}


}
