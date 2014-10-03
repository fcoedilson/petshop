package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Perfil;
import br.com.sample.entity.Permissao;
import br.com.sample.service.PermissaoService;
import br.com.sample.service.PerfilService;

@Scope("session")
@Component("permissaoBean")
public class PermissaoBean extends EntityBean<Long, Permissao> {

	private List<Perfil> roles = new ArrayList<Perfil>();
	private Perfil role;
	
	public static final String list = "/pages/cadastros/permissao/permissaoList.xhtml";
	public static final String single = "/pages/cadastros/permissao/permissao.xhtml";

	@Autowired
	private PermissaoService service = new PermissaoService();

	@Autowired
	private PerfilService perfilService = new PerfilService();

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

	protected Long retrieveEntityId(Permissao entity) {
		return entity.getId();
	}

	protected PermissaoService retrieveEntityService() {
		return this.service;
	}

	protected Permissao createNewEntity() {
		return new Permissao();
	}

	public List<Permissao> getPermissoes(){
		List<Permissao> result = new ArrayList<Permissao>(service.retrieveAll());
		return result;
	}


	public void setRoleService(PerfilService roleService) {
		this.perfilService = roleService;
	}

	public PerfilService getRoleService() {
		return perfilService;
	}

	public List<Perfil> getRoles() {
		return roles;
	}

	public void setRoles(List<Perfil> roles) {
		this.roles = roles;
	}

	public Perfil getRole() {
		return role;
	}

	public void setRole(Perfil role) {
		this.role = role;
	}

}