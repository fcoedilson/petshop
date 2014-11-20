package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Permissao;
import br.com.sample.service.ClienteService;
import br.com.sample.service.EnderecoService;
import br.com.sample.service.PessoaService;

@Scope("session")
@Component("clienteBean")
public class ClienteBean extends EntityBean<Long, Cliente> {

	@Autowired
	private ClienteService service;

	private Permissao permissao;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EnderecoService enderecoService;

	public static final String list = "/pages/cadastros/cliente/clienteList.xhtml";
	public static final String single = "/pages/cadastros/cliente/cliente.xhtml";	

	protected Long retrieveEntityId(Cliente entity) {
		return entity.getId();
	}

	protected ClienteService retrieveEntityService() {
		return this.service;
	}

	protected Cliente createNewEntity() {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		endereco.setPessoa(cliente);
		cliente.setEndereco(endereco);
		return cliente;
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
		return single;
	}

	public String prepareUpdate(){
		super.prepareUpdate();
		return single;
	}

	public Permissao getPermissao() {
		if (permissao == null) {
			permissao = new Permissao();
		}
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<SelectItem> getTipoDocumentos(){
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem(1, "CPF"));
		result.add(new SelectItem(2, "CNPJ"));
		return result;
	}
}