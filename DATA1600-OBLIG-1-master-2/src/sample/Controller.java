package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txtFornavn;

    @FXML
    private TextField txtEtternavn;

    @FXML
    private DatePicker dateBorn;

    @FXML
    private TextField txtEpost;

    @FXML
    private TextField txtTelefonnummer;

    @FXML
    private Label lblFornavnException;

    @FXML
    private Label lblFoedselsdatoException;

    @FXML
    private Label lblEpostException;

    @FXML
    private Label lblTelefonnummerException;

    @FXML
    private Button btnInn;

    @FXML
    private Label lblEtternavnException;

    @FXML
    private Button btnLastInnFil;

    @FXML
    private TableView<?> tableView;

    private ArrayList<Person> personer = new ArrayList<>();
    private PersonRegister personRegister = new PersonRegister(personer);

    @FXML
    private TextField txtFilter;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    void filtrer(ActionEvent event) {
        File file = new File("personregister.txt");


        FileReader fileReader = new FileReader();

        try {
            ObservableList<Person> people = fileReader.readPeople(file);

            ObservableList<Person> filtrertListe;
            filtrertListe = people;

            switch (choiceBox.getValue()) {
                case "Fornavn":
                    filtrertListe = filtrertListe.stream().filter(i -> i.getFornavn().contains(txtFilter.getText())).
                            collect(Collectors.toCollection(FXCollections::observableArrayList));
                    break;
                case "Etternavn":
                    filtrertListe = filtrertListe.stream().filter(i -> i.getEtternavn().contains(txtFilter.getText())).
                            collect(Collectors.toCollection(FXCollections::observableArrayList));
                    break;
                case "Alder":
                    filtrertListe = filtrertListe.stream().filter(i -> i.getAlder().equals(txtFilter.getText())).
                            collect(Collectors.toCollection(FXCollections::observableArrayList));
                    break;
                case "Epost":
                    filtrertListe = filtrertListe.stream().filter(i -> i.getEpost().contains(txtFilter.getText())).
                            collect(Collectors.toCollection(FXCollections::observableArrayList));
                    break;
                case "Tlf":
                    filtrertListe = filtrertListe.stream().filter(i -> i.getTelefonnummer().contains(txtFilter.getText())).
                            collect(Collectors.toCollection(FXCollections::observableArrayList));
                    break;




            }

            fileReader.attachTableView(tableView, filtrertListe);

        } catch (IOException ioe) {
            txtTelefonnummer.setText("Noe gikk feil: " + ioe.getMessage());
        }
    }




    @FXML
    void lastInnFil(ActionEvent event) {

        File file = new File("personregister.txt");


        FileReader fileReader = new FileReader();

        try {
            ObservableList<Person> people = fileReader.readPeople(file);

           fileReader.attachTableView(tableView, people);

        } catch (IOException ioe) {
            lblTelefonnummerException.setText("Noe gikk feil: " + ioe.getMessage());
        }
    }



    @FXML
    void sendInn(ActionEvent event) {

        String fornavn = txtFornavn.getText();
        String etternavn = txtEtternavn.getText();
        String epost = txtEpost.getText();
        String telefonnummer = txtTelefonnummer.getText();
        LocalDate foedselsdato = dateBorn.getValue();

        String fornavn1;
        String etternavn1;
        String epost1;
        String telefonnummer1;
        String foedselsdato1;
        String alder = CalculateAge.CalculateAge(foedselsdato);

        try {

            foedselsdato1 = Validering.foedselsdatoValidering(foedselsdato);

            if (Validering.valideringInput(fornavn, etternavn, epost, telefonnummer) == true) {
                fornavn1 = fornavn;
                etternavn1 = etternavn;
                epost1 = epost;
                telefonnummer1 = telefonnummer;
                Person person1 = new Person(fornavn1, etternavn1, epost1, telefonnummer1, foedselsdato1, alder);

                personRegister.add(person1);

                File file = new File("personregister.txt");
                //SERIALISERING -----------------------------------
                Path path = Paths.get("personregister.jobj");

                try (OutputStream os = Files.newOutputStream(path);
                     ObjectOutputStream out = new ObjectOutputStream(os))
                {
                    out.writeObject(person1);
                }
                //----------------------------------------------------

                String formatert = PersonFormatter.formatPerson(person1);


                FileSaver.save(formatert, file);


                txtFornavn.clear();
                txtEtternavn.clear();
                dateBorn.setValue(null);
                txtEpost.clear();
                txtTelefonnummer.clear();
                lblFornavnException.setText("");
                lblEtternavnException.setText("");
                lblFoedselsdatoException.setText("");
                lblEpostException.setText("");
                lblTelefonnummerException.setText("");
            }

        } catch (InputException ie) {
            switch (ie.type) {
                case "Fornavn":
                    lblFornavnException.setText(ie.getMessage());
                    break;
                case "Etternavn":
                    lblEtternavnException.setText(ie.getMessage());
                    break;
                case "Fødselsdato":
                    lblFoedselsdatoException.setText(ie.getMessage());
                    break;
                case "Epost":
                    lblEpostException.setText(ie.getMessage());
                    break;
                case "Telefonnummer":
                    lblTelefonnummerException.setText(ie.getMessage());
                    break;
            }
        } catch (InvalidAgeExeption iae) {
            switch (iae.type) {
                case "Negativ":
                    lblFoedselsdatoException.setText(iae.getMessage());
                    break;
                case "Tom":
                    lblFoedselsdatoException.setText(iae.getMessage());
                    break;
            }
        } catch (IOException ioe) {
            lblTelefonnummerException.setText("Feil");
        }
    }
}



