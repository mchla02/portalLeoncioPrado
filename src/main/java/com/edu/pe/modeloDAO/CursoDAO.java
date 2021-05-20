package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Curso> Listar() {
        String sql = "select id_curso , nombre_curso from Curso";
        List<Curso> lista = jdbc.query(sql, new RowMapper<Curso>() {
            @Override
            public Curso mapRow(ResultSet rs, int i) throws SQLException {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt(1));
                c.setNombreCurso(rs.getString(2));
                return c;
            }
        });

        return lista;
    }

    public String NombreCurso(int nroSeccion) {
        String sql = "select UPPER(Nombre_curso) "
                + " from seccion s inner join curso c on s.id_curso = c.id_curso "
                + " where nro_seccion  = ?";

        return jdbc.queryForObject(sql, String.class, nroSeccion);
    }
}
