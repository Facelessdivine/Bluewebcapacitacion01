
package models;

import data.PoolDB;
import responses.AccessResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import responses.Response;
import clases.Acceso;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blueweb
 */
public class AccesoModel {

    public AccessResponse conectarLista() {

        AccessResponse respuestaAcceso = new AccessResponse();
        Response claseRespuesta = new Response();
        String query = "";
        List<Acceso> lista = new ArrayList();

        PoolDB pool = new PoolDB();
        Connection con = null;

        try {

            con = pool.getConnection("Activa");

            query = "SELECT ID_ACCESO, NOMBRE_ACCESO, ORDEN,ACTIVO,FECHA_SERVIDOR FROM S_ACCESOS WITH (NOLOCK)";
            PreparedStatement consulta = con.prepareStatement(query);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Acceso acceso = new Acceso();
                acceso.setId_acceso(rs.getInt("ID_ACCESO"));
                acceso.setNombre_acceso(rs.getString("NOMBRE_ACCESO"));
                acceso.setOrden(rs.getInt("ORDEN"));
                acceso.setActivo(rs.getBoolean("ACTIVO"));
                String estado = rs.getBoolean("ACTIVO") ? "ACTIVO" : "INACTIVO";
                acceso.setEstado(estado);
                acceso.setFecha_servidor(rs.getDate("FECHA_SERVIDOR"));
                lista.add(acceso);

            }

            rs.close();
            consulta.close();
            con.close();

            if (!lista.isEmpty()) {
                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("exitoso");

                respuestaAcceso.setListaAcceso(lista);

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advertencia");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(AccesoModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaAcceso.setRespuesta(claseRespuesta);
        }
        return respuestaAcceso;
    }

    public AccessResponse addAccess(Acceso access) {
        AccessResponse respuestaAcceso = new AccessResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int bandera = 0;
        try {

            con = pool.getConnection("Activa");

            query = "INSERT INTO S_ACCESOS (NOMBRE_ACCESO, ORDEN, ACTIVO, FECHA_SERVIDOR) VALUES (?,?,?,GETDATE()) ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, access.getNombre_acceso());
            consulta.setInt(2, access.getOrden());
            consulta.setBoolean(3, access.getActivo());

            bandera = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (bandera != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro agregado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(AccesoModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaAcceso.setRespuesta(claseRespuesta);
        }

        return respuestaAcceso;
    }

    public AccessResponse deleteAccess(Acceso access) {
        AccessResponse respuestaAcceso = new AccessResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int bandera = 0;
        try {

            con = pool.getConnection("Activa");

            query = "DELETE FROM S_ACCESOS WHERE ID_ACCESO = ? ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setInt(1, access.getId_acceso());

            bandera = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (bandera != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro Eliminado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(AccesoModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaAcceso.setRespuesta(claseRespuesta);
        }

        return respuestaAcceso;
    }

    public AccessResponse updateAccess(Acceso access) {
        AccessResponse respuestaAcceso = new AccessResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int bandera = 0;
        try {

            con = pool.getConnection("Activa");

            query = "UPDATE S_ACCESOS SET NOMBRE_ACCESO = ?, ORDEN = ?, ACTIVO = ? WHERE ID_ACCESO = ? ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, access.getNombre_acceso());
            consulta.setInt(2, access.getOrden());
            consulta.setBoolean(3, access.getActivo());
            consulta.setInt(4, access.getId_acceso());

            bandera = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (bandera != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro Actualizado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(AccesoModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaAcceso.setRespuesta(claseRespuesta);
        }

        return respuestaAcceso;
    }

}
