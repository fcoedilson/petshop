package br.com.sample.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sample.entity.Cliente;
import br.com.sample.service.ClienteService;

@FacesConverter(value="clienteConverter")
public class ClienteConverter implements Converter  {


	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		ClienteService service = new ClienteService();
		Cliente cliente = service.retrieve(new Long(value));
		return cliente;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Cliente cliente = new Cliente();
		cliente = (Cliente) o;
		Long id = cliente.getId();
		return id.toString();
	}


}
