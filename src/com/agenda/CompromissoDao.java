package com.agenda;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CompromissoDao {


    
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
            
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO usuario(usuario, senha) VALUES (?, PASSWORD(?));");
            preparedStatement.setString(1, novoUsuario.getUsuario());
            preparedStatement.setString(2, novoUsuario.getUsuario());
            return preparedStatement.executeUpdate();
            
        }finally{
            close();
        }
    }
    
    private void getResultSet(ResultSet resultSet) throws Exception {
        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String user = resultSet.getString("usuario");
            String pass = resultSet.getString("senha");
//            System.out.println("id: "+id);
//            System.out.println("text: "+text);
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
