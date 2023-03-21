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

	public void excluir(Long id) {
		usuarioService.excluir(id);
		
		
	}
	
	public void imprimir(Long id) {
		System.out.println("O ID É " + id);
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.pesquisarTudo();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public GestaoUsuariosBean() {
		System.out.println("QUALQUER COISA");
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

	public void salvar() {
		System.out.println("Nome: " + usuario.getNome() + " - Cpf: " + usuario.getCpf() + " - Email: "
				+ usuario.getEmail() + " - Perfil" + usuario.getPerfil() + " - Data: " + usuario.getData()
				+ " - Endereco: " + usuario.getEnderecos());
	}

	public void teste() {
		System.out.println("testando");
	}

	public String listagem() {
		return "listagemUsuarios?faces-redirect=true";
	}
	
	/*public String detalhar(Long id) {
		usuario = usuarios.pesquisaPorId(id);
		System.out.println(usuario);
		return "detalhamento?faces-redirect=true";
	}*/

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	

}
