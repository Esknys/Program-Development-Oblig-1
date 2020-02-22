package sample;

public class InvalidAgeExeption extends Exception {
    public String type;
    public InvalidAgeExeption(String msg, String type) {
        super(msg);
        this.type = type;
    }
}
