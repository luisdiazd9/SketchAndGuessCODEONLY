package controlador;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iRMIServerDos extends Remote {

    public int login(String usuario, String clave, int region) throws RemoteException;

    public int registro(String usuario) throws RemoteException;

    public int[] datosUsuario(String usuario) throws RemoteException;

    public int getRegionLogin(int region) throws RemoteException;

    public void setRegionLogin(int region, int valor) throws RemoteException;
    
    public void nuevoPuntajeMaximoSV(int idEnDB, int nuevoPuntajeMax) throws RemoteException;
    

}
