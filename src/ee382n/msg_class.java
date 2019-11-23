package ee382n;

import java.util.ArrayList;

public class msg_class {
    int pid;
    int round;
    ArrayList<Double> msg = new ArrayList<Double>();

    public msg_class(int pid, int round, ArrayList msg) {
        this.pid = pid;
        this.round=round;
        this.msg=msg;
    }

    public int getPid() {
        return pid;
    }

    public int getRound() {
        return round;
    }

    public ArrayList getMsg() {
        return msg;
    }
}
