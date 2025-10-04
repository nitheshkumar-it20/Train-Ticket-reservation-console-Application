public class Passenger {
    public String name;
    public int age;
    public String gender;
    public String berthpreference;
    public String allotedberth;
    public String ticketid;

    public Passenger(String name,int age,String gender,String berthpreference,String allotedberth,String ticketid) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthpreference = berthpreference;
        this.allotedberth = allotedberth;
        this.ticketid = ticketid;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketid + ", Name: " + name +
               ", Age: " + age + ", Gender: " + gender +
               ", Berth: " + allotedberth;
    }
}
