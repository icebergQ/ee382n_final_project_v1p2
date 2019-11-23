package ee382n;

import java.util.ArrayList;

public class MSG_3D_CLASS {
    int pid;
    int round;
    ArrayList<ArrayList<MSG_2D_CLASS>> msg3d = new ArrayList<ArrayList<MSG_2D_CLASS>>();

    public MSG_3D_CLASS(int pid, int round, ArrayList<ArrayList<MSG_2D_CLASS>> msg) {
        this.pid = pid;
        this.round=round;
        this.msg3d=msg;
    }

    public int getRound() {
        return round;
    }

    public int getPid() {
        return pid;
    }

    public ArrayList<ArrayList<MSG_2D_CLASS>> getMsg3d() {
        return msg3d;
    }

    public void setMsg3d(ArrayList<ArrayList<MSG_2D_CLASS>> msg3d) {
        this.msg3d = msg3d;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
