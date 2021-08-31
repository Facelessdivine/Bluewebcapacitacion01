package entities;

import entities.CClientes;
import entities.HActivacion;
import entities.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-31T17:42:37")
@StaticMetamodel(CCiudad.class)
public class CCiudad_ { 

    public static volatile SingularAttribute<CCiudad, String> descripcion;
    public static volatile SingularAttribute<CCiudad, Short> lada;
    public static volatile SingularAttribute<CCiudad, Date> fechaBaja;
    public static volatile SingularAttribute<CCiudad, String> codigo;
    public static volatile SingularAttribute<CCiudad, Date> fechaAlta;
    public static volatile SingularAttribute<CCiudad, SUsuarios> idUsuario;
    public static volatile CollectionAttribute<CCiudad, HActivacion> hActivacionCollection;
    public static volatile CollectionAttribute<CCiudad, CClientes> cClientesCollection;
    public static volatile SingularAttribute<CCiudad, Long> idCiudad;
    public static volatile SingularAttribute<CCiudad, Date> fechaServidor;
    public static volatile SingularAttribute<CCiudad, Boolean> activo;

}