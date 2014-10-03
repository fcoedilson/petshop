package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cargo;
import br.com.sample.service.CargoService;

@Scope("session")
@Component("cargoBean")
public class CargoBean extends EntityBean<Long, Cargo> {

	@Autowired
	private CargoService service;


	public static final String list = "/pages/cadastros/cargo/cargoList.xhtml";
	public static final String single = "/pages/cadastros/cargo/cargo.xhtml";

	protected Long retrieveEntityId(Cargo entity) {
		return entity.getId();
	}

	protected CargoService retrieveEntityService() {
		return this.service;
	}

	protected Cargo createNewEntity() {
		Cargo cargo = new Cargo();
		return cargo;
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