/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.HActivacion;
import models.HActivacionJpaController;
import entities.SUsuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.SUsuariosJpaController;

/**
 *
 * @author Raúl Herrera Macías
 */
@ViewScoped
@ManagedBean(name = "rActivacionBean")
public class ReporteActivacionesBean {

    private SUsuarios user = new SUsuarios();

//    Models
    SUsuariosJpaController usuariosModel = new SUsuariosJpaController();
    HActivacionJpaController controllerHactivacion = new HActivacionJpaController();

    private List<HActivacion> listaHactivacion;

    private List<HActivacion> filtroHactivacion;
    private List<SUsuarios> comboUsuarios;
    private List<Date> range = new ArrayList();

    public ReporteActivacionesBean() {
        comboUsuarios = usuariosModel.findSUsuariosEntities();

    }

    public void filterUsersActivation() {
        FacesMessage msg = null;
        try {
            listaHactivacion = controllerHactivacion.getActivationsById(user);
            msg = (!listaHactivacion.isEmpty()) ? new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Filtrado exitoso")
                    : new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se encontraron Activaciones para el usuario: " + user.getNombreUsuario());
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Se produjo un error");
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void newFilter() {
        range = new ArrayList<>();
        listaHactivacion = new ArrayList<>();
        user = new SUsuarios();
    }

    public SUsuarios getUser() {
        return user;
    }

    public void setUser(SUsuarios user) {
        this.user = user;
    }

    public SUsuariosJpaController getUsuariosModel() {
        return usuariosModel;
    }

    public void setUsuariosModel(SUsuariosJpaController usuariosModel) {
        this.usuariosModel = usuariosModel;
    }

    public HActivacionJpaController getHactivacion() {
        return controllerHactivacion;
    }

    public void setHactivacion(HActivacionJpaController controllerHactivacion) {
        this.controllerHactivacion = controllerHactivacion;
    }

    public List<HActivacion> getListaHactivacion() {
        return listaHactivacion;
    }

    public void setListaHactivacion(List<HActivacion> listaHactivacion) {
        this.listaHactivacion = listaHactivacion;
    }

    public List<SUsuarios> getComboUsuarios() {
        return comboUsuarios;
    }

    public void setComboUsuarios(List<SUsuarios> comboUsuarios) {
        this.comboUsuarios = comboUsuarios;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }

    /**
     * @return the filtroHactivacion
     */
    public List<HActivacion> getFiltroHactivacion() {
        return filtroHactivacion;
    }

    /**
     * @param filtroHactivacion the filtroHactivacion to set
     */
    public void setFiltroHactivacion(List<HActivacion> filtroHactivacion) {
        this.filtroHactivacion = filtroHactivacion;
    }

}
