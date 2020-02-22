import org.junit.jupiter.api.Test;
import sample.InputException;
import sample.InvalidAgeExeption;
import sample.Validering;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValideringInputTest {

    @Test
    public void testValidInput() {
        assertDoesNotThrow( () -> Validering.valideringInput("Eskil", "Nysether", "s330457@oslomet.no", "48183217"));
        assertDoesNotThrow( () -> Validering.valideringInput("Ola", "Nordmann", "olanor@gmail.com", "12345678"));
        assertDoesNotThrow( () -> Validering.valideringInput("Kari", "Nordmann", "karnor@yahoo.com", "55555555"));
    }

    @Test
    public void testInvalidFornavn() {
        assertThrows(InputException.class, () -> Validering.valideringInput("", "Hansen", "olanor@gmail.com", "12345678"));
        assertThrows(InputException.class, () -> Validering.valideringInput("999", "Olsen", "s555555@oslomet.no", "98765432"));
    }

    @Test
    public void testInvalidEtternavn() {
        assertThrows(InputException.class, () -> Validering.valideringInput("Harald", "nilsen", "harnil@gmail.com", "55555555"));
        assertThrows(InputException.class, () -> Validering.valideringInput("Leana", "123", "leana@live.no", "65432178"));
    }

    @Test
    public void testInvalidEpost() {
        assertThrows(InputException.class, () -> Validering.valideringInput("Harald", "Nilsen", "", "55555555"));
        assertThrows(InputException.class, () -> Validering.valideringInput("Leana", "Rodriguez", "@feil.epost", "65432178"));
    }

    @Test
    public void testInvalidTelefonnummer() {
        assertThrows(InputException.class, () -> Validering.valideringInput("Harald", "Nilsen", "harnil@gmail.com", ""));
        assertThrows(InputException.class, () -> Validering.valideringInput("Leana", "Rodriguez", "leana@live.no", "65432hei"));
    }

}

class FoedselsdatoValideringTest {

    @Test
    public void testValidFoedselsdato() {
        LocalDate date = LocalDate.of(1990, 12, 03);

        assertDoesNotThrow( () -> Validering.foedselsdatoValidering(date));
    }

    @Test
    public void testInvalidFoedselsdato() {
        assertThrows(InvalidAgeExeption.class, () -> Validering.foedselsdatoValidering(null));
    }
}
