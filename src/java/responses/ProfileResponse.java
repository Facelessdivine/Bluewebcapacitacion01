/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import clases.Perfil;
import java.util.List;

/**
 *
 * @author Blueweb
 */
public class ProfileResponse {
    private List<Perfil> profileList;
    private Response response;

    public ProfileResponse() {
    }

    public List<Perfil> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Perfil> profileList) {
        this.profileList = profileList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    
}
