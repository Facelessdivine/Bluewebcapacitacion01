/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import clases.Cliente;
import java.util.List;

/**
 *
 * @author Blueweb
 */
public class ClientResponse {
    private List<Cliente> clientList;
    private Response response;

    public ClientResponse() {
    }

    public List<Cliente> getClientList() {
        return clientList;
    }

    public void setClientList(List<Cliente> clientList) {
        this.clientList = clientList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    
}
