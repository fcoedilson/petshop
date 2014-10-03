package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Servico;
import br.com.sample.service.ServicoService;

@Scope("session")
@Component("servicoBean")
public class ServicoBean extends EntityBean<Long, Servico>{

	@Autowired
	private ServicoService service;
	
	public static final String list = "/pages/cadastros/servico/servicoList.xhtml";
	public static final String single = "/pages/cadastros/servico/servico.xhtml";
	
	@Override
	protected ServicoService retrieveEntityService() {
		return this.service;
	}

	@Override
	protected Long retrieveEntityId(Servico entity) {
		return this.entity.getId();
	}

	@Override
	protected Servico createNewEntity() {
		return new Servico();
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

}
