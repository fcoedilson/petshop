package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cargo;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Medico;
import br.com.sample.service.CargoService;
import br.com.sample.service.MedicoService;

@Scope("session")
@Component("medicoBean")
public class MedicoBean extends EntityBean<Long, Medico> {

	@Autowired
	private MedicoService service;

	@Autowired
	private CargoService cargoService;

	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Medico medico;
	private String cpf;


	public static final String list = "/pages/cadastros/medico/medicoList.xhtml";
	public static final String single = "/pages/cadastros/medico/medico.xhtml";
	public static final String busca = "/pages/cadastros/medico/medicoBusca.xhtml";

	@PostConstruct
	public void init(){
		cargos = cargoService.retrieveAll();
	}

	protected Long retrieveEntityId(Medico entity) {
		return entity.getId();
	}

	protected MedicoService retrieveEntityService() {
		return this.service;
	}

	protected Medico createNewEntity() {
		Medico medico = new Medico();
		Endereco endereco = new Endereco();
		medico.setEndereco(endereco);
		endereco.setPessoa(medico);
		this.medico = null;
		return medico;
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
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		super.prepareUpdate();
		return single;
	}

	public String buscarMedico(){

		if(this.cpf != null && !this.cpf.equals("")){
			this.medico = service.findByCpf(this.cpf);
			this.entity = this.medico;
		}
		return single;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}