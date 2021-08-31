package entities;

import entities.CCiudad;
import entities.CClientes;
import entities.CDistribuidor;
import entities.CTipoTelefono;
import entities.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-31T16:01:47")
@StaticMetamodel(HActivacion.class)
public class HActivacion_ { 

    public static volatile SingularAttribute<HActivacion, Long> idActivacion;
    public static volatile SingularAttribute<HActivacion, SUsuarios> idUsuario;
    public static volatile SingularAttribute<HActivacion, String> distribuidor;
    public static volatile SingularAttribute<HActivacion, Date> fechaRespuesta;
    public static volatile SingularAttribute<HActivacion, CCiudad> idCiudad;
    public static volatile SingularAttribute<HActivacion, String> descripcionTipo;
    public static volatile SingularAttribute<HActivacion, String> cliente;
    public static volatile SingularAttribute<HActivacion, String> iccid;
    public static volatile SingularAttribute<HActivacion, Long> monto;
    public static volatile SingularAttribute<HActivacion, CClientes> idCliente;
    public static volatile SingularAttribute<HActivacion, String> ciudad;
    public static volatile SingularAttribute<HActivacion, Date> fechaPeticion;
    public static volatile SingularAttribute<HActivacion, String> imei;
    public static volatile SingularAttribute<HActivacion, Long> id;
    public static volatile SingularAttribute<HActivacion, String> telefono;
    public static volatile SingularAttribute<HActivacion, CDistribuidor> idDistribuidor;
    public static volatile SingularAttribute<HActivacion, CTipoTelefono> idTipoTelefonia;
    public static volatile SingularAttribute<HActivacion, String> respuestaAplicacion;
    public static volatile SingularAttribute<HActivacion, Date> fechaServidor;

}