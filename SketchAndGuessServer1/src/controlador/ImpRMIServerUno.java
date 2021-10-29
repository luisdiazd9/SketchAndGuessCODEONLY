package controlador;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import modelo.MediadorSv;

public class ImpRMIServerUno extends UnicastRemoteObject implements iRMIServerUno {

    public ImpRMIServerUno() throws RemoteException {
    }

    private static final long serialVersionUID = 1L;
    MediadorSv mediador = new MediadorSv();

    /* (non-Javadoc)
	 * @see classes.iRMI#sumar(int, int)
     */
    private String cifradoSha1(String p) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(p.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int login(String usuario, String clave, int region) {
        try {
            // TODO add your handling code here:
            String consulta;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess1", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT * FROM usuarios WHERE nombre='" + usuario + "' AND clave='" + cifradoSha1(clave) + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.first()) {
                return 0;
            } else {
                return 1;
            }

        } catch (ClassNotFoundException | SQLException e) {
        }
        return 0;
    }

    @Override
    public int registro(String usuario) {
        try {
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess1", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT id FROM usuarios WHERE nombre='" + usuario + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) { //está el usuario
                return 1;
            } else { //no está, al menos en esta db
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return 1;
    }

    @Override
    public void nuevoPuntajeMaximoSV(int idEnDB, int nuevoPuntajeMax) {
        try {
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess1", "root", "root");
            Statement stat = con.createStatement();
            consulta = "UPDATE usuarios SET puntajeMaximo='" + nuevoPuntajeMax + "'WHERE id='" + idEnDB + "'";
            stat.executeUpdate(consulta);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    @Override
    public int[] datosUsuario(String usuario) {
        int[] datosUs = new int[3];
        try {
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess1", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT id, tipo, puntajeMaximo FROM usuarios WHERE nombre='" + usuario + "'";
            ResultSet rs = stat.executeQuery(consulta);
            rs.next();
            datosUs[0] = rs.getInt(1);
            datosUs[1] = rs.getInt(2);
            datosUs[2] = rs.getInt(3);

        } catch (ClassNotFoundException | SQLException e) {
        }
        return datosUs;
    }

    @Override
    public int getRegionLogin(int region) {

        switch (region) {
            case 1:
                return mediador.getColombia();
            case 2:
                return mediador.getMexico();
            default:
                return mediador.getChile();
        }
    }

    @Override
    public void setRegionLogin(int region, int valor) {

        switch (region) {
            case 1:
                mediador.setColombia(valor);
                break;
            case 2:
                mediador.setMexico(valor);
                break;
            default:
                mediador.setChile(valor);
                break;
        }
    }

    @Override
    public int actualizarTiempoPartida(int tiempoNuevo, int region) { //0 cambio satisfactorio, 1 error
        try {
            return mediador.cambiarTiempoPartida(tiempoNuevo, region);
        } catch (Exception e) {
            return 1;
        }

    }

    @Override
    public int usuarioListo(int region, String nombre, int puntajeMaximo) {
        try {
            return mediador.usuarioListo(region, nombre, puntajeMaximo);
        } catch (Exception e) {
            return 100;
        }

    }

    @Override
    public String[] obtenerUsuarios(int region) {
        return mediador.obtenerUsuarios(region);
    }

    @Override
    public void empiezanPartida(int region) {
        mediador.empiezanPartida(region);
    }

    @Override
    public void primeraPalabra(int region, int idEnPartida, String palabra) {
        mediador.primeraPalabra(region, idEnPartida, palabra);
    }

    @Override
    public int obtenerCantidadJugadores(int region) {
        return mediador.obtenerCantidadJugadores(region);
    }

    @Override
    public int partidaCompleta(int region) {
        return mediador.partidaCompleta(region);
    }

    @Override
    public String palabraADibujar(int region, int idEnPartida) {
        return mediador.palabraADibujar(region, idEnPartida);
    }

    @Override
    public ImageIcon imagenAdivinar(int region, int idEnPartida) {
        return mediador.imagenAdivinar(region, idEnPartida);
    }

    @Override
    public void enviarImagen(ImageIcon image, int region, int idEnPartida, int puntaje) {
        mediador.enviarImagen(image, region, idEnPartida, puntaje);
    }

    @Override
    public void enviarPalabra(String palabra, int region, int idEnPartida, int puntaje) {
        mediador.enviarPalabra(palabra, region, idEnPartida, puntaje);
    }

    @Override
    public int verificarImagenesCompletas(int region) {
        return mediador.verificarImagenesCompletas(region);
    }

    @Override
    public int verificarPalabrasCompletas(int region) {
        return mediador.verificarPalabrasCompletas(region);
    }

    @Override
    public boolean lastRound(int region) {
        return mediador.lastRound(region);
    }

    @Override
    public String palabraInicial(int region, int idEnPartida) {
        return mediador.palabraInicial(region, idEnPartida);
    }

    @Override
    public String palabraAnterior(int region, int idEnPartida) {
        return mediador.palabraAnterior(region, idEnPartida);
    }

    @Override
    public int obtenerPuntajePartida(int region, int idEnPartida) {//Retorna el puntaje total en la partida
        return mediador.obtenerPuntajePartida(region, idEnPartida);
    }

    @Override
    public int[] obtenerPuntajesUsuarios(int region) {
        return mediador.obtenerPuntajesUsuarios(region);
    }

    //Para quitar todo de la región
    @Override
    public void limpiarRegion(int region) {
        mediador.limpiarRegion(region);
    }
}
