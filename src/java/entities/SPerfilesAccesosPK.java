/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Blueweb
 */
@Embeddable
public class SPerfilesAccesosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL")
    private int idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACCESO")
    private int idAcceso;

    public SPerfilesAccesosPK() {
    }

    public SPerfilesAccesosPK(int idPerfil, int idAcceso) {
        this.idPerfil = idPerfil;
        this.idAcceso = idAcceso;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPerfil;
        hash += (int) idAcceso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SPerfilesAccesosPK)) {
            return false;
        }
        SPerfilesAccesosPK other = (SPerfilesAccesosPK) object;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        if (this.idAcceso != other.idAcceso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SPerfilesAccesosPK[ idPerfil=" + idPerfil + ", idAcceso=" + idAcceso + " ]";
    }
    
}
