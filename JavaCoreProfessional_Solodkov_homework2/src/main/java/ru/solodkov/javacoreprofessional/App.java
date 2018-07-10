package ru.solodkov.javacoreprofessional;

import org.sqlite.JDBC;

import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            Statement stmt = conn.createStatement();

            java.sql.DriverManager.registerDriver( new Driver());
        } catch (SQLException e) {
            throw new RuntimeException( "Can't register driver!" );
        }
    }
}
