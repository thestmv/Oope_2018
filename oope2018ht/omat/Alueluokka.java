package oope2018ht.omat;
//Pakkaukset ja importit
import java.io.*;
import java.util.*;
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.*;
import static oope2018ht.omat.Kayttoliittyma.VIRHEILMOITUS;
import oope2018ht.tiedostot.*;
import oope2018ht.viestit.*;
/**
 * Konkreettinen Alueluokka, joka vastaa
 * keskustelualueen hallinnoinnista
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */
public class Alueluokka {
    //Vakioita
    static final String VIRHEILMOITUS = "Error!";
    static final String EROTIN = " ";
    static final String KUVA = "Kuva";
    static final String VIDEO = "Video";
    
    
    
    /*
     *
     * Attribuutit järjestyksessään.
     *
     */
    
    /** Viestiketjun tunniste */
    private int viestiKetjuTunnus;
    
    /** Lista kaikille viestiketjuille. */
    private OmaLista viestiKetjut;
    
    /** Aktiivinen viestiketju, joka aktivoi ne. */
    private Ketjuluokka aktiivinen; // 
    
    /** Viestin tunniste. */   
    private int viestiTunnus;
    
    /** Viite Viesti luokkaan */
    private Viesti viite;
    
    
    /** Alueluokan parametritön rakentaja
     *
     */
    public Alueluokka() throws Exception{
        viestiKetjuTunnus = 1;
        viestiTunnus = 1;
        viestiKetjut = new OmaLista();
    }
    
    @Getteri
    public int viestiKetjuTunnus(){
       return viestiKetjuTunnus;
    }
    
    /**
     *
     * @param vktunnus on viestiKetjuTunnus
     */
    @Setteri
    public void viestiKetjuTunnus(int vktunnus){
       viestiKetjuTunnus = vktunnus;
    }
    
    @Getteri
    public OmaLista viestiKetjut(){
       return viestiKetjut;
    }
    
    /**
     *
     * @param k on viestiKetjut
     */
    @Setteri
    public void viestiKetjut(OmaLista k){
       viestiKetjut = k;
    }
    
    @Getteri
    public Ketjuluokka aktiivinen(){
       return aktiivinen;
    }
    
    /**
     *
     * @param ketju on aktiivinen
     */
    @Setteri
    public void aktiivinen(Ketjuluokka ketju){
       aktiivinen = ketju;
    }
    
    /**
     *
     * @param vtunnus on viestiTunnus
     */
    @Setteri
    public void viestiTunnus(int vtunnus){
        viestiTunnus = vtunnus;
    }
    
    @Getteri
    public Viesti viite(){
        return viite;
    }
    
    /**
     *
     * @param v on viite
     */
    @Setteri
    public void viite(Viesti v){
            viite = v;
    }
    
    /**Tiedosto- tyyppinen tiedostonLukeminen operaatio
     *
     * @param tiedostonNimi on lähetettävän tiedoston nimi.
     * @return palauttaa video- tai kuvatiedoston.
     * @throws Exception jos jokin meni vikaan tai tiedosto oli virheellinen
     */
    public Tiedosto tiedostonLukeminen(String tiedostonNimi)throws Exception {
        //Virheensieppaus
        try{
            //Tiedostonlukemisen yritys, jonka jälkeen suljetaan lukija
            File tiedosto = new File(tiedostonNimi);
            Scanner lukija = new Scanner(tiedosto);
            String tiedostonMJono = lukija.nextLine();
            lukija.close();
            //Tehdään tiedostotaulukko merkkijonojen osista
            String[] tiedostotaulukko = tiedostonMJono.split(" ");
            Tiedosto tiedostonOsa;
            
            //Jos tiedosto on kuva, luodaan kuvaolio
            if(tiedostotaulukko[0].equals(KUVA)){
                //Luodaan alkiot jokaiselle osalle kuvatiedostosta
                int alkio1 = Integer.valueOf((String)tiedostotaulukko[1]);
                int alkio2 = Integer.valueOf((String)tiedostotaulukko[2]);
                int alkio3 = Integer.valueOf((String)tiedostotaulukko[3]);
                //Palautetaan luettu kuvatiedosto
                tiedostonOsa = new Kuva(tiedostonNimi, alkio1, alkio2, alkio3);
                return tiedostonOsa;
                
            }
            //Käydään läpi mahdollisen videotiedoston tapaukset
            //Luomalla video olio.
            else if(tiedostotaulukko[0].equals(VIDEO)){
                //Luodaan alkiot jokaiselle osalle videosta
                int alkio1 = Integer.valueOf((String)tiedostotaulukko[1]);
                String alkio2 = ((String)tiedostotaulukko[2]);
                double pituus = Double.parseDouble(alkio2);
                //Palautetaan luettu videotiedosto
                tiedostonOsa = new Video(tiedostonNimi, alkio1, pituus);
                return tiedostonOsa;
            }
            else{
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println(VIRHEILMOITUS);
            //Palautetaan null, jos tiedosto oli virheellinen
            return null;
        }
    }
    
    /** ADD Operaatio saa parametrinä aiheen ja luo sen perusteella
     *  uuden viestiketjun, joka asetetaan aktiiviseksi.
     *
     * @param aihe on viestiKetjun aihe.
     * @throws Exception jos jokin meni vikaan, tulostetaan VIRHEILMOITUS
     */
    public void lisaaViestiketju(String aihe)throws Exception{  
        try{
            //Luodaan viestiketju- olio, ja lisätään viestiKetjut
            //sen listalle.
            Ketjuluokka uusiKetju = new Ketjuluokka(viestiKetjuTunnus, aihe);
            viestiKetjut.lisaaLoppuun(uusiKetju);
            
            //Asetetaan uusi luotu ketju aktiiviseksi.
            aktiivinen = uusiKetju;
            viestiKetjuTunnus++;
        }
        catch(Exception e){
            System.out.println(VIRHEILMOITUS);
      }
    }
    
    /** CATALOG Operaatio tulostaa kaikki viestiketjut listana.
     *
     * @throws Exception jos viestiketjuja ei ole olemassa.
     * niin tulostetaan VIRHEILMOITUS.
     */
    public void listaaViestiketjut()throws Exception{
        if (viestiKetjut != null){
            //Silmukka käy läpi kaikki viestiketjut
            //ja listan alkiot tulostetaan
            for(int i = 0; i < viestiKetjut.koko(); i++){
                Ketjuluokka alkio = (Ketjuluokka)viestiKetjut.alkio(i);
                System.out.println(alkio);
            }
        }
        else {
            System.out.println(VIRHEILMOITUS);
        }
    }

    /** SELECT operaatio, joka valitsee luoduista viestiketjuista
     * jonkin numeron perusteella.
     *
     * @param ketjunT on ketjunT, joka asetetaan aktiiviseksi.
     * @throws Exception jos viestiketjua ei ole olemassa, niin VIRHEILMOITus
     */
    public void valitseKetju(int ketjunT)throws Exception{
        //Virheensieppaus
        try{
            //VIRHEILMOITUS, jos ketjun ehdot ovat väärät.
            if(ketjunT > viestiKetjut.koko() || ketjunT < 1 ){
                System.out.println(VIRHEILMOITUS);
            }
            //Laitetaan ketju aktiiviseksi ketjuntunnuken avulla.
            else{
                aktiivinen =(Ketjuluokka)viestiKetjut.alkio(ketjunT - 1);
            }
        }
        catch(Exception e){
            throw new Exception();
        }
    }

    /** NEW komento, luo uuden viestin valittuun viestiKetjuun. Saa parametrinä
     * viestin merkkijonon ja tiedoston nimen. Uusi viesti lisätään viesti, ja
     * topikki listaan.
     * @param viestimerkkijono on viestin String merkkijono
     * @param tiedostonNimi on mahdollinen viestin tiedosto
     * @throws Exception jos tiedostoa ei löydy
     */
    public void lisaaViesti(String viestimerkkijono, String tiedostonNimi)throws Exception{
        //Virheensieppaus
        try{
            //Alustetaan tiedosto-olio 
            Tiedosto tiedosto = null;
            if(tiedostonNimi != null){
                tiedosto = tiedostonLukeminen(tiedostonNimi);
                //Jos tiedosto tulee virheellisenä takas, niin viestiä ei lisätä
                //ja operaatio keskeytetään.
                if(tiedosto == null){
                    return;
                }
            }
            //Luodaan viestiolio, saatujen tietojen perusteella
            Viesti uusiViesti = new Viesti(viestiTunnus, viestimerkkijono, null, tiedosto);
                
            aktiivinen.viestienLista().lisaaLoppuun(uusiViesti);
            aktiivinen.topikki().lisaaLoppuun(uusiViesti);
            //Laskuri, joka lisää uusia viestejä
            viestiTunnus++;
        }
        catch (Exception e){
            throw new Exception();
        }
    }
    
    /**LIST operaatio, joka tulostaa viestiketjun aiheen ja viestit 
     * listamuodossa
     * @throws Exception jos jokin menee vikaan, niin VIRHEILMOITUS
     */
    public void tulostaKetjunLista()throws Exception{
        //Virheensieppaus
        try{
            OmaLista tulostettavaLista = aktiivinen.viestienLista();
            //Tulostettaan alkulistalla aihe
            System.out.println("=");
            System.out.println("==" + EROTIN + aktiivinen.toString());
            System.out.println("===");
            
            //Silmukka, joka käy listan läpi ja tulostaa sen alkiot viesteinä.
            for(int i = 0; i < tulostettavaLista.koko(); i++){
                Viesti tulostettanaViesti = (Viesti)tulostettavaLista.alkio(i);
                System.out.println(tulostettanaViesti.toString());
            }
        }
        catch (Exception e){
            System.out.println(VIRHEILMOITUS);
        }
        
    }

    /**LIST operaatio, saa parametrinä OmaLista tyyppisen listan
     * ja tulostaa sen
     * @param lista OmaLista tyyppinen merkkijonot sisältävä viestien lista
     */
    public void tulostaLista(OmaLista lista){
        //Silmuka, joka tulostaa listan alkiot toString
        //metodissa
        for(int i = 0; i < lista.koko(); i++){
            Viesti tulostettanaViesti = (Viesti)lista.alkio(i);
            System.out.println(tulostettanaViesti.toString());
        }
    }
    
    /**REPLY operaatio saa paremetrinä viestin tunnisteen, viestin merkkijonon
     * ja tiedoston nimen. Viesti luodaan näiden tietojen perusteella ja 
     * luotu viesti lisätään viestien ja vastausten sisältämään listaa.
     *
     * @param viestinTunnus on viestin tunnistenumero
     * @param viestimerkkijono on viestin merkkijono
     * @param tiedostonNimi on mahdollisen tiedoston nimi
     * @throws Exception jos jokin meni vikaan, niin VIRHEILMOITUS
     */
    public void vastaaViestiin(int viestinTunnus, String viestimerkkijono, String tiedostonNimi)throws Exception{
        //Virheensieppaus
        try{
            //Kutsutaan viestioperaatiota
            Viesti vastausViesti = aktiivinen.haeViesti(viestinTunnus);
            //Jos vastausViesti on null, niin poikkeus.
            if(vastausViesti == null){
                throw new Exception();
            }
            //Jos tiedosto ei ole null, niin luetaan tiedosto erillisen operaation avulla.
            Tiedosto tiedosto = null;
            if(tiedostonNimi != null){
                tiedosto = tiedostonLukeminen(tiedostonNimi);
                //Jos tiedosto tulee virheellisenä takas, niin vastausta ei lisätä.
                if(tiedosto == null){
                    return;
                }
            }
            //Palauttaa vastatun viestin.
            Viesti vastattuViesti = new Viesti(viestiTunnus, viestimerkkijono, vastausViesti, tiedosto);
            aktiivinen.viestienLista().lisaaLoppuun(vastattuViesti);
            //Laskuri
            viestiTunnus++;
        }
        catch (Exception e){
            System.out.println(VIRHEILMOITUS);
        } 
    }
    

    /**HEAD Operaatio tulostaa vanhimmat viestit listana määrätyn lukumäärän
     * perusteella.
     *
     * @param tulosViestLKM on tulostettavien viestien lukumäärä
     * @throws Exception jos jokin meni vikaan, niin VIRHEILMOITUS
     */
    public void tulostaVanhimmatViestitListana(int tulosViestLKM)throws Exception{
        //Virheensieppaus
        try{
            //Luodaan vanhoista viesteistä tulostettava OmaLista tyyppinen lista
            OmaLista vanhatViestit = aktiivinen.viestienLista().annaAlku(tulosViestLKM);
            //Silmukka, joka tulostaa vanhimmat viestit listana
            for(int i = 0; i < vanhatViestit.koko(); i++ ){
                System.out.println(vanhatViestit.alkio(i));
            }
        }
        catch(Exception e){
            System.out.println(VIRHEILMOITUS);
        }
    }

    /** TAIL Operaatio listaa uusia viestejä annetun määrän.
     *
     * @param lukumaara tulostukseen haluttaviesn viestien lukumäärä
     * @throws Exception jos jokin meni vikaan.
     */
    public void uusienViestienListaaminen(int lukumaara)throws Exception{
        //Virheensieppaus
        try{
            //Luodaan uusienViestienLista tulostettavista viesteistä
            OmaLista uusienViestienLista = aktiivinen.viestienLista().annaLoppu(lukumaara);
            //Tulostetaan kyseinen lista silmukassa
            for(int i = 0; i < uusienViestienLista.koko(); i++){
                System.out.println(uusienViestienLista.alkio(i));
            }
        } 
        //Poikkeus
        catch (Exception e){
            System.out.println(VIRHEILMOITUS);
        }
    }

    /**EMPTY Operaatio, joka saa tyhjennettävän viestin numeron parametrinä.
     * Muuttaa valitun viestin tilalle POISTETTUTEKSTI tekstin.
     * @param tyhjennettavaNro on tyhjennettävän viestin tunniste.
     * @throws Exception jos jokin meni vikaan, viestin tunnin virheellinen
     */
    public void viestinTyhjentaminen(int tyhjennettavaNro)throws Exception{
        //Virheensieppaus
        try{
            //Valitaan viestin nro, joka tyhjennetaan aktiivisesta ketjusta
            Viesti tyhjennettava = aktiivinen.samanViestinEtsiminen(tyhjennettavaNro);
            if(tyhjennettava == null){
                throw new Exception();
            }
            //Kutsutaan tyhjenna-operaatio viestiLuokasta ja toimitaan.
            //Sen mukaisesti viivaamalla viesti.
            tyhjennettava.tyhjenna();
        }
        catch(Exception e){
            throw new Exception();
        }
    }

    /**FIND operaatio, joka etsii annetun merkkijonon perusteella viestejä
     * ja palauttaa ne viestit, jotka sisältävät kyseisen merkkijonon.
     *
     * @param merkkiJono on etsittävä merkkijono
     * @return sen merkkijonon sisältävät viestit listana
     * @throws Exception jos jokin meni vikaan.
     */
    public OmaLista haeMerkkijono(String merkkiJono)throws Exception{
        //Virheensieppaus
        try{
            //OmaListattyyppinen aktiivisen ketjujen haettavat viestit
            OmaLista haettavatViestit = aktiivinen.viestienLista();
            
            //Lista viesteistä, joista löytyy tietty merkkijono
            OmaLista loydetytViestit = new OmaLista();
            //Silmukka, joka hakee näitä viestejä.
            for(int i = 0; i < haettavatViestit.koko(); i++){
                Viesti apuViite = (Viesti)haettavatViestit.alkio(i);
                String merkkiJonot = apuViite.toString();
                //Jos merkkijono löytyy, niin se laitetaan apuviitteeseen.
                if(merkkiJonot.contains(merkkiJono)){
                    loydetytViestit.lisaaLoppuun(apuViite);
                }
            }
            //Palauttaa merkkijonon sisältämät viestit listana.
            return loydetytViestit;
        }
        catch(Exception e){
            throw new Exception();
        }
    }

}