package part1And2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
 * @author Yuhong Chen
 * The server class.
 * creates a registry on port 8888 and binds a prosecuterService object into the registry.
 *
 */
class gameServer {
    static int port =8888;

    public static void registerService() {

        try {
            prosecutor pService = new prosecutorService();
            LocateRegistry.createRegistry(port);
            Naming.bind("rmi://localhost:8888/pService", pService);
            System.out.println(">>>>>Remote prosecutorService object is successfully bind to the registry.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        registerService();
    }
}


