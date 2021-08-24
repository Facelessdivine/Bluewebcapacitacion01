package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.CTelefoniaJpaController;
import entities.CTelefonia;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * Author: Raúl Herrera Macías Fecha:
 */
@ManagedBean(name = "telefoniaBean")
public class TelefoniaBean {

    private List<CTelefonia> lsitaTelefonia;
    private List<CTelefonia> filtroTelefonia;

    private CTelefonia telefonia;

    public TelefoniaBean() {
        telefonia = new CTelefonia();
    }

    @PostConstruct
    public void Select() {
        try {
            CTelefoniaJpaController modelTelefonia = new CTelefoniaJpaController();
            lsitaTelefonia = modelTelefonia.findCTelefoniaEntities();
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta exitosa", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateOrInsertTelefonia() {
        CTelefoniaJpaController modelTelefonia = new CTelefoniaJpaController();
        try {

            if (telefonia.getIdTelefonia() == null) {
                telefonia.setActivo(true);
                telefonia.setFechaServidor(new Date());
                modelTelefonia.create(telefonia);
                Select();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro agregado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {

                modelTelefonia.edit(telefonia);
                Select();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Actualizado con éxito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.telefonia = null;
    }
public void eliminarAcceso(CTelefonia tele) {
        try {
            CTelefoniaJpaController modelTelefonia = new CTelefoniaJpaController();
            modelTelefonia.destroy(tele.getIdTelefonia());
            Select();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CTelefonia> getLsitaTelefonia() {
        return lsitaTelefonia;
    }

    public void setLsitaTelefonia(List<CTelefonia> lsitaTelefonia) {
        this.lsitaTelefonia = lsitaTelefonia;
    }

    public List<CTelefonia> getFiltroTelefonia() {
        return filtroTelefonia;
    }

    public void setFiltroTelefonia(List<CTelefonia> filtroTelefonia) {
        this.filtroTelefonia = filtroTelefonia;
    }

    public CTelefonia getTelefonia() {
        return telefonia;
    }

    public void setTelefonia(CTelefonia telefonia) {
        this.telefonia = telefonia;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
