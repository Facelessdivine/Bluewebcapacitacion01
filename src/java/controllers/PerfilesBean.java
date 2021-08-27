package controllers;
//<editor-fold defaultstate="collapsed" desc="imports">

import entities.SAccesos;
import sesiones.Sesion;
import entities.SPerfiles;
import entities.SPerfilesAccesos;
import entities.SPerfilesAccesosPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.SAccesosJpaController;
import models.SPerfilesAccesosJpaController;
import models.SPerfilesJpaController;
import models.exceptions.IllegalOrphanException;
import models.exceptions.NonexistentEntityException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
//</editor-fold>

/**
 * Author: Raúl Herrera Macías Fecha:
 */
@ManagedBean(name = "PerfilesBean")
public class PerfilesBean implements Serializable {
    //OBJETOS
    private SPerfiles perfiles;
    private SPerfilesAccesos perfilesAccesos;
    private SPerfiles seleccionPerfiles;
    //LISTAS
    private List<SPerfiles> listaPerfiles;
    private DualListModel<SAccesos> plAccesos;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosActuales;
    //SESIÓN
    private Sesion s = new Sesion();

    public PerfilesBean() {
        perfiles = new SPerfiles();
        perfilesAccesos = new SPerfilesAccesos();
    }
    //MODELS
    SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
    SPerfilesJpaController sPerfilesJpa = new SPerfilesJpaController();
    SPerfilesAccesosJpaController sPerfilesAccesosJpa = new SPerfilesAccesosJpaController();
    SPerfilesAccesosPK perfilesAcceso = new SPerfilesAccesosPK();

    public void log(Exception ex) {
        Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
    }

    public void plCargarPerfilesAccesos() {
        try {
            listaAccesosDisponibles = sAccesosJpa.findSAccesosEntities();
            listaAccesosActuales = new ArrayList<>();
            plAccesos = new DualListModel<SAccesos>(listaAccesosDisponibles, listaAccesosActuales);

        } catch (Exception ex) {
            log(ex);
        }

    }

    public void cargarDatosPerfiles() {
        try {
            listaPerfiles = sPerfilesJpa.findSPerfilesEntities();

        } catch (Exception ex) {
            log(ex);
        }
    }

    public void onRowSelect(SelectEvent<SPerfiles> event) {
        try {
            perfiles = event.getObject();
            plAccesos = new DualListModel<>();
            listaAccesosDisponibles = new ArrayList<>();
            listaAccesosActuales = new ArrayList<>();
            listaAccesosActuales = sAccesosJpa.traerAccesosActuales(perfiles);
            listaAccesosDisponibles = sAccesosJpa.traerAccesosDisponibles(perfiles);
            plAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosActuales);
        } catch (Exception ex) {
            log(ex);
        }
    }

    public void nuevoPerfil() {
        try {
            perfiles = new SPerfiles();
            plCargarPerfilesAccesos();
        } catch (Exception ex) {
            log(ex);
        }
    }

    public void guardarPerfiles() {

        if (perfiles.getNombrePerfil().equals("")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe introducir un nombre");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plCargarPerfilesAccesos();
        } else {

            Date fechaActual = new Date();
            int usuarioSesion = s.getSesion("User").getId_usuario();

            perfiles.setFechaAlta(fechaActual);
            perfiles.setFechaServidor(fechaActual);
            perfiles.setActivo(true);
            perfiles.setIdUsuarioModifica(usuarioSesion);

            try {
                if (perfiles.getIdPerfil() == null) {
                    sPerfilesJpa.create(perfiles);

                    for (Object acc : plAccesos.getTarget()) {
                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt(acc.toString()));
                        perfilesAccesos.setSPerfiles(perfiles);
                        perfilesAccesos.setSAccesos(acceso);
                        perfilesAccesos.setFechaServidor(fechaActual);
                        perfilesAccesos.setIdUsuarioModifica(usuarioSesion);
                        sPerfilesAccesosJpa.create(perfilesAccesos);
                    }

                } else {

                    sPerfilesJpa.edit(perfiles);

                    List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();
                    listaPerfilesAccesos = sAccesosJpa.traerAccesosByPerfil(perfiles);

                    for (int i = 0; i < listaPerfilesAccesos.size(); i++) {
                        perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                        perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());

                        sPerfilesAccesosJpa.destroy(perfilesAcceso);
                    }

                    for (Object acc : plAccesos.getTarget()) {
                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt(acc.toString()));
                        perfilesAccesos.setSPerfiles(perfiles);
                        perfilesAccesos.setSAccesos(acceso);
                        perfilesAccesos.setFechaServidor(fechaActual);
                        perfilesAccesos.setIdUsuarioModifica(usuarioSesion);
                        sPerfilesAccesosJpa.create(perfilesAccesos);
                    }

                }
                nuevoPerfil();
                cargarDatosPerfiles();
                plCargarPerfilesAccesos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Guardado exitoso");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Se produjo un error");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                log(ex);
            }
        }

    }

    public void eliminarPerfil() {
        if (perfiles.getIdPerfil() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Requiere", "Se requiere tener un registro seleccionado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plCargarPerfilesAccesos();
        } else {

            try {
                List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();
                listaPerfilesAccesos = sAccesosJpa.traerAccesosByPerfil(perfiles);

                for (int i = 0; i < listaPerfilesAccesos.size(); i++) {
                    perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                    perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());
                    sPerfilesAccesosJpa.destroy(perfilesAcceso);
                }
                sPerfilesJpa.destroy(perfiles.getIdPerfil());
                nuevoPerfil();
                cargarDatosPerfiles();
                plCargarPerfilesAccesos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Registro(s) eliminados correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Se produjo un error");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                log(ex);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y sets">
    public List<SPerfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public SPerfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(SPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    public SPerfiles getSeleccionPerfiles() {
        return seleccionPerfiles;
    }

    public void setSeleccionPerfiles(SPerfiles seleccionPerfiles) {
        this.seleccionPerfiles = seleccionPerfiles;
    }

    public DualListModel<SAccesos> getPlAccesos() {
        return plAccesos;
    }

    public void setPlAccesos(DualListModel<SAccesos> plAccesos) {
        this.plAccesos = plAccesos;
    }

    public List<SAccesos> getListaAccesosDisponibles() {
        return listaAccesosDisponibles;
    }

    public void setListaAccesosDisponibles(List<SAccesos> listaAccesosDisponibles) {
        this.listaAccesosDisponibles = listaAccesosDisponibles;
    }

    public List<SAccesos> getListaAccesosActuales() {
        return listaAccesosActuales;
    }

    public void setListaAccesosActuales(List<SAccesos> listaAccesosActuales) {
        this.listaAccesosActuales = listaAccesosActuales;
    }

    public Sesion getS() {
        return s;
    }

    public void setS(Sesion s) {
        this.s = s;
    }

    public SPerfilesAccesos getPerfilesAccesos() {
        return perfilesAccesos;
    }

    public void setPerfilesAccesos(SPerfilesAccesos perfilesAccesos) {
        this.perfilesAccesos = perfilesAccesos;
    }

    public SAccesosJpaController getsAccesosJpa() {
        return sAccesosJpa;
    }

    public void setsAccesosJpa(SAccesosJpaController sAccesosJpa) {
        this.sAccesosJpa = sAccesosJpa;
    }

    public SPerfilesJpaController getsPerfilesJpa() {
        return sPerfilesJpa;
    }

    public void setsPerfilesJpa(SPerfilesJpaController sPerfilesJpa) {
        this.sPerfilesJpa = sPerfilesJpa;
    }

    public SPerfilesAccesosJpaController getsPerfilesAccesosJpa() {
        return sPerfilesAccesosJpa;
    }

    public void setsPerfilesAccesosJpa(SPerfilesAccesosJpaController sPerfilesAccesosJpa) {
        this.sPerfilesAccesosJpa = sPerfilesAccesosJpa;
    }

    public SPerfilesAccesosPK getPerfilesAcceso() {
        return perfilesAcceso;
    }

    public void setPerfilesAcceso(SPerfilesAccesosPK perfilesAcceso) {
        this.perfilesAcceso = perfilesAcceso;
    }
//</editor-fold>

}
