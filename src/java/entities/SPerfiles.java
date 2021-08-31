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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "S_PERFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SPerfiles.findAll", query = "SELECT s FROM SPerfiles s"),
    @NamedQuery(name = "SPerfiles.findByIdPerfil", query = "SELECT s FROM SPerfiles s WHERE s.idPerfil = :idPerfil"),
    @NamedQuery(name = "SPerfiles.findByNombrePerfil", query = "SELECT s FROM SPerfiles s WHERE s.nombrePerfil = :nombrePerfil"),
    @NamedQuery(name = "SPerfiles.findByDescripcion", query = "SELECT s FROM SPerfiles s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SPerfiles.findByActivo", query = "SELECT s FROM SPerfiles s WHERE s.activo = :activo"),
    @NamedQuery(name = "SPerfiles.findByFechaAlta", query = "SELECT s FROM SPerfiles s WHERE s.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "SPerfiles.findByFechaBaja", query = "SELECT s FROM SPerfiles s WHERE s.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "SPerfiles.findByFechaServidor", query = "SELECT s FROM SPerfiles s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SPerfiles.findByIdUsuarioModifica", query = "SELECT s FROM SPerfiles s WHERE s.idUsuarioModifica = :idUsuarioModifica")})
public class SPerfiles implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_MODIFICA")
    private int idUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sPerfiles")
    private Collection<SPerfilesAccesos> sPerfilesAccesosCollection;

    public SPerfiles() {
    }

    public SPerfiles(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public SPerfiles(Integer idPerfil, String nombrePerfil, boolean activo, Date fechaAlta, Date fechaServidor, int idUsuarioModifica) {
        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaServidor = fechaServidor;
        this.idUsuarioModifica = idUsuarioModifica;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
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

    public int getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(int idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    @XmlTransient
    public Collection<SPerfilesAccesos> getSPerfilesAccesosCollection() {
        return sPerfilesAccesosCollection;
    }

    public void setSPerfilesAccesosCollection(Collection<SPerfilesAccesos> sPerfilesAccesosCollection) {
        this.sPerfilesAccesosCollection = sPerfilesAccesosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SPerfiles)) {
            return false;
        }
        SPerfiles other = (SPerfiles) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SPerfiles[ idPerfil=" + idPerfil + " ]";
    }
    
}
