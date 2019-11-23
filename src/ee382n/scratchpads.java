
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;


/****************Secontion but you have to*********************/

/* Input is a vector given by the application or by argument
 * IP and port of all other servers, its pid,
 * general server class
 * initalize 5 servers
 * run RB and WT
 * */
public class ByzantineServer{

    //or randomly generated vector based on simulating robot x y z position
    int [] vectorValue = [1,2,3];
    public id = 1;
    public round = 0;
    int [] serverList = null;
    public void ByzantineServer(){
        neversentecho = false;
        neversentready = false;

    }

    public void setServerList(int [] list){
        this.serverList= list;
    }

    public RBSend(int pid, int round, arrayList vectorValue){
        for (int server : serverList) {
            System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
            TCPSendClientRequest(hostAddress, server, "request "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
        }
    }

    //echo
    public void run(){
        try{
            Scanner s = new Scanner(theClient.getInputStream());
            PrintWriter pout = new PrintWriter(theClient.getOutputStream());
            String command = s.nextLine();
            System.out.println(Integer.toString(theClient.getLocalPort())+" recieved command: "+command);

            switch(command){
                case value:
                    qid = command.qid
                    qround = command.qround
                    qvectorvalue = command.qmessagevalue

                    if (!neversentecho){
                        neversentecho = true;
                        for (int server : serverList) {
                            System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
                            TCPSendClientRequest(hostAddress, server, "request "+ Integer.toString(pid)+ " "+qroundecho+ Integer.toString(qvectorValue));
                        }
                    }
                case echo:
                    if(5-1&neversentready){
                        neversentready=true;
                        for (int server : serverList) {
                            System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
                            TCPSendClientRequest(hostAddress, server, "request "+ Integer.toString(pid)+ " "+qroundready+ Integer.toString(qvectorValue));
                        }
                    }

                case ready:
                    if(1+1& neversentready){
                        neversentready=true;
                        for (int server : serverList) {
                            System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
                            TCPSendClientRequest(hostAddress, server, "request "+ Integer.toString(pid)+ " "+qroundready+ Integer.toString(qvectorValue));
                        }
                    }
            }

        }

    }

    public RBRecv(int pid, int round, arraylist value){
        recv(·; qr{ready}; c) from n − f processes
        return (q,r,c);
    }
}




/*
 * serverthread
 * handle RB and WT
 * */
public class TCPServerThread extends Thread {
    Inventory inven;
    Socket theClient;

    public TCPServerThread(Inventory i, Socket s) {
        this.inven=i;
        theClient = s;
    }

    public void run() {
        try {
            Scanner s = new Scanner(theClient.getInputStream());
            PrintWriter pout = new PrintWriter(theClient.getOutputStream());

            String [] commandList = s.nextLine().split(" ");

            byte [] returnByte = null;
            switch (commandList[0]) {
                case "purchase":
                    returnByte = inven.Purchase(commandList);
                    break;
                case "search":
                    returnByte = inven.Search(commandList);
                    break;
                case "cancel":
                    returnByte = inven.Cancel(Integer.valueOf(commandList[1]));
                    break;
                case "list":
                    returnByte = inven.List();
                    break;

            }
            //System.out.println("Recived command: "+s.nextLine());
            pout.println(new String(returnByte));
            pout.flush();
            theClient.close();
            //
        }catch(IOException e){
            System.out.println(e);
        }

    }

}