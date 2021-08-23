
package models;
//<editor-fold defaultstate="collapsed" desc="imports">

import data.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import responses.Response;
import clases.Perfil;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import responses.ProfileResponse;
import sesiones.Sesion;
//</editor-fold>
/**
 *
 * @author Raúl Herrera Macías
 */
public class PerfilModel {
    // Hace una consulta en la base de datos, no recibe parámetros
    public ProfileResponse queryProfile() {

        ProfileResponse respuestaPerfil = new ProfileResponse();
        Response claseRespuesta = new Response();
        String query = "";
        List<Perfil> lista = new ArrayList();

        PoolDB pool = new PoolDB();
        Connection con = null;

        try {

            con = pool.getConnection("Activa");

            query = "SELECT ID_PERFIL, NOMBRE_PERFIL, DESCRIPCION, ACTIVO, FECHA_ALTA, FECHA_BAJA, FECHA_SERVIDOR, ID_USUARIO_MODIFICA FROM S_PERFILES WITH (NOLOCK)";
            PreparedStatement consulta = con.prepareStatement(query);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Perfil profile = new Perfil();
                profile.setId_perfil(rs.getInt("ID_PERFIL"));
                profile.setNombre_perfil(rs.getString("NOMBRE_PERFIL"));
                profile.setDescripcion(rs.getString("DESCRIPCION"));
                String estado = rs.getBoolean("ACTIVO") ? "ACTIVO" : "INACTIVO";
                profile.setEstado(estado);
                profile.setFecha_alta(rs.getDate("FECHA_ALTA"));
                profile.setFecha_baja(rs.getDate("FECHA_BAJA"));
                profile.setFecha_servidor(rs.getDate("FECHA_SERVIDOR"));
                profile.setId_usuario_modifica(rs.getInt("ID_USUARIO_MODIFICA"));

                lista.add(profile);

            }

            rs.close();
            consulta.close();
            con.close();

            if (!lista.isEmpty()) {
                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("exitoso");

                respuestaPerfil.setProfileList(lista);

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advertencia");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(PerfilModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaPerfil.setResponse(claseRespuesta);
        }
        return respuestaPerfil;
    }

    // Esta función agrega perfiles a la base de datos, recibiendo un objeto de tipo
    // perfil como parámetro
    public ProfileResponse addProfile(Perfil profile) {
        Sesion s = new Sesion();
        ProfileResponse respuestaPerfil = new ProfileResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int ban = 0;
        try {

            con = pool.getConnection("Activa");

            query = "INSERT INTO S_PERFILES (NOMBRE_PERFIL, DESCRIPCION, ACTIVO, FECHA_ALTA,FECHA_BAJA,FECHA_SERVIDOR,ID_USUARIO_MODIFICA) VALUES (?,?,?,GETDATE(),GETDATE(),GETDATE(),?) ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, profile.getNombre_perfil());
            consulta.setString(2, profile.getDescripcion());
            consulta.setBoolean(3, profile.getActivo());
            consulta.setInt(4, s.getSesion("User").getId_usuario());

            ban = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (ban != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro agregado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(PerfilModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaPerfil.setResponse(claseRespuesta);
        }

        return respuestaPerfil;
    }

    // Esta función recibe un parámetro de tipo Perfil para poder eliminar por medio
    // del ID_PERFIL en la base de datos, y retorna un valor de tipo ProfileResponse
    public ProfileResponse deleteProfile(Perfil profile) {
        ProfileResponse respuestaPerfil = new ProfileResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int ban = 0;
        try {

            con = pool.getConnection("Activa");

            query = "DELETE FROM S_PERFILES WHERE ID_PERFIL = ? ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setInt(1, profile.getId_perfil());

            ban = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (ban != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro Eliminado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(PerfilModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaPerfil.setResponse(claseRespuesta);
        }

        return respuestaPerfil;
    }

    // Esta función recibe un objeto de tipo perfil como parámetro y actualiza con base en el ID que se le haya enviado
    public ProfileResponse updateProfile(Perfil profile) {
        ProfileResponse respuestaPerfil = new ProfileResponse();
        Response claseRespuesta = new Response();
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        int ban = 0;
        try {

            con = pool.getConnection("Activa");

            query = "UPDATE S_PERFILES SET NOMBRE_PERFIL = ?, DESCRIPCION = ?, ACTIVO = ?, ID_USUARIO_MODIFICA = ? WHERE ID_PERFIL = ? ";
            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, profile.getNombre_perfil());
            consulta.setString(2, profile.getDescripcion());
            consulta.setBoolean(3, profile.getActivo());
            consulta.setInt(4, profile.getId_usuario_modifica());
            consulta.setInt(5, profile.getId_perfil());
            ban = consulta.executeUpdate();

            consulta.close();
            con.close();

            if (ban != 0) {

                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Registro Actualizado correctamente");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Advise");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(PerfilModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaPerfil.setResponse(claseRespuesta);
        }

        return respuestaPerfil;
    }

}
