package oope2018ht.omat;
// Otetaan käyttöön tiedostonkäsittelyyn liittyvät palvelut ja muita luokkia.
import java.io.*;
import java.util.*;
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.*;
import oope2018ht.tiedostot.*;
import oope2018ht.viestit.*;
/**
 * Käyttöliittymäluokka lukemaan käyttäjän syötteitä ja komentoja
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */ 
public class Kayttoliittyma {
    //Vakiot
    static final String ALOITUSSULJE = ">";
    static final String ALUSTIN = "";
    static final String EROTIN = " ";
    static final String VIRHEILMOITUS = "Error!"; 
    //Komennot vakioina
    static final String ADD = "add"; //2.3
    static final String CATALOG = "catalog"; //2.4
    static final String SELECT = "select"; //2.5
    static final String NEW = "new"; //2.6
    static final String REPLY = "reply"; //2.7
    static final String TREE = "tree"; //2.8
    static final String LIST = "list"; //2.9
    static final String HEAD = "head"; //2.10
    static final String TAIL = "tail"; //2.11
    static final String EMPTY = "empty"; //2.12
    static final String FIND = "find"; //2.13
    static final String EXIT = "exit"; //2.14
    
    static final String HYVASTI = "Bye! See you soon.";
    
    /** Alueluokan tyyppinen kätketty attribuutti */
    private Alueluokka alue;
   
    /** Viestiluokan tyyppinen kätketty attribuutti */
    private Viesti viesti;

    /** Rakentaja käyttöliittymälle
     *
     * @throws Exception
     */
    public Kayttoliittyma() throws Exception{
        alue = new Alueluokka();
        kayttoliittymanSilmukka();
    }
    //Lippumuuttuja käyttöliittymän silmukalle
    boolean lippu = true;

    /**
     * Varsinainen käyttöliittymä, jota pyörittää silmukka
     * @throws Exception
     */
    public void kayttoliittymanSilmukka() throws Exception{
        //Silmukka, joka 
        while(lippu == true) {
            //Tulostetaan joka kerta ohjelman eteen ilman rivinvaihtoa.
            System.out.print(ALOITUSSULJE);
            //Luetaan käyttäjän antama syöte
            String syote = In.readString();
            //Null-tarkistus
            
            if(syote != null){
                
                //Taulukkotyyppinen komennonluku, sekä merkkijonon katkaisu
                String[] sana = syote.split(EROTIN);
                int pituus = sana.length;
                
                //Metodit sisällön etsimiseen
                String viestinOsat = haeSisalto(sana);
                //Metodi tiedoston etsimiseen
                String tiedostonNimi = etsiTiedostonNimi(sana);
                
                //Luodaan alueelle viestiketju
                if(syote.startsWith(ADD + EROTIN)){
                    //Avaa metodin alue-luokasta
                    alue.lisaaViestiketju(viestinOsat);
                }
                //Listataan valitun alueen viestiketjut
                else if(syote.equals(CATALOG)){
                    alue.listaaViestiketjut();
                }
                //Valitaan alueelta viestiketju
                else if(syote.startsWith(SELECT + EROTIN)){
                    if(sana.length == 2 && sana.length >= 2){
                    //Virheensieppaus
                        try{
                            int viestiKetjuTunnus = Integer.parseInt(sana[1]);
                            //Ottaa numeron toisesta splittauksesta
                            if(sana.length != 2){
                                System.out.println(VIRHEILMOITUS);
                            }
                            alue.valitseKetju(viestiKetjuTunnus);
                        } 
                        catch(Exception e){
                            System.out.println(VIRHEILMOITUS);
                        }
                    }
                    else{
                        System.out.println(VIRHEILMOITUS);
                    }
                }
                //Luodaan uusi viesti viestiketjuun
                else if (syote.startsWith(NEW + EROTIN)){
                    //Lisätään uuteen viestiin sisältö
                    //ja mahdollinen tiedosto
                    alue.lisaaViesti(viestinOsat, tiedostonNimi);
                }
                //Listataan viestiketju listana
                else if (syote.startsWith(LIST)) {
                    alue.tulostaKetjunLista();
                }
                //Listataan viestiketju puuna
                else if(syote.equals(TREE)){
                    alue.aktiivinen().tulostaPuuna();
                }
                //Vastataan aiemmin lähetettyy viestiin
                else if(syote.startsWith(REPLY + EROTIN)){
                    String sisallonOsat2 = haeSisalto2(sana);
                    int vastattavaViestiNro = Integer.parseInt(sana[1]);
                    alue.vastaaViestiin(vastattavaViestiNro, sisallonOsat2, tiedostonNimi);
                    
                }
                //Listataan vanhimpia viestejä haluttu määrä
                else if(syote.startsWith(HEAD + EROTIN)){
                    if(sana.length == 2){
                        int viestinNro = Integer.parseInt(sana[1]);
                        
                        alue.tulostaVanhimmatViestitListana(viestinNro);
                    }
                    else {
                        System.out.println(VIRHEILMOITUS);
                    }
                }
                //Tulostaa annetun määrän uusia viestejä
                //valitusta ketjusta, uusin ensin.
                else if(syote.startsWith(TAIL + EROTIN)){
                    if(sana.length == 2){
                        int numero = Integer.parseInt(sana[1]);
                        alue.uusienViestienListaaminen(numero);
                    }
                    else {
                        System.out.println(VIRHEILMOITUS);
                    }
                }
                //Yliviivaa halutun aktiivisen viestin.
                else if(syote.startsWith(EMPTY + EROTIN)){
                    if(sana.length == 2){
                        int tyhjennettavaViestinNro = Integer.parseInt(sana[1]);
                        alue.viestinTyhjentaminen(tyhjennettavaViestinNro);
                    }
                    else {
                        System.out.println(VIRHEILMOITUS);
                    }
                }
                //Hakee merkkijonona aktiivisen ketjun viestejä ja tulostaa niitä
                else if(syote.startsWith(FIND + EROTIN)){
                    String haettavaMerkkijono = sana[1];
                    OmaLista tulostettavaLista = alue.haeMerkkijono(haettavaMerkkijono);
                    alue.tulostaLista(tulostettavaLista);
                }
                //Lopettaa ohjelman ja hyvästelee käyttäjän.
                else if (syote.startsWith(EXIT)){
                    System.out.println(HYVASTI);
                    lippu = false;
                }
                else {
                    System.out.println(VIRHEILMOITUS);
                }
            
            }
        }
    }

    /**Operaatio hakee merkkijonosta tiedostoa
     * 
     * @param sana, joka sisältää käyttäjän komennon
     * @return tiedoston merkkijono esitys
     */
    public String etsiTiedostonNimi(String[] sana){
        //Alustetaan tiedoston nimi
        String tiedostonNimi = null;
        
        String vikaSana = sana[sana.length-1];
        char ekaMerkki = vikaSana.charAt(0);
        //Kyseessä on tiedosto, jos '&' merkki löytyy
        if(ekaMerkki == '&'){
            tiedostonNimi = "";
            for(int i = 1; i < vikaSana.length(); i++){
                tiedostonNimi += vikaSana.charAt(i);  
            }
        }
        //Palautetaan tiedoston nimi
        return tiedostonNimi;
    }

    /**Operaatio hakee viestin sisällön.
     *
     * @param sisallonOsat syötteen sisältävä taulukko.
     * @return palauttaa viestin sisällön
     */
    public String haeSisalto(String[] sisallonOsat){
        //Alustetaan viestien osat
        String viestinOsat = "";
        String uusienViestienOsat = "";
        
        String viimeinenOsa = sisallonOsat[sisallonOsat.length - 1];
        char ensimmainenKirjain = viimeinenOsa.charAt(0);
        
        if(ensimmainenKirjain == '&'){
        //Silmukka tutkimaan viestin sisältöä, aloittaa indeksistä 1
            for(int i = 1; i < sisallonOsat.length - 1; i++){
                viestinOsat = viestinOsat += " " + sisallonOsat[i];
                uusienViestienOsat = viestinOsat.substring(1);
            }
        }
        else {
            for(int j = 1; j < sisallonOsat.length; j++){
                viestinOsat = viestinOsat += " " + sisallonOsat[j];
                uusienViestienOsat = viestinOsat.substring(1);
            }
        }
        //Palauttaa viestin sisällön
        return uusienViestienOsat;
        
    }
    /**Operaatio hakee viestin sisällön.
     *
     * @param sisallonOsat2 syötteen sisältävä taulukko.
     * @return palauttaa viestin sisällön
     */
    public String haeSisalto2(String[] sisallonOsat2){
        //Alustetaan viestien osat
        String viestinOsat = "";
        String uusienViestienOsat = "";
        
        String viimeinenOsa = sisallonOsat2[sisallonOsat2.length - 1];
        char ensimmainenKirjain = viimeinenOsa.charAt(0);
        //Silmukka tutkimaan viestin sisältöä
        if(ensimmainenKirjain == '&'){
        //Silmukka tutkimaan viestin sisältöä, aloittaa indeksistä 2
            for(int i = 2; i < sisallonOsat2.length - 1; i++){
                viestinOsat = viestinOsat += " " + sisallonOsat2[i];
                uusienViestienOsat = viestinOsat.substring(1);
            }
        }
        else {
            for(int j = 2; j < sisallonOsat2.length; j++){
                viestinOsat = viestinOsat += " " + sisallonOsat2[j];
                uusienViestienOsat = viestinOsat.substring(1);
            }
        }
        //Palauttaa viestin sisällön
        return uusienViestienOsat;
    }
}
