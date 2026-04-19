/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroautomatico.logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Movimiento {

    private static int contadorId = 1;

    private final int id;
    private final Tipo tipo;
    private final double monto;
    private final LocalDateTime fechaHora;

    public Movimiento(Tipo tipo, double monto) {
        this.id = contadorId++;
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
    }

    public int getId() {
        return id;
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
