package com.redsocial.modelo;

/**
 *
 * @author Usuario
 *
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String pwd;
    private String email;

    public Usuario() {

    }

    public Usuario(int idUsuario, String nombre, String email, String pwd) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.pwd = pwd;
    }

    public Usuario(String nombre, String email, String pwd) {

        this.nombre = nombre;
        this.email = email;
        this.pwd = pwd;
    }

    public Usuario(String nombre) {
        setNombre(nombre);
    }

    public int getid() {
        return idUsuario;
    }

    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setid(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String direccion) {
        this.email = direccion;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", pwd=" + pwd + ", email=" + email + "]";
    }

}
