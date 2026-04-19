/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cajeroautomatico.igu;

import com.mycompany.cajeroautomatico.logica.Movimiento;
import com.mycompany.cajeroautomatico.logica.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author midnight
 */
public class MenuFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuFrame.class.getName());
    private Usuario usuario;
    private Double montoDeposito;
    private List<Movimiento> historialTransacciones;
    private Double montoRetiro;
    private double saldoDisponible;
    //private final List<CuentaBancaria> cuentasDisponibles;

    private void MostrarDatos() {
        saldoDisponible = usuario.consultarSaldo();
        historialTransacciones = usuario.getHistorialMovimientos();
        lblNombreUsuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        lblNumeroCuenta.setText(usuario.getNumeroCuenta());
        lblSaldo.setText(String.format("$%.2f", saldoDisponible));
        lblSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    }

    private JList<Movimiento> lista;

    public void mostrarMovimientos() {
        DefaultListModel<Movimiento> modelo = new DefaultListModel<>();

        for (Movimiento m : usuario.getHistorialMovimientos()) {
            modelo.addElement(m);
        }

        if (lista == null) {
            lista = new JList<>();
            spMovimientos.setViewportView(lista);
        }

        lista.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lista.setForeground(Color.decode("#444441"));
        lista.setSelectionBackground(Color.decode("#EAF3DE"));
        lista.setSelectionForeground(Color.decode("#444441"));
        lista.setModel(modelo);
    }

    /**
     * Creates new form MenuFrame
     */
    public MenuFrame(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        MostrarDatos();
        mostrarMovimientos();
    }

    private void realizarDeposito() {
        String entrada = JOptionPane.showInputDialog(this, "Ingrese monto a depositar:");
        if (entrada == null) {
            return;
        }
        try {
            montoDeposito = Double.parseDouble(entrada.trim());
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "Desea confirmar el deposito de " + String.format("$%.2f", montoDeposito) + "?",
                    "Confirmar deposito",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }
            usuario.depositar(montoDeposito);
            MostrarDatos();
            JOptionPane.showMessageDialog(this, "Deposito realizado correctamente.");
            mostrarMovimientos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada invalida. Debe ingresar un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarRetiro() {
        String entrada = JOptionPane.showInputDialog(this, "Ingrese monto a retirar:");
        if (entrada == null) {
            return;
        }
        try {
            montoRetiro = Double.parseDouble(entrada.trim());
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "Desea confirmar el retiro de " + String.format("$%.2f", montoRetiro) + "?",
                    "Confirmar retiro",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }
            usuario.retirar(montoRetiro);
            MostrarDatos();
            JOptionPane.showMessageDialog(this, "Retiro realizado correctamente.");
            mostrarMovimientos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada invalida. Debe ingresar un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarTransferencia() {
        String cuenta = JOptionPane.showInputDialog(this, "Ingrese cuenta destino:");
        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "Cuenta no encontrada.");
            return;
        }

        Usuario destino = Usuario.buscarPorCuenta(cuenta);

        if (destino == null) {
            JOptionPane.showMessageDialog(this, "Cuenta no encontrada.");
            return;
        }

        String entrada = JOptionPane.showInputDialog(
                this,
                "Monto a transferir para " + destino.getNombre() + ":"
        );
        if (entrada == null) {
            return;
        }

        try {
            double monto = Double.parseDouble(entrada.trim());

            usuario.transferir(destino, monto);

            MostrarDatos();
            JOptionPane.showMessageDialog(this, "Transferencia realizada.");
            mostrarMovimientos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Desea cerrar sesion?",
                "Confirmar cierre de sesion",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            InicioFrame loginFrame = new InicioFrame();
            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);
            dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroCuenta = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnTransferir = new javax.swing.JButton();
        spMovimientos = new javax.swing.JScrollPane();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 247, 242));

        jLabel1.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel1.setText("Saldo:");

        jLabel2.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 52, 4));
        jLabel2.setText("Dashboard Cajero");

        jLabel3.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(39, 80, 10));
        jLabel3.setText("Usuario: ");

        lblNumeroCuenta.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblNumeroCuenta.setForeground(new java.awt.Color(39, 80, 10));
        lblNumeroCuenta.setText("Prueba");

        lblNombreUsuario.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(39, 80, 10));
        lblNombreUsuario.setText("Prueba");

        jLabel4.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(39, 80, 10));
        jLabel4.setText("Nº Cuenta:");

        lblSaldo.setBackground(new java.awt.Color(255, 255, 255));
        lblSaldo.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(23, 52, 4));
        lblSaldo.setText("10");
        lblSaldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblSaldo.setOpaque(true);

        btnCerrarSesion.setBackground(new java.awt.Color(59, 109, 17));
        btnCerrarSesion.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logout.png"))); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        btnDepositar.setBackground(new java.awt.Color(59, 109, 17));
        btnDepositar.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnDepositar.setForeground(new java.awt.Color(255, 255, 255));
        btnDepositar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus (1).png"))); // NOI18N
        btnDepositar.setText("   Depositar   ");
        btnDepositar.addActionListener(this::btnDepositarActionPerformed);

        btnRetirar.setBackground(new java.awt.Color(153, 60, 29));
        btnRetirar.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnRetirar.setForeground(new java.awt.Color(255, 255, 255));
        btnRetirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minus.png"))); // NOI18N
        btnRetirar.setText("   Retirar   ");
        btnRetirar.addActionListener(this::btnRetirarActionPerformed);

        btnTransferir.setBackground(new java.awt.Color(99, 153, 34));
        btnTransferir.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnTransferir.setForeground(new java.awt.Color(255, 255, 255));
        btnTransferir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Paper Plane.png"))); // NOI18N
        btnTransferir.setText("   Tranferir   ");
        btnTransferir.addActionListener(this::btnTransferirActionPerformed);

        spMovimientos.setBackground(new java.awt.Color(255, 255, 255));
        spMovimientos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(192, 221, 151)));
        spMovimientos.setForeground(new java.awt.Color(68, 68, 65));
        spMovimientos.setFont(new java.awt.Font("FiraCode Nerd Font Mono", 0, 15)); // NOI18N
        spMovimientos.setOpaque(true);

        btnSalir.setBackground(new java.awt.Color(153, 60, 29));
        btnSalir.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(609, 609, 609)
                        .addComponent(btnCerrarSesion))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(spMovimientos, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnDepositar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnRetirar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTransferir))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(316, 316, 316))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblNumeroCuenta))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(spMovimientos, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        // TODO add your handling code here:
        realizarDeposito();
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        cerrarSesion();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        // TODO add your handling code here:
        realizarRetiro();
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        // TODO add your handling code here:
        realizarTransferencia();
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Desea Salir?",
                "Confirmar su salida",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblNumeroCuenta;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JScrollPane spMovimientos;
    // End of variables declaration//GEN-END:variables
}
