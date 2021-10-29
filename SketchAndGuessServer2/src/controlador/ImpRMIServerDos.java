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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MediadorSv;

public class ImpRMIServerDos extends UnicastRemoteObject implements iRMIServerDos {

    public ImpRMIServerDos() throws RemoteException {
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
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT * FROM usuarios WHERE nombre='" + usuario + "' AND clave='" + cifradoSha1(clave) + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) {
                return 0;
            } else {
                return 1;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int registro(String usuario) {
        try {
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT id FROM usuarios WHERE nombre='" + usuario + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) { //está el usuario
                return 1;
            } else { //no está, al menos en esta db
                return 0;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public void nuevoPuntajeMaximoSV(int idEnDB, int nuevoPuntajeMax) {
        try {
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
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

}
