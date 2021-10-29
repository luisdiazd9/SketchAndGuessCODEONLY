package controlador;

import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
import javax.swing.ImageIcon;
import modelo.MediadorSv;

public class ImpRMI extends UnicastRemoteObject implements iRMI {

    public ImpRMI() throws RemoteException {
    }

    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
	 * @see classes.iRMI#sumar(int, int)
     */
    private iRMIServerUno service;
    private int validador; //Se usa para el login o registro, valida en qué db está el usuario
    private int conexion = 0;
    public MediadorSv mediador = new MediadorSv();

    public void conexionServidorServidor() {
        try {
            if (conexion == 0) {
                conexion = 1;
                service = (iRMIServerUno) Naming.lookup("rmi://127.0.0.3:1804/serviceSV");  //Se establece la conexión con el servidor
            } else {
                System.out.println("Ya hay conexicón");
            }

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
            int verificadorAcceso = 1;
            conexionServidorServidor();
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT * FROM usuarios WHERE nombre='" + usuario + "' AND clave='" + cifradoSha1(clave) + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) {
                validador = 0; //EL usuario está y es en esta db
                verificadorAcceso = 0;
            } else {
                try {
                    if (service.login(usuario, clave, region) == 0) {
                        validador = 1; //El usuario está y es en la otra db
                        verificadorAcceso = 0;
                    } else {
                        validador = 2; //El usuario no está
                        verificadorAcceso = 1;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (verificadorAcceso == 0) {
                    switch (region) {
                        case 1:
                            mediador.setColombia(service.getRegionLogin(region) + 1);
                            if (mediador.getColombia() <= 5) {
                                service.setRegionLogin(region, mediador.getColombia());
                                return 0; //accede a la region
                            } else {
                                return 2; //La región está llena
                            }
                        case 2:
                            mediador.setMexico(service.getRegionLogin(region) + 1);
                            if (mediador.getMexico() <= 5) {
                                service.setRegionLogin(region, mediador.getMexico());
                                return 0; //accede a la region
                            } else {
                                return 2; //La región está llena
                            }
                        default:
                            mediador.setChile(service.getRegionLogin(region) + 1);
                            ;
                            if (mediador.getChile() <= 5) {
                                service.setRegionLogin(region, mediador.getChile());
                                return 0; //accede a la region
                            } else {
                                return 2; //La región está llena
                            }
                    }
                } else {
                    return 1;
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ImpRMI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException | SQLException e) {
        }
        return 1;
    }

    @Override
    public int[] datosUsuario(String usuario) {
        int[] datosUs = new int[3];
        try {
            if (validador == 0) {
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
            } else if (validador == 1) {
                datosUs = service.datosUsuario(usuario);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException ex) {
            Logger.getLogger(ImpRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosUs;
    }

    @Override
    public int registro(String usuario, String clave, int region) {
        try {
            conexionServidorServidor();
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT id FROM usuarios WHERE nombre='" + usuario + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) { //está el usuario
                return 1;
            } else { //no está, al menos en esta db     
                try {
                    if (service.login(usuario, clave, region) == 0) { //está en la otra db
                        validador = 2;
                        return 1;
                    } else {//se agrega el nuevo usuario a la db                  
                        consulta = "INSERT INTO usuarios (tipo, nombre, clave, puntajeMaximo) VALUES ('" + 1 + "','" + usuario + "','" + cifradoSha1(clave) + "','" + "0" + "')";
                        stat.executeUpdate(consulta);
                        validador = 0;
                        return 0;
                    }
                } catch (RemoteException e) {
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return 1;
    }

    @Override
    public void nuevoPuntajeMaximo(String nombre, int idEnDB, int nuevoPuntajeMax) {
        try {
            conexionServidorServidor();
            String consulta;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sketchandguess2", "root", "root");
            Statement stat = con.createStatement();
            consulta = "SELECT id FROM usuarios WHERE nombre='" + nombre + "'";
            ResultSet rs = stat.executeQuery(consulta);
            if (rs.next()) { //está el usuario
                consulta = "UPDATE usuarios SET puntajeMaximo='" + nuevoPuntajeMax + "'WHERE id='" + idEnDB + "'";
                stat.executeUpdate(consulta);
            } else { //no está en esta db, está en la otra    
                try {
                    service.nuevoPuntajeMaximoSV(idEnDB, nuevoPuntajeMax);
                } catch (RemoteException e) {
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    @Override
    public int cambiarTiempoPartida(int tiempoNuevo, int region) { //0 cambio satisfactorio, 1 error

        try {
            return service.actualizarTiempoPartida(tiempoNuevo, region);
        } catch (RemoteException e) {
            return 1;
        }
    }

    @Override
    public int usuarioListo(int region, String nombre, int puntajeMaximo) {
        try {
            return service.usuarioListo(region, nombre, puntajeMaximo);
        } catch (RemoteException e) {
            return 100;
        }
    }

    @Override
    public String[] obtenerUsuarios(int region) {
        try {
            return service.obtenerUsuarios(region);
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override
    public void empiezanPartida(int region) {
        try {
            service.empiezanPartida(region);
        } catch (RemoteException e) {
            System.err.println("Error de conexión");
        }
    }

    @Override
    public void primeraPalabra(int region, int idEnPartida, String palabra) {
        try {
            service.primeraPalabra(region, idEnPartida, palabra);
        } catch (RemoteException e) {
            System.err.println("Error de conexión");
        }
    }

    @Override
    public int obtenerCantidadJugadores(int region) {
        try {
            return service.obtenerCantidadJugadores(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    @Override
    public int partidaCompleta(int region) {
        try {
            return service.partidaCompleta(region);
        } catch (RemoteException ex) {
            return 100;
        }
    }

    @Override
    public String palabraADibujar(int region, int idEnPartida) {
        try {
            return service.palabraADibujar(region, idEnPartida);
        } catch (RemoteException ex) {
            return "Error";
        }
    }

    @Override
    public ImageIcon imagenAdivinar(int region, int idEnPartida) {
        try {
            return service.imagenAdivinar(region, idEnPartida);
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override
    public void enviarImagen(ImageIcon image, int region, int idEnPartida, int puntaje) {
        try {
            service.enviarImagen(image, region, idEnPartida, puntaje);
        } catch (RemoteException e) {
        }
    }

    @Override
    public void enviarPalabra(String palabra, int region, int idEnPartida, int puntaje) {
        try {
            service.enviarPalabra(palabra, region, idEnPartida, puntaje);
        } catch (RemoteException e) {
        }
    }

    @Override
    public int verificarImagenesCompletas(int region) {
        try {
            return service.verificarImagenesCompletas(region);
        } catch (RemoteException e) {
            return 100;
        }
    }

    @Override
    public int verificarPalabrasCompletas(int region) {
        try {
            return service.verificarPalabrasCompletas(region);
        } catch (RemoteException e) {
            return 100;
        }
    }

    @Override
    public boolean lastRound(int region) {
        try {
            return service.lastRound(region);
        } catch (RemoteException e) {
            return false;
        }
    }

    //NUEVOS
    @Override
    public String palabraInicial(int region, int idEnPartida) {
        try {
            return service.palabraInicial(region, idEnPartida);
        } catch (RemoteException e) {
            return "Error";
        }
    }

    @Override
    public String palabraAnterior(int region, int idEnPartida) {
        try {
            return service.palabraAnterior(region, idEnPartida);
        } catch (RemoteException e) {
            return "Error";
        }
    }

    @Override
    public int obtenerPuntajePartida(int region, int idEnPartida) {//Retorna el puntaje total en la partida
        try {
            return service.obtenerPuntajePartida(region, idEnPartida);
        } catch (RemoteException e) {
            return 0;
        }
    }

    @Override
    public int[] obtenerPuntajesUsuarios(int region) {
        try {
            return service.obtenerPuntajesUsuarios(region);
        } catch (RemoteException e) {
            int[] retornoError = new int[5];
            return retornoError;
        }
    }

    //Para quitar todo de la región
    @Override
    public void limpiarRegion(int region) {
        try {
            service.limpiarRegion(region);
        } catch (RemoteException e) {

        }
    }

}
