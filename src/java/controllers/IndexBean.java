package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import sesiones.Sesion;



@ManagedBean(name="indexBean")
public class IndexBean {
    Sesion s= null;
    
    private List<String> images;    //declaramos las listas

    public IndexBean() {
        s= new Sesion();
    }
     
    @PostConstruct
    public void start(){
            
        
         
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
        

}
     public List<String> getImages() {
        return images;
    }
}