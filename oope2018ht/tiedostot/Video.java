//Pakkaukset
package oope2018ht.tiedostot;
//Otetaan käyttöön apuluokkia.
import oope2018ht.apulaiset.*;
import fi.uta.csjola.oope.lista.*;
/**
 * Tiedostoluokasta periytyvä videoluokka, joka sisältää
 * videon määritelmät.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */
public class Video extends Tiedosto {
    
    //Vakiot
    final char EROTIN = ' ';
    
    /** Videon pituus > 0. */
    private double pituus;
    
   /*
    * Rakentajat.
    *
    */

    /**
     * Kolmiparemetrinen rakentaja
     * @param n on periytyvä
     * @param k on periytyvä
     * @param p on pituus
     */


    public Video(String n, int k, double p){
        super(n, k);
        pituus(p);
    }
    
   /*
    * Aksessorit.
    *
    */
    
    /*
     * Getterit
     */
    
    @Getteri
    public double pituus(){
        return pituus;
    }
    
    /*
     * Setterit
     */

    /** 
     * Videon pituus > 0, muuten poikkeus
     * @param p on pituus
     */
    @Setteri
    public void pituus(double p) {
        if(p > 0){
            pituus = p;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**Korvataan toString metodi
     * Tulostetaan yliluokka merkkijonona: + ' ' + pituus + ' ' + 's'
     */
    @Override
    public String toString(){
        return super.toString() + EROTIN + pituus() + EROTIN + 's';
    }
}