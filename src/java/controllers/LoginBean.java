package controllers;

import clases.Usuario;
import java.io.IOException;
import sesiones.Sesion;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import responses.UserResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.UsuarioModel;

@ManagedBean(name = "login")//cambiarlo a login
public class LoginBean implements Serializable {
    private Usuario user;
    
    public LoginBean (){
    user = new Usuario();
    }
    public void login() throws IOException {
    Sesion s = new Sesion();
        UserResponse response = UsuarioModel.Login(user);
        switch (response.getResponse().getId()) {
            case 0:
                System.out.println("Logged in");
                //            Variables de Sesión
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Id_usuario", response.getUser().getId_usuario());
                s.setSesion(response.getUser(), "User");
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/empty.xhtml");
                break;
            case 1:
                addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Wrong username or password");
                break;
            case -1:
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Something went Wrong");
                break;
            default:
                break;
        }
    }

   /*
    Fecha: 16/08/2021
    Autor: Ulises Garcia
    Se añadio esta funcion ya que el usuario podia navegar entre las paginas sin estar loguado. 
    */
    public void isLoggedIn() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario userlogged = (Usuario) facesContext.getExternalContext().getSessionMap().get("User");
        if (userlogged == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/main/login.xhtml");
        }
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
}
