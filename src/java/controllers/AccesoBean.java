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

@ManagedBean(name = "accesoBean")
public class AccesoBean  {

    private List<Acceso> listaAcceso = new ArrayList<>();
    private List<Acceso> filtroAcceso;

    private AccesoModel accesomodel = null;
    private Acceso access;

    public AccesoBean() {
        access = new Acceso();
    }
    
    

    @PostConstruct
//    Función a la que va a entrar al inicio in
    public void init() {

        accesomodel = new AccesoModel();

        AccessResponse select = accesomodel.conectarLista();
        switch (select.getRespuesta().getId()) {
            case 0:
                listaAcceso = select.getListaAcceso();
                break;
            case 1:
                System.out.println("Warning");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getRespuesta().getMensaje()));
                break;
            case -1:
                System.out.println("Error");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getRespuesta().getMensaje()));
                break;
            default:
                break;
        }

    }
    


    
    
    public void removeAccess(Acceso access){
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
    
/**
     * 
     * Esta función obtiene el objeto de Acceso que es llenado en el Diálogo en la
     * vista, para poder determinar si se debe insertar o actualizar, es decir, si
     * el objeto ya tiene un ID_ACCESO  definido, significa que ya existe en la base
     * de datos y por lo tanto solo hace falta actualizarlo, de otro modo hay que
     * insertarlo en la base de datos como un nuevo Acceso 
     * 
     */
public void updateOrInsertAccess(){
    
    if(access.getId_acceso() != 0){
        
        AccessResponse update = accesomodel.updateAccess(access);
        switch (update.getRespuesta().getId()) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(update.getRespuesta().getMensaje()));
                init();
                this.access = null;
                break;
            case 1:
                System.out.println("Warning");
                break;
            case -1:
                System.out.println("Error");
                break;
            default:
                break;
        }
    }
    else{
        
        AccessResponse insert = accesomodel.addAccess(access);
        switch (insert.getRespuesta().getId()) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(insert.getRespuesta().getMensaje()));
                init();
                this.access = null;
                break;
            case 1: 
                System.out.println("Warning");
                break;
            case -1:
                System.out.println("Error");
                break;
            default:
                break;
        }
    }
    
}
//<editor-fold defaultstate="collapsed" desc="getters and setters ">

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
//</editor-fold>
    
//    Función para poder mostrar mensajes por pantalla como Feedback's para el usuario
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}