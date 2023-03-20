package com.controller;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.model.Endereco;
import com.model.Usuario;

@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //vinculando a pagina com o managerbean 
    
    private Usuario usuario = new Usuario();
    
    private Endereco endereco = new Endereco();
    
    public GestaoUsuariosBean() {
    	System.out.println("QUALQUER COISA");
    }
    
    public void salvarEnderecos() {
     	usuario.getEnderecos().add(endereco);
     	endereco = new Endereco();
    }
	
	public Endereco getEndereco() {
		return endereco;
	}
    
    public Usuario getUsuario() {
		return usuario;
	}
    
    public void salvar() {
    	System.out.println("Nome: " + usuario.getNome() + " - Cpf: "+usuario.getCpf() + " - Email: "+ usuario.getEmail()+ 
    			" - Perfil" + usuario.getPerfil() + " - Data: "+ usuario.getData() + " - Endereco: "+ usuario.getEnderecos());
    }
    
    public void teste() {
    	System.out.println("testando");
    }
    
    
    
   
}
