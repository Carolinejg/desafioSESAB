package com.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.model.Usuario;


@ApplicationScoped
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuarios(EntityManager em) {
		this.manager = em;
	}

	public Usuario pesquisaPorId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public Usuarios() {
		
	}

	// jpql
	public List<Usuario> pesquisarPorNome(String nomeRecebido) {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario where nome like :nome", Usuario.class);
		query.setParameter("nome", nomeRecebido + "%"); // tudo que começa com o nome passado
		return query.getResultList();
	}

	// jpql
	public List<Usuario> pesquisarTudo() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);// se ja existe atualiza no bd
	}

	public void remover(Long id) {
		Usuario usuario = porId(id);
		manager.remove(usuario);
	}

	private Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> pesquisarPorCpf(String cpfNovo) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

		Root<Usuario> root = criteriaQuery.from(Usuario.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.like(root.<String>get("cpf"), cpfNovo + "%"));

		TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
		return query.getResultList();

	}

}
