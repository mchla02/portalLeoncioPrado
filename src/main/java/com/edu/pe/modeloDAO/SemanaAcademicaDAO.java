package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SemanaAcademicaDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<SemanaAcademica> ListarSemanas(int num) {
        String sql = "select nro_semana , semana"
                + "  from semana_academica where nro_semana<=?"
                + " order by nro_semana asc";
        List<SemanaAcademica> lista = jdbc.query(sql, new RowMapper<SemanaAcademica>() {
            @Override
            public SemanaAcademica mapRow(ResultSet rs, int i) throws SQLException {
                SemanaAcademica s = new SemanaAcademica();
                s.setNroSemana(rs.getInt(1));
                s.setSemana(rs.getString(2));
                return s;
            }
        }, num);

        return lista;
    }
}
