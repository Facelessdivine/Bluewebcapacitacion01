package controllers;

import clases.Perfil;
import responses.ProfileResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.PerfilModel;

/**
 * Author: Raúl Herrera Macías Fecha:
 */
@ManagedBean(name = "perfilBean")
public class PerfilBean {

    private List<Perfil> listaPerfil = new ArrayList<>();
    private List<Perfil> filtroPerfil;

    private PerfilModel profileModel = null;
    private Perfil profile;

    public PerfilBean() {
        profile = new Perfil();
    }

    @PostConstruct
    public void init() {

        profileModel = new PerfilModel();
        ProfileResponse select = profileModel.queryProfile();
        switch (select.getResponse().getId()) {
            case 0:
                listaPerfil = select.getProfileList();
                break;
            case 1:
                System.out.println("Warning");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getResponse().getMensaje()));
                break;
            case -1:
                System.out.println("Error");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getResponse().getMensaje()));
                break;
            default:
                break;
        }

    }

    /**
     *
     * @param profile
     */
    public void removeProfile(Perfil profile) {
        profileModel = new PerfilModel();
        ProfileResponse remove = profileModel.deleteProfile(profile);
        if (remove.getResponse().getId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(remove.getResponse().getMensaje()));

            init();
            this.profile = null;

        } else if (remove.getResponse().getId() > 0) {
            System.out.println("Warning");
        } else if (remove.getResponse().getId() < 0) {
            System.out.println("Error");
        }
    }

    /**
     * 
     * Esta función obtiene el objeto de perfil que es llenado en el Diálogo en la
     * vista, para poder determinar si se debe insertar o actualizar, es decir, si
     * el objeto ya tiene un ID_PERFIL definido, significa que ya existe en la base
     * de datos y por lo tanto solo hace falta actualizarlo, de otro modo hay que
     * insertarlo en la base de datos como un nuevo perfíl
     * 
     */

    public void updateOrInsertProfile() {

        if (profile.getId_perfil() != 0) {

            ProfileResponse update = profileModel.updateProfile(profile);
            switch (update.getResponse().getId()) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(update.getResponse().getMensaje()));
                    init();
                    this.profile = null;
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
        } else {

            ProfileResponse insert = profileModel.addProfile(profile);
            if (insert.getResponse().getId() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(insert.getResponse().getMensaje()));

                init();
                this.profile = null;

            } else if (insert.getResponse().getId() > 0) {
                System.out.println("Warning");
            } else if (insert.getResponse().getId() < 0) {
                System.out.println("Error");
            }
        }

    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List<Perfil> getProfileList() {
        return listaPerfil;
    }
    
    public List<Perfil> getFiltroPerfil() {
        return filtroPerfil;
    }
    
    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }
    
    public void setFiltroPerfil(List<Perfil> filtroPerfil) {
        this.filtroPerfil = filtroPerfil;
    }
    
    public Perfil getProfile() {
        return profile;
    }
    
    public void setProfile(Perfil profile) {
        this.profile = profile;
    }
//</editor-fold>
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}