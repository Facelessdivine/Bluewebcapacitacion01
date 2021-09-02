/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
//<editor-fold defaultstate="collapsed" desc="imports">

import models.SUsuariosJpaController;
import entities.SUsuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
//</editor-fold>
/**
 *
 * @author Raúl Herrera Macías
 */
@FacesConverter (value = "userConverter")
public class userConverter implements Converter {
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        SUsuariosJpaController jpaController = new SUsuariosJpaController();
        return jpaController.findSUsuarios(Integer.valueOf(value));
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try{
            return ((SUsuarios) value).getIdUsuario().toString();
        }catch(Exception e){
            return null;
        }
    }
    
}