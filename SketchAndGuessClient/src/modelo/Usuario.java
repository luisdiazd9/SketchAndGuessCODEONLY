/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author luisd
 */
public class Usuario {

    public static String nombreUsuario;
    public static int tipoUsuario; //0 para administrador, 1 para usuario normal
    public static int puntajeMaximo;
    public static int region;
    public static int idUsuario;
    public static int idEnPartida;
    public static int validadorAccion = 0; //0 Recibir primera palabra, 1 recibir imagen, 2 recibir palabra que no es la primera
    
    

    public static int getIdEnPartida() {
        return idEnPartida;
    }

    public static void setIdEnPartida(int idEnPartida) {
        Usuario.idEnPartida = idEnPartida;
    }
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        Usuario.idUsuario = idUsuario;
    }
    
    

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        Usuario.region = region;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        Usuario.nombreUsuario = nombreUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        Usuario.tipoUsuario = tipoUsuario;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(int puntajeMaximo) {
        Usuario.puntajeMaximo = puntajeMaximo;
    }
    
    

}
