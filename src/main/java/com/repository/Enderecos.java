package com.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.model.Endereco;
import com.model.Usuario;


public class Enderecos implements Serializable{
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

	//jpql 
	//select * from endereco  where cep = "40230109" and logradouro = "Rua mata maroto"
	public List<Endereco>pesquisarEnderecos(Endereco endereco){
		TypedQuery<Endereco> query = manager.createQuery("from Endereco where cep = :cep and logradouro  = :logradouro",Endereco.class);
		query.setParameter("cep", endereco.getCep());
		query.setParameter("logradouro", endereco.getLogradouro());//tudo que começa com o nome passado
		return query.getResultList();
	}
	
	public void remover(Long id) {
		Endereco endereco = pesquisaPorId(id);
		System.out.println(endereco);
		System.out.println(endereco.getUsuarios());
		manager.remove(endereco);
	}
	
	//select * from usuario 
	//inner join usuario_endereco on usuario.id=usuario_endereco.usuario_id 
	
	public List<Endereco>pesquisarEnderecosPorIdUsuario(Long id){
		TypedQuery<Endereco> query = manager.createQuery("Select e from Endereco e inner join e.usuarios u where u.id = :id ",Endereco.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public void salvarEndereco(Endereco endereco) {
		manager.merge(endereco);
	}

}
