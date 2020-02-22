package sample;


public class PersonFormatter {

    public static String DELIMITER = ";";

    public static String formatPerson(Person p) {
        return "\n" + p.getFornavn() + DELIMITER + p.getEtternavn() + DELIMITER + p.getEpost() + DELIMITER + p.getTelefonnummer() + DELIMITER + p.getFoedselsdato() + DELIMITER + p.getAlder();
}


}
