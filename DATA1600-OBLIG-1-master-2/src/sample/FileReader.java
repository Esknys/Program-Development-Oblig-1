package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {


    public ObservableList<Person> readPeople(File file)
            throws IOException
    {
        ObservableList<Person> plist = FXCollections.observableArrayList();

        try (Scanner myReader = new Scanner(file)) {

            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Person p = FileReader.parsePerson(line);
                plist.add(p);
            }
        }
        return plist;
    }

    private static Person parsePerson(String line)
            throws InvalidPersonFormatException {
        String[] split = line.split(PersonFormatter.DELIMITER);
        if(split.length != 6) {
            throw new InvalidPersonFormatException("Must use semicolon to separate the five data fields");
        }

        String fornavn = split[0];
        String etternavn = split[1];
        String epost = split[2];
        String telefonnummer = split[3];
        String foedselsdato = split[4];
        String alder = split[5];

        return new Person(fornavn, etternavn, epost, telefonnummer, foedselsdato, alder);
    }

    public void attachTableView(TableView tv, ObservableList<Person> personer) {
        tv.setItems(personer);
    }

}
