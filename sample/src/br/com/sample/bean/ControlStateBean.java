package br.com.sample.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.sample.entity.Perfil;
import br.com.sample.entity.Permissao;
import br.com.sample.entity.Usuario;
import br.com.sample.service.UsuarioService;
import br.com.sample.util.BeanUtil;

@Scope("session")
@Component("controlBean")
public class ControlStateBean extends BaseStateBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(ControlStateBean.class);

	@Autowired
	private UsuarioService usuarioService;

	public String userMail;
	private Usuario usuario;
	private Perfil role;
	private List<Permissao> permissoes;


	@PostConstruct
	public String init() throws ServletException, IOException {

		if(!isUserIn()){

			if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("roleAnonymous")) {

				this.usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				BeanUtil.getSession().setAttribute(BeanUtil.CONECTEDIP, BeanUtil.getRequest().getRemoteAddr());

				this.permissoes = new ArrayList<Permissao>();
				this.role = this.usuario.getRole();

				for (Permissao permissao : this.role.getPermissoes()) {
					this.permissoes.add(permissao);
				}
			}
			return SUCCESS;
		} else {
			return FAIL;
		}
	}

	public boolean hasRole(String... roles) {
		this.permissoes = this.usuario.getRole().getPermissoes();
		boolean permit = false;

		List<String> roleList = new ArrayList<String>();

		for (Permissao p : permissoes) {
			roleList.add(p.getNome());
		}

		if (roles != null && roles.length > 0) {
			for (String s : roles) {
				if (roleList.contains(s)) {
					permit = true;
					break;
				}
			}
		}
		return permit;
	}	

	public String voltar() {
		if (BeanUtil.getSession() != null) {
			BeanUtil.getSession().invalidate();
		}
		return SUCCESS;
	}

	public boolean isLoginErro() {
		return BeanUtil.getRequest().getParameter("login_error") != null;
	}

	public String logout() {
		BeanUtil.getSession().setAttribute("userIn", null);
		BeanUtil.getSession().invalidate();
		return SUCCESS;
	}

	public String enviarEmail(){
		if ((userMail != null)&& (!"".equals(userMail.trim()))) {
			List<Usuario> list = usuarioService.retriveByMail(userMail);
			if ((list != null)&& (list.size() > 0)) {
				for (Usuario usuario: list) {
					try {
						String str = BeanUtil.sendMailToUser(userMail, usuario);
						if (str.equalsIgnoreCase(SUCCESS)) {
							BeanUtil.getRequest().setAttribute("sendmail_msg", "A Senha foi enviada ao e-mail informado.");
							return SUCCESS;
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			} else {
				BeanUtil.getRequest().setAttribute("sendmail_err", "Não foi encontrado usuário do e-mail informado.");
				return FAIL;
			}
		} else {
			BeanUtil.getRequest().setAttribute("sendmail_wrn", "Por favor informe o e-mail!");
			return FAIL;
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	protected static <E> E getUsuarioService(Class<E> clazz, HttpSession session) {
		ApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		return (E) wac.getBean("usuarioService");
	}

	@SuppressWarnings("unchecked")
	protected static <E> E getLogUsuarioService(Class<E> clazz, HttpSession session) {
		ApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		return (E) wac.getBean("logUsuarioService");
	}

	public boolean isAdmin() {
		return BeanUtil.isAdmin(this.usuario);
	}

	public boolean isUser() {
		return BeanUtil.isUser(this.usuario);
	}	

	public boolean isGerente() {
		return BeanUtil.isGerente(this.usuario);
	}

	public boolean isMedico() {
		return BeanUtil.isMedico(this.usuario);
	}


	public boolean isClienteBean(){
		return ClienteBean.class.getSimpleName().equals(getCurrentBean());
	}

	public boolean isPessoaBean(){
		return PessoaBean.class.getSimpleName().equals(getCurrentBean());
	}

	public boolean isUsuarioBean(){
		return UsuarioBean.class.getSimpleName().equals(getCurrentBean());
	}

	public boolean isAnimalBean(){
		return AnimalBean.class.getSimpleName().equals(getCurrentBean());
	}

	public boolean isRacaBean(){
		return RacaBean.class.getSimpleName().equals(getCurrentBean());
	}	

	public boolean isProdutoBean(){
		return ProdutoBean.class.getSimpleName().equals(getCurrentBean());
	}	
	
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getRole() {
		return role;
	}

	public void setRole(Perfil role) {
		this.role = role;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}