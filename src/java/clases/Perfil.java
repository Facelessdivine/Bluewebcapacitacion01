
package clases;

import java.util.Date;


public class Perfil {
    //    Se definen los datos de la tabla S_perfiles
    private int id_perfil;
    private String nombre_perfil;
    private String descripcion;
    private String activo;
    private Date fecha_alta;
    private Date fecha_baja;
    private Date fecha_servidor;
    private int id_usuario_modifica;


    public Perfil() {//para instanciar el objeto
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }


    public String getNombre_perfil() {
        return nombre_perfil;
    }

    public void setNombre_perfil(String nombre_perfil) {
        this.nombre_perfil = nombre_perfil;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public Date getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(Date fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
    public int getId_usuario_modifica() {
        return id_usuario_modifica;
    }

    public void setId_usuario_modifica(int id_usuario_modifica) {
        this.id_usuario_modifica = id_usuario_modifica;
    }

    
    
}
