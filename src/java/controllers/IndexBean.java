package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import sesiones.Sesion;

@ManagedBean(name = "indexBean")
public class IndexBean {

    Sesion s = null;

    private List<String> images;    //declaramos las listas

    public IndexBean() {
        s = new Sesion();
    }

    @PostConstruct
    public void start() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info", "Welcome, " + s.getSesion("User").getUsuario());
//        FacesMessage message = null;
//
//        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Welcome" + s.getSesion("User").getNombre_usuario());
//        FacesContext.getCurrentInstance().addMessage(null, message);

        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }

    }

    public List<String> getImages() {
        return images;
    }
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
