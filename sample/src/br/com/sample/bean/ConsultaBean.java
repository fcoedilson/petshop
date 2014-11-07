package br.com.sample.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Consulta;
import br.com.sample.entity.TipoConsulta;
import br.com.sample.service.AnimalService;
import br.com.sample.service.ConsultaService;
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
	
	
	private String nomeAnimal;
	private List<TipoConsulta> consultas;
	private Animal animalConsulta;
	

	public static final String list = "/pages/atendimentos/consulta/consultaList.xhtml";
	public static final String single = "/pages/atendimentos/consulta/consulta.xhtml";
	public static final String busca = "/pages/atendimentos/consulta/consultaBusca.xhtml";

	@PostConstruct
	public void init(){
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
		return list;
	}

	public String update(){
		super.update();
		return list;
	}

	public String prepareSave(){
		
		consultas = tipoConsultaService.retrieveAll();
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		consultas = tipoConsultaService.retrieveAll();
		super.prepareUpdate();
		return single;
	}
	
	public String buscarAnimal(){

		if(this.nomeAnimal != null && !this.nomeAnimal.equals("")){
			this.animalConsulta = animalService.findByNome(this.nomeAnimal);
		}
		return single;
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

}
