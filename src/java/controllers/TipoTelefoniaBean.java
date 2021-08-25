package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.CTelefonia;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.CTipoTelefonoJpaController;
import models.CTelefoniaJpaController;
import entities.CTipoTelefono;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * Author: Raúl Herrera Macías Fecha:
 */
@ManagedBean(name = "TipoTelefoniaBean")
public class TipoTelefoniaBean {

    private List<CTipoTelefono> listaTelefonia;
    private List<CTipoTelefono> filtroTelefonia;
    private List<CTelefonia> comboTelefonia;
    private long idTelefonia;

    private CTipoTelefono telefonia;
    private CTelefonia queryByIdTelefonia;
private long id;
    public TipoTelefoniaBean() {
        telefonia = new CTipoTelefono();
        queryByIdTelefonia = new CTelefonia();
    }

    CTipoTelefonoJpaController modelTipoTelefono = new CTipoTelefonoJpaController();
    CTelefoniaJpaController modelTelefonia = new CTelefoniaJpaController();

    @PostConstruct
    public void Select() {
        try {
            listaTelefonia = modelTipoTelefono.findCTipoTelefonoEntities();
            comboTelefonia = modelTelefonia.findCTelefoniaEntities();
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta exitosa", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertTelefonia() {

        telefonia.setActivo(true);
        telefonia.setFechaServidor(new Date());
        try {
            telefonia.setIdTelefonia(modelTelefonia.findCTelefonia(idTelefonia));
            modelTipoTelefono.create(telefonia);
            Select();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro agregado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.telefonia = null;

    }

    public void updateTelefonia() {
        System.out.println("UPDATE");
        try {
            telefonia.setIdTelefonia(modelTelefonia.findCTelefonia(idTelefonia));
            modelTipoTelefono.edit(telefonia);
            Select();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Actualizado con éxito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTelefonia(CTipoTelefono id) throws NonexistentEntityException {

        try {
            modelTipoTelefono.destroy(id.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoTelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Select();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro eliminado con éxito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//<editor-fold defaultstate="collapsed" desc="get y sets">
    public List<CTipoTelefono> getListaTelefonia() {
        return listaTelefonia;
    }

    public void setListaTelefonia(List<CTipoTelefono> listaTelefonia) {
        this.listaTelefonia = listaTelefonia;
    }

    public List<CTipoTelefono> getFiltroTelefonia() {
        return filtroTelefonia;
    }

    public void setFiltroTelefonia(List<CTipoTelefono> filtroTelefonia) {
        this.filtroTelefonia = filtroTelefonia;
    }

    public CTipoTelefono getTelefonia() {
        return telefonia;
    }

    public void setTelefonia(CTipoTelefono telefonia) {
        this.telefonia = telefonia;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public long getIdTelefonia() {
        return idTelefonia;
    }

    public void setIdTelefonia(long idTelefonia) {
        this.idTelefonia = idTelefonia;
    }

    public CTelefonia getQueryByIdTelefonia() {
        return queryByIdTelefonia;
    }

    public void setQueryByIdTelefonia(CTelefonia queryByIdTelefonia) {
        this.queryByIdTelefonia = queryByIdTelefonia;
    }

    public CTipoTelefonoJpaController getModelTipoTelefono() {
        return modelTipoTelefono;
    }

    public void setModelTipoTelefono(CTipoTelefonoJpaController modelTipoTelefono) {
        this.modelTipoTelefono = modelTipoTelefono;
    }

    public CTelefoniaJpaController getModelTelefonia() {
        return modelTelefonia;
    }

    public void setModelTelefonia(CTelefoniaJpaController modelTelefonia) {
        this.modelTelefonia = modelTelefonia;
    }

    public List<CTelefonia> getComboTelefonia() {
        return comboTelefonia;
    }

    public void setComboTelefonia(List<CTelefonia> comboTelefonia) {
        this.comboTelefonia = comboTelefonia;
    }
//</editor-fold>

    
}
