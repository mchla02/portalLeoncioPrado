package com.edu.pe.modeloDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodoDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    public int CantidadSemanas(int idPeriodo){
        String sql = "select cantidad_semanas from periodo where id_periodo = ?";
        
        return jdbc.queryForObject(sql, int.class , idPeriodo);
    }
}
