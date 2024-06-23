package com.redsocial.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@Controller
public class WallController {

    private static final Logger logger = LoggerFactory.getLogger(WallController.class);

    @RequestMapping(value = "wall", method = RequestMethod.GET)
    public String wall(HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Usuario user = (Usuario) request.getSession().getAttribute("user");

            ArrayList<Publicacion> publicaciones = DAOPublicacion.selectAll();
            Hashtable<String, Integer> likes = new Hashtable<>();
            Hashtable<String, Integer> checklikes = new Hashtable<>();
            Hashtable<String, ArrayList<Respuesta>> respuestas = new Hashtable<>();

            for (Publicacion publicacion : publicaciones) {
                int totalLikes = DAOLike.select(publicacion.getIdPublicacion()).size();
                likes.put(String.valueOf(publicacion.getIdPublicacion()), totalLikes);

                Like like = DAOLike.checkLike(publicacion.getIdPublicacion(), user.getEmail());
                if (like != null) {
                    checklikes.put(String.valueOf(publicacion.getIdPublicacion()), 1);
                } else {
                    checklikes.put(String.valueOf(publicacion.getIdPublicacion()), 0);
                }

                ArrayList<Respuesta> respuestasPublicacion = DAORespuesta.select(String.valueOf(publicacion.getIdPublicacion()));
                respuestas.put(String.valueOf(publicacion.getIdPublicacion()), respuestasPublicacion);
            }

            ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(user.getEmail());

            model.addAttribute("publicaciones", publicaciones);
            model.addAttribute("respuestas", respuestas);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("likes", likes);
            model.addAttribute("user", user);
            model.addAttribute("checklikes", checklikes);
            model.addAttribute("body", "publicaciones");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().invalidate();
        }
        return "home";
    }

    @RequestMapping(value = "darlike", method = RequestMethod.POST)
    public String darlike(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            String idPublicacion = request.getParameter("like-publicacion");
            Usuario user = (Usuario) request.getSession().getAttribute("user");
            Like like = new Like(user.getEmail(), Integer.parseInt(idPublicacion));
            Like resultado = DAOLike.checkLike(Integer.parseInt(idPublicacion), user.getEmail());

            if (resultado != null) {
                DAOLike.delete(like);
            } else {
                DAOLike.insert(like);
            }

            return "redirect:wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public String messages(HttpServletRequest request, Model model) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Usuario user = (Usuario) request.getSession().getAttribute("user");
            ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(user.getEmail());
            ArrayList<Usuario> usuarios = DAOUsuario.selectAll();

            model.addAttribute("mensajes", mensajes);
            model.addAttribute("user", user);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("body", "mensajes");

            return "wall";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            String destinatario = request.getParameter("destinatario");
            String emisor = ((Usuario) request.getSession().getAttribute("user")).getEmail();
            String mensaje = request.getParameter("mensaje");

            Calendar fecha = new GregorianCalendar();
            int year = fecha.get(Calendar.YEAR);
            int month = fecha.get(Calendar.MONTH) + 1;
            int day = fecha.get(Calendar.DAY_OF_MONTH);
            int hour = fecha.get(Calendar.HOUR_OF_DAY);
            int minute = fecha.get(Calendar.MINUTE);
            String monthS = (month < 10) ? "0" + month : String.valueOf(month);
            String dayS = (day < 10) ? "0" + day : String.valueOf(day);
            String fechaEnvio = dayS + "/" + monthS + "/" + year + " " + hour + ":" + minute;

            MensajesPrivados message = new MensajesPrivados(fechaEnvio, destinatario, emisor, mensaje);

            DAOMensajesPrivados.insert(message);

            return "redirect:messages";
        } else {
            return "home";
        }
    }
}
