package ee382n;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reliable_broadcast_and_witness {
    //private int[] serverList;
    Scanner din;
    PrintStream pout;
    Socket server;
    static int [] serverList = new int[1];

    //alg1
    public void RBSend(String msg, String messageType) throws IOException {

        for (int server_port: serverList) {
            System.out.println("sending message: "+msg);
            TCPSendClientRequest("localhost", server_port,  messageType+msg);
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
    }


    /*
    kai's /*Mendes Herlihy algorithm
    *input: I: input in R of d dimension

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

    */


    //algorithm 6!
  public void AsyncAgreeMH(){

    int fault= 1;
    int pid = 1;
    Reliable_broadcast_and_witness rbw= new Reliable_broadcast_and_witness();


    ArrayList<Double> msgVector = new ArrayList<Double>();
    msgVector.add(2.5);
    msgVector.add(3.5);
    double [] v = new double[];

    //calculate rounds needs to be a sychronous function


    int ROUND =calculateRounds(msgVector);
    int v = calculateRoundValue(msgVector);
    MSG_2D_CLASS newMessage= new MSG_2D_CLASS(pid, round, v);

    //for m → 1, . . . , d do
    for (int m = 1; m<=msgVector.size(); m++){
      Arraylist<MSG_2D_CLASS> H = new ArrayList<MSG_2D_CLASS>();
      Arraylist<MSG_2D_CLASS> V = new ArrayList<MSG_2D_CLASS>();
      MSG_2D_CLASS S = new MSG_2D_CLASS();
      int currentRound = 1;

      while(H.size()<= fault){
        //change out echo returns message
        Arraylist<MSG_2D_CLASS> V = RBSend(newMessage);
        S = Safe(V);
        v[m] = Midpoint(S[m]);

        if(currentRound == ROUND){
          //send halt message for this round
          RBSEND()
        }
        currentRound++;
        //add check halt variable here to exist loop
        

      }
    }
  }


    public static void main(String[] args) throws IOException {

        Reliable_broadcast_and_witness rbw= new Reliable_broadcast_and_witness();

        int portNumber = 5003;
        int pid = 1;
        int round = 1;
        int numServer = 1;
        boolean neverSentEcho = false;
        boolean neverSentReady = false;
        boolean recieveAllVal = false;
        int fault = 0;
        //serverList =new int[numServer];
        serverList[0] = 5002;
        ArrayList<Double> msgVector = new ArrayList<Double>();
        msgVector.add(2.5);
        msgVector.add(3.5);
        //rbw.RBSend(newMessage, "value:");
                System.out.println("tcp server on port "+Integer.toString(portNumber)+" started:");
        ServerSocket listener  = new ServerSocket(portNumber);
        MSG_2D_CLASS newMessage = new MSG_2D_CLASS(pid,round, msgVector);

        Map<String, Integer> send_echoCounter = new HashMap<>();
        Map<String, Integer> send_readyCounter = new HashMap<>();
        Map<String, Integer> receive_echoCounter = new HashMap<>();
        Map<String, Integer> receive_readyCounter = new HashMap<>();


        //algo 4
        ArrayList<MSG_2D_CLASS> val = new ArrayList<MSG_2D_CLASS>();    //value
        ArrayList<MSG_3D_CLASS> rep = new ArrayList<MSG_3D_CLASS>();    //report
        ArrayList<MSG_2D_CLASS> wit = new ArrayList<MSG_2D_CLASS>();    //witness

        while (true) {
            Socket connectionSocket = listener.accept();
            BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String message = inFromClient.readLine();
            System.out.println("Received: " + message);
            outToClient.writeBytes(message);
            MSG_2D_CLASS messageValue = null;


            String key;
            switch (message.split(":")[0]){
                //(1,1,.)
                //(1,2,.)
                //array
                //Map<String, Integer> = new HashMap<>();
                //Map<"pid.r", # of echos>
                //TODO change newmessage in these switch statements
                case "value":
                    messageValue.fromMessageValue( message.split(":")[1]);
                    key = Integer.toString(messageValue.getPid())+"."+Integer.toString(messageValue.getRound());
                    if(!send_echoCounter.containsKey(key)){
                        rbw.RBSend(newMessage.toMessageString(), "echo:");
                        //e.g. ("1.1",1)
                        send_echoCounter.put(key, 1);
                        receive_echoCounter.put(key,1);

                    }
                    break;
                    //get val for algo 4
                case "Val":
                    String messageVal=( message.split(":")[1]);
                    String []messageValString = messageVal.split(" ");

                    String [] valNum = messageValString[2].split("/");

                    ArrayList<MSG_2D_CLASS> eachValue = new ArrayList<MSG_2D_CLASS>();
                    for(int i = 0; i<valNum.length; i++){
                        MSG_2D_CLASS value = null;
                        value.fromMessageValue(valNum[i]);

                        eachValue.add(value);
                    }
                    MSG_3D_CLASS report = new MSG_3D_CLASS(Integer.parseInt(messageValString[0]), Integer.parseInt(messageValString[1]), eachValue);
                    rep.add(report);

                    //add this val array to report, then add common with current val into wit
                    if (wit.size()>= numServer-fault){
                        //done, val contains consensus
                        //return val;
                    }
                    else{
                        for (int i = 0; i<rep.size(); i++){
                            if(val.contains(rep.get(i).getMsg3d())){
                                wit=rep.get(i).getMsg3d();
                            }
                        }

                    }
                    break;
                case "echo":
                    messageValue.fromMessageValue( message.split(":")[1]);
                    //recieved echo from server 2
                    //echocoounter ("1.1",1)
                    key = Integer.toString(messageValue.getPid())+"."+Integer.toString(messageValue.getRound());
                    if(receive_echoCounter.get(key)!=null)
                        receive_echoCounter.put(key, receive_echoCounter.get(key)+1);
                    else
                        receive_echoCounter.put(key,1);

                    if(receive_echoCounter.get(key)>=(numServer-fault)){
                        if(!send_readyCounter.containsKey(key)){
                            rbw.RBSend(newMessage.toMessageString(), "ready:");
                            send_readyCounter.put(key,1);
                            receive_readyCounter.put(key,1);
                        }
                    }
                    break;
                case "ready":
                    messageValue.fromMessageValue( message.split(":")[1]);
                    val.add(messageValue);

                    key = Integer.toString(messageValue.getPid())+"."+Integer.toString(messageValue.getRound());

                    //algorithm 3 check:do we have n-f messages

                    if(receive_readyCounter.get(key)!=null)
                        receive_readyCounter.put(key, receive_readyCounter.get(key)+1);
                    else
                        receive_readyCounter.put(key,1);

                    //TODO find out whats going on next time
                    //TODO find out if we need to send and receive readycounter in the following block
                    if(receive_readyCounter.get(key)>= (fault+1)){
                        if(!send_readyCounter.containsKey(key)){
                            rbw.RBSend(newMessage.toMessageString(), "ready:");
                            send_readyCounter.put(key,1);
                            receive_readyCounter.put(key,1);
                        }
                    }

                    //if recieved n-f ready message, send out our recieved val array to all
                    if(val.size()>=(numServer-fault) &&  recieveAllVal==false){
                        recieveAllVal=true;
                        String valMessage = null;

                        for (int i = 0; i <val.size(); i++){
                            valMessage= valMessage+ val.get(i).toMessageString()+'/';
                        }

                        valMessage = Integer.toString(pid)+" "+ Integer.toString(round)+" "+valMessage;

                        //exp: 1 2 1 2 1.1 2.2/2 2 1.1 2.2
                        rbw.RBSend(valMessage.substring(0, valMessage.length() - 1), "val:");
                    }
                    /*
                    true if received_ready from n-f

                    if(received ready n-f times){

                        //val =RBReceiveWitness(r);

                        call alg 3; }

                    */


                    break;
            }

            
             
     /*open n-1 channels here to all other servers
        for (int i =0; i <= numServer-1; i++){
//            ServerSocket listener  = new ServerSocket(portNumber);
//
        }*/

        }

    }
}
