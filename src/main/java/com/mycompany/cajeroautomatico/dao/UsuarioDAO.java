package com.mycompany.cajeroautomatico.dao;

import com.mycompany.cajeroautomatico.logica.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

    public void save(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(usuario);
        tx.commit();
        session.close();
    }

    public void update(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(usuario);
        tx.commit();
        session.close();
    }

    public void delete(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(usuario);
        tx.commit();
        session.close();
    }

    public Usuario findById(int id) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = session.get(Usuario.class, id);
        session.close();
        return usuario;
    }

    public Usuario findByNumeroTarjeta(String numeroTarjeta) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = session
            .createQuery("FROM Usuario WHERE numeroTarjeta = :nt", Usuario.class)
            .setParameter("nt", numeroTarjeta)
            .getSingleResult();
        session.close();
        return usuario;
    }

    public List<Usuario> findAll() {
        Session session = HibernateUtil.getSession();
        List<Usuario> usuarios = session.createQuery("FROM Usuario", Usuario.class).list();
        session.close();
        return usuarios;
    }

    public Usuario findByNumeroTarjetaYPin(String numeroTarjeta, String pin) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = session
            .createQuery("FROM Usuario WHERE numeroTarjeta = :nt AND pin = :pin", Usuario.class)
            .setParameter("nt", numeroTarjeta)
            .setParameter("pin", pin)
            .getSingleResult();
        session.close();
        return usuario;
    }

    public Usuario findByNumeroCuenta(String numeroCuenta) {
        Session session = HibernateUtil.getSession();
        Usuario usuario = session
            .createQuery("FROM Usuario WHERE numeroCuenta = :nc", Usuario.class)
            .setParameter("nc", numeroCuenta)
            .getSingleResult();
        session.close();
        return usuario;
    }
}