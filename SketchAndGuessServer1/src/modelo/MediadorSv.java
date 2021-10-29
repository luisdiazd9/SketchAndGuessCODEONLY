/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author luisd
 */
public class MediadorSv {

    int colombia;
    int mexico;
    int chile;
    public ArrayList<Partida> regiones = new ArrayList<>();
    private int[] indiceRegiones = new int[3]; //Indice 0 guarda a Colombia (reg 1) en el arraylist de regiones. el 1 a México y el 2 a Chile

    public int[] getIndiceRegiones() {
        return indiceRegiones;
    }

    public void setIndiceRegiones(int[] indiceRegiones) {
        this.indiceRegiones = indiceRegiones;
    }

    public ArrayList<Partida> getRegiones() {
        return regiones;
    }

    public void setRegiones(ArrayList<Partida> regiones) {
        this.regiones = regiones;
    }

    public int getColombia() {
        return colombia;
    }

    public void setColombia(int colombia) {
        this.colombia = colombia;
    }

    public int getMexico() {
        return mexico;
    }

    public void setMexico(int mexico) {
        this.mexico = mexico;
    }

    public int getChile() {
        return chile;
    }

    public void setChile(int chile) {
        this.chile = chile;
    }

    //Para el inicio de la partida
    public int cambiarTiempoPartida(int tiempoNuevo, int region) { //0 cambio satisfactorio, 1 error
        try {
            if (regiones.isEmpty()) { //No hay partidas
                Partida partidaNueva = new Partida(region, 1, tiempoNuevo);
                regiones.add(partidaNueva);
                regiones.get(0).establecerTamañosArrays();
                switch (region) {
                    case 1: //Colombia
                        indiceRegiones[0] = 0;
                        break;
                    case 2: //México
                        indiceRegiones[1] = 0;
                        break;
                    case 3: //Chile
                        indiceRegiones[2] = 0;
                        break;
                }
                return 0;
            } else { //Ya hay partidas, se procede a buscar si ya está el servidor
                Partida partidaParaCambiar;
                for (int i = 0; i < regiones.size(); i++) {
                    if (regiones.get(i).getRegion() == region) {
                        partidaParaCambiar = regiones.get(i);
                        partidaParaCambiar.setTiempoPartida(tiempoNuevo); //Está la partida, se procede a actualizarla
                        return 0;
                    }
                }
                //Hay partidas, pero ninguna corresponde a la región seleccionada
                Partida partidaNueva = new Partida(region, 1, tiempoNuevo);
                regiones.add(partidaNueva);
                regiones.get(regiones.size() - 1).establecerTamañosArrays();
                switch (region) {
                    case 1: //Colombia
                        indiceRegiones[0] = regiones.size() - 1;
                        break;
                    case 2: //México
                        indiceRegiones[1] = regiones.size() - 1;
                        break;
                    case 3: //Chile
                        indiceRegiones[2] = regiones.size() - 1;
                        break;
                }
                return 0;
            }
        } catch (Exception e) {
            return 1;
        }

    }

    public int usuarioListo(int region, String nombre, int puntajeMaximo) { //Retorna el id del usuario para la sesión, 9 si ya empezó la partida
        int indiceLista = 0;
        if (regiones.isEmpty()) { //No hay partidas creadas
            Partida partidaNueva = new Partida(region, 1);
            regiones.add(partidaNueva);
            regiones.get(0).establecerTamañosArrays();
            regiones.get(0).getListaUsuarios().add(new Usuario(nombre, puntajeMaximo, 0));
            switch (region) {
                case 1: //Colombia
                    indiceRegiones[0] = 0;
                    break;
                case 2: //México
                    indiceRegiones[1] = 0;
                    break;
                case 3: //Chile
                    indiceRegiones[2] = 0;
                    break;
            }
            return 0;
        } else {//Ya hay partidas, se procede a buscar si sí es la región
            for (int i = 0; i < regiones.size(); i++) {
                if (regiones.get(i).getRegion() == region) {
                    int busqueda = region - 1;
                    int idUsuario = regiones.get(indiceRegiones[busqueda]).getListaUsuarios().size();
                    if (regiones.get(indiceRegiones[region - 1]).hasStarted) { //La partida ya empezó
                        return 9;
                    } else {
                        regiones.get(indiceLista).listaUsuarios.add(new Usuario(nombre, puntajeMaximo, idUsuario)); //Se añade el usuario a la partida
                        return idUsuario;
                    }
                }
            } //Si no ha retornado nada, es que hay partidas pero no es de esta región
            Partida partidaNueva = new Partida(region, 1);
            regiones.add(partidaNueva);
            regiones.get(regiones.size() - 1).establecerTamañosArrays();
            regiones.get(regiones.size() - 1).getListaUsuarios().add(new Usuario(nombre, puntajeMaximo, 0));
            switch (region) {
                case 1: //Colombia
                    indiceRegiones[0] = regiones.size() - 1;
                    break;
                case 2: //México
                    indiceRegiones[1] = regiones.size() - 1;
                    break;
                case 3: //Chile
                    indiceRegiones[2] = regiones.size() - 1;
                    break;
            }
            return 0;
        }

    }

    public String[] obtenerUsuarios(int region) {
        String[] usuarios = {null, null, null, null, null, null};
        if (regiones.get(indiceRegiones[region - 1]).listaUsuarios.isEmpty()) {
        } else {
            for (int i = 0; i < regiones.get(indiceRegiones[region - 1]).listaUsuarios.size(); i++) {
                usuarios[i] = regiones.get(indiceRegiones[region - 1]).listaUsuarios.get(i).nombreUsuario;
            }
        }
        if (regiones.get(indiceRegiones[region - 1]).isHasStarted()) {
            usuarios[5] = "Partida empezada";
        }
        return usuarios;
    }

    public void empiezanPartida(int region) {
        regiones.get(indiceRegiones[region - 1]).setHasStarted(true);
        regiones.get(indiceRegiones[region - 1]).setCantidadJugadores(regiones.get(indiceRegiones[region - 1]).getListaUsuarios().size());
        regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().add("pato");
        regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().add("minion");
        regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().add("pollito");
    }

    public int obtenerCantidadJugadores(int region) {
        return regiones.get(indiceRegiones[region - 1]).getListaUsuarios().size();
    }

    public void primeraPalabra(int region, int idEnPartida, String palabra) {
        regiones.get(indiceRegiones[region - 1]).getPalabras().set(idEnPartida, palabra);
    }

    //Control de la partida después de la primera palabra
    public int partidaCompleta(int region) {
        int cantidadJugadores = regiones.get(indiceRegiones[region - 1]).getCantidadJugadores();
        if (regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(cantidadJugadores - 1) != null) {
            return 1;
        }
        return 0;
    }

    //Ya la partida
    //Gestión de palabras
    public String palabraADibujar(int region, int idEnPartida) {
        switch (regiones.get(indiceRegiones[region - 1]).cantidadJugadores) {
            case 2:
                return palabraADibujarDosJugadores(region, idEnPartida);
            case 3:
                return palabraADibujarTresJugadores(region, idEnPartida);
            case 4:
                return palabraADibujarCuatroJugadores(region, idEnPartida);
            case 5:
                return palabraADibujarCincoJugadores(region, idEnPartida);
        }
        return "error";
    }

    private String palabraADibujarDosJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
        }
        return "error";
    }

    private String palabraADibujarTresJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 1:
                System.out.println("SIGUÓ DE RONDA");
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }

        }
        return "error";
    }

    private String palabraADibujarCuatroJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;

                }

        }
        return "error";
    }

    private String palabraADibujarCincoJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }
            case 3:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabras().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliar();
                        return retorno;
                }

        }
        return "error";
    }

    //Gestión de imágenes
    public ImageIcon imagenAdivinar(int region, int idEnPartida) {
        switch (regiones.get(indiceRegiones[region - 1]).cantidadJugadores) {
            case 2:
                return imagenAdivinarDosJugadores(region, idEnPartida);
            case 3:
                return imagenAdivinarTresJugadores(region, idEnPartida);
            case 4:
                return imagenAdivinarCuatroJugadores(region, idEnPartida);
            case 5:
                return imagenAdivinarCincoJugadores(region, idEnPartida);

        }
        return null;
    }

    private ImageIcon imagenAdivinarDosJugadores(int region, int idEnPartida) {
        ImageIcon retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
        }
        return null;
    }

    private ImageIcon imagenAdivinarTresJugadores(int region, int idEnPartida) {
        ImageIcon retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                System.out.println("Ronda 1");
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 2:
                System.out.println("Ronda 2");
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }

        }
        return null;
    }

    private ImageIcon imagenAdivinarCuatroJugadores(int region, int idEnPartida) {
        ImageIcon retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 3:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;

                }

        }
        return null;
    }

    private ImageIcon imagenAdivinarCincoJugadores(int region, int idEnPartida) {
        ImageIcon retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 3:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }
            case 4:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(1);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(2);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(3);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(4);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getImagenes().get(0);
                        regiones.get(indiceRegiones[region - 1]).controlAuxiliarImagenes();
                        return retorno;
                }

        }
        return null;
    }

    public void enviarImagen(ImageIcon image, int region, int idEnPartida, int puntaje) {
        System.out.println(regiones.get(indiceRegiones[region - 1]).getImagenes().size());
        regiones.get(indiceRegiones[region - 1]).getImagenes().set(idEnPartida, image);
        int puntajeNuevo = puntaje + regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(idEnPartida).getPuntajeActual();
        regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(idEnPartida).setPuntajeActual(puntajeNuevo);
    }

    public void enviarPalabra(String palabra, int region, int idEnPartida, int puntaje) {
        regiones.get(indiceRegiones[region - 1]).getPalabras().set(idEnPartida, palabra);
        int puntajeNuevo = puntaje + regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(idEnPartida).getPuntajeActual();
        regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(idEnPartida).setPuntajeActual(puntajeNuevo);
    }

    public int verificarImagenesCompletas(int region) { //1 si están todas, 0 si no
        System.out.println(regiones.get(indiceRegiones[region - 1]).getImagenes().get(0));
        int cantidadJugadores = regiones.get(indiceRegiones[region - 1]).getCantidadJugadores();
        int validadorCompleto = 0;
        for (int i = 0; i < cantidadJugadores; i++) {
            if (regiones.get(indiceRegiones[region - 1]).getImagenes().get(i) != null) {
                validadorCompleto++;
            }
        }
        if (validadorCompleto == cantidadJugadores) {
            return 1;
        } else {
            return 0;
        }
    }

    public int verificarPalabrasCompletas(int region) { //1 si están todas, 0 si no
        int cantidadJugadores = regiones.get(indiceRegiones[region - 1]).getCantidadJugadores();
        int validadorCompleto = 0;
        for (int i = 0; i < cantidadJugadores; i++) {
            if (regiones.get(indiceRegiones[region - 1]).getPalabras().get(i) != null) {
                validadorCompleto++;
            }
        }
        if (regiones.get(indiceRegiones[region - 1]).getRonda() == 0 && regiones.get(indiceRegiones[region - 1]).getValidador() == 0) {
//            regiones.get(indiceRegiones[region - 1]).establecerListaInicial();
            regiones.get(indiceRegiones[region - 1]).setValidador(1);
        }
        if (validadorCompleto == cantidadJugadores) {
            regiones.get(indiceRegiones[region - 1]).establecerListaAuxiliar();
            return 1;
        } else {
            return 0;
        }
    }

    public boolean lastRound(int region) {
        return regiones.get(indiceRegiones[region - 1]).isIsLastRound();
    }

    public String palabraInicial(int region, int idEnPartida) {
        switch (regiones.get(indiceRegiones[region - 1]).cantidadJugadores) {
            case 2:
                return palabraInicialDosJugadores(region, idEnPartida);
            case 3:
                return palabraInicialTresJugadores(region, idEnPartida);
            case 4:
                return palabraInicialCuatroJugadores(region, idEnPartida);
            case 5:
                return palabraInicialCincoJugadores(region, idEnPartida);
        }
        return "error";
    }

    private String palabraInicialDosJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                }
        }
        return "error";
    }

    private String palabraInicialTresJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        retorno = "minion";
                        return retorno;
                    case 1:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        retorno = "pollito";
                        return retorno;
                    case 2:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        retorno = "pato";
                        return retorno;
                }
            case 2:
                System.out.println("SIGUÓ DE RONDA");
                switch (idEnPartida) {
                    case 0:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        retorno = "pollito";
                        return retorno;
                    case 1:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        retorno = "pato";
                        return retorno;
                    case 2:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        retorno = "minion";
                        return retorno;
                }

        }
        return "error";
    }

    private String palabraInicialCuatroJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;

                }

        }
        return "error";
    }

    private String palabraInicialCincoJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(4);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(4);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(4);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                }
            case 3:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(2);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(3);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(4);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasInicial().get(0);
                        return retorno;
                }

        }
        return "error";
    }

    public String palabraAnterior(int region, int idEnPartida) {
        switch (regiones.get(indiceRegiones[region - 1]).cantidadJugadores) {
            case 2:
                return palabraAnteriorDosJugadores(region, idEnPartida);
            case 3:
                return palabraAnteriorTresJugadores(region, idEnPartida);
            case 4:
                return palabraAnteriorCuatroJugadores(region, idEnPartida);
            case 5:
                return palabraAnteriorCincoJugadores(region, idEnPartida);
        }
        return "error";
    }

    private String palabraAnteriorDosJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                }
        }
        return "error";
    }

    private String palabraAnteriorTresJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 1: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        retorno = "minion";
                        return retorno;
                    case 1:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        retorno = "pollito";
                        return retorno;
                    case 2:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        retorno = "pato";
                        return retorno;
                }
            case 2:
                System.out.println("SIGUÓ DE RONDA");
                switch (idEnPartida) {
                    case 0:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        retorno = "piolin";
                        return retorno;
                    case 1:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        retorno = "ganso";
                        return retorno;
                    case 2:
//                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        retorno = "minion";
                        return retorno;
                }

        }
        return "error";
    }

    private String palabraAnteriorCuatroJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;

                }

        }
        return "error";
    }

    private String palabraAnteriorCincoJugadores(int region, int idEnPartida) {
        String retorno;
        switch (regiones.get(indiceRegiones[region - 1]).getRonda()) {
            case 0: //La ronda para empezar
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(4);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                }
            case 1:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(4);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                }
            case 2:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(4);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                }
            case 3:
                switch (idEnPartida) {
                    case 0:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(1);
                        return retorno;
                    case 1:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(2);
                        return retorno;
                    case 2:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(3);
                        return retorno;
                    case 3:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(4);
                        return retorno;
                    case 4:
                        retorno = regiones.get(indiceRegiones[region - 1]).getPalabrasAuxiliar().get(0);
                        return retorno;
                }

        }
        return "error";
    }

    public int obtenerPuntajePartida(int region, int idEnPartida) {//Retorna el puntaje total en la partida
        return regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(idEnPartida).getPuntajeActual();
    }

    public int[] obtenerPuntajesUsuarios(int region) {
        int[] puntajes = new int[5];
        for (int i = 0; i < regiones.get(indiceRegiones[region - 1]).getCantidadJugadores(); i++) {
            puntajes[i] = regiones.get(indiceRegiones[region - 1]).getListaUsuarios().get(i).getPuntajeActual();
        }
        return puntajes;
    }

    //Para quitar todo de la región
    public void limpiarRegion(int region) {
        regiones.remove(indiceRegiones[region - 1]);
    }

}
