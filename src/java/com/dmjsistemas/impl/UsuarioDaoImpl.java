package com.dmjsistemas.impl;

import com.dmjsistemas.dao.IUsuarioDao;
import com.dmjsistemas.model.Usuario;
import com.dmjsistemas.util.HibernateUtil;
import com.dmjsistemas.util.Password;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuario obtenerDatosUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Usuario WHERE nombre=:nombre AND clave=:clave";
        Query q = session.createQuery(hql).setMaxResults(1);
        q.setParameter("nombre", usuario.getNombre());
        q.setParameter("clave", Password.sha512(usuario.getClave()));
        return (Usuario) q.uniqueResult();
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario user = this.obtenerDatosUsuario(usuario);
        if (user != null) {
            if (!user.getClave().equals(Password.sha512(usuario.getClave()))) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
