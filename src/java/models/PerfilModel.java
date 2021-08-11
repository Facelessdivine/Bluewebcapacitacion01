/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.Perfil;
import data.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import responses.ProfileResponse;
import responses.Response;

/**
 *
 * @author Blueweb
 */
public class PerfilModel {
    public ProfileResponse connectProfile() {
        ProfileResponse respuestaPerfil = new ProfileResponse();
        Response claseRespuesta = new Response();
        String query = "";
        List<Perfil> lista = new ArrayList();
        PoolDB pool = new PoolDB();
        Connection con = null;
        try {
            con = pool.getConnection("Activa");
            query = "SELECT * FROM S_PERFILES";
            PreparedStatement consulta = con.prepareStatement(query); 
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Perfil profile = new Perfil();
                profile.setId_perfil(rs.getInt("ID_PERFIL"));
                profile.setNombre_perfil(rs.getString("NOMBRE_PERFIL"));
                profile.setDescripcion(rs.getString("DESCRIPCION"));
                String activo = rs.getBoolean("ACTIVO") ? "ACTIVO" : "INACTIVO";
                profile.setActivo(activo);
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
                claseRespuesta.setMensaje("Successfull");
                respuestaPerfil.setProfileList(lista);
            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Warning");
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
