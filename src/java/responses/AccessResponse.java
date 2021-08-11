
package responses;


import clases.Acceso;
import java.util.List;


public class AccessResponse {
    
   //esta clase va a guardar la lista ciudad y la respuesta
  
    private List<Acceso> listaAcceso;
    private Response respuesta;

    public AccessResponse() {
    }

    public List<Acceso> getListaAcceso() {
        return listaAcceso;
    }

    public void setListaAcceso(List<Acceso> listaAcceso) {
        this.listaAcceso = listaAcceso;
    }

    public Response getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Response respuesta) {
        this.respuesta = respuesta;
    }
    
    
}