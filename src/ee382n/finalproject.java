package ee382n;

import java.util.*;

public class finalproject {
    ArrayList<MSG_2D_CLASS> msg_set1 = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ArrayList<Double> msg1= new ArrayList<Double>();
        //this.msg_set1.add(msg1);
        msg1.add(1.1);
        msg1.add(1.2);
        msg1.add(1.3);

        MSG_2D_CLASS msg_2D_class1 = new MSG_2D_CLASS(22,1,msg1);
        System.out.println(msg_2D_class1.getPid());
        System.out.println("HELLO");

    }
}
