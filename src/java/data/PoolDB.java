package data;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.*;

/**
 *
 * @author Blueweb
 */
public class PoolDB {

    /* Creates a new instance of PoolDB */
    public PoolDB() {
    }

    // Recibe el nombre de la conexión
    public Connection getConnection(String nName) throws SQLException, NamingException {
        InitialContext cxt = new InitialContext();
        DataSource ds = null;

        if (cxt == null) {
            throw new SQLException("No existe el contexto");
        } else {

            try {
                ds = (DataSource) cxt.lookup("java:comp/env/jdbc/Pool" + nName);// datasource objeot que hace la
                                                                                // conexion
            } catch (Exception ex) {
                ds = (DataSource) cxt.lookup("jdbc/Pool" + nName);
            }

        }

        if (ds == null) {
            throw new SQLException("Origen de Datos " + nName + " no Encontrado!");
        } else {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(true);
            System.out.println("conectado");
            return conn;
        }
    }

}
