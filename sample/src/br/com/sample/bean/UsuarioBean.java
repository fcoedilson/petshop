/**
 * 
 */
package br.com.sample.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Perfil;
import br.com.sample.entity.Pessoa;
import br.com.sample.entity.Usuario;
import br.com.sample.service.PerfilService;
import br.com.sample.service.PessoaService;
import br.com.sample.service.UsuarioService;
import br.com.sample.type.StatusUsuario;
import br.com.sample.util.BeanUtil;
import br.com.sample.util.JsfUtil;


@Scope("session")	
@Component("usuarioBean")
public class UsuarioBean extends EntityBean<Long, Usuario>{

	@Autowired
	private UsuarioService service;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PerfilService roleService;

	public static final String list = "/pages/cadastros/usuario/usuarioList.xhtml";
	public static final String single = "/pages/cadastros/usuario/usuario.xhtml";
	public static final String busca = "/pages/cadastros/usuario/usuarioBusca.xhtml";

	private Boolean status = true;
	private Boolean usuarioCadastrado;
	private String confirmaSenha;
	private String grupo;
	private String cpf;

	private Pessoa pessoa;
	private Perfil role;
	private Cliente cliente;
	private List<Perfil> perfis;
	private boolean pessoaExiste = false;


	@Override
	protected Usuario createNewEntity() {
		Pessoa pessoa = new Pessoa();
		Usuario user = new Usuario();
		user.setRole(new Perfil());
		Endereco endereco = new Endereco();
		endereco.setPessoa(pessoa);
		pessoa.setEndereco(endereco);
		return user;
	}

	protected Long retrieveEntityId(Usuario entity) {
		return entity.getId();
	}

	protected UsuarioService retrieveEntityService() {
		return this.service;
	}

	public String buscarUsuario(){

		if(this.cpf != null && !this.cpf.equals("")){
			this.pessoa = pessoaService.findByCpf(this.cpf);
			if(this.pessoa != null){
				pessoaExiste = true;
				this.entity.setPessoa(this.pessoa);
			}
		} else {
			
		}
		return single;
	}

	@Override
	public String search() {
		Usuario user = BeanUtil.usuarioLogado();
		this.entities = new ArrayList<Usuario>();

		if(BeanUtil.isAdmin(user)){
			//this.entities = service.findByStatus(StatusUsuario.ATIVO.toString());
			this.entities = service.retrieveAll();

		} else if(BeanUtil.isGerente(user)){

		} else {
			this.entities.add(service.retrieve(user.getId()));
		}
		Collections.sort(this.entities, new Comparator<Usuario>() {
			public int compare(Usuario o1, Usuario o2) {
				return o1.getPessoa().getNome().compareTo(o2.getPessoa().getNome());
			}
		});
		//super.search();
		return list;
	}

	public String save(){

		this.entity.setStatus(StatusUsuario.ATIVO);
		this.entity.setSenha(BeanUtil.md5(this.entity.getSenha()));

		boolean hasLogin = false;

		if(hasLogin){
			this.entity.setSenha(null);
			this.confirmaSenha = null;
			JsfUtil.getInstance().addErrorMessage("msg.error.login.existente");
			return FAIL;
		} else {
			super.save();
			return list;
		}

	}

	public String update(){
		super.update();
		return list;
	}

	public String prepareSave(){
		perfis = roleService.retrieveAll();
		super.prepareSave();
		return busca;
	}

	public String prepareUpdate(){
		pessoaExiste = true;
		perfis = roleService.retrieveAll();
		super.prepareUpdate();
		return single;
	}

	public String populate(){
		this.status = null;
		this.filter = "";
		return super.populate();
	}


	public List<SelectItem> getUserList(){
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem(null, "Selecione"));
		List<Usuario> users = service.retrieveAll();
		for (Usuario user : users) {
			result.add(new SelectItem(user.getId(), user.getPessoa().getNome()));
		}
		return result;
	}


	public Boolean validar() {
		if(!this.entity.getSenha().equals(this.confirmaSenha)){
			FacesContext.getCurrentInstance().addMessage("confirmaSenha", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "msg.error.senha.nao.conferem"));
			return false;
		}
		return true;
	}

	public void bloquear(){
		service.bloquear(this.entity.getId());
		search();
	}

	public void desbloquear(){
		service.desbloquear(this.entity.getId());
		search();
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getUsuarioCadastrado() {
		return usuarioCadastrado;
	}

	public void setUsuarioCadastrado(Boolean usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}

	public Perfil getRole() {
		return role;
	}

	public void setRole(Perfil role) {
		this.role = role;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public boolean isPessoaExiste() {
		return pessoaExiste;
	}

	public void setPessoaExiste(boolean pessoaExiste) {
		this.pessoaExiste = pessoaExiste;
	}


}
