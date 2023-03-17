package com.dmjsistemas.dao;

import com.dmjsistemas.model.Usuario;

public interface IUsuarioDao {

    public Usuario obtenerDatosUsuario(Usuario usuario);

    public Usuario login(Usuario usuario);

    public void guardarUsuario(Usuario usuario);

    public void actualizarUsuario(Usuario usuario);

    public void borrarUsuario(Usuario usuario);
}
