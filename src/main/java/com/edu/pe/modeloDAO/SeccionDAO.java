package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SeccionDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Seccion> SeccionAlumno(int idEst, int idPeriodo) {
        String sql = "select s.nro_seccion , c.id_curso , c.nombre_curso  ,concat(p.nombres , ' ',p.apellidos)  " //  , p.nombres , p.apellidos
                + " from seccion s inner join inscripcion i "
                + " on s.nro_seccion = i.nro_seccion "
                + " inner join curso c "
                + " on c.id_curso = s.id_curso "
                + " inner join profesor p "
                + " on p.id_profesor = s.id_profesor "
                + " where id_estudiante = ? and id_periodo = ?";
        Object[] parametros = {idEst, idPeriodo};

        List<Seccion> lista = jdbc.query(sql, new RowMapper<Seccion>() {
            @Override
            public Seccion mapRow(ResultSet rs, int i) throws SQLException {
                Seccion s = new Seccion();
                s.setNroSeccion(rs.getInt(1));
                s.setIdCurso(rs.getInt(2));
                s.setNombreCurso(rs.getString(3));
                s.setNomProfesor(rs.getString(4));
                return s;
            }
        }, parametros);

        return lista;
    }
    
    public List<Seccion> SeccionProfesor(int idProf, int idPeriodo) {
        String sql = "select s.nro_seccion , c.id_curso , c.nombre_curso  ,concat(p.nombres , ' ',p.apellidos)  " 
                + " from seccion s "
                + " inner join curso c "
                + " on c.id_curso = s.id_curso "
                + " inner join profesor p "
                + " on p.id_profesor = s.id_profesor "
                + " where p.id_profesor = ? and id_periodo = ?";
        Object[] parametros = {idProf, idPeriodo};

        List<Seccion> lista = jdbc.query(sql, new RowMapper<Seccion>() {
            @Override
            public Seccion mapRow(ResultSet rs, int i) throws SQLException {
                Seccion s = new Seccion();
                s.setNroSeccion(rs.getInt(1));
                s.setIdCurso(rs.getInt(2));
                s.setNombreCurso(rs.getString(3));
                s.setNomProfesor(rs.getString(4));
                return s;
            }
        }, parametros);

        return lista;
    }
}
