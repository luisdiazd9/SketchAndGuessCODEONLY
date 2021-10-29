/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchandguessserver;

import controlador.ImpRMI;
import controlador.ImpRMIServerDos;
import controlador.iRMI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import controlador.iRMIServerDos;

/**
 *
 * @author luisd
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            iRMI service = new ImpRMI();
            LocateRegistry.createRegistry(1803);
            Naming.rebind("//127.0.0.2:1803/service", service);
            iRMIServerDos serviceSV = new ImpRMIServerDos();
            LocateRegistry.createRegistry(1805);
            Naming.rebind("//127.0.0.4:1805/serviceSV", serviceSV);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
