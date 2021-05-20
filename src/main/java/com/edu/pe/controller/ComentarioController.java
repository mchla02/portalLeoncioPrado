package com.edu.pe.controller;

import com.edu.pe.modelo.Comentario;
import com.edu.pe.modeloDAO.ComentarioDAO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioDAO comenDao;

    @Autowired
    private HttpSession sesion;
    
    @PostMapping("/guardar")
    public ResponseEntity<?> Guardar(Comentario obj) {
        int res = 0;
        int idUsuario = Integer.parseInt(sesion.getAttribute("idUsuarioLogeado").toString());
        
        obj.setId_usuario(idUsuario); 
        
        try {
            res = comenDao.Guardar(obj);
        } catch (Exception ex) {
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/listar")
    public String ListarComentarios(Model model, @RequestParam int idForo) {
        List<Comentario> lista = comenDao.ListarPorForo(idForo);
        
        model.addAttribute("comentarios", lista);
        
        return "listar/ListadoComentario";
    }
}
