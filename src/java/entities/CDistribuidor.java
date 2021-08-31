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
@Table(name = "C_DISTRIBUIDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CDistribuidor.findAll", query = "SELECT c FROM CDistribuidor c"),
    @NamedQuery(name = "CDistribuidor.findByIdDistribuidor", query = "SELECT c FROM CDistribuidor c WHERE c.idDistribuidor = :idDistribuidor"),
    @NamedQuery(name = "CDistribuidor.findByClaveDistribuidor", query = "SELECT c FROM CDistribuidor c WHERE c.claveDistribuidor = :claveDistribuidor"),
    @NamedQuery(name = "CDistribuidor.findByNombre", query = "SELECT c FROM CDistribuidor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CDistribuidor.findByActivo", query = "SELECT c FROM CDistribuidor c WHERE c.activo = :activo"),
    @NamedQuery(name = "CDistribuidor.findByFechaAlta", query = "SELECT c FROM CDistribuidor c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CDistribuidor.findByFechaBaja", query = "SELECT c FROM CDistribuidor c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CDistribuidor.findByFechaServidor", query = "SELECT c FROM CDistribuidor c WHERE c.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "CDistribuidor.findByIdUsuarioModifica", query = "SELECT c FROM CDistribuidor c WHERE c.idUsuarioModifica = :idUsuarioModifica")})
public class CDistribuidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DISTRIBUIDOR")
    private Long idDistribuidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLAVE_DISTRIBUIDOR")
    private String claveDistribuidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_MODIFICA")
    private long idUsuarioModifica;
    @OneToMany(mappedBy = "idDistribuidor")
    private Collection<CClientes> cClientesCollection;
    @OneToMany(mappedBy = "idDistribuidor")
    private Collection<HActivacion> hActivacionCollection;

    public CDistribuidor() {
    }

    public CDistribuidor(Long idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public CDistribuidor(Long idDistribuidor, String claveDistribuidor, String nombre, long idUsuarioModifica) {
        this.idDistribuidor = idDistribuidor;
        this.claveDistribuidor = claveDistribuidor;
        this.nombre = nombre;
        this.idUsuarioModifica = idUsuarioModifica;
    }

    public Long getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Long idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getClaveDistribuidor() {
        return claveDistribuidor;
    }

    public void setClaveDistribuidor(String claveDistribuidor) {
        this.claveDistribuidor = claveDistribuidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public long getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(long idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    @XmlTransient
    public Collection<CClientes> getCClientesCollection() {
        return cClientesCollection;
    }

    public void setCClientesCollection(Collection<CClientes> cClientesCollection) {
        this.cClientesCollection = cClientesCollection;
    }

    @XmlTransient
    public Collection<HActivacion> getHActivacionCollection() {
        return hActivacionCollection;
    }

    public void setHActivacionCollection(Collection<HActivacion> hActivacionCollection) {
        this.hActivacionCollection = hActivacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistribuidor != null ? idDistribuidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CDistribuidor)) {
            return false;
        }
        CDistribuidor other = (CDistribuidor) object;
        if ((this.idDistribuidor == null && other.idDistribuidor != null) || (this.idDistribuidor != null && !this.idDistribuidor.equals(other.idDistribuidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CDistribuidor[ idDistribuidor=" + idDistribuidor + " ]";
    }
    
}
