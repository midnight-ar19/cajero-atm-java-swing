/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico.logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author midnight
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private String numeroCuenta;
    private String numeroTarjeta;
    private double saldo;
    private TipoCuenta tipoCuenta;
    private String pin;
    private List<Movimiento> movimientos;

    private static Map<String, Usuario> usuarios = new HashMap<>();

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String numeroCuenta, String numeroTarjeta,
            double saldo, TipoCuenta tipoCuenta, String pin, List<Movimiento> movimientos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroCuenta = numeroCuenta;
        this.numeroTarjeta = numeroTarjeta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.pin = pin;
        this.movimientos = (movimientos != null) ? movimientos : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public String getPin() {
        return pin;
    }

    public static void agregarUsuario(Usuario u) {
        usuarios.put(u.getNumeroTarjeta(), u);
    }

    public synchronized List<Movimiento> getHistorialMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }

    public synchronized double consultarSaldo() {
        return saldo;
    }

    public static Usuario validarUsuario(String numeroTarjeta, String pin) {
        Usuario usuario = usuarios.get(numeroTarjeta);
        if (usuario != null && usuario.getPin().equals(pin)) {
            return usuario;
        }
        return null;
    }

    public synchronized void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de deposito debe ser positivo.");
        }
        saldo += monto;
        movimientos.add(new Movimiento(Tipo.DEPOSITO, monto, this));
    }

    public synchronized void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de retiro debe ser positivo.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
        movimientos.add(new Movimiento(Tipo.RETIRO, monto, this));
    }

    public synchronized void transferir(Usuario destino, double monto) {
        if (destino == null) {
            throw new IllegalArgumentException("Cuenta destino inválida.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        this.saldo -= monto;
        destino.saldo += monto;

        this.movimientos.add(new Movimiento(Tipo.TRANSFERENCIA, monto, this));
        destino.movimientos.add(new Movimiento(Tipo.DEPOSITO, monto, destino));
    }
    
    public static Usuario buscarPorCuenta(String cuenta) {
    for (Usuario u : usuarios.values()) {
        if (u.getNumeroCuenta().equals(cuenta)) {
            return u;
        }
    }
    return null;
}

}
