package br.com.sample.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.context.SecurityContextHolder;
import br.com.sample.entity.Usuario;

public class BeanUtil{

	public static final String MAIL = "fcoedilson@gmail.com";
	public static final String USUARIO_LOGADO = "usuarioLogado";
	public static final String SESSION_OPEN = "sessionOpened";
	public static final String CONECTEDIP = "conectedip";
	public static final String ADMIN = "ADMIN";
	

	public static boolean isUserInRole(String role) {
		return getRequest().isUserInRole(role);
	}

	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static Usuario usuarioLogado(){
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static Boolean isUser(Usuario user){
		if(user.getRole().getAuthority().equals("USER")){
			return true;
		}
		return false;
	}

	public static Boolean isAdmin(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_ADMIN")){
			return true;
		}
		return false;
	}

	public static Boolean isGerente(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_GERENTE")){
			return true;
		}
		return false;
	}	

	public static Boolean isMedico(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_MEDICO")){
			return true;
		}
		return false;
	}	

	public static Boolean isCliente(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_CLIENTE")){
			return true;
		}
		return false;
	}

	public static Boolean isAssistente(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_ASSISTENTE")){
			return true;
		}
		return false;
	}	

	public static Boolean isAtendente(Usuario user){
		if(user.getRole().getAuthority().equals("ROLE_ATENDENTE")){
			return true;
		}
		return false;
	}	
	
	public static String md5(String valor) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
			String s = hash.toString(16);
			if (s.length() %2 != 0) s = "0" + s;
			return s;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean validaCpf(String cpf){
		//TODO validar cpf
		return true;
	}
	
	public static synchronized String sendMailToUser(String mail, Usuario user) throws Exception{
		String msg = "Sr. " + user.getNome() + "\n seu login atual é " + 
		user.getLogin() + "\n sua senha atual é:" + user.getPassword();
		return MailUtil.sendEmail(mail, "recuperação de login e senha", msg);
	}
}