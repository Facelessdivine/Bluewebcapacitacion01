/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.Cliente;
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
import responses.ClientResponse;
import responses.Response;

/**
 *
 * @author Blueweb
 */
public class ClienteModel {
    public ClientResponse connectClient() {
        ClientResponse respuestaCliente = new ClientResponse();
        Response claseRespuesta = new Response();
        String query = "";
        List<Cliente> lista = new ArrayList();
        PoolDB pool = new PoolDB();
        Connection con = null;
        try {
            con = pool.getConnection("Activa");
            query = "SELECT * FROM C_CLIENTES";
            PreparedStatement consulta = con.prepareStatement(query); 
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setId(rs.getInt("ID_CLIENTE"));
                client.setNumero(rs.getString("NUM_CLIENTE"));
                client.setNombre(rs.getString("NOMBRE_CLIENTE"));
                lista.add(client);
            }
            rs.close();
            consulta.close();
            con.close();
            if (!lista.isEmpty()) {
                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Successfull");
                respuestaCliente.setClientList(lista);
            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Warning");
            }
        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(CiudadModel.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            respuestaCliente.setResponse(claseRespuesta);
        }
        return respuestaCliente;
    }

}
