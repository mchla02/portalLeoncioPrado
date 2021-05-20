package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EstudianteDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public Estudiante BuscarPorUsuario(String username) {
        Estudiante obj = jdbc.queryForObject("select u.id_usuario , correo, username , id_grado , nombres , apellidos,id_estudiante"
                + "  from estudiante e"
                + "  inner join usuario u "
                + " on e.id_usuario = u.id_usuario "
                + " where u.username =  ?",
                new RowMapper<Estudiante>() {
            @Override
            public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
                Estudiante e = new Estudiante();
                e.setIdUsuario(rs.getInt(1));
                e.setCorreo(rs.getString(2));
                e.setUsuario(rs.getString(3));
                e.setId_grado(rs.getInt(4));
                e.setNombres(rs.getString(5));
                e.setApellidos(rs.getString(6));
                e.setIdEstudiante(rs.getInt(7));
                return e;
            }
        }, new Object[]{username});
        
        return obj;
    }
}
