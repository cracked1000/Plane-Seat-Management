public class Person {
    private String name;//assigns a name variable
    private String surname;//assigns a surname variable
    private String email;// assigns a email variable

    public Person(String name, String surname, String email) {//initiates a constructor
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {//gets name and returns name
        return name;
    }

    public void setName(String name) {
        this.name = name;//sets the name
    }

    public String getSurname() {//gets surname and retuns the surname
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;//sets the surname
    }

    public String getEmail() {//gets the email and returns the email
        return email;
    }

    public void setEmail(String email) {
        this.email = email;//sets the email
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }//prints out the name and surname and email
}
