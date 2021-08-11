package clases;

//en esta clase definimos el objeto ciudad 

import java.util.Date;

public class Acceso {
    private int id_acceso;
    private String nombre_acceso;
    private int orden;
    private String activo;
    private Date fecha_servidor;
    private Date fecha_baja;

    public int getId_acceso() {
        return id_acceso;
    }

    public void setId_acceso(int id_acceso) {
        this.id_acceso = id_acceso;
    }

    public String getNombre_acceso() {
        return nombre_acceso;
    }

    public void setNombre_acceso(String nombre_acceso) {
        this.nombre_acceso = nombre_acceso;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }


    public Date getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(Date fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
    
    
    public Acceso() {
    }
}
