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
@ManagedBean(name = "HactivacionBean")
@ViewScoped
public class HactivacionBean {

    private SUsuarios user = new SUsuarios();
//    Models
    SUsuariosJpaController usuariosModel = new SUsuariosJpaController();
    HActivacionJpaController hactivacion = new HActivacionJpaController();

    private List<HActivacion> listaHactivacion;
    private List<HActivacion> filtroHactivacion;
    private List<SUsuarios> comboUsuarios;
    private List<Date> range = new ArrayList();
    private int idUsuario;

    public HactivacionBean() {
        comboUsuarios = usuariosModel.findSUsuariosEntities();

    }

    public void filterUsersActivation() {
        FacesMessage msg = null;
        try {

            user = (idUsuario != 0) ? usuariosModel.findSUsuarios(idUsuario) : null;
            listaHactivacion = hactivacion.getActivationsByDate(user, range.get(0), range.get(1));

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Filtrado exitoso");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Se produjo un error");
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void newFilter() {
        range = new ArrayList<>();
        listaHactivacion = new ArrayList<>();
        idUsuario = 0;
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
        return hactivacion;
    }

    public void setHactivacion(HActivacionJpaController hactivacion) {
        this.hactivacion = hactivacion;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
