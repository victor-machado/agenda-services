package com.agenda;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

@Path("/usuarios")
public class UsuarioService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int criarUsuario(UsuarioTo usuario) {

		UsuarioBo bo = new UsuarioBo();
		int resultado = -1;
		try {
			
			resultado = bo.criarUsuario(usuario);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return resultado;
	}
	
	@GET
	@Path("/usuario")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public int consultarUsuario(@QueryParam("usuario") String usuario) {

		UsuarioBo bo = new UsuarioBo();
		int resultado = -1;
		try {
			resultado = bo.consultarUsuario("{"+usuario+"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
