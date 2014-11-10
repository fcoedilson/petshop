package br.com.sample.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Cliente;
import br.com.sample.entity.Especie;
import br.com.sample.entity.Raca;
import br.com.sample.service.AnimalService;
import br.com.sample.service.ClienteService;
import br.com.sample.service.EspecieService;
import br.com.sample.service.RacaService;

@Scope("session")
@Component("animalBean")
public class AnimalBean extends EntityBean<Long, Animal> {

	@Autowired
	private AnimalService service;

	@Autowired
	private RacaService racaService;

	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private ClienteService clienteService;

	private List<Raca> racas;
	private List<Especie> especies;
	private List<Cliente> clientes;

	public static final String list = "/pages/cadastros/animal/animalList.xhtml";
	public static final String single = "/pages/cadastros/animal/animal.xhtml";	


	protected Long retrieveEntityId(Animal entity) {
		return entity.getId();
	}

	protected AnimalService retrieveEntityService() {
		return this.service;
	}

	protected Animal createNewEntity() {
		Animal animal = new Animal();
		animal.setRaca(new Raca());
		animal.setEspecie(new Especie());
		animal.setCliente(new Cliente());
		return animal;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		super.save();
		return list;
	}

	public String update(){
		super.update();
		return list;
	}

	public String prepareSave(){
		racas = racaService.retrieveAll();
		especies = especieService.retrieveAll();
		clientes = clienteService.retrieveAll();
		super.prepareSave();
		return single;
	}

	public String prepareUpdate(){
		racas = racaService.retrieveAll();
		especies = especieService.retrieveAll();
		clientes = clienteService.retrieveAll();
		super.prepareUpdate();
		return single;
	}
	
	

}