package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


@ViewScoped
@ManagedBean(name="galeria")
public class galleriaBean {
    
     private List<String> images;    //declaramos las listas
     
    @PostConstruct
    public void init(){
        
         
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
        

}
     public List<String> getImages() {
        return images;
    }
}