package com.redsocial.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import com.redsocial.auxiliares.SendMail;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        return "home";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) throws Exception {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        Usuario user = DAOUsuario.login(email, password);
        if (user != null) {
            model.addAttribute("user", user);
            request.getSession().setMaxInactiveInterval(600);
            request.getSession().setAttribute("user", user);
            return "redirect:wall";

        } else {
            model.addAttribute("message", "No se puede loguear");
            return "home";
        }

    }

    @RequestMapping(value = "registrar", method = RequestMethod.POST)
    public String registrar(HttpServletRequest request, Model model) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password-register");
        String username = request.getParameter("username");

        // Encriptar la contraseña antes de crear el usuario
        String encryptedPassword = Utilidades.Encriptar(password);
        Usuario usuario = new Usuario(username, email, encryptedPassword);
        Usuario usuarioInsertado = DAOUsuario.insert(usuario);

        if (usuarioInsertado != null) {
            request.getSession().invalidate();
            model.addAttribute("user", usuario);
            request.getSession().setMaxInactiveInterval(600);
            request.getSession().setAttribute("user", usuario);
            return "redirect:wall";
        } else {
            model.addAttribute("message", "No se puede registrar");
            return "home";
        }
    }

    @RequestMapping(value = "viewRecordarPass", method = RequestMethod.GET)
    public String recordarPass(Model model) throws Exception {

        return "viewRecordarPass";
    }

    @RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
    public String forgotpassword(HttpServletRequest request, Model model) throws Exception {

        String email = request.getParameter("email");
        Usuario usuario = new Usuario("", email, "");
        Usuario user = DAOUsuario.select(usuario);

        if (user != null) {
            SendMail send = new SendMail();
            send.sendMail(user.getEmail(), Utilidades.Desencriptar(user.getPwd()));
            model.addAttribute("message", "Se ha enviado correctamente la contraseña");
        }
        return "viewRecordarPass";
    }

}
