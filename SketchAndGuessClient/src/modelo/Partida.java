/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.iRMI;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vista.vistaLogin;

/**
 *
 * @author luisd
 */
public class Partida {

    static public int cantidadJugadores;
    public String[] listaUsuarios = new String[5];
    public String[] palabraJuego = new String[5];
    public int[] puntajesUsuarios = new int[5];
    private iRMI service;
    static public int tiempoPartida = 60;
    private int conexion = 0;
    BufferedImage imagenActual;

    public void conexionServidor() {
        try {
            if (conexion == 0) {
                conexion = 1;
                service = (iRMI) Naming.lookup("rmi://127.0.0.1:1802/service");  //Se establece la conexión con el servidor
            } else {
                System.out.println("Ya hay conexicón");
            }

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Relacionado con el login y registro
    public int login(String usuario, String clave, int region) {
        try {
            return service.login(usuario, clave, region);
        } catch (RemoteException ex) {
            Logger.getLogger(vistaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public int[] datosUsuario(String usuario) {
        int[] datosUs = new int[3];
        try {
            return service.datosUsuario(usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(vistaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosUs;
    }

    public int registro(String usuario, String clave, int region) {
        try {
            return service.registro(usuario, clave, region);
        } catch (RemoteException ex) {
            Logger.getLogger(vistaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    //Relacionado con el juego
    public int cambiarTiempoPartida(int tiempoNuevo, int region) {
        tiempoPartida = tiempoNuevo;
        try {
            return service.cambiarTiempoPartida(tiempoNuevo, region);
        } catch (RemoteException ex) {
            Logger.getLogger(vistaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int usuarioListo(int region, String nombre, int puntajeMaximo) {
        try {
            return service.usuarioListo(region, nombre, puntajeMaximo);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    public String[] obtenerUsuarios(int region) {
        try {
            return service.obtenerUsuarios(region);
        } catch (RemoteException ex) {
            return null;
        }
    }

    public void empiezanPartida(int region) {
        try {
            service.empiezanPartida(region);
        } catch (RemoteException ex) {

        }
    }

    //Ya en juego
    public void primeraPalabra(int region, int idEnPartida, String palabra) {
        try {
            service.primeraPalabra(region, idEnPartida, palabra);
        } catch (RemoteException ex) {

        }
    }

    public int obtenerCantidadJugadores(int region) {
        try {
            return service.obtenerCantidadJugadores(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    public int partidaCompleta(int region) {
        try {
            return service.partidaCompleta(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    public String palabraADibujar(int region, int idEnPartida) {
        try {
            return service.palabraADibujar(region, idEnPartida);
        } catch (RemoteException ex) {
            return "Error";
        }
    }

    public ImageIcon imagenAdivinar(int region, int idEnPartida) {
        try {
            return service.imagenAdivinar(region, idEnPartida);
        } catch (RemoteException ex) {
            return null;
        }
    }

    public void enviarImagen(ImageIcon image, int region, int idEnPartida, int puntaje) {
        try {
            service.enviarImagen(image, region, idEnPartida, puntaje);
        } catch (RemoteException ex) {

        }
    }

    public void enviarPalabra(String palabra, int region, int idEnPartida, int puntaje) {
        try {
            service.enviarPalabra(palabra, region, idEnPartida, puntaje);
        } catch (RemoteException ex) {

        }
    }

    public int verificarImagenesCompletas(int region) {
        try {
            return service.verificarImagenesCompletas(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    public int verificarPalabrasCompletas(int region) {
        try {
            return service.verificarPalabrasCompletas(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    public boolean lastRound(int region) {
        try {
            return service.lastRound(region);
        } catch (RemoteException ex) {
            return false;
        }
    }

    //Nuevos
    public String palabraInicial(int region, int idEnPartida) {
        try {
            return service.palabraInicial(region, idEnPartida);
        } catch (RemoteException ex) {
            return "Error";
        }

    }

    public String palabraAnterior(int region, int idEnPartida) {
        try {
            return service.palabraAnterior(region, idEnPartida);
        } catch (RemoteException ex) {
            return "Error";
        }
    }

    public int obtenerPuntajePartida(int region, int idEnPartida) {
        try {
            return service.obtenerPuntajePartida(region, idEnPartida);
        } catch (RemoteException ex) {
            return 100;
        }
    } //Retorna el puntaje total en la partida

    public int[] obtenerPuntajesUsuarios(int region) {
        int[] puntajes = new int[5];
        try {
            return service.obtenerPuntajesUsuarios(region);
        } catch (RemoteException ex) {
            Logger.getLogger(vistaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puntajes;
    }

    //Se comparte en ambos SV
    public void nuevoPuntajeMaximo(String nombre, int idEnDB, int nuevoPuntajeMax) {
        try {
            service.nuevoPuntajeMaximo(nombre, idEnDB, nuevoPuntajeMax);
        } catch (RemoteException ex) {
        }
    }

    //Para quitar todo de la región
    public void limpiarRegion(int region) {
        try {
            service.limpiarRegion(region);
        } catch (RemoteException ex) {
        }
    }

}
