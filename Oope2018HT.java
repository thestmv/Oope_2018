//Imporataan omat kansio, jossa käyttöliittymän luokka sijaitsee.
import oope2018ht.omat.*;

/**
 * Mainmetodi, joka käynnistää ohjelman käyttöliittymän.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */ 
public class Oope2018HT {
    //Vakio tervetuloa tekstille.
    private static final String TERVETULOA = "Welcome to S.O.B.";
    /** Toivottaa käyttäjän tervetulleeksi ja käynnistää ohjelman
     * käyttöliittymän.
     * @param args 
     */
    public static void main(String[] args) throws Exception{
        System.out.println(TERVETULOA);
        Kayttoliittyma omat = new Kayttoliittyma();
        //Käynnistää käyttölittymän silmukan, joka pyörittää ohjelmaa.
        omat.kayttoliittymanSilmukka();
    }
}
