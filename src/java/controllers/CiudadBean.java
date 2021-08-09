package controllers;

import clases.Ciudad;
import java.io.Serializable;
import responses.CityResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.CiudadModel;

@ManagedBean(name = "ciudad")
public class CiudadBean implements Serializable {

    private List<Ciudad> listaCiudad = new ArrayList<>();
    private List<Ciudad> filtroCiudad;

    @PostConstruct

    public void init() {

        CiudadModel ciudadmodel = new CiudadModel();
        

        CityResponse respuesta = ciudadmodel.conectarLista();
        if (respuesta.getRespuesta().getId() == 0) {
            //esto no se usa pero por el momento lo voya dejar asi xd
            String mensaje = respuesta.getRespuesta().getMensaje();

            listaCiudad = respuesta.getListaCiudad();

        } else if (respuesta.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (respuesta.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }

    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public List<Ciudad> getFiltroCiudad() {
        return filtroCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public void setFiltroCiudad(List<Ciudad> filtroCiudad) {
        this.filtroCiudad = filtroCiudad;
    }

}
