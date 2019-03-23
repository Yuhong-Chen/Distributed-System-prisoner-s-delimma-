package extend1_manyCases;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Yuhong Chen
 * Define a remote interface.
 */
public interface prosecutor extends Remote {
    /**
     * Test connection with the server is successful.
     * @return
     */
    String testConnection() throws RemoteException;


    /**
     * Due to P2 is randomly chosen by the server,this method provide this choice to client for result.
     * @return the computer random choice of P2
     * @throws RemoteException
     */
    String getP2() throws RemoteException;


    /**
     * calculate and return the sentence of the client based on the choice
     * @param inputA the choice of the client
     * @return sentence of the client
     * @throws RemoteException
     */
    int mainService (String casemode, String inputA) throws RemoteException;
}



