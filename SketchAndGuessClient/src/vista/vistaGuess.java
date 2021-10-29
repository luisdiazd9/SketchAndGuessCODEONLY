/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Dibujo;
import modelo.Partida;
import modelo.Usuario;

/**
 *
 * @author luisd
 */
public class vistaGuess extends javax.swing.JFrame {

    /**
     * Creates new form vistaDibujante
     */
    Dibujo canvas1;
    BufferedImage image;
    public Partida partida = new Partida();
    JFrame f;
    public Usuario usuarioActual = new Usuario();
    private int colorEspera = 1;
    private ImageIcon icono;

    public vistaGuess() {
        initComponents();
        this.getContentPane().setBackground(new Color(80, 207, 164));
        setFocusableWindowState(true);
        this.setLocationRelativeTo(null);
        barraDeTiempo.setVisible(false);
        jlabelTextoAdvinar1.setVisible(false);
        fieldAdivinar.setVisible(false);
        botonAdivinar.setVisible(false);
        partida.conexionServidor();
        jlabelNombre.setText(Usuario.nombreUsuario);
    }

    Thread hiloTiempo = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i <= Partida.tiempoPartida; i++) {
                try {
                    Thread.sleep(1000);
                    barraDeTiempo.setValue(i);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } //Se acaba el tiempo
            JOptionPane.showMessageDialog(
                    f,
                    "¡TIEMPO!");
            itsDone();
        }
    };

    private void itsDone() {
        try {
            String era = partida.palabraInicial(Usuario.region, Usuario.idEnPartida);
            String acaban = partida.palabraAnterior(Usuario.region, Usuario.idEnPartida);
            String aMostrar = "¿Cómo calificarías este dibujo?\nLa palabra inicial era: " + era + "\nY acaban de intentar dibujar: " + acaban;
            Object seleccion = JOptionPane.showInputDialog(
                    f,
                    aMostrar,
                    "A calificar!",
                    JOptionPane.QUESTION_MESSAGE,
                    null, // null para icono defecto
                    new Object[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                    1);
            int puntaje = (Integer.parseInt(String.valueOf(seleccion))) * 12;
            partida.enviarPalabra(fieldAdivinar.getText(), Usuario.region, Usuario.idEnPartida, puntaje);
            if (partida.lastRound(Usuario.region)) { //Es la última ronda, se procede a la parte de resultados
                JOptionPane.showMessageDialog(
                        f,
                        "Hemos terminado :(");
                vistaResultados resultados = new vistaResultados();
                resultados.setVisible(true);
                this.setVisible(false);
            } else { //No es la última ronda
                JOptionPane.showMessageDialog(
                        f,
                        "¡Es hora de Dibujar!");
                vistaSketch sketch = new vistaSketch();
//                vistaGuess guess = new vistaGuess();
                sketch.setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    private void mostrarCanvas() {
        lblActualizar.setVisible(false);
        lblEspera.setVisible(false);
        jlabelTextoAdvinar1.setVisible(true);
        fieldAdivinar.setVisible(true);
        botonAdivinar.setVisible(true);
        barraDeTiempo.setVisible(true);
        canvas1 = new Dibujo();
        canvas1.setBounds(43, 150, 1170, 466);
        canvas1.addMouseMotionListener(new java.awt.event.MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                canvas1.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        canvas1.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                canvas1.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas1.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(canvas1);
        canvas1.m_alzada = true;
        canvas1.changeColor(Color.white);
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
        barraDeTiempo = new javax.swing.JProgressBar();
        lblEspera = new javax.swing.JLabel();
        botonAdivinar = new javax.swing.JButton();
        fieldAdivinar = new javax.swing.JTextField();
        jlabelTextoAdvinar1 = new javax.swing.JLabel();
        lblActualizar = new javax.swing.JLabel();
        jlabelNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guess");
        setBackground(new java.awt.Color(80, 207, 164));
        setFocusableWindowState(false);
        setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 800));

        jLabelLogo.setBackground(new java.awt.Color(0, 216, 198));
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoSketchAndGuessSmall.png"))); // NOI18N

        barraDeTiempo.setBackground(new java.awt.Color(204, 255, 255));

        lblEspera.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblEspera.setForeground(new java.awt.Color(255, 255, 255));
        lblEspera.setText("Espera a los demás jugadores");

        botonAdivinar.setBackground(new java.awt.Color(0, 192, 126));
        botonAdivinar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonAdivinar.setForeground(new java.awt.Color(255, 255, 255));
        botonAdivinar.setText("¡LISTO!");
        botonAdivinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAdivinarActionPerformed(evt);
            }
        });

        fieldAdivinar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        fieldAdivinar.setText("Escribe aquí...");

        jlabelTextoAdvinar1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jlabelTextoAdvinar1.setForeground(new java.awt.Color(255, 255, 255));
        jlabelTextoAdvinar1.setText("¿Qué ves?");

        lblActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update.png"))); // NOI18N
        lblActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActualizarMouseClicked(evt);
            }
        });

        jlabelNombre.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jlabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jlabelNombre.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabelNombre)
                .addGap(114, 114, 114))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(barraDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jlabelTextoAdvinar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178)
                        .addComponent(botonAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(lblEspera)
                        .addGap(47, 47, 47)
                        .addComponent(lblActualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jlabelNombre)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(lblEspera))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblActualizar)))
                .addGap(346, 346, 346)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraDeTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelTextoAdvinar1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarMouseClicked
        // TODO add your handling code here:

        if (partida.verificarImagenesCompletas(Usuario.region) == 1) { //Si retornó 1, quiere decir que ya están todos los usuarios (se valida que todos hayan enviado la imagen) 
            Usuario.validadorAccion = 2; //Lo siguiente que hará es recibir una palabra
            icono = partida.imagenAdivinar(Usuario.region, Usuario.idEnPartida);
            mostrarCanvas();
            colocarImagen();
            hiloTiempo.start();
        } else {
            if (colorEspera == 1) {
                lblEspera.setForeground(new Color(255, 255, 188));
                colorEspera = 0;
            } else {
                lblEspera.setForeground(Color.white);
                colorEspera = 1;
            }
        }
    }//GEN-LAST:event_lblActualizarMouseClicked

    private void colocarImagen() {
        try {
            canvas1.setImg(icono.getImage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    private void botonAdivinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAdivinarActionPerformed
        // TODO add your handling code here:
        hiloTiempo.stop();
        itsDone();
    }//GEN-LAST:event_botonAdivinarActionPerformed

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
            java.util.logging.Logger.getLogger(vistaGuess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaGuess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaGuess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaGuess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaGuess().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraDeTiempo;
    private javax.swing.JButton botonAdivinar;
    private javax.swing.JTextField fieldAdivinar;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jlabelNombre;
    private javax.swing.JLabel jlabelTextoAdvinar1;
    private javax.swing.JLabel lblActualizar;
    private javax.swing.JLabel lblEspera;
    // End of variables declaration//GEN-END:variables
}
