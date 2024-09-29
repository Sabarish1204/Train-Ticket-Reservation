package trainTricketReservation;

import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        int opt = -1;
        while (flag) {
            System.out.println("\n -------------------------");
            System.out.println("1.Book Ticket \n2.Cancel Ticket \n3.Check Availability \n4.Booked Tickets \n5.Exit");
            System.out.println("--------------------------\n Select an option.");
            opt = s.nextInt();
            String name;
            int age;
            String pref;

            Ticket ticket=new Ticket();
            switch (opt) {
                case 1:
                    System.out.println("Enter Name : ");
                    name = s.next();
                    System.out.println("Enter Age : ");
                    age = s.nextInt();
                    System.out.println("Select Berth ('U' or 'M' or 'L')");
                    pref = s.next();
                    Passenger p= new Passenger(name, age, pref);
                    bookTicket(p);

                    break;
                case 2:

                    //get only the passenger Id
                    System.out.println("Enter the Passenger ID : ");
                    int id=s.nextInt();
                    cancelTicket(id);
                    break;
                case 3:
                    System.out.println("Available Seats\n---------------");
                    Ticket book=new Ticket();
                    book.printAvailable();
                    break;
                case 4:
                    Ticket book1=new Ticket();
                    book1.printPassenger();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter a valid option number");
                    break;
            }
        }
    }
    static void bookTicket(Passenger p){
        Ticket book=new Ticket();

        if(Ticket.wait ==0){
            System.out.println("No Tickets Available");
            return;
        }
        if((p.pref.equals("L") && Ticket.lowBerth >0) || (p.pref.equals("M") && Ticket.midBerth>0) || (p.pref.equals("U") && Ticket.upBerth>0)){
            if(p.pref.equals("L")){
                System.out.println("Lower Berth is given");
                book.bookTicket(p,(Ticket.lowBerthList.get(0)),"L");
                Ticket.lowBerthList.remove(0);
                Ticket.lowBerth--;

            }
            else if(p.pref.equals("M")){
                System.out.println("Middle Berth is given");
                book.bookTicket(p,(Ticket.midBerthList.get(0)),"M");
                Ticket.midBerthList.remove(0);
                Ticket.midBerth--;
            }
            else if(p.pref.equals("U")){
                System.out.println("Upper Berth is given");
                book.bookTicket(p,(Ticket.upBerthList.get(0)),"U");
                Ticket.upBerthList.remove(0);
                Ticket.upBerth--;
            }
        }
        //booking any available tickets
        else if(Ticket.lowBerth > 0){
            System.out.println("Lower Berth is given");
            book.bookTicket(p,(Ticket.lowBerthList.get(0)),"L");
            Ticket.lowBerthList.remove(0);
            Ticket.lowBerth--;
        }
        else if(Ticket.midBerth > 0){
            System.out.println("Middle Berth is given");
            book.bookTicket(p,(Ticket.midBerthList.get(0)),"M");
            Ticket.midBerthList.remove(0);
            Ticket.midBerth--;
        }
        else if(Ticket.upBerth > 0){
            System.out.println("Upper Berth is given");
            book.bookTicket(p,(Ticket.upBerthList.get(0)),"U");
            Ticket.upBerthList.remove(0);
            Ticket.upBerth--;
        }
        else if(Ticket.rac > 0){
            System.out.println("RAC is Available");
            book.addRAC(p,(Ticket.racList.get(0)),"RAC");

        }
        else if (Ticket.wait > 0){
            System.out.println("Waiting List is Available");
            book.addWaitingList(p,(Ticket.waitList.get(0)),"WL");
        }
    }
    static void cancelTicket(int id){
        Ticket book=new Ticket();
        if(!book.passengers.containsKey(id)){
            System.out.println("Passenger Details Unknown");
        }
        else{
            book.cancelTicket(id);
        }
    }
}
