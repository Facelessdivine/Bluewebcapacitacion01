package controllers;

import clases.Acceso;
import responses.AccessResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.AccesoModel;

@ManagedBean(name = "acceso")//con el nombre bean
public class AccesoBean  {

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

        AccessResponse select = accesomodel.conectarLista();
        if (select.getRespuesta().getId() == 0) {
            //esto no se usa pero por el momento lo voya dejar asi xd
//            String mensaje = select.getRespuesta().getMensaje();

            listaAcceso = select.getListaAcceso();

        } else if (select.getRespuesta().getId() > 0) {
            System.out.println("Warning");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getRespuesta().getMensaje()));

        } else if (select.getRespuesta().getId() < 0) {
            System.out.println("Error");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getRespuesta().getMensaje()));
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
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
    
    public void removeAccess(Acceso access){
//        System.out.println("funcion remove");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hola ando en el remove"));
            accesomodel = new AccesoModel();
        AccessResponse remove = accesomodel.deleteAccess(access);    
        if (remove.getRespuesta().getId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(remove.getRespuesta().getMensaje()));

                init();
                this.access = null;
            
        } else if (remove.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (remove.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }
    }
    
    
public void save(Acceso access){
    if(access.getId_acceso() != 0){
        AccessResponse update = accesomodel.updateAccess(access);
         if (update.getRespuesta().getId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(update.getRespuesta().getMensaje()));

                init();
                this.access = null;
            
        } else if (update.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (update.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }
    }
    else{
        
        AccessResponse insert = accesomodel.addAccess(access);
         if (insert.getRespuesta().getId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(insert.getRespuesta().getMensaje()));

                init();
                this.access = null;
            
        } else if (insert.getRespuesta().getId() > 0) {
            System.out.println("Warning");
        } else if (insert.getRespuesta().getId() < 0) {
            System.out.println("Error");
        }
    }
    
}
}
