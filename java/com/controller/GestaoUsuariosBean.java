package com.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

	private String pesquisaNome;
	private String pesquisaCpf;
	private Date pesquisaInicio;
	private Date pesquisaFim;

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
		Date data = new Date();
		usuario.setData(data);
		usuarioService.salvar(usuario);

		if (jaHouvePesquisa()) {
			pesquisarPorNome();
		}
		messages.info("Usuario cadastrado com sucesso!");
	}

	public void cadastrarEndereco() {
		usuario.addEndereco(endereco);
		usuarioService.salvar(usuario);
		endereco = new Endereco();
		todosUsuarios();
		usuario = usuarios.pesquisaPorId(usuario.getId());
	}

	public void excluindoEndereco(Long id) {
		enderecoService.excluir(id);
		todosUsuarios();
		usuario = usuarios.pesquisaPorId(usuario.getId());

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
		return pesquisaNome != null && !"".equals(pesquisaNome);
	}

	public void pesquisarPorNome() {
		listaUsuarios = usuarios.pesquisarPorNome(pesquisaNome);

		if (listaUsuarios.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public void pesquisar() {
		listaUsuarios = usuarios.pesquisarPorCampos(pesquisaCpf, pesquisaNome, pesquisaInicio, pesquisaFim);

		if (listaUsuarios.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.pesquisarTudo();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public GestaoUsuariosBean() {
		
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

	public List<Endereco> imprimirEnderecos() {
		return usuario.getEnderecos();
	}

	public String listagem() {
		return "listagemUsuarios?faces-redirect=true";
	}

	public String getPesquisaNome() {
		return pesquisaNome;
	}

	public void setPesquisaNome(String pesquisaNome) {
		this.pesquisaNome = pesquisaNome;
	}

	public String getPesquisaCpf() {
		return pesquisaCpf;
	}

	public void setPesquisaCpf(String pesquisaCpf) {
		this.pesquisaCpf = pesquisaCpf;
	}

	public Date getPesquisaInicio() {
		return pesquisaInicio;
	}

	public void setPesquisaInicio(Date pesquisaInicio) {
		this.pesquisaInicio = pesquisaInicio;
	}

	public Date getPesquisaFim() {
		return pesquisaFim;
	}

	public void setPesquisaFim(Date pesquisaFim) {
		this.pesquisaFim = pesquisaFim;
	}

	// selecao de uma linha na tabela
	public boolean estaSelecionado() {
		return usuario != null && usuario.getId() != null;

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
	}

}
