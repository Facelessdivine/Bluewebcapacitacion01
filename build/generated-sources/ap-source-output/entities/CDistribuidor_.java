package entities;

import entities.CClientes;
import entities.HActivacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-31T17:42:37")
@StaticMetamodel(CDistribuidor.class)
public class CDistribuidor_ { 

    public static volatile SingularAttribute<CDistribuidor, Date> fechaBaja;
    public static volatile SingularAttribute<CDistribuidor, Date> fechaAlta;
    public static volatile CollectionAttribute<CDistribuidor, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CDistribuidor, Long> idUsuarioModifica;
    public static volatile CollectionAttribute<CDistribuidor, CClientes> cClientesCollection;
    public static volatile SingularAttribute<CDistribuidor, Long> idDistribuidor;
    public static volatile SingularAttribute<CDistribuidor, String> claveDistribuidor;
    public static volatile SingularAttribute<CDistribuidor, String> nombre;
    public static volatile SingularAttribute<CDistribuidor, Date> fechaServidor;
    public static volatile SingularAttribute<CDistribuidor, Boolean> activo;

}