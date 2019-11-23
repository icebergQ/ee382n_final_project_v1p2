package ee382n;

import java.io.*;
import java.util.*;

public class finalproject {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ArrayList<Double> msg1= new ArrayList<Double>();
        msg1.add(1.1);
        msg1.add(1.2);
        msg1.add(1.3);

        msg_class msg_class1 = new msg_class(22,1,msg1);
        System.out.println(msg_class1.getPid());

    }
}
