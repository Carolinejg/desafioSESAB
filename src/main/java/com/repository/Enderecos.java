package com.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.model.Endereco;
import com.model.Usuario;


public class Enderecos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public Enderecos(EntityManager em) {
		this.manager = em;
	}
	
	public Endereco pesquisaPorId(Long id) {
		return manager.find(Endereco.class, id);
	}
	
	
	//jpql 
	//select * from endereco  where cep = "40230109" and logradouro = "Rua mata maroto"
	public List<Endereco>pesquisarEnderecos(Endereco endereco){
		TypedQuery<Endereco> query = manager.createQuery("from Endereco where cep = :cep and logradouro  = :logradouro",Endereco.class);
		query.setParameter("cep", endereco.getCep());
		query.setParameter("logradouro", endereco.getLogradouro());//tudo que começa com o nome passado
		return query.getResultList();
	}

}
