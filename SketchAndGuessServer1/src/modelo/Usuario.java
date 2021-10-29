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

    public String nombreUsuario;
    public int puntajeMaximo;
    public int idUsuario; //Es el id en la partida, no en la db
    public int puntajeActual;

    public Usuario(String nombreUsuario, int puntajeMaximo, int idUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.puntajeMaximo = puntajeMaximo;
        this.idUsuario = idUsuario;
    }
           
    public Usuario(String nombreUsuario, int puntajeMaximo) {
        this.nombreUsuario = nombreUsuario;
        this.puntajeMaximo = puntajeMaximo;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(int puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }
    
    

}
