/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico.logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Movimiento {

    private int id;
    private Tipo tipo;
    private double monto;
    private LocalDateTime fechaHora;
    private Usuario usuario;

    public Movimiento() {
    }

    public Movimiento(Tipo tipo, double monto, Usuario usuario) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   @Override
public String toString() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    return String.format(
        "ID: %-3d | Tipo: %-10s | Monto: %-8.2f | Fecha: %s",
        id, tipo, monto, fechaHora.format(formato)
    );
}

}
