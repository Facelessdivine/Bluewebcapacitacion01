package controllers;

import clases.Cliente;
import java.io.Serializable;
import responses.ClientResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import models.ClienteModel;

@ManagedBean(name = "cliente")
public class ClienteBean implements Serializable {

    private List<Cliente> clientList = new ArrayList<>();
    private List<Cliente> clienteFilter;

    @PostConstruct
    public void init() {
        ClienteModel clientemodelo = new ClienteModel();
        ClientResponse respuestaCliente = clientemodelo.connectClient();
        if (respuestaCliente.getResponse().getId() == 0) {
            clientList = respuestaCliente.getClientList();
        } else if (respuestaCliente.getResponse().getId() < 0) {
            System.out.println("Warning");
        } else if (respuestaCliente.getResponse().getId() > 0) {
            System.out.println("Error");
        }

    }

    public List<Cliente> getClientList() {
        return clientList;
    }

    public void setClientList(List<Cliente> clientList) {
        this.clientList = clientList;
    }

    public List<Cliente> getClienteFilter() {
        return clienteFilter;
    }

    public void setClienteFilter(List<Cliente> clienteFilter) {
        this.clienteFilter = clienteFilter;
    }
    

    }
