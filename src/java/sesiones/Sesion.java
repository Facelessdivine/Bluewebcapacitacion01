/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesiones;

import clases.Usuario;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.UsuarioModel;

/**
 *
 * @author Raúl Herrera Macías
 */
@ManagedBean(name = "userSessionBean")
public class Sesion {

    private Usuario sesion;

    public Usuario getSesion(String key) {
        this.sesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
        return sesion;
    }

    public void setSesion(Usuario user, String key) {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        session.put(key, user);
    }

    public void logOut() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext() 
                    .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                            + "/faces/login.xhtml");
        } catch (IOException io) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, io);
        } catch (Exception e) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void isLoggedIn() {
        Usuario userlogged = getSesion("User");
        try {

            if (userlogged == null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                                + "/faces/login.xhtml");
            }
        } catch (IOException iOException) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, iOException);
        } catch (Exception e) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
