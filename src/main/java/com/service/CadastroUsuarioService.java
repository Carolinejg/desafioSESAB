package com.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.model.Usuario;
import com.repository.Usuarios;
import com.util.Transacional;


//aqui ficam as regras de negocio
//n]ao precisa avbrir ou fechar as transacoes gracas ao cdi
public class CadastroUsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	
	@Transacional
	public void excluir(Usuario usuario) {
		usuarios.remover(usuario);
	}
}
