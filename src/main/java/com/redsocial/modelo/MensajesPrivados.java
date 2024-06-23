package com.redsocial.modelo;

/**
 *
 * @author Usuario
 *
 */
public class MensajesPrivados {

    private int idmensaje;
    private String fecha;
    private String emaildestinatario;
    private String emailemisor;
    private String mensaje;

    public MensajesPrivados() {
    }

    public MensajesPrivados(String fecha, String destinatario, String emisor, String mensaje) {
        super();
        this.fecha = fecha;
        this.emaildestinatario = destinatario;
        this.emailemisor = emisor;
        this.mensaje = mensaje;
    }

    public MensajesPrivados(int idmensaje, String fecha, String destinatario, String emisor, String mensaje) {
        super();
        this.idmensaje = idmensaje;
        this.fecha = fecha;
        this.emaildestinatario = destinatario;
        this.emailemisor = emisor;
        this.mensaje = mensaje;
    }

    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
    }

    public String getEmaildestinatario() {
        return emaildestinatario;
    }

    public void setEmaildestinatario(String emaildestinatario) {
        this.emaildestinatario = emaildestinatario;
    }

    public String getEmailemisor() {
        return emailemisor;
    }

    public void setEmailemisor(String emailemisor) {
        this.emailemisor = emailemisor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDestinatario() {
        return emaildestinatario;
    }

    public void setDestinatario(String destinatario) {
        this.emaildestinatario = destinatario;
    }

    public String getEmisor() {
        return emailemisor;
    }

    public void setEmisor(String emisor) {
        this.emailemisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "MensajesPrivados [idmensaje=" + idmensaje + ", fecha=" + fecha + ", destinatario=" + emaildestinatario
                + ", emisor=" + emailemisor + ", mensaje=" + mensaje + "]";
    }
}
