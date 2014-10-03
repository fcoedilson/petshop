package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Raca;
import br.com.sample.service.RacaService;

@Scope("session")
@Component("racaBean")
public class RacaBean extends EntityBean<Long, Raca> {

	@Autowired
	private RacaService service;


	public static final String list = "/pages/cadastros/raca/racaList.xhtml";
	public static final String single = "/pages/cadastros/raca/raca.xhtml";

	protected Long retrieveEntityId(Raca entity) {
		return entity.getId();
	}

	protected RacaService retrieveEntityService() {
		return this.service;
	}

	protected Raca createNewEntity() {
		Raca raca = new Raca();
		return raca;
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