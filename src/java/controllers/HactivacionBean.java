/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entities.HActivacion;
import java.util.List;
import models.HActivacionJpaController;
import sesiones.Sesion;
import entities.SUsuarios;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Raúl Herrera Macías
 */

@ManagedBean(name = "HactivacionBean")
public class HactivacionBean {
        HActivacionJpaController hactivacion = new HActivacionJpaController();
        Sesion s = new Sesion();
        private List<HActivacion> listaPerfiles;
        private SUsuarios user;
    public HactivacionBean() {
        
        user.setIdUsuario(s.getSesion("User").getId_usuario());
//        hactivacion.getActivationsByDate(user);
    }
        
    
    
}
