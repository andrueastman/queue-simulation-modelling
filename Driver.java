package simulation;

import java.util.LinkedList;

import javax.xml.namespace.QName;

public class Driver{
    public Driver(){
        queue=new LinkedList<customer>();
        queue2=new LinkedList<customer>();
    }
    private double clock=0;
    private double arrivalClock=0;
    private LinkedList<customer> queue;
    private LinkedList<customer> queue2;
    private int qPeople=0;
    private double totalIdleTime=0;
    private double totalServiceTime=0;
    private double totalWaitingTime=0;

    public void newCustomer(){
        customer cus=new customer();
        cus.setIat(); // generate random inter arrival time
        //clock=clock+cus.getIat();
        arrivalClock=arrivalClock+cus.getIat();
        cus.setClockTime(arrivalClock);
        queue.add(cus);
    }

    public void serveCustomer(){
        customer cus=queue.remove();//get first
        cus.setSertime();
        if(!queue2.isEmpty()){
            //cus.setSerstart(queue2.peekLast().getSerend());
            if(queue2.peekLast().getSerend()>cus.getClockTime()){
                cus.setSerstart(queue2.peekLast().getSerend());
            }else{
                cus.setSerstart(arrivalClock);
            }
            cus.setIdleTime(cus.getSerstart()-queue2.peekLast().getSerend());//set the idle time of server
            if(cus.getClockTime()<queue2.peekLast().getSerend()){//count queue people
                qPeople++;
            }
        }else{//if outgoers do not exist
            cus.setSerstart(arrivalClock);
            cus.setIdleTime(0);

        }

        clock=clock+cus.getSertime();
        cus.setSerend(clock);
        queue2.add(cus);
        totalServiceTime=totalServiceTime+cus.getSertime();
        totalWaitingTime=totalWaitingTime+cus.getWaitTime();
        totalIdleTime=totalIdleTime+cus.getIdleTime();
        System.out.println("IAT: "+cus.getIat()+" CLockTime: "+cus.getClockTime()+" Service Time: "+cus.getSertime()+" Service Start: "+cus.getSerstart()+" Service End: "+cus.getSerend()+" Idle Time: "+cus.getIdleTime());
    }


    public static void main(String[] args) {
        Driver drive=new Driver();
        //System.out.println(drive.getArrivalClock() + "\n"+drive.getClock());
        int customers=10;
        for(int i=0;i<customers;i++){
            drive.newCustomer();
            drive.serveCustomer();
        }
        //System.out.println(drive.getArrivalClock() + "\n"+drive.getClock());
        System.out.println("People who passed through queue: "+drive.qPeople);
        System.out.println("Total service time: "+drive.totalServiceTime);
        float averageServiceTime=(float)drive.totalServiceTime/customers;
        System.out.println("Average Service time: "+averageServiceTime);
        float averageWaitTime=(float)drive.totalWaitingTime/customers;
        System.out.println("Average Waiting Time time: "+averageWaitTime);
        double idleTimeProportion=drive.totalIdleTime/drive.clock;
        System.out.println("Proportion of Idle Time: "+idleTimeProportion);


    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public double getArrivalClock() {
        return arrivalClock;
    }

    public void setArrivalClock(double arrivalClock) {
        this.arrivalClock = arrivalClock;
    }
}

