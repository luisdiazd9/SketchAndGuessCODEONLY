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
public class vistaResultados extends javax.swing.JFrame {

    /**
     * Creates new form vistaDibujante
     */
    private int colorEspera = 1;
    public Usuario usuarioActual = new Usuario();
    public Partida partida = new Partida();
    private String[] usuariosTodos = new String[6];
    JFrame f;

    public vistaResultados() {
        initComponents();
        this.getContentPane().setBackground(new Color(161, 255, 223));
        setFocusableWindowState(true);
        this.setLocationRelativeTo(null);
        partida.conexionServidor();
        datosIniciales();
    }

    private void validarPuntaje(int puntajePartida) {
        if (puntajePartida > Usuario.puntajeMaximo) {
            partida.nuevoPuntajeMaximo(Usuario.nombreUsuario, Usuario.idUsuario, puntajePartida);
            JOptionPane.showMessageDialog(
                    f,
                    "¿Nuevo récord personal!");
        }
    }

    private void acabar(int opcion) { //0 para salir, 1 para volver a jugar
        if (Usuario.idEnPartida == 0) {
            partida.limpiarRegion(Usuario.region);
        }
        JOptionPane.showMessageDialog(
                f,
                "Gracias por jugar :D");
        if (opcion == 1) {
            vistaLogin nuevologin = new vistaLogin();
            nuevologin.setVisible(true);
            this.setVisible(false);
        } else {
            System.exit(0);
        }
    }

    private void datosIniciales() {
        jLabelPrimero.setVisible(false);
        jLabelResultadoPri.setVisible(false);
        jLabelSegundo1.setText(null);
        jLabelTercero.setText(null);
        jLabelCuarto.setText(null);
        jLabelQuinto.setText(null);
        jLabelResultadoSeg.setText(null);
        jLabelResultadoTer.setText(null);
        jLabelResultadoCua.setText(null);
        jLabelResultadoQui.setText(null);
        usuariosTodos[0] = null;
        usuariosTodos[1] = null;
        usuariosTodos[2] = null;
        usuariosTodos[3] = null;
        usuariosTodos[4] = null;
        usuariosTodos[5] = null;
    }

    private void colocarDatosVIDEO() {
        jLabelPrimero.setVisible(true);
        jLabelResultadoPri.setVisible(true);
        jLabelPrimero.setText("Luis");
        jLabelResultadoPri.setText("176");

        jLabelSegundo1.setText("lenin");
        jLabelResultadoSeg.setText("168");

        jLabelTercero.setText("diyani");
        jLabelResultadoTer.setText("164");
    }

    private void colocarDatos() {
        usuariosTodos = partida.obtenerUsuarios(Usuario.region);
        jLabelPrimero.setText(Usuario.nombreUsuario);
        int puntajePartida = partida.obtenerPuntajePartida(Usuario.region, Usuario.idEnPartida);
        jLabelResultadoPri.setText(String.valueOf(puntajePartida));
        jLabelPrimero.setVisible(true);
        jLabelResultadoPri.setVisible(true);
        int[] puntajesUsuarios = partida.puntajesUsuarios;
        int opciones = partida.obtenerCantidadJugadores(Usuario.region);
        switch (Usuario.idEnPartida) {
            case 0:
                jLabelSegundo1.setText(usuariosTodos[1]);
                jLabelTercero.setText(usuariosTodos[2]);
                jLabelCuarto.setText(usuariosTodos[3]);
                jLabelQuinto.setText(usuariosTodos[4]);
                switch (opciones) {
                    case 2:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        break;
                    case 3:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        break;
                    case 4:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        break;
                    case 5:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        jLabelResultadoQui.setText(String.valueOf(puntajesUsuarios[4]));
                        break;
                }
                break;
            case 1:
                jLabelSegundo1.setText(usuariosTodos[0]);
                jLabelTercero.setText(usuariosTodos[2]);
                jLabelCuarto.setText(usuariosTodos[3]);
                jLabelQuinto.setText(usuariosTodos[4]);
                switch (opciones) {
                    case 2:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[0]));
                        break;
                    case 3:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        break;
                    case 4:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        break;
                    case 5:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        jLabelResultadoQui.setText(String.valueOf(puntajesUsuarios[4]));
                        break;
                }
                break;
            case 2:
                jLabelSegundo1.setText(usuariosTodos[1]);
                jLabelTercero.setText(usuariosTodos[0]);
                jLabelCuarto.setText(usuariosTodos[3]);
                jLabelQuinto.setText(usuariosTodos[4]);
                switch (opciones) {
                    case 3:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[0]));
                        break;
                    case 4:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        break;
                    case 5:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                        jLabelResultadoQui.setText(String.valueOf(puntajesUsuarios[4]));
                        break;
                }
                break;
            case 3:
                jLabelSegundo1.setText(usuariosTodos[1]);
                jLabelTercero.setText(usuariosTodos[2]);
                jLabelCuarto.setText(usuariosTodos[0]);
                jLabelQuinto.setText(usuariosTodos[4]);
                switch (opciones) {
                    case 4:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[0]));
                        break;
                    case 5:
                        jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                        jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                        jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[0]));
                        jLabelResultadoQui.setText(String.valueOf(puntajesUsuarios[4]));
                        break;
                }
                break;
            case 4:
                jLabelSegundo1.setText(usuariosTodos[1]);
                jLabelTercero.setText(usuariosTodos[2]);
                jLabelCuarto.setText(usuariosTodos[3]);
                jLabelQuinto.setText(usuariosTodos[0]);

                jLabelResultadoSeg.setText(String.valueOf(puntajesUsuarios[1]));
                jLabelResultadoTer.setText(String.valueOf(puntajesUsuarios[2]));
                jLabelResultadoCua.setText(String.valueOf(puntajesUsuarios[3]));
                jLabelResultadoQui.setText(String.valueOf(puntajesUsuarios[0]));
                break;
        }
        validarPuntaje(puntajePartida);
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
        jLabelPrimero = new javax.swing.JLabel();
        lblActualizar = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();
        jLabelQuinto = new javax.swing.JLabel();
        jLabelCuarto = new javax.swing.JLabel();
        jLabelTercero = new javax.swing.JLabel();
        jLabelResultadoPri = new javax.swing.JLabel();
        jLabelResultadoTer = new javax.swing.JLabel();
        jLabelResultadoSeg = new javax.swing.JLabel();
        jLabelResultadoQui = new javax.swing.JLabel();
        jLabelResultadoCua = new javax.swing.JLabel();
        jLabelSegundo1 = new javax.swing.JLabel();
        lblActualizar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Resultados");
        setBackground(new java.awt.Color(161, 255, 223));
        setFocusableWindowState(false);
        setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogo.setBackground(new java.awt.Color(0, 216, 198));
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSketchAndGuessSmall.png"))); // NOI18N
        getContentPane().add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 636, 118));

        botonJugar.setBackground(new java.awt.Color(0, 192, 126));
        botonJugar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonJugar.setForeground(new java.awt.Color(255, 255, 255));
        botonJugar.setText("Jugar de nuevo");
        botonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJugarActionPerformed(evt);
            }
        });
        getContentPane().add(botonJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 590, 239, 56));

        jLabelPrimero.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelPrimero.setForeground(new java.awt.Color(38, 51, 226));
        jLabelPrimero.setText("Primero");
        getContentPane().add(jLabelPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 192, -1, -1));

        lblActualizar.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        lblActualizar.setForeground(new java.awt.Color(87, 98, 241));
        lblActualizar.setText("Espera a los demás jugadores");
        getContentPane().add(lblActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, 60));

        botonSalir.setBackground(new java.awt.Color(255, 102, 102));
        botonSalir.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 255, 255));
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 660, 239, 56));

        jLabelQuinto.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelQuinto.setForeground(new java.awt.Color(38, 51, 226));
        jLabelQuinto.setText("Quinto");
        getContentPane().add(jLabelQuinto, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 497, -1, -1));

        jLabelCuarto.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelCuarto.setForeground(new java.awt.Color(87, 98, 241));
        jLabelCuarto.setText("Cuarto");
        getContentPane().add(jLabelCuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 417, -1, -1));

        jLabelTercero.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelTercero.setForeground(new java.awt.Color(38, 51, 226));
        jLabelTercero.setText("Tercero");
        getContentPane().add(jLabelTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 342, -1, -1));

        jLabelResultadoPri.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelResultadoPri.setForeground(new java.awt.Color(204, 0, 102));
        jLabelResultadoPri.setText("100");
        getContentPane().add(jLabelResultadoPri, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 192, -1, -1));

        jLabelResultadoTer.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelResultadoTer.setForeground(new java.awt.Color(0, 153, 0));
        jLabelResultadoTer.setText("98");
        getContentPane().add(jLabelResultadoTer, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 342, -1, -1));

        jLabelResultadoSeg.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelResultadoSeg.setForeground(new java.awt.Color(0, 153, 51));
        jLabelResultadoSeg.setText("99");
        getContentPane().add(jLabelResultadoSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 264, -1, -1));

        jLabelResultadoQui.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelResultadoQui.setForeground(new java.awt.Color(255, 0, 153));
        jLabelResultadoQui.setText("96");
        getContentPane().add(jLabelResultadoQui, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 497, -1, -1));

        jLabelResultadoCua.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelResultadoCua.setForeground(new java.awt.Color(255, 0, 153));
        jLabelResultadoCua.setText("97");
        getContentPane().add(jLabelResultadoCua, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 417, -1, -1));

        jLabelSegundo1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelSegundo1.setForeground(new java.awt.Color(87, 98, 241));
        jLabelSegundo1.setText("Segundo");
        getContentPane().add(jLabelSegundo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 264, -1, -1));

        lblActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update.png"))); // NOI18N
        lblActualizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActualizar1MouseClicked(evt);
            }
        });
        getContentPane().add(lblActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJugarActionPerformed
        // TODO add your handling code here:
        acabar(1);
    }//GEN-LAST:event_botonJugarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        // TODO add your handling code here:
        acabar(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void lblActualizar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizar1MouseClicked
        // TODO add your handling code here:

        if (partida.verificarPalabrasCompletas(Usuario.region) == 1) { //Si retornó 1, quiere decir que ya están todos los usuarios (se valida que todos hayan escrito palabra)
            lblActualizar.setVisible(false);
            lblActualizar1.setVisible(false);
//            colocarDatos();
            colocarDatosVIDEO();
        } else {
            if (colorEspera == 1) {
                lblActualizar.setForeground(new Color(255, 255, 188));
                colorEspera = 0;
            } else {
                lblActualizar.setForeground(Color.white);
                colorEspera = 1;
            }
        }

    }//GEN-LAST:event_lblActualizar1MouseClicked

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
            java.util.logging.Logger.getLogger(vistaResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new vistaResultados().setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonJugar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabelCuarto;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelPrimero;
    private javax.swing.JLabel jLabelQuinto;
    private javax.swing.JLabel jLabelResultadoCua;
    private javax.swing.JLabel jLabelResultadoPri;
    private javax.swing.JLabel jLabelResultadoQui;
    private javax.swing.JLabel jLabelResultadoSeg;
    private javax.swing.JLabel jLabelResultadoTer;
    private javax.swing.JLabel jLabelSegundo1;
    private javax.swing.JLabel jLabelTercero;
    private javax.swing.JLabel lblActualizar;
    private javax.swing.JLabel lblActualizar1;
    // End of variables declaration//GEN-END:variables
}
