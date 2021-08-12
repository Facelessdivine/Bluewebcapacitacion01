package controllers;

import clases.Acceso;
import java.io.Serializable;
import responses.AccessResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.AccesoModel;

@ManagedBean(name = "acceso")
public class AccesoBean implements Serializable {

    private List<Acceso> listaAcceso = new ArrayList<>();
    private List<Acceso> filtroAcceso;

    private AccesoModel accesomodel = null;
    private Acceso access;

    public AccesoBean() {
        access = new Acceso();
    }
    
    

    @PostConstruct
    public void init() {

        accesomodel = new AccesoModel();

        AccessResponse respuesta = accesomodel.conectarLista();
        if (respuesta.getRespuesta().getId() == 0) {
            //esto no se usa pero por el momento lo voya dejar asi xd
//            String mensaje = respuesta.getRespuesta().getMensaje();

            listaAcceso = respuesta.getListaAcceso();

        } else if (respuesta.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (respuesta.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }

    }

    public List<Acceso> getListaAcceso() {
        return listaAcceso;
    }

    public List<Acceso> getFiltroAcceso() {
        return filtroAcceso;
    }

    public void setListaAcceso(List<Acceso> listaAcceso) {
        this.listaAcceso = listaAcceso;
    }

    public void setFiltroAcceso(List<Acceso> filtroAcceso) {
        this.filtroAcceso = filtroAcceso;
    }

    public Acceso getAccess() {
        return access;
    }

    public void setAccess(Acceso access) {
        this.access = access;
    }

    
    public void addAccess() {
        accesomodel = new AccesoModel();
        AccessResponse insert = accesomodel.addAccess(access);
        if (insert.getRespuesta().getId() == 0) {
            //esto no se usa pero por el momento lo voya dejar asi xd
//            String mensaje = insert.getRespuesta().getMensaje();

            listaAcceso = insert.getListaAcceso();

        } else if (insert.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (insert.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }
    }
    

}
