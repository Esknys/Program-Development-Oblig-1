package sample;

public class InputException extends Exception {
    public String type;
    public InputException(String msg, String type) {
        super(msg);
        this.type = type;
    }
}
