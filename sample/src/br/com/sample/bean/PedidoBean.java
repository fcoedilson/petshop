package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.ItemPedido;
import br.com.sample.entity.Pedido;
import br.com.sample.entity.Produto;
import br.com.sample.service.PedidoService;
import br.com.sample.service.ProdutoService;


@Scope("session")
@Component("pedidoBean")
public class PedidoBean extends EntityBean<Long, Pedido> {

	@Autowired
	private PedidoService service;

	@Autowired
	private ProdutoService produtoService;


	private List<Produto> produtos;
	private Produto produto = new Produto();
	private Integer quantidade;

	private List<ItemPedido> itens;
	private ItemPedido item = new ItemPedido();

	public static final String list = "/pages/cadastros/pedido/pedidoList.xhtml";
	public static final String single = "/pages/cadastros/pedido/pedido.xhtml";

	@Override
	protected PedidoService retrieveEntityService() {
		return this.service;
	}

	@Override
	protected Long retrieveEntityId(Pedido entity) {
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
		this.produtos = produtoService.retrieveAll();
		itens = new ArrayList<ItemPedido>();
		super.prepareSave();
		return single;
	}

	public void adicionar(){
		if(this.quantidade != null && this.quantidade != null){
			ItemPedido item = new ItemPedido();
			item.setProduto(this.produto);
			item.setQuantidade(this.quantidade);
			item.setValorItem(this.produto.getPrecoVenda()*quantidade);
			item.setPedido(this.entity);
			itens.add(item);
		} else {

		}
	}

	public void remover(){
		itens.remove(this.item);
	}

	public String prepareUpdate(){
		this.produtos = produtoService.retrieveAll();
		this.itens = this.entity.getItens();
		super.prepareUpdate();
		return single;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public ItemPedido getItem() {
		return item;
	}

	public void setItem(ItemPedido item) {
		this.item = item;
	}	

}
