package extend2_courtServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class courtService extends UnicastRemoteObject implements court {

    public courtService() throws RemoteException {
        super();
    }

    @Override
    public int appealService(String appealRequest,int originalSentence) throws RemoteException{
        int newSentence = originalSentence;
        if(appealRequest.equals("APPEAL")){
            newSentence = (int)(Math.random()*originalSentence);
        }
        return newSentence;
    }
}
