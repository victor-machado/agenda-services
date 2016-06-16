package com.agenda;

import java.util.ArrayList;

public class CompromissoBo {

	public int criarCompromisso(CompromissoTo novoCompromisso) throws Exception{
		
		CompromissoDao dao = new CompromissoDao();
		return dao.criaCompromisso(novoCompromisso);
	}
	
	public ArrayList<CompromissoTo> consultarCompromissos(String idUsuario) throws Exception{
		
		CompromissoDao dao = new CompromissoDao();
		return dao.consultaCompromissos(Integer.parseInt(idUsuario));
	}
	
	public int excluirCompromisso(int id) throws Exception{
		
		CompromissoDao dao = new CompromissoDao();
		return dao.excluiCompromisso(id);
	}
}
