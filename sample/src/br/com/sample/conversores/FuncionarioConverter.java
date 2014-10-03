package br.com.sample.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sample.entity.Funcionario;
import br.com.sample.service.FuncionarioService;

@FacesConverter(value="funcionarioConverter")
public class FuncionarioConverter implements Converter  {


	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		FuncionarioService service = new FuncionarioService();
		Funcionario cliente = service.retrieve(new Long(value));
		return cliente;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Funcionario cliente = new Funcionario();
		cliente = (Funcionario) o;
		Long id = cliente.getId();
		return id.toString();
	}


}
