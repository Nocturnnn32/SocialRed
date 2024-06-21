package com.redsocial.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.redsocial.modelo.Like;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Respuesta;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOLike;
import com.redsocial.persistencia.DAOPublicacion;
import com.redsocial.persistencia.DAORespuesta;
import com.redsocial.persistencia.DAOUsuario;
import java.sql.SQLException;

@Controller
public class PublicacionController {

    @RequestMapping(value = "publicar", method = RequestMethod.POST)
    public String publicar(HttpServletRequest request, Model model) throws SQLException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");

        if (user != null) {
            String mensaje = request.getParameter("mensaje");
            String fechaPublicacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            Publicacion publicacion = new Publicacion();
            publicacion.setEmail(user.getEmail());
            publicacion.setNombre(user.getNombre());
            publicacion.setMensaje(mensaje);
            publicacion.setFecha(fechaPublicacion);

            DAOPublicacion.insert(publicacion);

            return "redirect:wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "comentar", method = RequestMethod.POST)
    public String comentar(HttpServletRequest request, Model model) throws SQLException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");

        if (user != null) {
            String mensaje = request.getParameter("respuesta");
            int idPublicacion = Integer.parseInt(request.getParameter("respuesta-publicacion"));
            String fechaPublicacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            Respuesta respuesta = new Respuesta(user.getEmail(), fechaPublicacion, idPublicacion, mensaje, user.getNombre());
            DAORespuesta.insert(respuesta);

            return "redirect:wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "borrarpublicacion", method = RequestMethod.GET)
    public String borrarpublicacion(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");

        if (user != null) {
            int idPublicacion = Integer.parseInt(request.getParameter("id"));
            ArrayList<Respuesta> respuestas = DAORespuesta.select(String.valueOf(idPublicacion));
            for (Respuesta respuesta : respuestas) {
                DAORespuesta.delete(respuesta.getId());
            }

            Usuario usuarioObj = DAOUsuario.select(user);
            Like like = DAOLike.checkLike(idPublicacion, usuarioObj.getEmail());
            if (like != null) {
                DAOLike.delete(like);
            }

            DAOPublicacion.delete(idPublicacion);

            return "redirect:wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "editarpublicacion", method = RequestMethod.GET)
    public String editarpublicacion(@RequestParam int id, HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");

        if (user != null) {
            Publicacion publicacion = DAOPublicacion.select(String.valueOf(id));
            model.addAttribute("publi", publicacion);
            model.addAttribute("id", id);
            model.addAttribute("body", "editarPublicacion");
            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "updatepublicacion", method = RequestMethod.POST)
    public String updatePublicacion(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");

        if (user != null) {
            int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
            String nombre = request.getParameter("update-name");
            String email = request.getParameter("update-email");
            String fecha = request.getParameter("update-fecha");
            String mensaje = request.getParameter("update-mensaje");

            Publicacion publicacion = new Publicacion(idPublicacion, email, nombre, fecha, "", mensaje);
            DAOPublicacion.update(publicacion);

            return "redirect:wall";
        } else {
            return "home";
        }
    }
}