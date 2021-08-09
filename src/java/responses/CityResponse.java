
package responses;


import clases.Ciudad;
import responses.Response;
import java.util.List;


public class CityResponse {
    
   //esta clase va a guardar la lista ciudad y la respuesta
  
    private List<Ciudad> listaCiudad;
    private Response respuesta;

    public CityResponse() {
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public Response getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Response respuesta) {
        this.respuesta = respuesta;
    }
    
    
}