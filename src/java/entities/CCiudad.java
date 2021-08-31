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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "C_CIUDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CCiudad.findAll", query = "SELECT c FROM CCiudad c"),
    @NamedQuery(name = "CCiudad.findByIdCiudad", query = "SELECT c FROM CCiudad c WHERE c.idCiudad = :idCiudad"),
    @NamedQuery(name = "CCiudad.findByDescripcion", query = "SELECT c FROM CCiudad c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CCiudad.findByCodigo", query = "SELECT c FROM CCiudad c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CCiudad.findByLada", query = "SELECT c FROM CCiudad c WHERE c.lada = :lada"),
    @NamedQuery(name = "CCiudad.findByActivo", query = "SELECT c FROM CCiudad c WHERE c.activo = :activo"),
    @NamedQuery(name = "CCiudad.findByFechaAlta", query = "SELECT c FROM CCiudad c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CCiudad.findByFechaBaja", query = "SELECT c FROM CCiudad c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CCiudad.findByFechaServidor", query = "SELECT c FROM CCiudad c WHERE c.fechaServidor = :fechaServidor")})
public class CCiudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CIUDAD")
    private Long idCiudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "LADA")
    private Short lada;
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
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuario;
    @OneToMany(mappedBy = "idCiudad")
    private Collection<CClientes> cClientesCollection;
    @OneToMany(mappedBy = "idCiudad")
    private Collection<HActivacion> hActivacionCollection;

    public CCiudad() {
    }

    public CCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public CCiudad(Long idCiudad, String descripcion) {
        this.idCiudad = idCiudad;
        this.descripcion = descripcion;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getLada() {
        return lada;
    }

    public void setLada(Short lada) {
        this.lada = lada;
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

    public SUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SUsuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CCiudad)) {
            return false;
        }
        CCiudad other = (CCiudad) object;
        if ((this.idCiudad == null && other.idCiudad != null) || (this.idCiudad != null && !this.idCiudad.equals(other.idCiudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CCiudad[ idCiudad=" + idCiudad + " ]";
    }
    
}
