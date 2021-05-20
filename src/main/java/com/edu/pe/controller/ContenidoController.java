package com.edu.pe.controller;

import com.edu.pe.modelo.Contenido;
import com.edu.pe.modeloDAO.ContenidoDAO;
import com.edu.pe.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/contenido")
public class ContenidoController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ContenidoDAO contenidoDao;

    @Autowired
    private Utils utils;

    @PostMapping("/guardar")
    public ResponseEntity<Object> Guardar(Contenido obj,
            @RequestParam("archivos") MultipartFile multiPart) {
        String nombreImagen = "";

        if (!multiPart.isEmpty()) {
            String nombreOriginal = "";
            String ruta = ".//src//main//resources//static//archivos//";

            nombreOriginal = multiPart.getOriginalFilename();
            nombreImagen = utils.guardarArchivo(multiPart, ruta, nombreOriginal);

            obj.setArchivo(nombreImagen);
        }

        int estado = contenidoDao.Guardar(obj);

        return new ResponseEntity<Object>(estado, HttpStatus.OK);
    }
}
