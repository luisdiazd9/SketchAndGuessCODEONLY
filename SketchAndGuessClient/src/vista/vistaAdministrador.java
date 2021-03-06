/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Partida;
import modelo.Usuario;

/**
 *
 * @author luisd
 */
public class vistaAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form vistaDibujante
     */
    public Partida partida = new Partida();
    JFrame f;
    public Usuario usuarioActual = new Usuario();

    public vistaAdministrador() {
        initComponents();
        this.getContentPane().setBackground(new Color(201, 203, 236));
        setFocusableWindowState(true);
        this.setLocationRelativeTo(null);
        partida.conexionServidor();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        botonJugar = new javax.swing.JButton();
        botonTiempo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setBackground(new java.awt.Color(201, 203, 236));
        setFocusableWindowState(false);
        setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 800));

        jLabelLogo.setBackground(new java.awt.Color(0, 216, 198));
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSketchAndGuessSmall.png"))); // NOI18N

        botonJugar.setBackground(new java.awt.Color(0, 192, 126));
        botonJugar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonJugar.setForeground(new java.awt.Color(255, 255, 255));
        botonJugar.setText("Jugar");
        botonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJugarActionPerformed(evt);
            }
        });

        botonTiempo.setBackground(new java.awt.Color(0, 186, 170));
        botonTiempo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonTiempo.setForeground(new java.awt.Color(255, 255, 255));
        botonTiempo.setText("Cambiar tiempo de partida");
        botonTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTiempoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(424, 424, 424))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(541, 541, 541))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(botonTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(botonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTiempoActionPerformed
        // TODO add your handling code here:
        String tiempoSt = JOptionPane.showInputDialog(
                f,
                "Digita el nuevo tiempo de partida en segundos",
                "Cambiar Tiempo",
                JOptionPane.QUESTION_MESSAGE);
        try {
            int tiempo = Integer.parseInt(tiempoSt);
            if (0 == partida.cambiarTiempoPartida(tiempo, usuarioActual.getRegion())) {
                JOptionPane.showMessageDialog(
                        f,
                        "Cambio guardado satisfactoriamente");
                Partida.tiempoPartida = tiempo;
            } else {
                JOptionPane.showMessageDialog(
                        f,
                        "Error, intenta nuevamente");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    f,
                    "Dato inv??lido, debe ser un n??mero");
        }
    }//GEN-LAST:event_botonTiempoActionPerformed

    private void botonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJugarActionPerformed
        // TODO add your handling code here:
        vistaSalaEspera sala = new vistaSalaEspera();
        sala.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonJugarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaAdministrador().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonJugar;
    private javax.swing.JButton botonTiempo;
    private javax.swing.JLabel jLabelLogo;
    // End of variables declaration//GEN-END:variables
}
