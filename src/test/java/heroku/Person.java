package heroku;

public class Person {
    private String firstName;
    private String lastName;
    private String due;

    Person(String firstName, String lastName, String due){
        this.firstName = firstName;
        this.lastName = lastName;
        this.due = due;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public Double getDue(){
        return Double.parseDouble(this.due.replace("$", ""));
    }
}
