package com.agenda;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/compromissos")
public class CompromissoService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int criarCompromisso(CompromissoTo compromisso) {

		CompromissoBo bo = new CompromissoBo();
		int resultado = -1;
		try {
			
			resultado = bo.criarCompromisso(compromisso);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return resultado;
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CompromissoTo> consultarCompromissos(@QueryParam("idUsuario") String idUsuario) {

		CompromissoBo bo = new CompromissoBo();
		ArrayList<CompromissoTo> compromissos = new ArrayList<CompromissoTo>();

		try {
			
			compromissos = bo.consultarCompromissos(idUsuario);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return compromissos;
	}
	
	@GET
	@Path("/excluir")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public int excluirCompromisso(@QueryParam("id") String id) {

		CompromissoBo bo = new CompromissoBo();
		int resultado = -1;
		try {
			
			resultado = bo.excluirCompromisso(Integer.parseInt(id));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return resultado;
	}
	

}
