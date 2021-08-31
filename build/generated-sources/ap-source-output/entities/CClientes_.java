package entities;

import entities.CCiudad;
import entities.CDistribuidor;
import entities.HActivacion;
import entities.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-31T17:42:37")
@StaticMetamodel(CClientes.class)
public class CClientes_ { 

    public static volatile SingularAttribute<CClientes, Date> fechaBaja;
    public static volatile SingularAttribute<CClientes, String> numExt;
    public static volatile SingularAttribute<CClientes, Date> fechaAlta;
    public static volatile SingularAttribute<CClientes, String> calle;
    public static volatile SingularAttribute<CClientes, String> telContacto;
    public static volatile SingularAttribute<CClientes, String> numCliente;
    public static volatile SingularAttribute<CClientes, String> cp;
    public static volatile SingularAttribute<CClientes, String> rfc;
    public static volatile SingularAttribute<CClientes, CCiudad> idCiudad;
    public static volatile SingularAttribute<CClientes, String> colonia;
    public static volatile SingularAttribute<CClientes, Long> idCliente;
    public static volatile SingularAttribute<CClientes, String> nombreCliente;
    public static volatile CollectionAttribute<CClientes, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CClientes, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<CClientes, CDistribuidor> idDistribuidor;
    public static volatile SingularAttribute<CClientes, Date> fechaServidor;
    public static volatile SingularAttribute<CClientes, Boolean> activo;

}