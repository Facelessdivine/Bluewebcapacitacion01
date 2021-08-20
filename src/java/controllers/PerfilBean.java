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

@ManagedBean(name = "perfilBean")
public class PerfilBean  {

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

        ProfileResponse select = profileModel.connectProfile();
        if (select.getResponse().getId() == 0) {
            listaPerfil = select.getProfileList();

        } else if (select.getResponse().getId() > 0) {
            System.out.println("Warning");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getResponse().getMensaje()));

        } else if (select.getResponse().getId() < 0) {
            System.out.println("Error");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(select.getResponse().getMensaje()));
        }

    }
    


    
    
    public void removeProfile(Perfil profile){
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
     * 
     * 
     * este guarda y actualiza los acceos
     */
    
public void save(){
    
    if(profile.getId_perfil()!= 0){
        
        ProfileResponse update = profileModel.updateProfile(profile);
        switch (update.getResponse().getId()) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(update.getResponse().getMensaje()));
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
    }
    else{
        
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
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}