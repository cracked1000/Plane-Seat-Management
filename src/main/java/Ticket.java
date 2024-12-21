import java.io.FileWriter;
import java.io.File;
public class Ticket {
    private int row;//assognd a row variable
    private int column;//assigns a column vaiable
    private double price;//assign a price variable
    private Person person;//assigns a person variable

    public Ticket(int row, int column, double price, Person person) {//sets a constructer
        this.row = row;
        this.column = column;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return row;//gets the row and returns row
    }

    public void setRow(int row) {//sets the row
        this.row = row;
    }

    public int getColumn() {//gets the column and returns column
        return column;
    }

    public void setColumn(int column) {
        this.column = column;//sets the column
    }

    public double getPrice() {//gets the price and returns the price
        return price;
    }

    public void setPrice(double price) {
        this.price = price;//sets the price
    }

    public Person getPerson() {//sets the person and returns the person
        return person;
    }

    public void setPerson(Person person){
        this.person = person;//sets the person
    }

    public void printTicketInfo() {//prints out the ticket info
        person.display();
        System.out.println("Row: " + (char) ('A' + row));
        System.out.println("Seat: " + (column + 1));
        System.out.println("Price: $" + price);

    }
    public void save() {
        try {
            FileWriter writer = new FileWriter(Character.toString((char) ('A' + row)) + column + ".txt");
            writer.write("Name:" + person.getName() + "\n");
            writer.write("Surname:" + person.getSurname() + "\n");
            writer.write("Email:" + person.getEmail() + "\n");
            writer.write("Row Letter:" + (char) ('A' + row)+"\n");
            writer.write("Seat Number:" + column + "\n");
            writer.write("Price:" + "£" + price + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);//this writes all the info to the text file
        }
    }

}
