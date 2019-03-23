package extend2_courtServices;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Yuhong Chen
 * Define a remote interface to be implemented by courtService.
 */
public interface court  extends Remote {

    /**
     *
     * @param appealRequest
     * @param originalSentence
     * @return the new sentence randomly decreased.
     * @throws RemoteException
     */
    int appealService (String appealRequest,int originalSentence) throws RemoteException;
}
