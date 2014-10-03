/**
 * 
 */
package br.com.sample.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sample.entity.Permissao;
import br.com.sample.service.PermissaoService;


public class PermissaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String str) {
		if(str != null && !str.equals("")){
			PermissaoService permissaoService = new PermissaoService();
			Permissao permissao = permissaoService.retrieve(Long.valueOf(str));
			return permissao;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object obj) {
		if(obj == null){
			return null;
		}else{
			Permissao permissao = (Permissao) obj;
			return String.valueOf(permissao.getId());
		}
	}
}
