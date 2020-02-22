package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1;


    public String fornavn;
    public String etternavn;
    public String epost;
    public String telefonnummer;
    public String foedselsdato;
    public String alder;

    public Person(String fornavn, String etternavn, String epost, String telefonnummer, String foedselsdato, String alder) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
        this.foedselsdato = foedselsdato;
        this.alder = alder;
    }



    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getFoedselsdato() {
        return foedselsdato;
    }

    public String getAlder() {
        return alder;
    }

    public String toString() {
        String ut = "Fornavn: "+fornavn+"\n Etternavn: "+etternavn+"\n Epost: "+epost+"\n Telefonnummer: "+telefonnummer+"\n Fødselsdato: "+foedselsdato + "\n Alder: " + alder;
        return ut;
    }
}
