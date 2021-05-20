package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.Contenido;
import com.edu.pe.modelo.Foro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ForoDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Foro> ListarForo(int nroSemana, int nroSeccion) {
        String sql = "select * from foro where nro_semana = ? and nro_seccion = ?";
        Object[] parametros = {nroSemana, nroSeccion};
        List<Foro> lista = jdbc.query(sql, new ForoRowMapper(), parametros);

        return lista;
    }

    public int GuardarForo(Foro obj) {
        Object[] parametros = {obj.getNro_semana(), obj.getNro_seccion(), obj.getFechaInicio(), obj.getFechaCierre(), obj.getDescripcion(), obj.getTitulo()};
        int res = jdbc.update("insert into Foro(nro_semana,nro_seccion,fechaInicio,fechaCierre,descripcion,titulo) "
                + " values(?,?,?,?,?,?)", parametros);

        return res;
    }

    public int ActualizarForo(Foro obj) {
        Object[] parametros = {obj.getTitulo(), obj.getFechaInicio(), obj.getFechaCierre(), obj.getDescripcion(), obj.getId_foro()};
        int res = jdbc.update("update Foro set titulo=?,fechaInicio=?,fechaCierre=?,descripcion=? where id_foro=? ", parametros);

        return res;
    }

    public Foro BuscarPorId(int idForo) {
        Foro objForo = jdbc.queryForObject("select * from foro where id_foro = ?", new ForoRowMapper(), new Object[]{idForo});
        return objForo;
    }

    public int Eliminar(int idForo) {
        return jdbc.update("delete from foro where id_foro = ?", idForo);
    }
}

class ForoRowMapper implements RowMapper<Foro> {

    @Override
    public Foro mapRow(ResultSet rs, int i) throws SQLException {
        Foro c = new Foro();
        c.setId_foro(rs.getInt(1));
        c.setNro_semana(rs.getInt(2));
        c.setNro_seccion(rs.getInt(3));
        c.setTitulo(rs.getString(4));
        c.setFechaInicio(rs.getString(5));
        c.setFechaCierre(rs.getString(6));
        c.setDescripcion(rs.getString(7));

        return c;
    }
}
