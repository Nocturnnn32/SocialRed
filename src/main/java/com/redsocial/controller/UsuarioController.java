package com.redsocial.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Like;
import com.redsocial.modelo.MensajesPrivados;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Respuesta;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOLike;
import com.redsocial.persistencia.DAOMensajesPrivados;
import com.redsocial.persistencia.DAOPublicacion;
import com.redsocial.persistencia.DAORespuesta;
import com.redsocial.persistencia.DAOUsuario;
import java.sql.SQLException;

@Controller
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @RequestMapping(value = "editarusuario", method = RequestMethod.POST)
    public String editarUsuario(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            String nombre = request.getParameter("edit-nombre");
            String email = request.getParameter("edit-email");
            String newpwd = request.getParameter("edit-new-pwd");

            Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPwd(Utilidades.Encriptar(newpwd));

            DAOUsuario.update(usuario);
            return "redirect:wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "borrarusuario", method = RequestMethod.GET)
    public String borrarUsuario(HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");

            // Borrar todas las publicaciones del usuario con los respectivos likes y respuestas
            ArrayList<Publicacion> publicaciones = DAOPublicacion.selectAll();
            for (Publicacion publicacion : publicaciones) {
                if (publicacion.getEmail().equals(usuario.getEmail())) {
                    Like like = DAOLike.checkLike(publicacion.getIdPublicacion(), usuario.getEmail());
                    if (like != null) {
                        DAOLike.delete(like);
                    }
                    ArrayList<Respuesta> respuestas = DAORespuesta.select(String.valueOf(publicacion.getIdPublicacion()));
                    for (Respuesta respuesta : respuestas) {
                        if (respuesta.getEmail().equals(usuario.getEmail())) {
                            DAORespuesta.delete(respuesta.getId());
                        }
                    }
                    DAOPublicacion.delete(publicacion.getIdPublicacion());
                } else {
                    Like like = DAOLike.checkLike(publicacion.getIdPublicacion(), usuario.getEmail());
                    if (like != null) {
                        DAOLike.delete(like);
                    }
                    ArrayList<Respuesta> respuestas = DAORespuesta.select(String.valueOf(publicacion.getIdPublicacion()));
                    for (Respuesta respuesta : respuestas) {
                        if (respuesta.getEmail().equals(usuario.getEmail())) {
                            DAORespuesta.delete(respuesta.getId());
                        }
                    }
                }
            }

            DAOUsuario.delete(usuario.getIdUsuario());
            request.getSession().invalidate();
            return "home";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "listadousuarios", method = RequestMethod.GET)
    public String listarUsuarios(HttpServletRequest request, Model model) throws SQLException {
        if (request.getSession().getAttribute("user") != null) {
            ArrayList<Usuario> users = DAOUsuario.selectAll();
            ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getEmail());

            model.addAttribute("users", users);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("body", "listadousuarios");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "admineditaruser", method = RequestMethod.GET)
    public String adminEditarUsuario(@RequestParam int id, HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Usuario user = DAOUsuario.selectWithID(id);
            ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getEmail());

            model.addAttribute("usuario", user);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("body", "editarUsuario");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "updateuser", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String nombre = request.getParameter("update-name");
            String email = request.getParameter("update-email");
            String pwd = request.getParameter("update-pwd");

            Usuario user = new Usuario(idUsuario, nombre, email, Utilidades.Encriptar(pwd));
            DAOUsuario.update(user);

            ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getEmail());
            ArrayList<Usuario> users = DAOUsuario.selectAll();

            model.addAttribute("users", users);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("body", "listadousuarios");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "admindeleteuser", method = RequestMethod.GET)
    public String adminDeleteUser(@RequestParam int id, HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Usuario usuario = DAOUsuario.selectWithID(id);

            ArrayList<Publicacion> publicaciones = DAOPublicacion.selectAll();
            for (Publicacion publicacion : publicaciones) {
                if (publicacion.getEmail().equals(usuario.getEmail())) {
                    Like like = DAOLike.checkLike(publicacion.getIdPublicacion(), usuario.getEmail());
                    if (like != null) {
                        DAOLike.delete(like);
                    }
                    ArrayList<Respuesta> respuestas = DAORespuesta.select(String.valueOf(publicacion.getIdPublicacion()));
                    for (Respuesta respuesta : respuestas) {
                        if (respuesta.getEmail().equals(usuario.getEmail())) {
                            DAORespuesta.delete(respuesta.getId());
                        }
                    }
                    DAOPublicacion.delete(publicacion.getIdPublicacion());
                } else {
                    Like like = DAOLike.checkLike(publicacion.getIdPublicacion(), usuario.getEmail());
                    if (like != null) {
                        DAOLike.delete(like);
                    }
                    ArrayList<Respuesta> respuestas = DAORespuesta.select(String.valueOf(publicacion.getIdPublicacion()));
                    for (Respuesta respuesta : respuestas) {
                        if (respuesta.getEmail().equals(usuario.getEmail())) {
                            DAORespuesta.delete(respuesta.getId());
                        }
                    }
                }
            }

            DAOUsuario.delete(usuario.getIdUsuario());

            ArrayList<Usuario> users = DAOUsuario.selectAll();
            model.addAttribute("users", users);
            model.addAttribute("body", "listadousuarios");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "adminadduser", method = RequestMethod.POST)
    public String adminAddUser(HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            String nombre = request.getParameter("add-nombre");
            String email = request.getParameter("add-email");
            String pwd = request.getParameter("add-pwd");

            Usuario usuario = new Usuario(nombre, email, Utilidades.Encriptar(pwd));
            Usuario usuarioInsertado = DAOUsuario.select(usuario);

            if (usuarioInsertado == null) {
                usuario = DAOUsuario.insert(usuario);
                model.addAttribute("message", "Usuario insertado correctamente");
                model.addAttribute("status", 1);
            } else {
                model.addAttribute("message", "Este usuario ya existe.");
                model.addAttribute("status", 0);
            }

            ArrayList<Usuario> users = DAOUsuario.selectAll();
            model.addAttribute("users", users);
            model.addAttribute("body", "listadousuarios");

            return "wall";
        } else {
            return "home";
        }
    }
}
