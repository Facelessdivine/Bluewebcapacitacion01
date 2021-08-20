/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "C_TIPO_TELEFONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CTipoTelefono.findAll", query = "SELECT c FROM CTipoTelefono c"),
    @NamedQuery(name = "CTipoTelefono.findById", query = "SELECT c FROM CTipoTelefono c WHERE c.id = :id"),
    @NamedQuery(name = "CTipoTelefono.findByDescripcion", query = "SELECT c FROM CTipoTelefono c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CTipoTelefono.findByClave", query = "SELECT c FROM CTipoTelefono c WHERE c.clave = :clave"),
    @NamedQuery(name = "CTipoTelefono.findByIdTelefonia", query = "SELECT c FROM CTipoTelefono c WHERE c.idTelefonia = :idTelefonia"),
    @NamedQuery(name = "CTipoTelefono.findByActivo", query = "SELECT c FROM CTipoTelefono c WHERE c.activo = :activo"),
    @NamedQuery(name = "CTipoTelefono.findByFechaServidor", query = "SELECT c FROM CTipoTelefono c WHERE c.fechaServidor = :fechaServidor")})
public class CTipoTelefono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "ID_TELEFONIA")
    private Long idTelefonia;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;

    public CTipoTelefono() {
    }

    public CTipoTelefono(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdTelefonia() {
        return idTelefonia;
    }

    public void setIdTelefonia(Long idTelefonia) {
        this.idTelefonia = idTelefonia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CTipoTelefono)) {
            return false;
        }
        CTipoTelefono other = (CTipoTelefono) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controllers.CTipoTelefono[ id=" + id + " ]";
    }
    
}
