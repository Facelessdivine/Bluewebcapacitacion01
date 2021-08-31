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
@Table(name = "C_CLIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CClientes.findAll", query = "SELECT c FROM CClientes c"),
    @NamedQuery(name = "CClientes.findByIdCliente", query = "SELECT c FROM CClientes c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "CClientes.findByNumCliente", query = "SELECT c FROM CClientes c WHERE c.numCliente = :numCliente"),
    @NamedQuery(name = "CClientes.findByNombreCliente", query = "SELECT c FROM CClientes c WHERE c.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "CClientes.findByTelContacto", query = "SELECT c FROM CClientes c WHERE c.telContacto = :telContacto"),
    @NamedQuery(name = "CClientes.findByRfc", query = "SELECT c FROM CClientes c WHERE c.rfc = :rfc"),
    @NamedQuery(name = "CClientes.findByCalle", query = "SELECT c FROM CClientes c WHERE c.calle = :calle"),
    @NamedQuery(name = "CClientes.findByNumExt", query = "SELECT c FROM CClientes c WHERE c.numExt = :numExt"),
    @NamedQuery(name = "CClientes.findByColonia", query = "SELECT c FROM CClientes c WHERE c.colonia = :colonia"),
    @NamedQuery(name = "CClientes.findByCp", query = "SELECT c FROM CClientes c WHERE c.cp = :cp"),
    @NamedQuery(name = "CClientes.findByActivo", query = "SELECT c FROM CClientes c WHERE c.activo = :activo"),
    @NamedQuery(name = "CClientes.findByFechaAlta", query = "SELECT c FROM CClientes c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CClientes.findByFechaBaja", query = "SELECT c FROM CClientes c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CClientes.findByFechaServidor", query = "SELECT c FROM CClientes c WHERE c.fechaServidor = :fechaServidor")})
public class CClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUM_CLIENTE")
    private String numCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TEL_CONTACTO")
    private String telContacto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CALLE")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUM_EXT")
    private String numExt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COLONIA")
    private String colonia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CP")
    private String cp;
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
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD")
    @ManyToOne
    private CCiudad idCiudad;
    @JoinColumn(name = "ID_DISTRIBUIDOR", referencedColumnName = "ID_DISTRIBUIDOR")
    @ManyToOne
    private CDistribuidor idDistribuidor;
    @JoinColumn(name = "ID_USUARIO_MODIFICA", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuarioModifica;
    @OneToMany(mappedBy = "idCliente")
    private Collection<HActivacion> hActivacionCollection;

    public CClientes() {
    }

    public CClientes(Long idCliente) {
        this.idCliente = idCliente;
    }

    public CClientes(Long idCliente, String numCliente, String nombreCliente, String telContacto, String rfc, String calle, String numExt, String colonia, String cp, boolean activo, Date fechaAlta, Date fechaServidor) {
        this.idCliente = idCliente;
        this.numCliente = numCliente;
        this.nombreCliente = nombreCliente;
        this.telContacto = telContacto;
        this.rfc = rfc;
        this.calle = calle;
        this.numExt = numExt;
        this.colonia = colonia;
        this.cp = cp;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaServidor = fechaServidor;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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

    public CCiudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(CCiudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public CDistribuidor getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(CDistribuidor idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public SUsuarios getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(SUsuarios idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CClientes)) {
            return false;
        }
        CClientes other = (CClientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CClientes[ idCliente=" + idCliente + " ]";
    }
    
}
