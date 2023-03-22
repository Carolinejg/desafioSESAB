package com.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.repository.Enderecos;
import com.util.Transacional;

public class EnderecoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Enderecos endereco;
	
	@Transacional
	public void excluir(Long id) {
		endereco.remover(id);
	}
	

}
