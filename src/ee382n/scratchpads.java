
import ee382n.MSG_2D_CLASS;
import ee382n.MSG_3D_CLASS;

import java.io.IOException;
import java.net.Socket;
import java.util.*;


/****************Secontion but you have to*********************/

/* Input is a vector given by the application or by argument
 * IP and port of all other servers, its pid,
 * general server class
 * initalize 5 servers
 * run RB and WT
 * */


public class ByzantineServer{

    //or randomly generated vector based on simulating robot x y z position
    ArrayList<Double> vectorValue = new ArrayList<Double>([1,2]);
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

    /*
    Algo 1 RBSend
    TODO add handler for different content value
    sender information:
    *   sender id p
    *   sender round r
    *   sender content c
    */
    public void RBSend(int pid, int round, ArrayList<Double> vectorValue){
        for (int server : serverList) {
            System.out.println(Integer.toString(theClient.getLocalPort())+" requesting pid and lamport clock "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
            TCPSendClientRequest(hostAddress, server, "request "+ Integer.toString(pid)+ " "+ Integer.toString(vectorValue));
        }
    }

    //receive witness technique: algo 4
    public RBReceiveWitness(int r){
        Arraylist<MSG_2D_CLASS> val = new ArrayList<MSG_2D_CLASS>();    //value
        ArrayList<Arraylist<MSG_2D_CLASS>> rep = new ArrayList<ArrayList<MSG_2D_CLASS>>();    //report
        int [] wit = "";    //witness

        //change how we receieve this info**********************************
        Scanner s = new Scanner(theClient.getInputStream());
        PrintWriter pout = new PrintWriter(theClient.getOutputStream());
        String xContent = s.nextLine();
        System.out.println(Integer.toString(theClient.getLocalPort())+" recieved command: "+xContent);
        //******************************************************************

        while(val.length< 5-1){
            //implement on receive
            onReceive(int pid, int r, int[] pvalue){
                MSG_2D_CLASS xLocalContent = new MSG_2D_CLASS(xContent[pid], xContent[r], double xContent[pvalue]);
                val.add(xLocalContent);
            }

        }
        //this is sending Val, array of 2d classes
        //!TODO add distinction in RBSend to handle this
        RBSend(pid, r, Val);

        while(wit.length < n-f){
            //adding the extra incoming message into val, repeat of above to catch f messages
            onReceive(int pid, int r, double[] pvalue){
                MSG_2D_CLASS xLocalContent = new MSG_2D_CLASS(xContent[pid], xContent[r], double xContent[pvalue]);
                val.add(xLocalContent);
            }

            //receive Val information
            onReceive(int pid, int r, Arraylist<MSG_2D_CLASS> val){
                rep.add(pid, r, val);
            }
            //Wit ← {(px, r, Valx) ∈ Rep : Valx ⊆ Val}
            //witness will find n-f copies of the same valx thats in its val, when theres n-f, process is complete

        }
        return val;
    }


    /*Mendes Herlihy algorithm
    *input: I: input in R of d dimension
    */
    public AsyncAgreeMH(int [] I){
        //set the number of rounds needed R and vector updated current value v
        CalculateRounds(I);
        int [] midpoint;
        //d = number of input vector dimension
        for (int m =1 , m<= d; m++){
            H = null;
            int currentRound = 1;
            while (H.length <= f){
                RBSend(pid, currentRound, v);
                //need to wait for witness to return
                int [] Val = RBReceiveWitness(currentRound);
                //implement safe area algo
                int [] S = Safef(val);
                //implement midpoint algo
                midpoint[m] = Midpoint(S[m]);
                if(currentRound==R) RBSend(pid, currentRound, "Halt");
                else currentRound++;

                //stop and add halt to H,
                onReceive("Halt"){
                    if
                        //add halt message to H
                        H+="Halt";
                }

            }

        }
        return midpoint;
    }

    //RBEcho algo 2
    /*
           when other processes receive M, they echo it
           when processes receive n-f echo messages for M, they send ready message for M
           when processes see f+1 ready for M, it means a non-faulty processs advocates the existence of M
           when theres n-f messages, the original message can be delivered
     */
    //Alg2 upon receiving blocking or not?
    //we open n servers, each open n-1 thread to handle tcp connection to n-1 other servers
    //Each thread has while true loop to handle different incoming messages.
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

    // RBRecv algo 3
    //called by algo 6, TODO add functino to handle different 3rd parameter
    //TODO how to implement wait until n-f processes
    //may be able to add a call function in algo 2
    public RBRecv(int pid, int round, arraylist value){
        recv(·; qr{ready}; c) from n − f processes
        return (q,r,c);
    }

    //input vector in R^d
    //output scalar R,the number of rounds needed to converge along
    //each dimension 1 ≤ m ≤ d.
    //output an updated current value v;
    //MSG2D consists of element of (p,r,v)
    //Val = ArrayList<MSG2D>
    //wit = ArrayList(p,r, Val)
    //MSG3D consists of element of (p,r,Val)
    //content of wit = ArrayList<ArrayList<MSG2D>> = ArrayList<Val>
    public CalculateRounds(arraylist input_vector){
        RBSend(p, 0, input_vector);
        //each item is a 2d mssage
        ArrayList<MSG_2D_CLASS> V_received = new ArrayList<MSG_2D_CLASS>();
        //each item is a 3d message
        ArrayList<MSG_3D_CLASS> Wit_recieved = new ArrayList<MSG_3D_CLASS>();
        //Content of recevied 3d msg
        ArrayList<ArrayList<MSG_2D_CLASS>> W_received = new ArrayList<ArrayList<MSG_2D_CLASS>>();
        //( V, W ) ← (Val , Content(Wit)) from RBReceiveWitness(0)
        (V_received,Wit_recieved) = RBReceiveWitness(0);
        //calculate content of Wit_received
        W_received = Wit_recieved.getcontent();
        //U ← { barycenter of Safe f ( W ′ ) : W ′ ∈ W }
        //define U as a set of barycenter
        for (int i =0; i<W_received.length; i++){
            U.add(barycenter(Safe(W_received(i))));
        }
        //v ← barycenter of Safe f ( U )
        v= barycenter(Safe(U));
        //R ← log 2 ( d / ε · max {δ U ( m ) : 1 ≤ m ≤ d} )
        d_sqrt_over_epsolon = Math.sqrt(d)/epsilon;
        max_range_U = max_range(U);
        R = Math.ceil(Math.log(d_sqrt_over_epsolon*max_range_U)/Math.log(2));
        return (R,v);
    }

    public AsyncAgreeVG(arraylist input_vector){
        d_sqrt_over_epsolon = Math.sqrt(d)/epsilon;
        R = 1 + Math.ceil(Math.log(d_sqrt_over_epsolon*(U-v))/Math.log(1/(1-lamda)));


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