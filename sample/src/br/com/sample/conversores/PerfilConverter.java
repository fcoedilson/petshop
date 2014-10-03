/**
 * 
 */
package br.com.sample.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sample.entity.Perfil;
import br.com.sample.service.PerfilService;


public class PerfilConverter implements Converter{

	@Autowired
	private PerfilService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String str) {
		if(str != null && !str.equals("")){
			Perfil role = service.findById(new Integer(str));
			return (Object)role;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj == null || obj.equals("")){
			return null;
		}else{
			Perfil role = (Perfil) obj;
			return String.valueOf(role.getId());
		}
	}

}
