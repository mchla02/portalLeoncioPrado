package com.edu.pe.controller;

import com.edu.pe.modelo.Contenido;
import com.edu.pe.modelo.Foro;
import com.edu.pe.modelo.Seccion;
import com.edu.pe.modelo.SemanaAcademica;
import com.edu.pe.modeloDAO.ContenidoDAO;
import com.edu.pe.modeloDAO.CursoDAO;
import com.edu.pe.modeloDAO.ForoDAO;
import com.edu.pe.modeloDAO.SemanaAcademicaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.pe.utils.Utils;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private SemanaAcademicaDAO semanaDao;

    @Autowired
    private ContenidoDAO contenidoDao;

    @Autowired
    private CursoDAO cursoDao;

    @Autowired
    private ForoDAO foroDao;

    @Autowired
    private Utils utils;

    @GetMapping("/seccion/{id}")
    public String CursosSeccion(@PathVariable("id") int id, Model model) {
        int nroSemanas = utils.ObtenerCantidadSemanas();
        String nomCurso = cursoDao.NombreCurso(id);
        model.addAttribute("semanas", ListarSemanas(nroSemanas, id));
        model.addAttribute("nomCurso", nomCurso);
        model.addAttribute("seccion", id);
      //  model.addAttribute("secciones", utils.ObtenerSesiones());
        return "PagCurso";
    }

    @GetMapping("/listar/seccion/{id}")
    public String ListarCurso(Model model, @PathVariable("id") int id, HttpSession session) {
        int nroSemanas = utils.ObtenerCantidadSemanas();
        
        model.addAttribute("seccion", id);
        model.addAttribute("semanas", ListarSemanas(nroSemanas, id));
     //   model.addAttribute("secciones", utils.ObtenerSesiones());
        return "listar/ListadoCursos";
    }

    public List<SemanaAcademica> ListarSemanas(int nroSemanas, int idSeccion) {
        List<SemanaAcademica> lista = semanaDao.ListarSemanas(nroSemanas);

        for (SemanaAcademica s : lista) {
            List<Contenido> contenidos = contenidoDao.ListarSemanas(s.getNroSemana(), idSeccion);
            List<Foro> foros = foroDao.ListarForo(s.getNroSemana(), idSeccion);
            s.setArchivos(contenidos);
            s.setForos(foros);
        }

        return lista;
    }

}
