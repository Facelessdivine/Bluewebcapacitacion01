package controllers;

import clases.Perfil;
import java.io.Serializable;
import responses.ProfileResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.PerfilModel;

@ManagedBean(name = "perfil")
public class PerfilBean implements Serializable {

    private List<Perfil> profileList = new ArrayList<>(); 
    private List<Perfil> profileFilter;

    @PostConstruct
    
    public void init() {
        PerfilModel perfilmodelo = new PerfilModel(); 
        ProfileResponse respuestaPerfil = perfilmodelo.connectProfile(); 
        
        switch (respuestaPerfil.getResponse().getId()) {
            case 0:
                profileList = respuestaPerfil.getProfileList();
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

    public List<Perfil> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Perfil> profileList) {
        this.profileList = profileList;
    }

    public List<Perfil> getProfileFilter() {
        return profileFilter;
    }

    public void setProfileFilter(List<Perfil> profileFilter) {
        this.profileFilter = profileFilter;
    }

    

    }
