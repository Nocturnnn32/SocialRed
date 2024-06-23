package com.redsocial.modelo;

/**
 * 
 * @author Usuario
 *
 */
public class Like {
  private String emailUsuario;
  private int idPublicacion;
  
  public Like(String email, int idPublicacion2) {
    super();
    this.emailUsuario = email;
    this.idPublicacion = idPublicacion2;
  }

  public String getEmailUsuario() {
    return emailUsuario;
  }

  public void setEmailUsuario(String emailUsuario) {
    this.emailUsuario = emailUsuario;
  }

  public int getIdPublicacion() {
    return idPublicacion;
  }

  public void setIdPublicacion(int idPublicacion) {
    this.idPublicacion = idPublicacion;
  }

  @Override
  public String toString() {
    return "Like [emailUsuario=" + emailUsuario + ", idPublicacion=" + idPublicacion + "]";
  }
}