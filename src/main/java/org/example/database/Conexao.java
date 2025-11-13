package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final static String URL = "jdbc:mysql://localhost:3306/teste?useSSL=false&serverTimezone=UTC";
    private final static String NAME = "root";
    private final static String SENHA = "mysqlPW";
    public static Connection conexao()throws SQLException{
        return DriverManager.getConnection(URL,NAME,SENHA);
    }
}
