package com.mycompany.cajeroautomatico.database;

import com.mycompany.cajeroautomatico.dao.HibernateUtil;
import com.mycompany.cajeroautomatico.logica.Usuario;
import java.util.List;
import org.hibernate.Session;

public class TestHibernateConnection {
    public static void main(String[] args) {
        System.out.println("=== Probando conexion con Hibernate ===");
        
        try (Session session = HibernateUtil.getSession()) {
            System.out.println("Conexion exitosa!");
            System.out.println("Base de datos: atm_db");
            
            List<Usuario> usuarios = session.createQuery("FROM Usuario", Usuario.class).list();
            System.out.println("Usuarios en BD: " + usuarios.size());
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}