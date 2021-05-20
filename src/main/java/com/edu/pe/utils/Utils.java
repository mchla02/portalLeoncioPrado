package com.edu.pe.utils;

import com.edu.pe.modelo.Seccion;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class Utils {

    @Autowired
    private HttpSession session;

    public List<Seccion> ObtenerSesiones() {
        List<Seccion> lista = (ArrayList<Seccion>) session.getAttribute("secciones");
        return lista;
    }

    public int ObtenerCantidadSemanas() {
        int nroSemanas = (int) session.getAttribute("cantSemanas");
        return nroSemanas;
    }

    public String PerfilLogeado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String perfil = "";
        for (GrantedAuthority gauth : authorities) {
            perfil = gauth.getAuthority();
        }

        return perfil;
    }

    public String UsuarioLogeado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String username = userDetail.getUsername().trim();
        return username;
    }

    public boolean ActivarComentario(String fechaInicio, String fechaFin) {

        Date fInicio = Convertir(fechaInicio);
        Date fFin = Convertir(fechaFin);
        Date actual = new Date();

        if (actual.getTime() >= fInicio.getTime() && actual.getTime() <= fFin.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static Date Convertir(String cadena) {
        Date fecha = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            fecha = sf.parse(cadena);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fecha;
    }

    public static String guardarArchivo(MultipartFile multiPart,
            String ruta, String nombreOriginal) {
        //  String nombreOriginal = "A" + multiPart.getOriginalFilename();
        try {
            File archivo = new File(ruta);

            if (!archivo.exists()) {
                System.err.println("La ruta " + ruta + " no existe");
                archivo.mkdirs(); // creando ruta

                if (archivo.mkdirs()) {
                    System.out.println("Creando Ruta");
                } else {
                    System.err.println("Creando ");
                }
            }

            Path path = Paths.get(ruta + nombreOriginal);

            System.out.println("Ruta Absoluta File : " + archivo.getAbsoluteFile());
            System.out.println("Ruta Absoluta Path : " + path.toAbsolutePath().toString());

            archivo = new File(path.toAbsolutePath().toString());
            System.out.println("Nombre del Archivo : " + multiPart.getOriginalFilename());
            System.out.println("Tipo de dato : " + multiPart.getContentType());
            System.out.println("Tamaño : " + multiPart.getSize());

            multiPart.transferTo(archivo);

            return nombreOriginal;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public static void EliminarArchivo(String nombreOriginal,
            String ruta) {
        try {

            File imageFile = new File(ruta + nombreOriginal);
            if (imageFile.delete()) {
                System.out.println("Archivo Eliminado con exito");
            } else {
                System.err.println("No se ha podido eliminar Archivo");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

}
