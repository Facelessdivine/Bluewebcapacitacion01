package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import sesiones.Sesion;



@ManagedBean(name="index")
public class IndexBean {
    Sesion s= new Sesion();
    
    private List<String> images;    //declaramos las listas
     
    @PostConstruct
    public void start(){
            
            try {
            if(s.getSesion("User") == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/main/login.xhtml");
            }
            } catch (IOException ex) {
                Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
        

}
     public List<String> getImages() {
        return images;
    }
}