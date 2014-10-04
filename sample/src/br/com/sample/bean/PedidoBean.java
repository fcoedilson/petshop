package br.com.sample.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.ItemPedido;
import br.com.sample.entity.Pedido;
import br.com.sample.service.PedidoService;


@Scope("session")
@Component("pedidoBean")
public class PedidoBean extends EntityBean<Long, Pedido> {
	
	@Autowired
	private PedidoService service;
	
	public static final String list = "/pages/cadastros/pedido/pedidoList.xhtml";
	public static final String single = "/pages/cadastros/pedido/pedido.xhtml";


	@Override
	protected PedidoService retrieveEntityService() {
		// TODO Auto-generated method stub
		return this.service;
	}

	@Override
	protected Long retrieveEntityId(Pedido entity) {
		// TODO Auto-generated method stub
		return this.entity.getId();
	}

	@Override
	protected Pedido createNewEntity() {
		Pedido pedido = new Pedido();
		pedido.setItens(new ArrayList<ItemPedido>());
		return pedido;
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
