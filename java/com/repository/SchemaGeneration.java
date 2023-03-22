package com.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Usuario;


public class SchemaGeneration {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> lista = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		System.out.println(lista);
		
		em.close();
		emf.close();
		
		
	}
	
	
}
