package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cargo;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Funcionario;
import br.com.sample.service.CargoService;
import br.com.sample.service.FuncionarioService;

@Scope("session")
@Component("funcionarioBean")
public class FuncionarioBean extends EntityBean<Long, Funcionario> {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private CargoService cargoService;

	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Funcionario funcionarioBusca;
	private String cpf;


	public static final String list = "/pages/cadastros/funcionario/funcionarioList.xhtml";
	public static final String single = "/pages/cadastros/funcionario/funcionario.xhtml";
	public static final String busca = "/pages/cadastros/funcionario/funcionarioBusca.xhtml";

	@PostConstruct
	public void init(){
		cargos = cargoService.retrieveAll();
	}

	protected Long retrieveEntityId(Funcionario entity) {
		return entity.getId();
	}

	protected FuncionarioService retrieveEntityService() {
		return this.service;
	}

	protected Funcionario createNewEntity() {
		Funcionario funcionario = new Funcionario();
		Endereco endereco = new Endereco();
		funcionario.setEndereco(endereco);
		endereco.setPessoa(funcionario);
		this.funcionarioBusca = null;
		return funcionario;
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

	public String buscarFuncionario(){

		if(this.cpf != null && !this.cpf.equals("")){
			this.funcionarioBusca = service.findByCpf(this.cpf);
			this.entity = this.funcionarioBusca;
		}
		return single;
	}
	
	public List<Funcionario> buscaPorNome(String nome){
		return service.buscaPorNome(nome);
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Funcionario getFuncionarioBusca() {
		return funcionarioBusca;
	}

	public void setFuncionarioBusca(Funcionario funcionarioBusca) {
		
		this.funcionarioBusca = funcionarioBusca;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

}