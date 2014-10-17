/**
 * 
 */
package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Perfil;
import br.com.sample.entity.Permissao;
import br.com.sample.service.PerfilService;
import br.com.sample.service.PermissaoService;

@Scope("session")
@Component("perfilBean")
public class PerfilBean extends EntityBean<Long, Perfil>{


	@Autowired
	private PerfilService service;

	@Autowired
	private PermissaoService permissaoService;


	public static final String list = "/pages/cadastros/perfil/perfilList.xhtml";
	public static final String single = "/pages/cadastros/perfil/perfil.xhtml";

	private Permissao permissao = new Permissao();
	private List<Permissao> permissoes;
	private List<Permissao> permissoesMarcadas;


	@Override
	protected Perfil createNewEntity() {
		this.permissoes = new ArrayList<Permissao>();
		this.permissoes.addAll(permissaoService.retrieveAll());
		this.permissoesMarcadas = new ArrayList<Permissao>();
		this.permissao = new Permissao();
		return new Perfil();
	}

	@Override
	protected Long retrieveEntityId(Perfil entity) {
		return entity.getId();
	}

	@Override
	protected PerfilService retrieveEntityService() {
		return this.service;
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		this.entity.setPermissoes(this.permissoesMarcadas);
		super.save();
		return list;
	}

	public String update(){
		this.entity.setPermissoes(this.permissoesMarcadas);
		super.update();
		return list;
	}

	public String prepareSave(){
		this.permissoesMarcadas =  new ArrayList<Permissao>();
		this.permissoes = permissaoService.retrieveAll();
		super.prepareSave();
		return single;
	}

	public String prepareUpdate(){
		this.permissoes = permissaoService.retrieveAll();
		this.permissoesMarcadas =  new ArrayList<Permissao>();

		if(this.entity.getPermissoes() != null){
			this.permissoesMarcadas.addAll(this.entity.getPermissoes());
			this.permissoes.removeAll(this.entity.getPermissoes());
		}
		super.prepareUpdate();
		return single;
	}

	public boolean isPermissaoStatus(){
		return this.permissoes.size() > 0;
	}

	@Transactional(readOnly=true)
	public List<Perfil> getRoles(){
		return  new ArrayList<Perfil>(service.findAll());
	}

	public void adicionar(){
		this.permissoesMarcadas.add(this.permissao);
		this.permissoes.remove(this.permissao);
	}


	public void remover(){
		this.permissoesMarcadas.remove(this.permissao);
		this.permissoes.add(this.permissao);
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}


	public List<Permissao> getPermissoesMarcadas() {
		return permissoesMarcadas;
	}


	public void setPermissoesMarcadas(List<Permissao> permissoesMarcadas) {
		this.permissoesMarcadas = permissoesMarcadas;
	}

}
