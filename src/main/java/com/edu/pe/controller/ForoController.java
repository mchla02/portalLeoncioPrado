package com.edu.pe.controller;

import com.edu.pe.modelo.Foro;
import com.edu.pe.modeloDAO.ForoDAO;
import com.edu.pe.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foro")
public class ForoController {

    @Autowired
    private ForoDAO foroDao;

    @Autowired
    private Utils utils;

    @GetMapping("/{idSeccion}/{idForo}")
    public String VerForo(Model model, @PathVariable int idSeccion, @PathVariable int idForo) {
        Foro objForo = foroDao.BuscarPorId(idForo);

        model.addAttribute("foro", objForo);
        model.addAttribute("seccion", idSeccion);
      //  model.addAttribute("secciones", utils.ObtenerSesiones());
        model.addAttribute("activar", utils.ActivarComentario(objForo.getFechaInicio(), objForo.getFechaCierre()));

        return "PagForo";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> Guardar(Foro objForo) {
        int res = 0;

        try {
            res = foroDao.GuardarForo(objForo);
        } catch (Exception ex) {
        }

        return ResponseEntity.ok(res);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> Actualizar(Foro objForo) {
        int res = 0;

        try {
            res = foroDao.ActualizarForo(objForo);
        } catch (Exception ex) {
        }

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable("id") int id) {
        int res = foroDao.Eliminar(id);
        return ResponseEntity.ok(res);
    }
}
