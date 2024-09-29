package trainTricketReservation;

public class Passenger {
    static int id=1;
    String name;
    int age;
    String pref;
    int passengerId;
    String alloted;
    int number;

    public Passenger(String name,int age,String pref){
        this.name=name;
        this.age=age;
        this.pref=pref;
        this.passengerId=id++;
        this.alloted="";
        this.number=-1;
    }
}
