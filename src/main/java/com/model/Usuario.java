package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.br.CPF;

@Entity(name = "Usuario")
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames="cpf")})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String nome;
	
	@Column(unique = true, nullable = false)
	@CPF
	private String cpf;
	
	private String email;
	
	@ManyToOne()
	private Perfil perfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL})
	@JoinTable(name = "usuario_endereco ",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "endereco_id"))
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	
	public Usuario() {
		super();
	}
	
	public void addEndereco(Endereco endereco) {
		enderecos.add(endereco);
		endereco.getUsuarios().add(this);
	}
	
	public void removeEndereco(Endereco endereco) {
		enderecos.remove(endereco);
		endereco.getUsuarios().remove(this);
	}

	

	public Usuario(Long id, Date data, String nome, String cpf, String email, Perfil perfil, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.data = data;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.perfil = perfil;
		this.enderecos = enderecos;
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	public List<Endereco> getEnderecos() {
		return enderecos;
	}



	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", data=" + data + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ ", perfil=" + perfil + ", enderecos=" + enderecos + "]";
	}
		
	
	
	
	
	
	
	
	
	

}
