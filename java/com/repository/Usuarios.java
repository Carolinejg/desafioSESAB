package com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
		query.setParameter("nome", nomeRecebido + "%"); 
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

	public List<Usuario> pesquisarPorCampos(String cpfNovo, String nome, Date inicio, Date fim) {
		if (fim != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fim);
			c.add(Calendar.DATE, 1);
			fim = c.getTime();
		}

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

		Root<Usuario> root = criteriaQuery.from(Usuario.class);

		criteriaQuery.select(root);
		List<Predicate> listaPred = new ArrayList<>();
		listaPred.add(criteriaBuilder.and(criteriaBuilder.like(root.<String>get("cpf"), cpfNovo + "%"),
				criteriaBuilder.like(root.<String>get("nome"), nome + "%")));

		if (inicio != null && fim != null) {
			listaPred.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("data"), inicio));
			listaPred.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("data"), fim));
		}
		if (inicio != null && fim == null) {
			listaPred.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("data"), inicio));
		}
		if (inicio == null && fim != null) {
			listaPred.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("data"), fim));
		}

		criteriaQuery.where(listaPred.toArray(new Predicate[] {}));
		TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
		return query.getResultList();

	}

}
