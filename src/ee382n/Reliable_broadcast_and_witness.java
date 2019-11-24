package ee382n;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Reliable_broadcast_and_witness {
    private static int[] serverList;
    Scanner din;
    PrintStream pout;
    Socket server;
    //int [] serverList;
    //alg1
    public void RBSend(MSG_2D_CLASS msg2dobj) throws IOException {

        for (int server_port: serverList) {
            //System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
            TCPSendClientRequest("localhost", server_port, msg2dobj.toMessageString());
        }
    }

    public void getSocket(String hostAddress, int port) throws IOException{
        server = new Socket(hostAddress, port);
        din = new Scanner(server.getInputStream());
        pout = new PrintStream(server.getOutputStream());
    }

    public String TCPSendClientRequest(String hostAddress, int tcpPort, String outMessage) throws IOException {
        getSocket(hostAddress, tcpPort);
        pout.println(outMessage);
        pout.flush();
        String returnString = din.nextLine();
        returnString = returnString.replace("\t", "\n");
        server.close();
        System.out.println(returnString);
        return returnString;
    }

    public static void main(String[] args) throws IOException {
        int portNumber = 5003;
        int pid = 1;
        int round = 1;
        int numServer = 1;
        serverList =new int[numServer];
        serverList[0] = 5002;
        System.out.println("tcp server on port "+Integer.toString(portNumber)+" started:");
        ServerSocket listener  = new ServerSocket(portNumber);

        /*open n-1 channels here to all other servers
        for (int i =0; i <= numServer-1; i++){
//            ServerSocket listener  = new ServerSocket(portNumber);
//
        }*/

        while (true) {
            Socket connectionSocket = listener.accept();
            BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String message = inFromClient.readLine();
            System.out.println("Received: " + message);
            outToClient.writeBytes(message);
        }

    }
}
