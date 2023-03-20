package com.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.model.Perfil;
import com.model.TipoPerfil;

@Named
@ViewScoped
public class GestaoPerfisBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Perfil perfil = new Perfil();
	
	public String[] getTiposPerfis() {
		String[] nomes = {"ADM", "USER"};
		return nomes;
	}

}
