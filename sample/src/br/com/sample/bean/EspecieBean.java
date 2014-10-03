package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Especie;
import br.com.sample.service.EspecieService;

@Scope("session")
@Component("especieBean")
public class EspecieBean extends EntityBean<Long, Especie> {

	@Autowired
	private EspecieService service;

	public static final String list = "/pages/cadastros/especie/especieList.xhtml";
	public static final String single = "/pages/cadastros/especie/especie.xhtml";

	protected Long retrieveEntityId(Especie entity) {
		return entity.getId();
	}

	protected EspecieService retrieveEntityService() {
		return this.service;
	}

	protected Especie createNewEntity() {
		Especie especie = new Especie();
		return especie;
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