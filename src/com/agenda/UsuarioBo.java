package com.agenda;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONObject;

public class UsuarioBo {

	public int criarUsuario(UsuarioTo novoUsuario) throws Exception{
		
		UsuarioDao dao = new UsuarioDao();
		return dao.criaUsuario(novoUsuario);
	}
	
	public int consultarUsuario(String usuario) throws Exception{
		
		JSONObject json = null;
		
		try {

			json = new JSONObject(usuario);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		UsuarioTo usuarioAutenticacao = new UsuarioTo();
		usuarioAutenticacao.setUsuario(json.getString("usuario"));
		usuarioAutenticacao.setSenha(json.getString("senha"));
		
		UsuarioDao dao = new UsuarioDao();
		
		ArrayList<UsuarioTo> cadastros = new ArrayList<UsuarioTo>();
		cadastros = dao.consultaUsuario(usuarioAutenticacao);
		
		boolean cadastrado = false;
		
		for (int i = 0; i < cadastros.size(); i++) {
			
			if (usuarioAutenticacao.getUsuario().equals(cadastros.get(i).getUsuario()) && usuarioAutenticacao.getSenha().equals(cadastros.get(i).getSenha())) {
				cadastrado = true;
				break;
			}
		}
		
		if ( cadastrado) {
			return 1;
		} else {
			return -1;
		}
	}
}





