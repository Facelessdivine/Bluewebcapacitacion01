/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import data.PoolDB;
import responses.CityResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import responses.Response;
import clases.Ciudad;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blueweb
 */
public class CiudadModel {

    public CityResponse conectarLista() {
        //instanciar clase de respuesta ciudad
        CityResponse respuestaCiudad = new CityResponse();
        Response claseRespuesta = new Response();
        String query = "";//declaramos el query
        List<Ciudad> lista = new ArrayList();//creas la lista

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 

        try {
            con = pool.getConnection("Activa");//aqui se conecta 

            query = "SELECT * FROM C_CIUDAD";//query para hacer la consulta
            PreparedStatement consulta = con.prepareStatement(query); // hacer la conexion mandando llamar al query de arriba/preparedstatment/llamar procedimientos almacenados  
            ResultSet rs = consulta.executeQuery();//el rs de tipo resulset se va a usar para traer los datos

            while (rs.next()) {
                Ciudad ciudad = new Ciudad();//declaramos el objeto ciudad
                ciudad.setId(rs.getInt("ID_CIUDAD"));
                ciudad.setDescripcion(rs.getString("DESCRIPCION"));
                ciudad.setCodigo(rs.getString("CODIGO"));
                lista.add(ciudad);

            }

            rs.close();//cerramos todas las conexiones
            consulta.close();
            con.close();

            if (!lista.isEmpty()) {
                claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("exitoso");

                respuestaCiudad.setListaCiudad(lista);

            } else {
                claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("Advertencia");
            }
            //return lista;//regresamos la lista, para esto el metodo no puede ser void 

        } catch (SQLException | NamingException e) {
            claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(CiudadModel.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            respuestaCiudad.setRespuesta(claseRespuesta);
        }
        return respuestaCiudad;//en caso de no poder retornar la lista, debe retornar algo, por eso el return null
    }
}
