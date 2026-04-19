package com.mycompany.cajeroautomatico.dao;

import com.mycompany.cajeroautomatico.logica.Movimiento;
import com.mycompany.cajeroautomatico.logica.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovimientoDAO {

    public void save(Movimiento movimiento) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(movimiento);
        tx.commit();
        session.close();
    }

    public void update(Movimiento movimiento) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(movimiento);
        tx.commit();
        session.close();
    }

    public List<Movimiento> findByUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSession();
        List<Movimiento> movimientos = session
            .createQuery("FROM Movimiento WHERE usuario = :usuario", Movimiento.class)
            .setParameter("usuario", usuario)
            .list();
        session.close();
        return movimientos;
    }
}