package controlador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

public interface iRMI extends Remote {

    public int login(String usuario, String clave, int region) throws RemoteException;
    public int[] datosUsuario(String usuario) throws RemoteException;
    public int registro(String usuario, String clave, int region) throws RemoteException;

    public int cambiarTiempoPartida(int tiempoNuevo, int region) throws RemoteException;
    public int usuarioListo(int region, String nombre, int puntajeMaximo) throws RemoteException;
    public String[] obtenerUsuarios(int region) throws RemoteException;
    
    public void empiezanPartida(int region) throws RemoteException;
    
    public void primeraPalabra(int region, int idEnPartida, String palabra) throws RemoteException;
    public int obtenerCantidadJugadores(int region) throws RemoteException;
    public int partidaCompleta(int region) throws RemoteException;
    
    public String palabraADibujar(int region, int idEnPartida) throws RemoteException;
    public ImageIcon imagenAdivinar(int region, int idEnPartida) throws RemoteException; 
    
    public void enviarImagen(ImageIcon image, int region, int idEnPartida, int puntaje) throws RemoteException; 
    public void enviarPalabra(String palabra, int region, int idEnPartida, int puntaje) throws RemoteException;  
    
    public int verificarImagenesCompletas(int region) throws RemoteException; 
    public int verificarPalabrasCompletas(int region) throws RemoteException; 
    
    public boolean lastRound(int region) throws RemoteException;
    
    //Se comparte en ambos SV
    public void nuevoPuntajeMaximo(String nombre, int idEnDB, int nuevoPuntajeMax) throws RemoteException;
    
    public String palabraInicial(int region, int idEnPartida) throws RemoteException; 
    public String palabraAnterior(int region, int idEnPartida) throws RemoteException; 
    
    public int obtenerPuntajePartida(int region, int idEnPartida) throws RemoteException; //Retorna el puntaje total en la partida
    public int[] obtenerPuntajesUsuarios(int region) throws RemoteException; 

    //Para quitar todo de la región
    public void limpiarRegion(int region) throws RemoteException; 
}
