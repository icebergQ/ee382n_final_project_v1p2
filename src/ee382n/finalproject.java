package ee382n;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class finalproject {
    //private int[] serverList;
    Scanner din;
    PrintStream pout;
    Socket server;
    static int [] serverList = new int[1];
    //alg1
    public void RBSend(MSG_2D_CLASS msg2dobj) throws IOException {

        for (int server_port: serverList) {
            System.out.println("sending message: "+msg2dobj.toMessageString());
            TCPSendClientRequest("localhost", server_port, msg2dobj.toMessageString());
        }
    }

    public  void getSocket(String hostAddress, int port) throws IOException{
        server = new Socket(hostAddress, port);
        din = new Scanner(server.getInputStream());
        pout = new PrintStream(server.getOutputStream());
    }

    public  void TCPSendClientRequest(String hostAddress, int tcpPort, String outMessage) throws IOException {
        getSocket(hostAddress, tcpPort);
        pout.println(outMessage);
        pout.flush();
//        String returnString = din.nextLine();
//        returnString = returnString.replace("\t", "\n");
//        server.close();
//        System.out.println(returnString);
//        return returnString;
    }

    public static void main(String[] args) throws IOException {

        finalproject rbw= new finalproject();

        int portNumber = 5002;
        int pid = 2;
        int round = 1;
        int numServer = 1;
        //int [] serverList =new int[numServer];
        serverList[0] = 5003;
        ArrayList<Double> msgVector = new ArrayList<Double>();
        msgVector.add(12.5);
        msgVector.add(13.5);

        System.out.println("tcp server on port "+Integer.toString(portNumber)+" started:");
        ServerSocket listener  = new ServerSocket(portNumber);
        MSG_2D_CLASS newMessage = new MSG_2D_CLASS(pid,round, msgVector);
        rbw.RBSend(newMessage);
        rbw.RBSend(newMessage);
        rbw.RBSend(newMessage);

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
