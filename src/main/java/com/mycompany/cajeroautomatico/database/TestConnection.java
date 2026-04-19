package com.mycompany.cajeroautomatico.database;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Probando conexion a PostgreSQL ===");
        System.out.println();
        
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        System.out.println("Intentando conectar...");
        db.testConnection();
        
        System.out.println();
        System.out.println("=== Prueba completada exitosamente ===");
    }
}