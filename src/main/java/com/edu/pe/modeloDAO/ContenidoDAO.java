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
public class ContenidoDAO {

    @Autowired
    private JdbcTemplate jdbc;
    
      public int Guardar(Contenido obj) {
        Object[] parametros = {obj.getNro_semana() , obj.getNro_seccion() , obj.getArchivo()};
        int res = jdbc.update("insert into Contenido(nro_semana,nro_seccion,archivo) "
                + " values(?,?,?)", parametros);

        return res;
    }

    public List<Contenido> ListarSemanas(int nroSemana, int nroSeccion) {
        String sql = "select  id_contenido,archivo from contenido where nro_semana = ? and nro_seccion = ?";
        Object[] parametros = {nroSemana, nroSeccion};
        List<Contenido> lista = jdbc.query(sql, new RowMapper<Contenido>() {
            @Override
            public Contenido mapRow(ResultSet rs, int i) throws SQLException {
                Contenido c = new Contenido();
                c.setId_contenido(rs.getInt(1));
                c.setArchivo(rs.getString(2));
                return c;
            }
        }, parametros);

        return lista;
    }
}
