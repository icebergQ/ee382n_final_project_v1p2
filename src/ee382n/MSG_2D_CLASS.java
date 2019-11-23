package ee382n;

import java.util.ArrayList;

public class MSG_2D_CLASS {
    int pid;
    int round;
    ArrayList<Double> msg2d = new ArrayList<Double>();

    public MSG_2D_CLASS(int pid, int round, ArrayList msg2d) {
        this.pid = pid;
        this.round=round;
        this.msg2d = msg2d;
    }

    public int getPid() {
        return pid;
    }

    public int getRound() {
        return round;
    }

    public ArrayList getMsg2d() {
        return msg2d;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setPid(int pid) {
        this.pid = pid;

    }

    public void setMsg2d(ArrayList<Double> msg2d) {
        this.msg2d = msg2d;
    }
}
