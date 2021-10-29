/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchandguessserver;

import controlador.ImpRMI;
import controlador.ImpRMIServerUno;
import controlador.iRMI;
import controlador.iRMIServerUno;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
            LocateRegistry.createRegistry(1802);
            Naming.rebind("//127.0.0.1:1802/service", service);
            iRMIServerUno serviceSV = new ImpRMIServerUno();
            LocateRegistry.createRegistry(1804);
            Naming.rebind("//127.0.0.3:1804/serviceSV", serviceSV);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
