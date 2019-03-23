package extend1_manyCases;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * @author Yuhong Chen
 * Implement the remote interface to provide the prosecuter service.
 */
public class prosecutorService extends UnicastRemoteObject implements prosecutor {

    String[] P2Choices = {"C", "B"};
    private static int decisionForP1;
    private static String P2;
    /**
     * constructor.
     *
     * @throws RemoteException
     */
    public prosecutorService() throws RemoteException {
        super();
    }

    /**
     * @return test result
     * @throws RemoteException
     */
    @Override
    public String testConnection() throws RemoteException {
        return "Hello from Server.";
    }


    /**
     * @param inputA the choice of the client
     * @return sentence result for client.
     * @throws RemoteException
     */
    @Override
    public int mainService(String casemode, String inputA) throws RemoteException {

        switch (casemode) {
            case "RANDOM":

                P2 = P2Choices[(int) (Math.random() * 2)];
                if (inputA.equals("C") && P2.equals("C")) {
                    decisionForP1 = 5;
                    return decisionForP1;
                } else if (inputA.equals("B") && P2.equals("C")) {
                    decisionForP1 = 3;
                    return decisionForP1;
                } else if (inputA.equals("B") && P2.equals("B")) {
                    decisionForP1 = 1;
                    return decisionForP1;
                } else if (inputA.equals("C") && P2.equals("B")) {
                    decisionForP1 = 2;
                    return decisionForP1;
                }

            case "RETALIATION":

                if (inputA.equals("B")) {
                    P2 = "B";
                    decisionForP1 = 1;
                    return decisionForP1;
                }

                if (inputA.equals("C")) {
                    P2 = P2Choices[(int) (Math.random() * 2)];
                    if (P2.equals("C")) {
                        decisionForP1 = 5;
                        return decisionForP1;
                    } else {
                        decisionForP1 = 2;
                        return decisionForP1;
                    }
                }

            case "TIT-FOR-TAT":

                if (inputA.equals("C")) {
                    P2 = "C";
                    decisionForP1 = 5;
                    return decisionForP1;
                }

                if (inputA.equals("B")) {
                    P2 = P2Choices[(int) (Math.random() * 2)];
                    if (P2.equals("C")) {
                        decisionForP1 = 3;
                        return decisionForP1;
                    } else {
                        decisionForP1 = 1;
                        return decisionForP1;
                    }
                }
                default:
                return -1;
        }
    }

    @Override
    public String getP2() throws RemoteException{
        return P2;
    }

}
