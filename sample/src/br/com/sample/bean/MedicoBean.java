package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cargo;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Medico;
import br.com.sample.entity.Pessoa;
import br.com.sample.service.CargoService;
import br.com.sample.service.MedicoService;
import br.com.sample.service.PessoaService;

@Scope("session")
@Component("medicoBean")
public class MedicoBean extends EntityBean<Long, Medico> {

	@Autowired
	private MedicoService service;
	
	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private CargoService cargoService;

	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Medico medico;
	private String cpf;
	private boolean pessoaExiste = false;


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
		Pessoa pessoa = new Pessoa();
		pessoa.setEndereco(endereco);
		endereco.setPessoa(pessoa);
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
			Pessoa pessoa = pessoaService.findByCpf(this.cpf);
			if(pessoa != null){
				this.entity.setPessoa(pessoa);
				this.pessoaExiste = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Pessoa não Cadastrada!"));
				return busca;
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ops! Informe um cpf!"));
			return busca;
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

	public boolean isPessoaExiste() {
		return pessoaExiste;
	}

	public void setPessoaExiste(boolean pessoaExiste) {
		this.pessoaExiste = pessoaExiste;
	}

}