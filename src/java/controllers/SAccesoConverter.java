/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.SAccesosJpaController;
import entities.SAccesos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;



import javax.faces.convert.Converter;


/**
 *
 * @author Blueweb
 */
@FacesConverter (value = "sAccesoConverter")
public class SAccesoConverter implements Converter {
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert the unique String representation of Foo to the actual Foo object.

        if(value == null || value.trim().equals("")){
            return null;
        }else if (value.trim().equals("NA")){
            return null;
        }

        SAccesosJpaController jpaController = new SAccesosJpaController();
        return jpaController.findSAccesos(Integer.valueOf(value));
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try{
            return ((SAccesos) value).getIdAcceso().toString();
        }catch(Exception e){
            return null;
        }
    }
    
}