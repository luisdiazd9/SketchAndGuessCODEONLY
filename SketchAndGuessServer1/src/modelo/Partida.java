/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author luisd
 */
public class Partida {

    public int region;  //1 en la gestión
    int estadoRegion; //0 si no se ha levantado la región, 1 si ya   //2 en la gestión
    boolean hasStarted; //3 en la gestión
    public int tiempoPartida; //4 en la gestión
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>(); //5 en la gestión
    public ArrayList<String> palabras = new ArrayList<>(); //7 en la gestión
    public ArrayList<String> palabrasAuxiliar = new ArrayList<>();
    public ArrayList<String> palabrasInicial = new ArrayList<>(); //7 en la gestión
    public ArrayList<Integer> puntajes = new ArrayList<>(); //8 en la gestión
    public ArrayList<ImageIcon> imagenes = new ArrayList<>();
    public int cantidadJugadores;
    public int ronda;
    public int auxiliarRonda;
    public boolean isLastRound;
    public int auxiliarImagenes;
    public int validador;

    public int getValidador() {
        return validador;
    }

    public void setValidador(int validador) {
        this.validador = validador;
    }
    
    

    public int getAuxiliarImagenes() {
        return auxiliarImagenes;
    }

    public void setAuxiliarImagenes(int auxiliarImagenes) {
        this.auxiliarImagenes = auxiliarImagenes;
    }

    public ArrayList<String> getPalabrasAuxiliar() {
        return palabrasAuxiliar;
    }

    public void setPalabrasAuxiliar(ArrayList<String> palabrasAuxiliar) {
        this.palabrasAuxiliar = palabrasAuxiliar;
    }

    public ArrayList<String> getPalabrasInicial() {
        return palabrasInicial;
    }

    public void setPalabrasInicial(ArrayList<String> palabrasInicial) {
        this.palabrasInicial = palabrasInicial;
    }

    public boolean isIsLastRound() {
        return isLastRound;
    }

    public void setIsLastRound(boolean isLastRound) {
        this.isLastRound = isLastRound;
    }

    public void controlFinPartida() {
        if (ronda == (cantidadJugadores - 1)) {
            isLastRound = true;
        }
    }

    public void controlAuxiliarImagenes() {
        auxiliarImagenes++;
        if (auxiliarImagenes == cantidadJugadores) {
            auxiliarImagenes = 0;
//            imagenes.clear();
//            imagenes.add(null);
//            imagenes.add(null);
//            imagenes.add(null);
//            imagenes.add(null);
//            imagenes.add(null);
//            llenarPalabrasNull();
        }

    }

    public void controlAuxiliar() {
        auxiliarRonda++;
        if (auxiliarRonda == cantidadJugadores) {
            ronda++;
            auxiliarRonda = 0;
            controlFinPartida();
        }
    }

    private void llenarPalabrasNull() {
        palabras.clear();
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
    }

    public int getAuxiliarRonda() {
        return auxiliarRonda;
    }

    public void setAuxiliarRonda(int auxiliarRonda) {
        this.auxiliarRonda = auxiliarRonda;
    }

    public void establecerListaInicial() {
        palabrasInicial = palabras;
    }

    public void establecerListaAuxiliar() {
        palabrasAuxiliar = palabras;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public void establecerTamañosArrays() {
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
        palabras.add(null);
        imagenes.add(null);
        imagenes.add(null);
        imagenes.add(null);
        imagenes.add(null);
        imagenes.add(null);
        puntajes.add(0);
        puntajes.add(0);
        puntajes.add(0);
        puntajes.add(0);
        puntajes.add(0);
    }

    public Partida(int region, int estadoRegion, int tiempoPartida) {
        this.region = region;
        this.estadoRegion = estadoRegion;
        this.tiempoPartida = tiempoPartida;
        this.hasStarted = false;
        this.ronda = 0;
        this.auxiliarRonda = 0;
        this.isLastRound = false;
        this.validador = 0;
    }

    public Partida(int region, int estadoRegion) {
        this.region = region;
        this.estadoRegion = estadoRegion;
        this.hasStarted = false;
        this.tiempoPartida = 60;
        this.ronda = 0;
        this.auxiliarRonda = 0;
        this.isLastRound = false;
        this.validador = 0;
    }

    public int getTiempoPartida() {
        return tiempoPartida;
    }

    public void setTiempoPartida(int tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<ImageIcon> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<ImageIcon> imagenes) {
        this.imagenes = imagenes;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public ArrayList<Integer> getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(ArrayList<Integer> puntajes) {
        this.puntajes = puntajes;
    }

    public int getEstadoRegion() {
        return estadoRegion;
    }

    public void setEstadoRegion(int estadoRegion) {
        this.estadoRegion = estadoRegion;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

}
