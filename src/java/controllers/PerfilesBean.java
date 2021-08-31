package controllers;
//<editor-fold defaultstate="collapsed" desc="imports">

import entities.SAccesos;
import sesiones.Sesion;
import entities.SPerfiles;
import entities.SPerfilesAccesos;
import entities.SPerfilesAccesosPK;
import entities.SUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import entities.HActivacion;
import javax.faces.context.FacesContext;
import models.SUsuariosJpaController;
import models.HActivacionJpaController;
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
    private List<SPerfiles> filtroPerfiles;
    private DualListModel<SAccesos> plAccesos;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosActuales;
    //SESIÓN
    private Sesion s = new Sesion();
    //MODELS
    SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
    SPerfilesJpaController sPerfilesJpa = new SPerfilesJpaController();
    SPerfilesAccesosJpaController sPerfilesAccesosJpa = new SPerfilesAccesosJpaController();
    SPerfilesAccesosPK perfilesAcceso = new SPerfilesAccesosPK();
    SUsuariosJpaController usuariosModel = new SUsuariosJpaController();
//    nuevo
    private SUsuarios user = new SUsuarios();
    HActivacionJpaController hactivacion = new HActivacionJpaController();
    private HActivacion hactivacionList;
    public PerfilesBean() {
        perfiles = new SPerfiles();
        perfilesAccesos = new SPerfilesAccesos();
        user = usuariosModel.findSUsuarios(2);
        
        Date fechainicial = new Date(116, 0, 15);
        hactivacion.getActivationsByDate(user, fechainicial, new Date());
//        System.out.println(hactivacionList);
    }

    @PostConstruct
    public void loadPickListData() {
        try {
            listaAccesosDisponibles = sAccesosJpa.findSAccesosEntities();
            listaAccesosActuales = new ArrayList<>();
            plAccesos = new DualListModel<SAccesos>(listaAccesosDisponibles, listaAccesosActuales);
            loadProfileData();

        } catch (Exception ex) {
            log(ex);
        }
    }

    public void loadProfileData() {
        listaPerfiles = sPerfilesJpa.findSPerfilesEntities();
    }

    public void log(Exception ex) {
        Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
    }

    public void onRowSelect(SelectEvent<SPerfiles> event) {
        try {
            perfiles = event.getObject();
            plAccesos = new DualListModel<>();
            listaAccesosDisponibles = new ArrayList<>();
            listaAccesosActuales = new ArrayList<>();
            listaAccesosActuales = sAccesosJpa.getAsignedAccess(perfiles);
            listaAccesosDisponibles = sAccesosJpa.getUnasignedAccess(perfiles);
            plAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosActuales);
        } catch (Exception ex) {
            log(ex);
        }
    }

    public void newProfile() {
        try {
            perfiles = new SPerfiles();
            loadPickListData();
        } catch (Exception ex) {
            log(ex);
        }
    }

    public void save() {

        if (perfiles.getNombrePerfil().equals("")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe introducir un nombre");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            loadPickListData();
        } else {

            Date fechaActual = new Date();
            int usuarioSesion = s.getSesion("User").getId_usuario();
//            Datos estáticos que se insertan directamente al objeto de la base de datos
            perfiles.setFechaAlta(fechaActual);
            perfiles.setFechaServidor(fechaActual);
            perfiles.setActivo(true);
            perfiles.setIdUsuarioModifica(usuarioSesion);

            try {
                List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();
                if (perfiles.getIdPerfil() == null) {

                    sPerfilesJpa.create(perfiles);
                    for (SAccesos acc : plAccesos.getTarget()) {
//                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt((String) acc));
                        //Datos estáticos que se insertan directamente al objeto de la base de datos
                        SPerfilesAccesosPK perfilaccesoPK = new SPerfilesAccesosPK();
                        perfilesAccesos.setSPerfiles(perfiles);
                        perfilesAccesos.setSAccesos(acc);
                        perfilesAccesos.setFechaServidor(fechaActual);
                        perfilesAccesos.setIdUsuarioModifica(usuarioSesion);
                        perfilaccesoPK.setIdAcceso(acc.getIdAcceso());
                        perfilaccesoPK.setIdPerfil(perfiles.getIdPerfil());
                        listaPerfilesAccesos.add(perfilesAccesos);
//                        sPerfilesAccesosJpa.create(perfilesAccesos);
                    }
                    perfiles.setSPerfilesAccesosCollection(listaPerfilesAccesos);
                } else {

                    listaPerfilesAccesos = sAccesosJpa.getAccessByProfile(perfiles);
                    perfiles.setSPerfilesAccesosCollection(listaPerfilesAccesos);
                    sPerfilesJpa.edit(perfiles);
                    for (int i = 0; i < listaPerfilesAccesos.size(); i++) {
                        perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                        perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());

                        sPerfilesAccesosJpa.destroy(perfilesAcceso);
                    }

                    for (Object acc : plAccesos.getTarget()) {
//                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt(acc.toString()));
                        //Datos estáticos que se insertan directamente al objeto de la base de datos
                        perfilesAccesos.setSPerfiles(perfiles);
                        perfilesAccesos.setSAccesos((SAccesos) acc);
                        perfilesAccesos.setFechaServidor(fechaActual);
                        perfilesAccesos.setIdUsuarioModifica(usuarioSesion);
                        sPerfilesAccesosJpa.create(perfilesAccesos);
                    }

                }
                newProfile();
                loadProfileData();
                loadPickListData();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Guardado exitoso");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Se produjo un error");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                log(ex);
            }
        }

    }

    public void removeProfile() {
        if (perfiles.getIdPerfil() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No ha seleccionado ningún registro");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            loadPickListData();
        } else {

            try {
                List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();
                listaPerfilesAccesos = sAccesosJpa.getAccessByProfile(perfiles);

                for (int i = 0; i < listaPerfilesAccesos.size(); i++) {
                    perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                    perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());
                    sPerfilesAccesosJpa.destroy(perfilesAcceso);
                }
                sPerfilesJpa.destroy(perfiles.getIdPerfil());
                newProfile();
                loadProfileData();
                loadPickListData();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Registro(s) eliminados correctamente");
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

    public List<SPerfiles> getFiltroPerfiles() {
        return filtroPerfiles;
    }

    public void setFiltroPerfiles(List<SPerfiles> filtroPerfiles) {
        this.filtroPerfiles = filtroPerfiles;
    }

//</editor-fold>
}
