package trainTricketReservation;

import java.util.*;

public class Ticket {
    static int upBerth=1;
    static int midBerth=1;
    static int lowBerth=1;
    static int rac=1;
    static int wait=1;

    static ArrayList<Integer> upBerthList =new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> midBerthList =new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> lowBerthList =new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> racList =new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> waitList =new ArrayList<>(Arrays.asList(1));
    static ArrayList<Integer> bookedList=new ArrayList<>();
    static HashMap<Integer,Passenger> passengers=new HashMap<>();
    static Queue<Integer> racQ=new LinkedList<>();
    static Queue<Integer> waitingQ=new LinkedList<>();

    public void bookTicket(Passenger p,int berthInfo,String alloted){
        p.number=berthInfo;
        p.alloted=alloted;

        passengers.put(p.passengerId,p);  //adding the passengers in the map using id

        bookedList.add(p.passengerId);  //add the total booking list

        System.out.println("--------- Ticket Booked Successfully ---------");
    }
    public void addRAC(Passenger p,int racNum,String alloted){
        p.number=racNum;
        p.alloted=alloted;
        passengers.put(p.passengerId,p); //adding the passengers according to their passenger ID

        racQ.add(p.passengerId);  //Adding the rac passenger to the queue
        rac--;
        racList.remove(0);  //remove the position that is alloted to the passengers
        System.out.println("------ Added to the RAC List -------");

    }
    public void addWaitingList(Passenger p,int WLnum,String alloted){
        p.number=WLnum;
        p.alloted=alloted;
        passengers.put(p.passengerId,p);

        waitingQ.add(p.passengerId);   //adding to the Wait List Queue
        wait--;
        waitList.remove(0);
        System.out.println("------- Added to the Waiting List -------");
    }
    public void cancelTicket(int id){
        Passenger p=passengers.get(id);
        passengers.remove(Integer.valueOf(id));  //removing passenger from the map
        bookedList.remove(Integer.valueOf(id));  //removing from the bookings List

        int positionBooked=p.number;  //taking the booked position which is free now
        System.out.println("-------- Ticket Cancelled Successfully --------");

        //increasing the position for the free seat
        if(p.alloted=="L"){
            lowBerth++;
            lowBerthList.add(positionBooked);
        }
        else if (p.alloted=="M") {
            midBerth++;
            midBerthList.add(positionBooked);
        }
        else if (p.alloted=="U") {
            upBerth++;
            upBerthList.add(positionBooked);
        }

        //alloting free seats to RAC passengers
        if(racQ.size() > 0){
            Passenger pRac=passengers.get(racQ.poll());
            int posRac=pRac.number;
            racList.add(posRac);  //free up the occupied seat of the passenger
            racQ.remove(pRac.passengerId);
            rac++;

            //moving Waiting lis to RAC list
            if(waitingQ.size() > 0){
                Passenger pWL=passengers.get(waitingQ.poll());
                int posWl= pWL.number;
                waitList.add(posWl);
                waitingQ.remove(pWL.passengerId);

                pWL.number=racList.get(0);
                pWL.alloted="RAC";
                racList.remove(0);
                racQ.add(pWL.passengerId);

                wait++;
                rac--;
            }

            Train.bookTicket(pRac); //to book the ticket for the rac passenger
        }
    }

    public void printAvailable(){
        System.out.println("Lower Berth : "+lowBerth);
        System.out.println("Middle Berth : "+midBerth);
        System.out.println("Upper Berth : "+upBerth);
        System.out.println("RAC : "+rac);
        System.out.println("Waiting List : "+wait);
        System.out.println("-----------------------");
    }

    public void printPassenger(){
        if(passengers.size()==0){
            System.out.println("No Passengers Details Available");
            return;
        }

        for(Passenger p : passengers.values()){
            System.out.println("Passenger ID : "+p.passengerId);
            System.out.println("Name : "+p.name);
            System.out.println("Age : "+p.age);
            System.out.println("Status : "+p.number+p.alloted);
            System.out.println(" ---------------------- ");
        }
    }
}
