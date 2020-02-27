//Pakkaukset
package oope2018ht.viestit;
//Otetaan käyttöön apuluokkia.
import oope2018ht.tiedostot.*;
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.*;
import fi.uta.csjola.oope.lista.*;



/**
 * Viestiluokka hallinnoimaan yksittäisiä viestejä.
 * Säilötään viitteet vastaaviin viesteihin.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */ 
public class Viesti implements Komennettava<Viesti>, Comparable<Viesti> {

    /*
     * Attribuutit.
     *
     */
        
    /**viestinTunniste == null, jos viesti on itsenäinen keskustelunavaus */
    private int viestinTunniste;
    
    /**Viestin merkkijono teksti, Viesti voi vastata aiempaan viestiin */
    private String viestinTeksti;
    
    /**vastattuViesti */
    private Viesti vastattuViesti;
    
    /** viiteTiedosto */
    private Tiedosto viiteTiedosto;
    
    /** OmaLista-tyyppinen attribuutti vastaaville viesteille. */
    private OmaLista vastaavaViesti;
    
    
    
    /*
     *
     * Rakentajat
     *
     */
     
     /**
     * Neliparametrinen rakentaja Viestiluokassa
     * @param tu
     * @param te
     * @param vv
     * @param vt
     * @throws IllegalArgumentException
     */
    public Viesti(int tu, String te, Viesti vv, Tiedosto vt)throws IllegalArgumentException {
        viestinTunniste(tu);
        viestinTeksti(te);
        vastattuViesti(vv);
        viiteTiedosto(vt);
        //Kutsutaan oliota
        if (vastattuViesti != null){
            vv.lisaaVastaus(this);
        }
        vastaavaViesti = new OmaLista();
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
    public int viestinTunniste(){
        return viestinTunniste;
    }
    
    @Getteri
    public String viestinTeksti(){
        return viestinTeksti;
    }
    
    @Getteri
    public Viesti vastattuViesti(){
        return vastattuViesti;
    }
    
    @Getteri
    public Tiedosto viiteTiedosto(){
        return viiteTiedosto;
    }
    
    @Getteri
    public OmaLista vastaavaViesti(){
        return vastaavaViesti;
    }
    
    /*
     * Setterit
     */ 

    /**
     * koko on suuremi kuin 0 tai muuten poikkaus
     * @param tu on viestinTunniste
     * @throws IllegalArgumentException
     */
    @Setteri
    public void viestinTunniste(int tu)throws IllegalArgumentException{
         if (tu > 0) {
             viestinTunniste = tu;
         }
         else {
            throw new IllegalArgumentException();
        }
    }
     
    /**
     * Tekstiä vähintään 1 merkki, eikä saa olla null, muuten poikkeus.
     * @param te on viestinTeksti
     * @throws IllegalArgumentException Poikkeus.
     */
    @Setteri
    public void viestinTeksti(String te)throws IllegalArgumentException{
        if ((te != null) && (te.length() >= 1)){
            viestinTeksti = te;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Palauttaa vastatun viestin.
     * @param vv on vastattuViesti
     */
    @Setteri
    public void vastattuViesti(Viesti vv){
        vastattuViesti = vv;
    }
    
    /** Kiinnittää viestiin liitetyn tiedosto-olion.
     *
     * @param vt on viiteTiedosto
     * @throws IllegalArgumentException
     */
    @Setteri
    public void viiteTiedosto(Tiedosto vt)throws IllegalArgumentException{
        if(vt != null){
            viiteTiedosto = vt;
        } 
        else {
            //On null jos viestillä ei ole tiedostoa.
            viiteTiedosto = null;
            
        }
    }
    /** Säilöö viitteet, jotka liittävät viestin vastaaviin viesteihin
     * Lista on tyhjä, kun vastauksia ei ole, muuten poikkeus
     * @param vav on vastaavaViesti
     */
    @Setteri
    public void vastaavaViesti(OmaLista vav){
        if (vav != null){
            vastaavaViesti = vav;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**Hakee viestiä listalta, joka säilöö viitteet viestiin vastaaviin viesteihin.
    * Hyödyntää OmaLista-luokan hae-operaatiota
    * @param haettava viite haettavaan viestiin
    * @return viite löydettyyn tietoon. Palauttaa null-arvon, jos haettavaa
    * viestiä ei löydetä.
    * @throws IllegalArgumentException jos parametri on null-arvoinen.
    */
    @Override
    public Viesti hae(Viesti haettava) throws IllegalArgumentException {
        //
        if(haettava == null){
            //Palauttaa poikkeuksen, jos parametri on null-arvoinen.
            throw new IllegalArgumentException();
        }
        //Hakee viestiä
        else{
            //palauttaa viestin vastaavaan viestiin.
            Viesti haunTulos = (Viesti)vastaavaViesti.hae(haettava);
            return haunTulos;
        }
    }
    
    /** Lisää uuden viitteen listalle, joka säilöö viitteet viestiin vastaaviin
     * viesteihin. Uusi viite lisätään listan loppuun. Viitettä ei lisätä,
     * jos listalla on jo viite lisättävään vastaukseen. Hyödyntää hae-metodia.
     *
     * @param lisattava viite lisättävään viestiin.
     * @throws IllegalArgumentException jos lisäys epäonnistui, koska parametri
     * on null-arvoinen tai koska vastaus on jo lisätty listalle.
     */
    @Override
    public void lisaaVastaus(Viesti lisattava) throws IllegalArgumentException {
        if( lisattava == null || hae(lisattava) != null ){
            //Palauttaa poikkeuksen, jos parametri on null-arvoinen.
            throw new IllegalArgumentException();
        }
        else {
           vastaavaViesti.lisaa(lisattava);
        }
    }

    /** Asettaa viestin tekstiksi vakion POISTETTUTEKSTI ja poistaa viestiin
     * mahdollisesti liitetyn tiedoston asettamalla attribuutin arvoksi null-arvon.
     */
    @Override
    public void tyhjenna() {       
        viestinTeksti = POISTETTUTEKSTI;
        viiteTiedosto = null;
    }
    
    //ComprateTo metodi, standardin mukaiset paluuarvot.
    @Override
    public int compareTo(Viesti o) {
        // Tällä oliolla pienempi tunniste kuin toisella.
        if( viestinTunniste < o.viestinTunniste()){
            return -1;
        }
        // Olioilla sama tunniste
        else if( viestinTunniste == o.viestinTunniste()){
            return 0;
        }
        // Tällä oliolla suurempi tunniste kuin toisella.
        else {
            return 1;
        }
    }
    
   /*
    * Object-luokan metodien korvaukset.
    *
    */
    /**
     * toString metodi oliolle.
     * @return Palauttaa merkkijonoesityksen.
     */
    @Override
    public String toString(){
        //Vakiot
        final char EROTIN = ' ';
        final String RISTIMERKKI = "#";
  
        
        //Jos viiteTiedosto on null, niin sitä ei tulosteta.
        if (viiteTiedosto() == null){
            return RISTIMERKKI + viestinTunniste() + EROTIN + viestinTeksti();
        }
        //Muuten tulostetaan normaalisti.
        else {
            return RISTIMERKKI + viestinTunniste() + EROTIN + viestinTeksti() + EROTIN + '(' + viiteTiedosto() + ')';
        }
    }
    
    /**Vertailee olioita tunnisteiden perusteella.
     * @param o Object tyyppinen olio
     */
    @Override
    public boolean equals(Object o){
        try{
            // Asetetaan olioon apuviite, jonka kautta voidaan kutsua Vietin metodia.
            Viesti toinenViesti = (Viesti)o;
            // Viestin tunnisteet ovat samat, jos niiden arvot ovat samat.
            return viestinTunniste == toinenViesti.viestinTunniste();
        }
        catch (Exception e) {
            return false;
        }
    }
}
