package com.controller;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.model.Endereco;
import com.model.Usuario;
import com.repository.Usuarios;
import com.util.FacesMessages;

@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //vinculando a pagina com o managerbean 
    
    private Usuario usuario = new Usuario();
    
    private List<Usuario> listaUsuarios;
    
    private Endereco endereco = new Endereco();
    
    private String termoPesquisa;
    
    @Inject
    private FacesMessages messages;
    
    @Inject
    private Usuarios usuarios;
    
    public void todosUsuarios() {
    	listaUsuarios = usuarios.pesquisarTudo();
    }
    
    public List<Usuario>getListaUsuarios(){
    	return listaUsuarios;
    }
    
    public GestaoUsuariosBean() {
    	System.out.println("QUALQUER COISA");
    }
    
    public void pesquisarPorNome() {
    	listaUsuarios = usuarios.pesquisarPorNome(termoPesquisa);
    	
    	if(listaUsuarios.isEmpty()) {
    		messages.info("Sua consulta não retornou registros");
    	}
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
    
    public String listagem() {
    	return "listagemUsuarios?faces-redirect=true";
    }

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
    
    
    
   
}
