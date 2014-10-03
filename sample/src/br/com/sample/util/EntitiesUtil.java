package br.com.sample.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Usuario;

import com.vividsolutions.jts.geom.Point;

public class EntitiesUtil {

	public static final String CLIENTE_LOGADO = "clienteLogado";
	public static final String USUARIO_LOGADO = "UserLogado";
	public static final String ADMIN = "ADMIN";

	public static boolean isAdmin() {
		return getRequest().isUserInRole(ADMIN);
	}


	public static boolean isUserInRole(String role) {
		return getRequest().isUserInRole(role);
	}

	public static Usuario retrieveUserLogado() {

		HttpSession session = getSession();
		if (session != null && session.getAttribute(USUARIO_LOGADO) != null) {
			return (Usuario) session.getAttribute(USUARIO_LOGADO);
		} else {
			return null;
		}
	}

	public static Cliente retrieveClienteLogado() {

		HttpSession session = getSession();
		if (session != null && session.getAttribute(CLIENTE_LOGADO) != null) {
			return (Cliente) session.getAttribute(CLIENTE_LOGADO);
		} else {
			return null;
		}
	}

	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static Float distanciaEntrePonto(Point pointA, Point  pointB) {

		double lat1 = pointA.getX() / (180D / (22D / 7D));
		double lng1 = pointA.getY() / (180D / (22D / 7D));
		double lat2 = pointB.getX() / (180D / (22D / 7D));
		double lng2 = pointB.getY() / (180D / (22D / 7D));
		return (float) (long) (6378800D * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1)));
	}

	public static synchronized String sendMailToUser(String mail, Usuario user) throws Exception{

		String msg = "Sr. " + user.getUsername() + "\n seu login atual: " + user.getLogin() + "\n sua senha atual :" + user.getSenha();
		return MailUtil.sendEmail(mail, "recuperação de login e senha", msg);
	}
}