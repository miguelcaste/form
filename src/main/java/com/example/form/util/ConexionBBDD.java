package com.example.form.util;

import java.sql.*;

public class ConexionBBDD {

    private Connection conexion;
    private String schema="javafx";
    private String usuario="usuario";
    private String password="123456";

    private String url="jdbc:mysql://localhost:3306/"+ schema;


    public Connection getConexion() {
        try {
            conexion= DriverManager.getConnection(url,usuario,password);
            //Conectado!

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public ResultSet ejecutarConsulta(String consulta){
        Statement statement = null;
        ResultSet queryOutput = null;
        try {
            statement = conexion.createStatement();
            queryOutput= statement.executeQuery(consulta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return queryOutput;
    }


}
