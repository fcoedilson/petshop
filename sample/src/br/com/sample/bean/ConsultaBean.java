package br.com.sample.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Cliente;
import br.com.sample.entity.Consulta;
import br.com.sample.entity.Medico;
import br.com.sample.entity.TipoConsulta;
import br.com.sample.service.AnimalService;
import br.com.sample.service.ClienteService;
import br.com.sample.service.ConsultaService;
import br.com.sample.service.MedicoService;
import br.com.sample.service.TipoConsultaService;

@Scope("session")
@Component("consultaBean")
public class ConsultaBean extends EntityBean<Long, Consulta> {


	@Autowired
	private ConsultaService service;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private TipoConsultaService tipoConsultaService;
	
	@Autowired
	private ClienteService clienteService;


	@Autowired
	private MedicoService medicoService;


	private String nomeAnimal;
	private String cpf;
	private Cliente cliente;
	private List<TipoConsulta> consultas;
	private List<Medico> medicos;
	private Animal animalConsulta;
	private List<Animal> animais;


	public static final String list = "/pages/atendimentos/consulta/consultaList.xhtml";
	public static final String single = "/pages/atendimentos/consulta/consulta.xhtml";
	public static final String busca = "/pages/atendimentos/consulta/consultaBusca.xhtml";

	@PostConstruct
	public void init(){

		medicos = medicoService.retrieveAll();
	}

	protected Long retrieveEntityId(Consulta entity) {
		return entity.getId();
	}

	protected ConsultaService retrieveEntityService() {
		return this.service;
	}

	protected Consulta createNewEntity() {
		Consulta consulta = new Consulta();
		consulta.setAnimal(new Animal());
		return consulta;
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		super.save();
		this.entity.setAnimal(this.animalConsulta);
		this.entity.setData(new Date());
		return list;
	}

	public String update(){

		super.update();
		this.entity.setAnimal(this.animalConsulta);
		return list;
	}

	public String prepareSave(){

		consultas = tipoConsultaService.retrieveAll();
		medicos = medicoService.retrieveAll();
		if (medicos == null || medicos.size() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Problema no cadastro de médicos"));
			return busca;
		}
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		this.animalConsulta = this.entity.getAnimal();
		this.animais = animalConsulta.getCliente().getAnimais();
		this.cliente = animalConsulta.getCliente();

		consultas = tipoConsultaService.retrieveAll();
		medicos = medicoService.retrieveAll();
		super.prepareUpdate();
		return single;
	}

	public String buscarAnimal(){

		medicos = medicoService.retrieveAll();
		if (medicos == null || medicos.size() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Problema no cadastro de médicos"));
			return busca;
		}
		if(this.cpf != null && !this.cpf.equals("")){
			cliente = clienteService.buscaPorCPF(this.cpf);
			animais = cliente.getAnimais(); //animalService.findAnimaisByCliente(cpf);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! CPF inválido!"));
			return busca;
		}

		if(this.animais != null && this.animais.size() > 0){
			super.prepareSave();
			return single;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Sem animais cadastrados"));
			return busca;
		}

	}


	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public List<TipoConsulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<TipoConsulta> consultas) {
		this.consultas = consultas;
	}

	public Animal getAnimalConsulta() {
		return animalConsulta;
	}

	public void setAnimalConsulta(Animal animalConsulta) {
		this.animalConsulta = animalConsulta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

}
