package br.com.sample.bean;

import java.util.ArrayList;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Pessoa;
import br.com.sample.entity.Usuario;
import br.com.sample.service.PessoaService;
import br.com.sample.util.BeanUtil;
import br.com.sample.util.JsfUtil;


@Scope("session")
@Component("pessoaBean")
public class PessoaBean extends EntityBean<Long, Pessoa>{

	@Autowired
	private PessoaService service;

	private Cliente cliente;
	private Boolean usuarioCadastrado;
	private String cpf;


	@Override
	protected Pessoa createNewEntity() {
		return new Pessoa();
	}

	@Override
	protected Long retrieveEntityId(Pessoa entity) {
		return entity.getId();
	}

	@Override
	protected PessoaService retrieveEntityService() {
		return this.service;
	}

	public String search(){

		this.entities = new ArrayList<Pessoa>();
		Usuario user = BeanUtil.usuarioLogado();
		Pessoa pessoa = null;

		if(BeanUtil.isAdmin(user) || BeanUtil.isGerente(user) || BeanUtil.isAtendente(user)){

			this.entities = service.retrieveAll();

		} else if (BeanUtil.isAssistente(user)){
			
		} else if(BeanUtil.isMedico(user)){
			
		} else if(BeanUtil.isCliente(user)){
			
		} 
		setCurrentBean(currentBeanName());
		setCurrentState(SEARCH);
		return SUCCESS;
	}

	public String prepareUpdate(){
		return super.prepareUpdate();
	}

	public String prepareSave(){
		this.entity = new Pessoa();
		return super.prepareSave();
	}

	public String update(){
		if(!BeanUtil.validaCpf(this.entity.getCpf())){
			JsfUtil.getInstance().addErrorMessage("msg.error.cpf.digito.verificador.invalido");
			return FAIL;
		}
		retrieveEntityService().update(this.entity);
		setCurrentBean(currentBeanName());
		this.entity.setCpf(null);
		return search();
	}

	public String save(){
		try{
			Usuario user = BeanUtil.usuarioLogado();
			if(this.entity.getCpf() != null){
				if(!BeanUtil.validaCpf(this.entity.getCpf())){
					JsfUtil.getInstance().addErrorMessage("msg.error.cpf.digito.verificador.invalido");
					return FAIL;
				}
			} else {
				return FAIL;
			}
			if(this.entity.getCpf() != null){
				boolean temcpf = service.findByCpf(this.entity.getCpf()) != null ? true : false;
				if(temcpf){
					JsfUtil.getInstance().addErrorMessage("msg.error.cpf.duplicado");
					return FAIL;
				} 
				return SUCCESS;
			} else {
				return FAIL;
			}
		} catch (NonUniqueResultException e) {
			JsfUtil.getInstance().addErrorMessage("msg.error.cpf.duplicado");
			return FAIL;
		}
	}

	public String searchByCPF(){
		this.entities = new ArrayList<Pessoa>();
		Pessoa p = service.findByCpf(this.entity.getCpf());
		if(p != null){
			this.entities.add(p);
		}
		return SUCCESS;
	}


	public void limpar(){
		this.entity = null;
	}

	public void setUsuarioCadastrado(Boolean usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}

	public Boolean getUsuarioCadastrado() {
		return usuarioCadastrado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
