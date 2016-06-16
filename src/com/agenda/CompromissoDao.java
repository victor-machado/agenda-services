package com.agenda;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CompromissoDao {

	private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public int criaCompromisso(CompromissoTo novoCompromisso) throws Exception {
        try {
            Class.forName(AgendaUtil.MYSQL_DRIVER);
            connection = (Connection) DriverManager.getConnection(AgendaUtil.MYSQL_URL);
            
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO compromisso(id_usuario, agendamento, dia, hora, descricao, ativo) VALUES (?, ?, ?, ?, ?, 1);");
            preparedStatement.setInt(1, novoCompromisso.getIdUsuario());
            preparedStatement.setString(2, stringData(novoCompromisso.getData(), novoCompromisso.getHorario()));
            preparedStatement.setString(3, novoCompromisso.getData());
            preparedStatement.setString(4, novoCompromisso.getHorario());
            preparedStatement.setString(5, novoCompromisso.getDescricao());
            return preparedStatement.executeUpdate();
            
        }finally{
            close();
        }
    }
    
    private String stringData(String data, String hora){
    	
    	String[] stringData = data.split("/");
    	String[] stringHora = hora.split(":");
    	
    	return stringData[2] + "-" + stringData[1] + "-" + stringData[0] + " " + stringHora[0] + ":" + stringHora[1] + ":00";
    }

    public ArrayList<CompromissoTo> consultaCompromissos(int idUsuario) throws Exception{

        try {
            Class.forName(AgendaUtil.MYSQL_DRIVER);
            connection = (Connection) DriverManager.getConnection(AgendaUtil.MYSQL_URL);
            
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM compromisso WHERE id_usuario = ?;");
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            
            ArrayList<CompromissoTo> compromissos = new ArrayList<CompromissoTo>();
            
            while (rs.next()) {

            	CompromissoTo compromisso = new CompromissoTo();
            	compromisso.setId(rs.getInt("id"));
            	compromisso.setIdUsuario(rs.getInt("id_usuario"));
            	compromisso.setAgendamento(rs.getDate("agendamento"));
            	compromisso.setData(rs.getString("dia"));
            	compromisso.setHorario(rs.getString("hora"));
            	compromisso.setDescricao(rs.getString("descricao"));
            	
            	compromissos.add(compromisso);
			}
            
            return compromissos;
            
        }finally{
            close();
        }
    }
    
    private void close(){
        try {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        } catch(Exception e){}
    }
}
