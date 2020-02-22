package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonRegister implements Serializable {

    public ArrayList<Person> personer;

    public PersonRegister(ArrayList<Person> personer) {
        this.personer = personer;
    }

    public void add (Person person) {
        this.personer.add(person);
    }

    public String toString() {
        StringBuilder ut = new StringBuilder();
        for (Person person1 : personer) {
            ut.append("\n \n Fornavn: ").append(person1.fornavn).append("\n Etternavn: ").append(person1.etternavn).append("\n Epost: ").append(person1.epost).append("\n Telefonnummer: ").append(person1.telefonnummer).append("\n Fødselsdato: ").append(person1.foedselsdato).append("\n Alder: ").append(person1.alder);
        }
        return ut.toString();
    }


    private transient ObservableList<Person> peopleObservable = FXCollections.observableArrayList();

    public ObservableList<Person> getPeopleObservable() {
        return peopleObservable;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(peopleObservable));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Person> list = (List<Person>) s.readObject();
        peopleObservable = FXCollections.observableArrayList();
        peopleObservable.addAll(list);
    }

}
