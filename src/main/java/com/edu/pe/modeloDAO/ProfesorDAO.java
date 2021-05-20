package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProfesorDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public Profesor BuscarPorUsuario(String username) {
        Profesor obj = jdbc.queryForObject("select u.id_usuario , correo, username , nombres , apellidos , e.id_profesor"
                + "  from profesor e"
                + "  inner join usuario u "
                + " on e.id_usuario = u.id_usuario "
                + " where u.username =  ?",
                new RowMapper<Profesor>() {
            @Override
            public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
                Profesor e = new Profesor();
                e.setIdUsuario(rs.getInt(1));
                e.setCorreo(rs.getString(2));
                e.setUsuario(rs.getString(3));
                e.setNombresProf(rs.getString(4));
                e.setApellidosProf(rs.getString(5));
                e.setId_profesor(rs.getInt(6));
                return e;
            }
        }, new Object[]{username});

        return obj;
    }
}
