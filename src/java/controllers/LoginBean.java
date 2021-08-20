package controllers;

//<editor-fold defaultstate="collapsed" desc="imports">
import clases.Usuario;
import java.io.IOException;
import sesiones.Sesion;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import responses.UserResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.UsuarioModel;
//</editor-fold>
@ManagedBean(name = "loginBean")//cambiarlo a login
public class LoginBean implements Serializable {
    private Usuario user;
    private Sesion s;
    public LoginBean (){
    user = new Usuario();
    s = new Sesion();
    }
    public void login() throws IOException {
        UserResponse response = UsuarioModel.Login(user);
        switch (response.getResponse().getId()) {
            case 0:
                System.out.println("Logged in");
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
   //<editor-fold defaultstate="collapsed" desc="Getters and setters ">
   
   public Usuario getUser() {
       return user;
   }
   
   public void setUser(Usuario user) {
       this.user = user;
   }
   
//</editor-fold>
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
}
