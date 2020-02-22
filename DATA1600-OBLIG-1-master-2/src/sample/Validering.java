package sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validering {


    public static boolean valideringInput(String fornavn, String etternavn, String epost, String telefonnummer) throws InputException {

            if (fornavn.matches("[A-ZÆØÅ][a-zæøå]*")) {

                if (etternavn.matches("[A-ZÆØÅ][a-zæøå]*")) {

                    if (epost.matches("[a-z0-9._]+@[a-z0-9.]+[.][a-z]{2,6}")) {

                        if (telefonnummer.matches("[0-9]{8}")) {
                            return true;
                        } else {
                            throw new InputException("Ugyldig telefonnummer", "Telefonnummer");
                        }
                    } else {
                        throw new InputException("Ugyldig epost", "Epost");
                    }
                } else {
                    throw new InputException("Ugyldig etternavn", "Etternavn");
                }
            } else {
                throw new InputException("Ugyldig fornavn", "Fornavn");
            }
        }


    public static String foedselsdatoValidering(LocalDate foedselsdato) throws InvalidAgeExeption {

        if (foedselsdato == null) {
            throw new InvalidAgeExeption("Tom dato", "Tom");
        } else {
            LocalDate now = LocalDate.now();
            boolean isBefore = foedselsdato.isBefore(now);

            if (isBefore == true) {
                DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String foedselsdato1 = foedselsdato.format(format1);

                return foedselsdato1;
            } else {
                throw new InvalidAgeExeption("Negativ alder", "Negativ");
            }
        }
    }
    }
