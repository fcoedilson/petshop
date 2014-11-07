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
import br.com.sample.entity.Consulta;
import br.com.sample.entity.Medico;
import br.com.sample.entity.TipoConsulta;
import br.com.sample.service.AnimalService;
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
	private MedicoService medicoService;


	private String nomeAnimal;
	private String clienteCpf;
	private List<TipoConsulta> consultas;
	private List<Medico> medicos;
	private Animal animalConsulta;


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
		return consulta;
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		super.save();
		this.entity.setData(new Date());
		return list;
	}

	public String update(){
		super.update();
		return list;
	}

	public String prepareSave(){

		consultas = tipoConsultaService.retrieveAll();
		medicos = medicoService.retrieveAll();
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		consultas = tipoConsultaService.retrieveAll();
		medicos = medicoService.retrieveAll();
		super.prepareUpdate();
		return single;
	}

	public String buscarAnimal(){

		if(this.nomeAnimal != null && !this.nomeAnimal.equals("")){
			this.animalConsulta = animalService.findByNome(this.nomeAnimal);
		} else if( this.clienteCpf != null && !this.clienteCpf.equals("") ){
			this.animalConsulta = animalService.findByCliente(this.clienteCpf);
		} 

		if(this.animalConsulta != null){
			this.entity.setAnimal(this.animalConsulta);
			return single;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Nome do Animal ou Cliente não encontrado!!"));
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

	public String getClienteCpf() {
		return clienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	
}
