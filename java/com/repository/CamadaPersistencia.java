package com.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Endereco;
import com.model.Perfil;
import com.model.TipoPerfil;
import com.model.Usuario;

public class CamadaPersistencia {
public static void main(String[] args) {
		
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//declarando os repositorios 
		Usuarios usuarios = new Usuarios(em);
		Enderecos enderecos = new Enderecos(em);
		
		//buscando no bd
		List<Usuario> listaUsuarios = usuarios.pesquisarPorNome("");
		System.out.println(listaUsuarios);
		
		Usuario usuarioId =  usuarios.pesquisaPorId(1L);
		System.out.println(usuarioId);
		
		List<Usuario> usuarioCpf = usuarios.pesquisarPorCpf("06421140569");
		System.out.println(usuarioCpf);
		
		Endereco enderecoEspecifico = new Endereco("40230109", "Rua mata maroto");
		
		List<Endereco>listaEndereco =  enderecos.pesquisarEnderecos(enderecoEspecifico);
		TipoPerfil tipoPerfil = TipoPerfil.ADM;
		Perfil perfil = new Perfil(10L,tipoPerfil);
		
		//criando um usuario
		Usuario usuario = new Usuario();
		usuario.setCpf("06421140564");
		usuario.setData(new Date());
		usuario.setEmail("carolinhza@gmail.com");
		//usuario.setEnderecos(listaEndereco);
		usuario.setNome("SAULO");
		//usuario.setPerfil(perfil.getId()); nâo funcionou
		
		//salvando um usuario 
		usuarios.guardar(usuario);
		
		em.getTransaction().commit();
		
		//verifica se foi inserido
		List<Usuario> listaUsuario2 = usuarios.pesquisarPorNome("");
		System.out.println(listaUsuario2);
		
		em.close();
		emf.close();*/
		
		
	}

}
