package com.agenda;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UsuarioDao {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    
    public void readData() throws Exception {
        try {
            Class.forName(AgendaUtil.MYSQL_DRIVER);
            connection = (Connection) DriverManager.getConnection(AgendaUtil.MYSQL_URL);
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery("select * from usuario;");
            getResultSet(resultSet);
            preparedStatement = (PreparedStatement) connection.prepareStatement("insert into javaTestDB.test_table values (default,?)");
            preparedStatement.setString(1,"insert test from java");
            preparedStatement.executeUpdate();
        }finally{
            close();
        }
    }
 
    public int criaUsuario(UsuarioTo novoUsuario) throws Exception {
        try {
            Class.forName(AgendaUtil.MYSQL_DRIVER);
            connection = (Connection) DriverManager.getConnection(AgendaUtil.MYSQL_URL);
            
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO usuario(usuario, senha, ativo) VALUES (?, ?, 1);");
            preparedStatement.setString(1, novoUsuario.getUsuario());
            preparedStatement.setString(2, novoUsuario.getSenha());
            
            return preparedStatement.executeUpdate();
            
        }finally{
            close();
        }
    }
    
    public ArrayList<UsuarioTo> consultaUsuario(UsuarioTo usuario) throws Exception {
        try {
            Class.forName(AgendaUtil.MYSQL_DRIVER);
            connection = (Connection) DriverManager.getConnection(AgendaUtil.MYSQL_URL);
            
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND senha = ? AND ativo = 1;");
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getSenha());
            
			ResultSet rs = preparedStatement.executeQuery();

			ArrayList listaUsuarios = new ArrayList<UsuarioTo>();
			
			while (rs.next()) {

				UsuarioTo usuarioConsulta = new UsuarioTo();
				usuarioConsulta.setId(rs.getInt("id"));
				usuarioConsulta.setUsuario(rs.getString("usuario"));
				usuarioConsulta.setSenha(rs.getString("senha"));
				listaUsuarios.add(usuarioConsulta);

			}
            
            return listaUsuarios;
            
        }finally{
            close();
        }
    }
    
    private void getResultSet(ResultSet resultSet) throws Exception {
        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String user = resultSet.getString("usuario");
            String pass = resultSet.getString("senha");
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
