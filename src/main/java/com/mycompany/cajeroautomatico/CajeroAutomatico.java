/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cajeroautomatico;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.cajeroautomatico.igu.InicioFrame;
import com.mycompany.cajeroautomatico.logica.Usuario;
import java.awt.Color;
import javax.swing.UIManager;


public class CajeroAutomatico {

    public static void main(String[] args) {

        UIManager.put("Component.arc", 15);
        UIManager.put("Button.arc", 20);
        UIManager.put("TextComponent.arc", 15);
        UIManager.put("ProgressBar.arc", 20);
        UIManager.put("ScrollBar.thumbArc", 20);
        UIManager.put("Component.focusWidth", 1);

        Color verde = Color.decode("#C0DD97");
        Color cancelar = Color.decode("#993C1D");
        Color verdeAceptar = Color.decode("#3B6D11");

        UIManager.put("Component.focusColor", verde);
        UIManager.put("Button.focusedBorderColor", verde);

        UIManager.put("Button.default.background", verdeAceptar);
        UIManager.put("Button.default.foreground", Color.WHITE);

        UIManager.put("Button.default.startBackground", verdeAceptar);
        UIManager.put("Button.default.endBackground", verdeAceptar);

        UIManager.put("Button.default.focusedBackground", verdeAceptar);

        UIManager.put("Button.background", cancelar);
        UIManager.put("Button.foreground", Color.WHITE);

        FlatLightLaf.setup();

     
        Usuario u1 = new Usuario(1, "Alan Fabricio", "Alvarez Ramos", "001", "1111", 1000,
                "AHORRO", "1234", null);
        Usuario u2 = new Usuario(2, "Zury Nicol", "Castillo Rivera", "002", "2222", 500,
                "CORRIENTE", "4321", null);

        Usuario.agregarUsuario(u1);
        Usuario.agregarUsuario(u2);

        InicioFrame ventana = new InicioFrame();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

    }

}
