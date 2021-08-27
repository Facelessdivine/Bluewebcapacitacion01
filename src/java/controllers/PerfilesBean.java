package controllers;
//<editor-fold defaultstate="collapsed" desc="imports">
import entities.SAccesos;
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
import javax.faces.context.FacesContext;
import models.SAccesosJpaController;
import models.SPerfilesAccesosJpaController;
import models.SPerfilesJpaController;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
//</editor-fold>



public class PerfilesBean implements Serializable {

    private List<SPerfiles> listaPerfiles;
    private SPerfiles perfiles;
    private SPerfiles seleccionPerfiles;

    private DualListModel<SAccesos> plAccesos;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosActuales;

    private SPerfilesAccesos perfilesAccesos;

    public PerfilesBean() {
        perfiles = new SPerfiles();
        perfilesAccesos = new SPerfilesAccesos();
    }

    /**
     * Al cargar la pagina carga los datos del picklist
     *
     */
    public void plCargarPerfilesAccesos() {
        try {
            SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
            listaAccesosDisponibles = sAccesosJpa.findSAccesosEntities();
            listaAccesosActuales = new ArrayList<>();
            plAccesos = new DualListModel<SAccesos>(listaAccesosDisponibles, listaAccesosActuales);

        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Al cargar la pagina carga los datos de la tabla
     *
     */
    public void cargarDatosPerfiles() {
        try {
            SPerfilesJpaController sPerfilesJpa = new SPerfilesJpaController();
            listaPerfiles = sPerfilesJpa.findSPerfilesEntities();

        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evento de seleccion en la tabla carga los datos al picklis del registro
     * seleccionado y asigna a los campos los valores del registro seleccionado
     *
     * @param event
     */
    public void onRowSelect(SelectEvent<SPerfiles> event) {
        try {
            perfiles = event.getObject();
            plAccesos = new DualListModel<>();
            listaAccesosDisponibles = new ArrayList<>();
            listaAccesosActuales = new ArrayList<>();

            SAccesosJpaController sAccesosJpa = new SAccesosJpaController();

            listaAccesosActuales = sAccesosJpa.traerAccesosActuales(perfiles);
            listaAccesosDisponibles = sAccesosJpa.traerAccesosDisponibles(perfiles);
            plAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosActuales);
        } catch (Exception ex) {
//            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpia los campos y carga los datos por defecto del picklist
     *
     */
    public void nuevoPerfil() {
        try {
            perfiles = new SPerfiles();
            plCargarPerfilesAccesos();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Guarda o actualiza un perfil ademas de que guarda los elementos del
     * target en la tabla perfilesAccesos
     *
     */
    public void guardarPerfiles() {

        if (perfiles.getNombrePerfil().equals("")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Requiere", "Se requiere el campo Nombre.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plCargarPerfilesAccesos();
        } else {
            SPerfilesJpaController sPerfilesJpa = new SPerfilesJpaController();
            SPerfilesAccesosJpaController sPerfilesAccesosJpa = new SPerfilesAccesosJpaController();
            SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
            Date fechaActual = new Date();
            int usuarioSesion = TraeDatoSesion.traerIdUsuario();

            perfiles.setFechaAlta(fechaActual);
            perfiles.setFechaServidor(fechaActual);
            perfiles.setActivo(true);
            perfiles.setIdUsuarioModifica(usuarioSesion);

            try {
                if (perfiles.getIdPerfil() == null) {
                    sPerfilesJpa.create(perfiles);

                    for (Object accPer : plAccesos.getTarget()) {
                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt(accPer.toString()));
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

                        SPerfilesAccesosPK perfilesAcceso = new SPerfilesAccesosPK();

                        perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                        perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());

                        sPerfilesAccesosJpa.destroy(perfilesAcceso);
                    }

                    for (Object accPer : plAccesos.getTarget()) {
                        SAccesos acceso = sAccesosJpa.findSAccesos(Integer.parseInt(accPer.toString()));
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
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se guardó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se produjo un error");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Elimina un perfil y tambien elimina los datos de perfilAcceso
     *
     */
    public void eliminarPerfil() {
        if (perfiles.getIdPerfil() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Requiere", "Se requiere tener un registro seleccionado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            plCargarPerfilesAccesos();
        } else {
            SPerfilesJpaController sPerfilesJpa = new SPerfilesJpaController();
            SPerfilesAccesosJpaController sPerfilesAccesosJpa = new SPerfilesAccesosJpaController();
            SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
            try {
                List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();
                listaPerfilesAccesos = sAccesosJpa.traerAccesosByPerfil(perfiles);

                for (int i = 0; i < listaPerfilesAccesos.size(); i++) {

                    SPerfilesAccesosPK perfilesAcceso = new SPerfilesAccesosPK();

                    perfilesAcceso.setIdAcceso(listaPerfilesAccesos.get(i).getSPerfilesAccesosPK().getIdAcceso());
                    perfilesAcceso.setIdPerfil(perfiles.getIdPerfil());

                    sPerfilesAccesosJpa.destroy(perfilesAcceso);
                }

                sPerfilesJpa.destroy(perfiles.getIdPerfil());
                nuevoPerfil();
                cargarDatosPerfiles();
                plCargarPerfilesAccesos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", "Se eliminó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", "Se produjo un error");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="getters and stters">
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
    
    public SPerfilesAccesos getPerfilesAccesos() {
        return perfilesAccesos;
    }
    
    public void setPerfilesAccesos(SPerfilesAccesos perfilesAccesos) {
        this.perfilesAccesos = perfilesAccesos;
    }
//</editor-fold>
    
}

    

    