package sample;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {
    public static String CalculateAge(LocalDate fødselsdato) {
        int alder = Period.between(fødselsdato, LocalDate.now()).getYears();
        return Integer.toString(alder);
    }
}
