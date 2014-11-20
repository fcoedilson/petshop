package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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

	private String cpf;

	public static final String list = "/pages/cadastros/animal/animalList.xhtml";
	public static final String single = "/pages/cadastros/animal/animal.xhtml";	
	public static final String busca = "/pages/cadastros/animal/animalBusca.xhtml";


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


	public String clienteBusca(){


		if(this.cpf != null && !this.cpf.equals("")){

			Cliente cliente = clienteService.buscaPorCPF(this.cpf);

			if(cliente != null){
				this.entity.setCliente(cliente);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Cliente não  cadastrado!"));
				return busca;
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Informe um cpf!"));
			return busca;
		}

		return single;
	}

	public boolean isStatusCliente(){

		Cliente cliente = this.entity.getCliente();
		if(cliente != null){
			return true;
		} else {
			return false;
		}
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public List<SelectItem> getClienteItens(){
		List<SelectItem> result = new ArrayList<SelectItem>();

		for (Cliente c : clientes) {
			result.add(new SelectItem(c, c.getNome()));
		}
		return result;
	}

	public String prepareSave(){

		racas = racaService.retrieveAll();
		especies = especieService.retrieveAll();
		clientes = clienteService.retrieveAll();
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		if ( this.entity.getCliente() == null ){
			this.entity.setCliente(new Cliente());
		}

		racas = racaService.retrieveAll();
		especies = especieService.retrieveAll();
		clientes = clienteService.retrieveAll();
		super.prepareUpdate();
		return single;
	}



}