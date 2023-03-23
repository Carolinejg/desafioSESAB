package com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.model.Endereco;
import com.model.Usuario;

public class Enderecos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Enderecos(EntityManager em) {
		this.manager = em;
	}

	public Endereco pesquisaPorId(Long id) {
		return manager.find(Endereco.class, id);
	}

	public Enderecos() {
		super();
	}

	
	public List<Endereco> pesquisarEnderecos(Endereco endereco) {
		TypedQuery<Endereco> query = manager.createQuery("from Endereco where cep = :cep and logradouro  = :logradouro",
				Endereco.class);
		query.setParameter("cep", endereco.getCep());
		query.setParameter("logradouro", endereco.getLogradouro());
		return query.getResultList();
	}

	public void remover(Long id) {
		Endereco endereco = pesquisaPorId(id);
		List<Usuario> use = new ArrayList<Usuario>(endereco.getUsuarios());
		manager.remove(endereco);
		for (Usuario usuario : use) {
			usuario.removeEndereco(endereco);
			Usuario use2 = manager.merge(usuario);
		}

	}

	public List<Endereco> pesquisarEnderecosPorIdUsuario(Long id) {
		TypedQuery<Endereco> query = manager
				.createQuery("Select e from Endereco e inner join e.usuarios u where u.id = :id ", Endereco.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	public void salvarEndereco(Endereco endereco) {
		manager.merge(endereco);
	}

}
