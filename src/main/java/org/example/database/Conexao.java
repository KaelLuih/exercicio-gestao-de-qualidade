package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  Conexao {


    private final static String conexao= "";
    private final static String nome = "";
    private final static String senha= "";
    public static Connection conexao()throws SQLException{
        return DriverManager.getConnection(conexao,nome,senha);
    }
}
