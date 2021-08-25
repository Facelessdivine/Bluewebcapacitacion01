/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "C_TELEFONIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CTelefonia.findAll", query = "SELECT c FROM CTelefonia c"),
    @NamedQuery(name = "CTelefonia.findByIdTelefonia", query = "SELECT c FROM CTelefonia c WHERE c.idTelefonia = :idTelefonia"),
    @NamedQuery(name = "CTelefonia.findByDescripcion", query = "SELECT c FROM CTelefonia c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CTelefonia.findByClave", query = "SELECT c FROM CTelefonia c WHERE c.clave = :clave"),
    @NamedQuery(name = "CTelefonia.findByFechaServidor", query = "SELECT c FROM CTelefonia c WHERE c.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "CTelefonia.findByActivo", query = "SELECT c FROM CTelefonia c WHERE c.activo = :activo")})
public class CTelefonia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEFONIA")
    private Long idTelefonia;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "CLAVE")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @OneToMany(mappedBy = "idTelefonia")
    private Collection<CTipoTelefono> cTipoTelefonoCollection;

    public CTelefonia() {
    }

    public CTelefonia(Long idTelefonia) {
        this.idTelefonia = idTelefonia;
    }

    public CTelefonia(Long idTelefonia, Date fechaServidor) {
        this.idTelefonia = idTelefonia;
        this.fechaServidor = fechaServidor;
    }

    public Long getIdTelefonia() {
        return idTelefonia;
    }

    public void setIdTelefonia(Long idTelefonia) {
        this.idTelefonia = idTelefonia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<CTipoTelefono> getCTipoTelefonoCollection() {
        return cTipoTelefonoCollection;
    }

    public void setCTipoTelefonoCollection(Collection<CTipoTelefono> cTipoTelefonoCollection) {
        this.cTipoTelefonoCollection = cTipoTelefonoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefonia != null ? idTelefonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CTelefonia)) {
            return false;
        }
        CTelefonia other = (CTelefonia) object;
        if ((this.idTelefonia == null && other.idTelefonia != null) || (this.idTelefonia != null && !this.idTelefonia.equals(other.idTelefonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CTelefonia[ idTelefonia=" + idTelefonia + " ]";
    }
    
}
