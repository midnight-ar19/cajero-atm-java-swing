/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico.logica;

import java.util.ArrayList;
import java.util.List;

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
    private String tipoCuenta;
    private String pin;
    private List<Movimiento> movimientos;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String numeroCuenta, String numeroTarjeta,
            double saldo, String tipoCuenta, String pin, List<Movimiento> movimientos) {
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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double consultarSaldo() {
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

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Movimiento> getHistorialMovimientos() {
        return movimientos;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de deposito debe ser positivo.");
        }
        saldo += monto;
        movimientos.add(new Movimiento(Tipo.DEPOSITO, monto, this));
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de retiro debe ser positivo.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
        movimientos.add(new Movimiento(Tipo.RETIRO, monto, this));
    }

    public void transferir(Usuario destino, double monto) {
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
}
