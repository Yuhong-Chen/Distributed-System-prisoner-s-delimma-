package extend2_courtServices;

import extend1_manyCases.prosecutor;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

/**
 * @author Yuhong Chen
 * The client class.
 * Runs RMI of the prosecuterService object.
 */
class prisonerClient {
    static Scanner scanner = new Scanner(System.in);
    static String input;
    static String appealRequest;
    static int sentence;
    static int newSentence;
    private static String caseMode;


    /**
     * look up the registry and return the prosecuterService object, so its method can be used in this client class.
     * @return the prosecuterService object
     */
    static prosecutor lookForProsecuterService(){
        try {
            Remote remote = Naming.lookup("rmi://localhost:8888/pService");
            if (remote instanceof prosecutor) {
                prosecutor pService = (prosecutor) remote;
                return pService;
            }else return null;
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * look up the registry and return the courtService object, so its method can be used in this client class.
     * @return the courtService object
     */

    static court lookForCourtService(){
        try {
            Remote remote = Naming.lookup("rmi://localhost:8888/cService");
            if (remote instanceof court) {
                court cService = (court) remote;
                return cService;
            }else return null;
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Remote call of testConnection method.
     * @throws RemoteException
     */
    static void testConnection () throws RemoteException {
        String result = lookForProsecuterService().testConnection();
        System.out.println(result);
    }


    /**
     * print instruction of the game, prompt for input and display the sentence and appeal results if needed.
     * @throws RemoteException
     */
    static void startGame() throws RemoteException{
        System.out.println("\nINSTRUCTION OF PRISONER'S DILEMMA \n" + "This is a well-known example of a game representing a social dilemma involving two or more participants.\n"
                + "Each participant represents a prisoner in a cell with no communication with other prisoners.\n" +
                "They are all suspect of a crime and the prosecutor needs to establish the sentence for each of the prisoners.\n" +
                "The prosecutor gives the following choice to each of the prisoners:\n" +
                "\u000F Betray the others by testifying that the others committed the crime\n" +
                "\u000F Cooperate with the others by remaining silent\n" +
                "On the basis of the answers received, the prosecutor decides to discount the sentence according to rules below:\n" +
                "\u000F If the two prisoners cooperate, each player gets 5 years reduction\n" +
                "\u000F If the two prisoners betray each other, each player gets 1 year reduction\n" +
                "\u000F If a prisoner cooperates and the other one betrays, the first gets 2 years reduction and the other one gets 3 years reduction.\n" +
                "\nNow you are playing this game with a computer.");
        System.out.println("\nThere are 3 game modes: Random, Retaliation, and Tit for Tat. !!!" +
                "\nRandom: This game mode, which is most like a real situation in life,\n makes " +
                "it so that the computer randomly chooses which path it will choose. It's" +
                " randomized onto whether or not it will defect or cooperate. This is the most" +
                " simple game mode as well.\nRetaliation: As it sounds, " +
                " if you defect, the computer will defect. But if you cooperate, the computer will randomly make choices.\nTit-for-Tat: " +
                "works like this: \nyou cooperate, the computer will then cooperate. However, if you defect, the the computer will randomly make choices.\n\nWhich game mode would you like to play? \r");
        caseMode = scanner.next().toUpperCase();
        System.out.println("\nYou have chosen " + caseMode + " mode. Now type in C for cooperation and B for betray.");
        input = scanner.next().toUpperCase();
        sentence = lookForProsecuterService().mainService(caseMode,input);
        System.out.println("The computer chose " + lookForProsecuterService().getP2() + " and you have been sentenced to " + sentence + " years." +
                "\n\n If you are not happy with your sentence years, you may appeal to a court to have your discount increased." +
                "\n if you want to appeal,type APPEAL; if you don't, type NOAPPEAL and the game is over.");
        appealRequest = scanner.next().toUpperCase();
        if(appealRequest.equals("APPEAL")){
            System.out.println("Your appeal request is now sent to the court.");
            newSentence =lookForCourtService().appealService(appealRequest,sentence);
            System.out.println("After the discussion of the court, your sentence is now " + newSentence + " years. I am afraid you only have ONE appealing chance" +
                    " and the game now is over.") ;
        }
    }


    /**
     * main method.
     * first test connection with the server and start the game.
     * @param args
     */
    public static void main(String[] args) {
        try {
            testConnection();
            startGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

