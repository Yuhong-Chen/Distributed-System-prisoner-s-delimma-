package part1And2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * @author Yuhong Chen
 * Implement the remote interface to provide the prosecuter service.
 */
    public class prosecutorService extends UnicastRemoteObject implements prosecutor {
    private String[] P2Choices = {"C", "B"};
    String P2 = P2Choices[(int) (Math.random() * 2)];

    /**
     * constructor.
     * @throws RemoteException
     */
   public prosecutorService() throws RemoteException {
        super();
    }

    /**
     *
     * @return test result
     * @throws RemoteException
     */
    @Override
    public String testConnection() throws RemoteException {
        return "Hello from Server.";
    }


    /**
     *
     * @param choice the choice of the client
     * @return sentence result for client.
     * @throws RemoteException
     */
    @Override
    public int mainService(String choice) throws RemoteException{
        int decisionForP1;
        if (choice.equals("C") && P2.equals("C")) {
            decisionForP1 = 5;
            return decisionForP1;
        } else if (choice.equals("B") && P2.equals("C")) {
            decisionForP1 = 3;
            return decisionForP1;
        }  else if (choice.equals("B") && P2.equals("B")) {
            decisionForP1 = 1;
            return decisionForP1;
        } else if (choice.equals("C") && P2.equals("B")) {
            decisionForP1 = 2;
            return decisionForP1;
        }
        else {
            return -1;
        }
    }

    @Override
    public String getP2() throws RemoteException{
        return P2;
    }

}

