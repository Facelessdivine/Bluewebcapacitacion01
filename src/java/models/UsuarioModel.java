/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import data.PoolDB;
import responses.UserResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import responses.Response;
import clases.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.HexDigest;

/**
 *
 * @author Blueweb
 */
public class UsuarioModel {

    public static UserResponse Login(Usuario user) {

        UserResponse respuestaUsuario = new UserResponse();
        Response claseRespuesta = new Response();
        String query = "";
        boolean ban = false;
        PoolDB pool = new PoolDB();
        Connection con = null;
        String passEncriptado;

        Usuario usuario = null;
        try {
            con = pool.getConnection("Activa");

            query = ("SELECT id_usuario,usuario,nombre_usuario, password FROM s_usuarios WHERE usuario = ? and password = ?");

            passEncriptado = HexDigest.hexDigest(user.getPassword());

            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, user.getUsuario());
            consulta.setString(2, passEncriptado);
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_USUARIO"));
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                ban = true;
            }

            rs.close();
            consulta.close();
            con.close();

            if (ban) {
                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("exitoso");

            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Wrong username or password");
            }

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaUsuario.setResponse(claseRespuesta);
        }
        respuestaUsuario.setUser(usuario);
        return respuestaUsuario;
    }
}
