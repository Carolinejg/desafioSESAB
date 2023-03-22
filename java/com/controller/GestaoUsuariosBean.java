package com.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.model.Endereco;
import com.model.Usuario;
import com.repository.Enderecos;
import com.repository.Usuarios;
import com.service.CadastroUsuarioService;
import com.service.EnderecoService;
import com.util.FacesMessages;

@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// vinculando a pagina com o managerbean

	private Usuario usuario = new Usuario();

	private List<Usuario> listaUsuarios;

	private Endereco endereco = new Endereco();

	private String termoPesquisa;

	@Inject
	private FacesMessages messages;

	@Inject
	private Usuarios usuarios;

	@Inject
	private Enderecos enderecos;

	@Inject
	private CadastroUsuarioService usuarioService;
	
	@Inject 
	EnderecoService enderecoService;

	public void cadastrar() {
		// usuario.setEnderecos(null);
		Date data = new Date();
		usuario.setData(data);
		usuarioService.salvar(usuario);

		if (jaHouvePesquisa()) {
			pesquisarPorNome();
		}
		messages.info("Usuario cadastrado com sucesso!");
	}
	
	public void excluindoEndereco(Long id) {
		enderecoService.excluir(id);
		todosUsuarios();
		usuario= usuarios.pesquisaPorId(usuario.getId());
		
	}
	
	public EnderecoService getEnderecoService() {
		return this.enderecoService;
	}
	
	public void salvarEndereco() {
		enderecos.salvarEndereco(endereco);
	}
	
	public void removerEnderecoDaLista(Endereco endereco) {
		usuario.removeEndereco(endereco);
	}
	
	public void excluirEnderecoDoUsuario(Endereco endereco) {
		enderecos.remover(endereco.getId());
		todosUsuarios();
	}
	
	public void atualizar() {
		usuarioService.salvar(usuario);
		
	}

	public void excluir(Long id) {
		usuarioService.excluir(id);
		todosUsuarios();
		
		
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.pesquisarTudo();
	}

	public List<Usuario> getListaUsuarios() {
		System.out.println("Lista de usuarios "+listaUsuarios);
		return listaUsuarios;
	}

	public GestaoUsuariosBean() {
		System.out.println("Construtor do bean");
	}

	public void pesquisarPorNome() {
		listaUsuarios = usuarios.pesquisarPorNome(termoPesquisa);

		if (listaUsuarios.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public void salvarEnderecos() {
		if (endereco.getLogradouro().equals("") || endereco.getCep().equals("")) {
			messages.info("Logradouro e cep precisam ser informados");
		} else {
			usuario.getEnderecos().add(endereco);
			endereco = new Endereco();
		}

	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Endereco> imprimirEnderecos(){
		System.out.println("endereco para tabela "+usuario.getEnderecos());
		return usuario.getEnderecos();
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
	
	//selecao de uma linha na tabela
	public boolean estaSelecionado() {
		return usuario != null && usuario.getId() != null;
		
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		System.out.println("Setando usuario" + usuario );
	}

}
