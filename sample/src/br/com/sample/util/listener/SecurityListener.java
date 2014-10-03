package br.com.sample.util.listener;

import java.security.Principal;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.sample.entity.Usuario;
import br.com.sample.service.UsuarioService;
import br.com.sample.util.BeanUtil;

public class SecurityListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent event) {
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		Principal principal = request.getUserPrincipal();
		HttpSession session = request.getSession();
		if (principal != null && session.getAttribute(BeanUtil.USUARIO_LOGADO) == null) {
			String[] properties = principal.getName().split("");
			Long id = Long.parseLong(properties[0]);
			Usuario usuario = retrieveUsuario(id, session);
			session.setAttribute(BeanUtil.USUARIO_LOGADO, usuario);
		}
	}

	public void requestDestroyed(ServletRequestEvent event) {}

	private Usuario retrieveUsuario(Long id, HttpSession request) {
		UsuarioService service = getUsuarioService(UsuarioService.class, request);
		return service.retrieve(id);
	}

	public static void createLogUsuario(Usuario usuario, HttpSession request) {
		try {
			UsuarioService usuarioService = getUsuarioService(UsuarioService.class, request);
			usuario = usuarioService.retrieve(usuario.getId());

			usuarioService.update(usuario);
			request.setAttribute(BeanUtil.CONECTEDIP, BeanUtil.getRequest().getRemoteAddr());
			BeanUtil.getSession().setAttribute(BeanUtil.SESSION_OPEN, Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateLogUsuario(Usuario usuario, HttpSession request) {
		try {
			UsuarioService usuarioService = getUsuarioService(UsuarioService.class, request);
			usuario = usuarioService.retrieve(usuario.getId());
			usuarioService.update(usuario);
			BeanUtil.getSession().setAttribute(BeanUtil.SESSION_OPEN, Boolean.FALSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}