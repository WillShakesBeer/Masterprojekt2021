package Logic;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOutTask extends TimerTask {
    private Thread t;
    private Timer timer;
    private Game game;

    TimeOutTask(Thread t, Timer timer,Game game){
        this.t = t;
        this.timer = timer;
    }

    public void run() {
        if (t != null && t.isAlive()) {
            t.interrupt();
            timer.cancel();
            System.out.println("Interrupt");
        }
    }
}
