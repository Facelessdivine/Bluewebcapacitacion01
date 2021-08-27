package controllers;
//<editor-fold defaultstate="collapsed" desc="imports">

import controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.SAccesosJpaController;
import entities.SAccesos;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.exceptions.IllegalOrphanException;
//</editor-fold>

/**
 * Author: Raúl Herrera Macías Fecha:
 */
@ManagedBean(name = "AccesosBean")
public class AccesosBean {

    private List<SAccesos> listaAccesos;
    private List<SAccesos> filtroAccesos;

    private long idAccesos;

    private SAccesos accesos;

    SAccesosJpaController modelAccesos = new SAccesosJpaController();

    public AccesosBean() {
        accesos = new SAccesos();
        accesos.setSPerfilesAccesosCollection(new ArrayList<>());
    }

    @PostConstruct
    public void Select() {
        try {
            listaAccesos = modelAccesos.findSAccesosEntities();

//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta exitosa", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertAccesos() {

        accesos.setActivo(true);
        accesos.setFechaServidor(new Date());
        try {
//            accesos.setIdTelefonia(modelAccesos.findCTelefonia(idAccesos));
            modelAccesos.create(accesos);
            Select();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro agregado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.accesos = null;

    }


    public void updateAccesos() {
FacesMessage msg = null;
        try {

//            accesos.setIdTelefonia(modelAccesos.findCTelefonia(selectedValue.getIdTelefonia()));
                accesos.setFechaServidor(new Date());
            modelAccesos.edit(accesos);
            Select();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Actualizado con éxito");
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Falló la actualización del registro");
        }
            FacesContext.getCurrentInstance().addMessage(null, msg);

        this.accesos = null;
    }

    public void eliminarAccesos(SAccesos id) throws NonexistentEntityException {

        try {
            modelAccesos.destroy(id.getIdAcceso());
        } catch (IllegalOrphanException | models.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Select();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro eliminado con éxito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//<editor-fold defaultstate="collapsed" desc="get y sets">


    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



    public List<SAccesos> getListaAccesos() {
        return listaAccesos;
    }

    public void setListaAccesos(List<SAccesos> listaAccesos) {
        this.listaAccesos = listaAccesos;
    }

    public List<SAccesos> getFiltroAccesos() {
        return filtroAccesos;
    }

    public void setFiltroAccesos(List<SAccesos> filtroAccesos) {
        this.filtroAccesos = filtroAccesos;
    }

    public long getIdAccesos() {
        return idAccesos;
    }

    public void setIdAccesos(long idAccesos) {
        this.idAccesos = idAccesos;
    }

    public SAccesos getAccesos() {
        return accesos;
    }

    public void setAccesos(SAccesos accesos) {
        this.accesos = accesos;
    }

    public SAccesosJpaController getModelAccesos() {
        return modelAccesos;
    }

    public void setModelAccesos(SAccesosJpaController modelAccesos) {
        this.modelAccesos = modelAccesos;
    }
//</editor-fold>
}
