package com.redsocial.modelo;

/**
 *
 * @author Usuario
 *
 */
public class Respuesta {

    private int idRespuesta;
    private String email;
    private String fecha;
    private int idPublicacion;
    private String mensaje;
    private String nombre;

    public Respuesta(String email, String fecha, int idPublicacion, String mensaje, String nombre) {
        super();
        this.email = email;
        this.fecha = fecha;
        this.idPublicacion = idPublicacion;
        this.mensaje = mensaje;
        this.nombre = nombre;
    }

    public Respuesta(int idRespuesta, String email, String fecha, int idPublicacion, String mensaje, String nombre) {
        super();
        this.idRespuesta = idRespuesta;
        this.email = email;
        this.fecha = fecha;
        this.idPublicacion = idPublicacion;
        this.mensaje = mensaje;
        this.nombre = nombre;
    }

    public int getId() {
        return idRespuesta;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
