//Pakkaukset
package oope2018ht.tiedostot;
//Otetaan käyttöön apuluokkia.
import oope2018ht.apulaiset.*;
import fi.uta.csjola.oope.lista.*;

/**
 * Tiedostoluokasta periytyvä kuvaluokka, joka sisältää
 * kuvan määritelmät.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */ 
public class Kuva extends Tiedosto {
    
    //Vakiot
    final char EROTIN = ' ';
    
    
    /** Kuvan mittojen on oltava vähintään 1×1-pikseliä. */
    private int leveys;
    
    /** Korkeus on oltava suurempi tai yhtäsuuri kuin 1. */
    private int korkeus;

    /**Neliparametrinen rakentaja
     *
     * @param l on periytyvä 
     * @param k on perityvä
     * @param le on leveys
     * @param ko on korkeus
     */
    public Kuva(String l, int k, int le, int ko){
        //Kutsutaan yläluokan rakentajaa.
        super(l, k);
        leveys(le);
        korkeus(ko);
    }
 
   /*
    * Aksessorit.
    *
    */
    
    /*
     * Getterit
     */
    
    @Getteri
    public int leveys(){
        return leveys;
    }
    
    @Getteri
    public int korkeus(){
        return korkeus;
    }
    
    /*
     * Setterit
     */
    
    /** Leveys on oltava suurempi tai yhtäsuuri kuin 1 tai muuten poikkeus.
     *
     * @param le on leveys
     */
    @Setteri
    public void leveys(int le) {
        if( le >= 1){
            leveys = le;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }

    /**Korkeus on oltava suurempi tai yhtäsuuri kuin 1 tai muuten poikkeus.
     *
     * @param ko on korkeus
     */
    @Setteri
    public void korkeus(int ko) {
        if( ko >= 1){
            korkeus = ko;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }
    /**Korvataan toString metodi
     * Tulostetaan yliluokka + ' ' + leveys + 'x' + korkeus
     */
    public String toString(){
        return super.toString() + EROTIN + leveys() + 'x' + korkeus();
    }
}
