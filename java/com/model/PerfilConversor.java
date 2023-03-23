package com.model;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Perfil.class)
public class PerfilConversor implements javax.faces.convert.Converter {

	
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return ""; // Never return null here!
		}

		if (modelValue instanceof Perfil) {
			Perfil perfil = (Perfil)modelValue;
			if (perfil.perfil.equals(TipoPerfil.ADM)) {
				return "ADM";
			}
				
				
			if (perfil.perfil.equals(TipoPerfil.USER)) {
				return "USER";
			}
				
		}
		
		throw new ConverterException(new FacesMessage(modelValue + " is not a valid Perfil"));
	}

	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		if (submittedValue.equals("ADM")) {
			Perfil perfil = new Perfil();
			perfil.setPerfil(TipoPerfil.ADM);
			perfil.setId(1L);
			return perfil;
		}
		if (submittedValue.equals("USER")) {
			Perfil perfil = new Perfil();
			perfil.setPerfil(TipoPerfil.USER);
			perfil.setId(2L);
			return perfil;

		}
		
		throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Perfil name"));

	}
}
