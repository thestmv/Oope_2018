//Pakkaukset
package oope2018ht.omalista;
//Otetaan käyttöön apuluokkia.
import oope2018ht.apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.*;

/**
 *OmaLista- luokka, joka periytyy linkitetystä listasta.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {
    
    /** Tutkii onko listalla haettavaa oliota equals-mielessä vastaava alkio.     
     * Haku aloitetaan listan alusta ja se etenee kohti listan loppua.
     * Haku pysähtyy ensimmäiseen osumaan, jos listalla on useita haettavaa oliota
     * vastaavia alkioita. Operaatio ei muuta listan sisältöä millään tavalla.
     *
     * Jos parametri liittyy esimerkiksi merkkijonoon "ab" ja listan tietoalkiot
     * ovat [ "ab", "AB, "Ab", "aB", "ab" ], palauttaa operaatio viitteen listan
     * ensimmäiseen tietoalkioon, koska "ab".equals("ab") == true ja kysessä on
     * haun ensimm�inen osuma.
     *
     * @param haettava olio, jota vastaavaa alkiota listalta haetaan. Olion luokan
     * tai luokan esivanhemman oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite löydettyyn alkioon. Paluuarvo on null, jos parametri on
     * null-arvoinen, vastaavaa alkiota ei löydy tai lista on tyhjä.
     */
    @Override
    public Object hae(Object haettava) {
        //null-tarkistus jos ei voida hakea.
        if (haettava == null  || onkoTyhja()){
            return null;
        }
        
        int i = 0;
        //Luodaan lippumuuttuja
        boolean lippu = true;
        
        Object haunTulos = null;
        //Silmukka katkeaa, jos päästään listan loppuun
        //Tai lippumuuttuja on false
        while ( i < koko && lippu ) {
            if (alkio(i).equals(haettava)){
                haunTulos = alkio(i);
                lippu = false;              
            }
            else {
                i++;
            }
        }
        //Palautetaan haunTulos
        return haunTulos;
    }
    
   /** Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
     * tehdään vain tällä operaatiolla, koska uusi alkion lisätään listalle siten,
     * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
     * jälkeen ja ennen kaikkia itseään suurempia alkioita. Alkioiden suuruus-
     * järjestys selvitetään Comparable-rajapinnan compareTo-metodilla.
     *
     * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot
     * ovat [ 0, 3 ], on listan sisältö lisäyksen jälkeen [ 0, 2, 3 ],
     * koska 0 < 2 < 3.
     *
     * Sijoita toteuttamasi metodin yleisten kommenttien ja metodin otsikon väliin
     * annotaatio "@SuppressWarnings({"unchecked"})", jolla kääntäjälle vakuutetaan,
     * että metodin koodi on turvallista. Ilman määrettä kääntäjä varoittaa,
     * että Comparable-rajapintaa käytetään ei-geneerisellä tavalla. Esimerkki
     * annotaation käytöstä on annettu harjoitustehtävän 7.3. mallivastauksessa.
     *
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return true, jos lisäys onnistui ja false, jos uutta alkiota ei voitu
     * vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen tai sillä
     * ei ole vastoin oletuksia Comparable-rajapinnan toteutusta.
     */
    @SuppressWarnings({"unchecked"})
    public boolean lisaa(Object uusi) {
        //Virheensieppaus
        try{
            //Null tarkistus
            if(uusi == null){
                return false;
            } 
            else {
                //Silmukka pyörittämään lisäystä.
                for(int i = 0; i < koko(); i++){
                   Comparable vertailtava = (Comparable)alkio(i);
                   
                    if ( vertailtava.compareTo(uusi) > 0){
                        lisaa(i, uusi);
                        //Palautetaan true, koska lisäys onnistui
                        return true;
                    }
                }
                //Palautetaan true, koska lisäys onnistui
                lisaaLoppuun(uusi);
                return true;
            }
        } 
        catch (Exception e){
            //Palautetaan false koska alkiota ei voitu luoda.
            return false;
        }
    }
    
   /** Luo ja palauttaa uuden listan, jolla on viitteet n ensimmäiseen listan
     * tietoalkioon. Palautettavan listan ensimmäinen viite liittyy listan
     * ensimmäiseen alkioon, palautettavan listan toinen viite liittyy listan
     * toiseen alkioon ja niin edelleen. Palautettavan listan viimeinen viite
     * liittyy listan n. alkioon. Operaatio ei muuta listan sisältää millään
     * tavalla.
     *
     * Jos parametrin arvo on esimerkiksi kaksi ja listan tietoalkiot ovat
     * [ "AB", "Ab", "aB", "ab" ], on palautetulta listalta viitteet listan
     * ensimmäiseen ja toiseen alkioon, jolloin palautetun listan tietoalkiot
     * ovat [ "AB, "Ab" ].
     *
     * @param n palautettavalle listalle lisättävien viitteiden lukumäärä.
     * @return uusi listaan, joka sisältää viitteet listan n ensimmäiseen
     * tietoalkioon. 
     * Paluuarvo on null, jos lista on tyhjä tai jos parametrin
     * arvo on pienempi kuin yksi tai suurempi kuin listan alkioiden lukumäärä.
     */
    @Override
    public OmaLista annaAlku(int n) {
        //Paluuarvo on null, jos lista on tyhjä tai jos parametrin
        //arvo on pienempi kuin yksi tai suurempi kuin listan alkioiden lukumäärä.
        if  (( n < 1 )||( n > koko() ) || ( koko() <= 0 )) {
            return null;
        } 
        else{
            //Luodaan uusi lista
            OmaLista uusi = new OmaLista();
            //Silmukka katsoo listan viitteitä ja lisää alkioita loppuun
            for(int i = 0; i < n; i++){
               uusi.lisaaLoppuun(alkio(i));
            }
            //Palautetaan uusi lista
            return uusi;
        }
    }
    
   /** Luo ja palauttaa uuden listan, jolla on viitteet n viimeiseen listan
     * tietoalkioon. Palautettavan listan ensimmäinen viite liittyy listan m.
     * (koko - n + 1) alkioon, palautettavan listan toinen viite liittyy listan
     * m + 1. (koko - n + 2) alkioon ja niin edelleen. Palautettavan listan
     * viimeinen viite liittyy listan viimeiseen alkioon. Operaatio ei muuta
     * listan sisältää millään tavalla.
     *
     * Jos parametrin arvo on esimerkiksi kaksi ja listan tietoalkiot ovat
     * [ "AB", "Ab", "aB", "ab" ], on palautetulta listalta viitteet listan
     * kolmanteen ja neljänteen alkioon, jolloin palautetun listan tietoalkiot
     * ovat [ "aB, "ab" ].
     *
     * @param n palautettavalle listalle lisättävien viitteiden lukumäärä.
     * @return viite listaan, joka sisältää viitteet listan n viimeiseen
     * tietoalkioon. Paluuarvo on null, jos lista on tyhjä tai jos parametrin
     * arvo on pienempi kuin yksi tai suurempi kuin listan alkioiden lukumäärä.
     */
    @Override
    public OmaLista annaLoppu(int n) {
        //Paluuarvo on null, jos lista on tyhjä tai jos parametrin
        //arvo on pienempi kuin yksi tai suurempi kuin listan alkioiden lukumäärä.
        if  (( n < 1 )||( n > koko() ) || ( koko() <= 0 )) {
            return null;
        } 
        else{
            //Luodaan uusi lista
            OmaLista uusi = new OmaLista();
            //Silmukka katsoo listan viitteitä ja lisää alkioita alkuun.
            for(int i = koko() - 1; i > koko() - n - 1 ; i--){
               uusi.lisaaAlkuun(alkio(i));
            }
        //Palautetaan uusi lista
        return uusi;
        }
    }
}
