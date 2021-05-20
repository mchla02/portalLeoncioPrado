package com.edu.pe.modeloDAO;

import com.edu.pe.modelo.Comentario;
import com.edu.pe.modelo.Foro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public int Guardar(Comentario obj) {
        Object[] parametros = {obj.getId_foro(), obj.getId_usuario(), obj.getDesc_comentario()};
        int res = jdbc.update("insert into Comentario(id_foro,id_usuario,desc_comentario) "
                + " values(?,?,?)", parametros);

        return res;
    }

    public List<Comentario> ListarPorForo(int idForo) {
        String sql = "{call sp_listarComentarios(?)}";
        List<Comentario> lista = jdbc.query(sql, new RowMapper<Comentario>() {
            @Override
            public Comentario mapRow(ResultSet rs, int i) throws SQLException {
                Comentario c = new Comentario();
                c.setNomUsuario(rs.getString(1));
                c.setDesc_comentario(rs.getString(2));
                return c;
            }
        }, idForo);

        return lista;
    }
}

class ComentarioRowMapper implements RowMapper<Comentario> {

    @Override
    public Comentario mapRow(ResultSet rs, int i) throws SQLException {
        Comentario c = new Comentario();
        c.setId_comentario(rs.getInt(1));
        c.setId_foro(rs.getInt(2));
        c.setId_usuario(rs.getInt(3));
        c.setDesc_comentario(rs.getString(4));

        return c;
    }
}
