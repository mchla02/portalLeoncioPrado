package com.edu.pe.controller;

import com.edu.pe.modelo.Curso;
import com.edu.pe.modelo.Estudiante;
import com.edu.pe.modelo.Profesor;
import com.edu.pe.modelo.Seccion;
import com.edu.pe.modeloDAO.CursoDAO;
import com.edu.pe.modeloDAO.EstudianteDAO;
import com.edu.pe.modeloDAO.PeriodoDAO;
import com.edu.pe.modeloDAO.ProfesorDAO;
import com.edu.pe.modeloDAO.SeccionDAO;
import com.edu.pe.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private CursoDAO cursoDao;

    @Autowired
    private SeccionDAO seccionDao;

    @Autowired
    private PeriodoDAO periodoDao;

    @Autowired
    private EstudianteDAO estDao;

    @Autowired
    private ProfesorDAO profDao;

    @Autowired
    private HttpSession sesion;

    @Autowired
    private Utils utils;

    @GetMapping("/login")
    public String Inicio() {
        return "Index";
    }

    @GetMapping("/acceso")
    public String IniciarSesion() {
        String perfil = utils.PerfilLogeado();
        String user = utils.UsuarioLogeado();
        Estudiante e = null;
        Profesor p = null;
        List<Seccion> lista = null;
        String datosLogeado = "";
        int idPeriodo = 1;
        int idUsuario;

        if (perfil.equalsIgnoreCase("Estudiante")) {
            e = estDao.BuscarPorUsuario(user);
            idUsuario = e.getIdUsuario();
            datosLogeado = e.getNombres() + " " + e.getApellidos();
            lista = seccionDao.SeccionAlumno(e.getIdEstudiante(), idPeriodo);
        } else {
            p = profDao.BuscarPorUsuario(user);
            idUsuario = p.getIdUsuario();
            datosLogeado = p.getNombresProf() + " " + p.getApellidosProf();
            lista = seccionDao.SeccionProfesor(p.getId_profesor(), idPeriodo);
        }

        int semanas = periodoDao.CantidadSemanas(idPeriodo);

        sesion.setAttribute("secciones", lista);
        sesion.setAttribute("cantSemanas", semanas);
        sesion.setAttribute("userLogeado", datosLogeado);
        sesion.setAttribute("perfilLogeado", perfil);
        sesion.setAttribute("idUsuarioLogeado", idUsuario);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String Home() {
        return "Home";
    }

}
