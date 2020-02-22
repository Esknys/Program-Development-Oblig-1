package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class Filtering {
    public static ObservableList<Person> Filtrer(ObservableList<Person> liste, String filterType, String filterText) {


        switch (filterType) {
            case "Fornavn":
                liste.stream().filter(i -> i.getFornavn().contains(filterText)).
                        collect(Collectors.toCollection(FXCollections::observableArrayList));
                System.out.println("Du filtrerer etter fornavn");
            break;
            case "Etternavn":

                break;
            case "Alder":

                break;
            case "Epost":

                break;
            case "Tlf":

                break;




        }
        return liste;
    }
}
