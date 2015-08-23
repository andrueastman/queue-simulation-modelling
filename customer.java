
package simulation;

import java.util.Random;

public class customer {
    private double iat;
    private double sertime;
    private double idleTime;
    private double serstart;
    private double serend;
    private double clockTime;
    private double serviceTimeRange=2;
    private double arrivalTimeRange=6;

    Random number = new Random();

    public double getIat() {
        return iat;
    }
    public void setIat() {
        this.iat = number.nextDouble()*arrivalTimeRange;
    }
    public double getSertime() {
        return sertime;
    }
    public void setSertime() {
        this.sertime = number.nextDouble()*serviceTimeRange;
    }

    public double getWaitTime() {
        return serstart-clockTime;
    }

    public double getSerstart() {
        return serstart;
    }
    public void setSerstart(double serstart) {
        this.serstart = serstart;
    }
    public double getSerend() {
        return serend;
    }
    public void setSerend(double serend) {
        this.serend = serstart+sertime;
    }
    public double getClockTime() {
        return clockTime;
    }
    public void setClockTime(double clockTime) {
        this.clockTime = clockTime;
    }
    public double getIdleTime() {
        return idleTime;
    }
    public void setIdleTime(double idleTime) {
        this.idleTime = idleTime;
    }
}
