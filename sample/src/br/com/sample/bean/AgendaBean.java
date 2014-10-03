package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Agenda;
import br.com.sample.entity.Cliente;
import br.com.sample.entity.Endereco;
import br.com.sample.service.AgendaService;
import br.com.sample.service.ClienteService;
import br.com.sample.type.StatusAgenda;
import br.com.sample.util.BeanUtil;
import br.com.sample.util.JsfUtil;

@Scope("session")
@Component("agendaBean")
public class AgendaBean extends EntityBean<Long, Agenda> {

	@Autowired
	private AgendaService service;

	@Autowired
	private ClienteService clienteService;

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Cliente cliente;


	public static final String list = "/pages/atendimentos/agenda/agendaList.xhtml";
	public static final String single = "/pages/atendimentos/agenda/agenda.xhtml";

	protected Long retrieveEntityId(Agenda entity) {
		return entity.getId();
	}

	protected AgendaService retrieveEntityService() {
		return this.service;
	}

	protected Agenda createNewEntity() {
		Agenda agenda = new Agenda();
		this.cliente = new Cliente();
		return agenda;
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		this.entity.setUsuarioCadastro(BeanUtil.usuarioLogado());
		this.entity.setStatus(StatusAgenda.AGENDADA);
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


	public List<Cliente> buscaCliente(String query) {

		List<Cliente> result = clienteService.buscaPorNome(query);
		return result;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}