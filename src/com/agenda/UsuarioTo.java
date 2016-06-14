package com.agenda;

import java.io.Serializable;

public class UsuarioTo implements Serializable{

	private int id;
	private String usuario;
	private String senha;
	public UsuarioTo() {
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
