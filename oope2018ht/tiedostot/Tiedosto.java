//Pakkaukset
package oope2018ht.tiedostot;
//Otetaan käyttöön apuluokkia.
import oope2018ht.apulaiset.*;
import fi.uta.csjola.oope.lista.*;

/**
 * Abstrakti Tiedosto-luokka, joka sisältää tiedoston määritelmät.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */
public abstract class Tiedosto {
    
    //Vakiot
    final char EROTIN = ' ';
    
    /*
     * Attribuutit.
     *
     */
    
    /** Nimessä tulee olla vähintään yksi merkki */
    private String tiedostonNimi;
    
    /** Koon tulee olla vähintään yksi tavua. */
    private int tiedostonKoko;
    
    /* 
     * Rakentajat.
     *
     */
    
    /** Kaksiparametrinen rakentaja
     *
     * @param n
     * @param k
     */
    public Tiedosto(String n, int k){
        if(n == null || n.length() < 1){
            throw new IllegalArgumentException();
        }
        tiedostonNimi = n;
        if (k < 1){
            throw new IllegalArgumentException();
        }
        tiedostonKoko = k;
    }
    
    /*
     *
     * Aksessorit.
     *
     */
    
    /*
     * Getterit
     */
    
    @Getteri
    public String tiedostonNimi(){
        return tiedostonNimi;
    }
    
    @Getteri
    public int tiedostonKoko(){
        return tiedostonKoko;
    }
    
    /*
     * Setterit
     */

    /** Nimessä tulee olla vähintään yksi merkki, muuten poikeus
     *
     * @param n on tiedostonNimi
     */
    @Setteri
    public void tiedostonNimi(String n){
        if ((n != null) && (n.length() >= 1)){
            tiedostonNimi = n;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /** Koon tulee olla vähintään yksi tavua, muuten poikeus
     *
     * @param k on tiedostonKoko
     */
    @Setteri
    public void tiedostonKoko(int k){
        if (k > 1){
            tiedostonKoko = k;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**Korvataan toString metodi
     * Merkkijonoesitys: tiedostonNimi + _ + tiedostonKoko + _ + B
     */
    public String toString(){
        return tiedostonNimi() + EROTIN + tiedostonKoko() + EROTIN + 'B';
    } 
}