package com.edu.pe.modelo;

public class Comentario {

    private int id_comentario;
    private int id_foro;
    private int id_usuario;
    private String desc_comentario;
    private String nomUsuario;

    @Override
    public String toString() {
        return "Comentario{" + "id_comentario=" + id_comentario + ", id_foro=" + id_foro + ", id_usuario=" + id_usuario + ", desc_comentario=" + desc_comentario + '}';
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDesc_comentario() {
        return desc_comentario;
    }

    public void setDesc_comentario(String desc_comentario) {
        this.desc_comentario = desc_comentario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    
}
