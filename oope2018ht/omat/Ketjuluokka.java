package oope2018ht.omat;
//Pakkaus ja importteja.
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.*;
import oope2018ht.tiedostot.*;
import oope2018ht.viestit.Viesti;
/**
 * Ketjuluokka, joka hallinnoi viestikejuja.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2018.
 * <p>
 * @author Teemu Vuorinen (vuorinen.teemu.m@student.uta.fi),
 * Luonnontieteiden tiedekunta, Tampereen yliopisto.
 */
public class Ketjuluokka {
        
    //Vakioita
    final String RISUMERKKI = "#";
    final String EROTIN = " ";
            
    /*
     * Attribuutit
     *
     */
    
    /** Viestiketjun tunniste */
    private int viestiKetjuTunniste;
    
    /** aihe, Tähän tallennetaan jokainen aihe */
    private String aihe;
    
    /** OmaLista tyyppinen attribuutti topicille, oksaviesti*/
    private OmaLista topikki;
    
    /** OmaLista tyyppinen attribuutti viestien listalle */
    private OmaLista viestienLista;

    /**Luokan rakentaja
     * 
     * @param t on viestiKetjuTunniste
     * @param a on aihe
     * topikki ja viestienLista ovat OmaLista tyyppisiä
     */
    public Ketjuluokka(int t, String a) {
        viestiKetjuTunniste = t;
        aihe = a;
        topikki = new OmaLista();
        viestienLista = new OmaLista();
    }
    
    /*
     * Metodit 
     *
     */
    
    @Getteri
    public int viestiKetjuTunniste(){
        return viestiKetjuTunniste;
    }
    
    /** Operaatio tarkistaa viestiKetjunTunnisteen olevan suurempi
     * kuin 1
     *
     * @param t on viestiKetjuTunniste
     * @throws IllegalArgumentException jos jokin meni vikaan
     */
    @Setteri
    public void viestiKetjuTunniste(int t)throws IllegalArgumentException{
        //Tarkistetaan, että t on suurempi kuin 1, muuten poikkeus
        if(t > 1){
            viestiKetjuTunniste = t;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    @Getteri
    public String aihe(){
        return aihe;
    }
    
    /** Aihe ei saa olla null eikä lyhyempi kuin 1 merkki.
     *
     * @param a on aihe
     * @throws IllegalArgumentException
     */
    @Setteri
    public void aihe(String a)throws IllegalArgumentException{
        if(a == null || a.length() < 1){
            throw new IllegalArgumentException();
        }
        else {
            aihe = a;
        }
    }
    
    @Getteri
    public OmaLista topikki(){
        return topikki;
    }
    
    /**OmaLista tyyppinen attribuutti oksaviesteille
     *
     * @param t on topikki
     */
    @Setteri
    public void topikki(OmaLista t){
        topikki = t;
    }
    
    @Getteri
    public OmaLista viestienLista(){
        return viestienLista;
    }
    
    /**OmaLista tyyppinen attribuutti viestienListalle.
     *
     * @param vl on viestienLista
     */
    @Setteri
    public void viestienLista(OmaLista vl){
        viestienLista = vl;
    }
    
    /**Operaatio tulostaa aktiivisen viestiketjun 
     * näytölle puumaisessa muodossa
     *
     */
    public void tulostaPuuna(){
        //Käydään läpi viestiketjun topikki säilövä lista
        //ja tulostetaan
        System.out.println("=");
        System.out.println("== " + this.toString());
        System.out.println("===");
        //Kutsutaan rekursiivistä tulostusalgoritmia
        for(int j = 0; j < topikki.koko(); j++){
            tulostaPuuna((Viesti)topikki.alkio(j), 0);
        }
    }
    /**Operaatio tulostaa aktiivisen viestiketjun 
     * näytölle puumaisessa muodossa
     * @param viesti on Viesti- tyyppinen olio
     * @param syvyys on sisennyksen syvyys
     */
    public void tulostaPuuna(Viesti viesti, int syvyys){
        //Tulostetaan annetun syvyinen sisennys.
        for(int i = 0; i < syvyys; i++ ){
            System.out.print(EROTIN);
        }
        //Tulostetaan viestin merkijonoesitys.
        System.out.println(viesti.toString());
        //Asetetaan apuviite viestin vastaukset säilövään listaan
        OmaLista vastaukset = viesti.vastaavaViesti();
        //Tulostetaan vastaukset rekursiivisesti. Metodista palataan,
        //kun vastauslista on tyhjä.
        for(int j = 0; j < vastaukset.koko(); j++){
            if( j == 0){
                syvyys = syvyys + 3;
            }
            tulostaPuuna((Viesti)vastaukset.alkio(j), syvyys);
        }
    }
    
    /** Operaatio hakee viestin parametrin avulla
     *
     * @param viestinTunnus on viestin parametri
     * @return palauttaa viestin
     * @throws Exception jos tuli virhe
     */
    public Viesti haeViesti(int viestinTunnus)throws Exception{
        //Virheensieppaus
        try{
            //Määritellään viestin alkuarvoksi null
            Viesti viesti = null;
            for(int i = 0; i < viestienLista.koko(); i++){
                Viesti alkio = (Viesti)viestienLista.alkio(i);
                //Jos tunniste ja tunnus ovat samat
                if(alkio.viestinTunniste() == viestinTunnus){
                    viesti = alkio;
                    i = viestienLista.koko();
                }
            }
            //Palauttaa viestin
            return viesti;
        }
        catch (Exception e){
            throw new Exception();
        }
    }

    /**Viestityyppinen operaatio saman viestin etsimiseen.
     * Operaatio etsii saman viestin tunnisteen perusteella
     *
     * @param viestinTunniste on viestin tunniste
     * @return palauttaa edellisen viitteen
     */
    public Viesti samanViestinEtsiminen(int viestinTunniste){
        //Määritellään viite nulliksi
        Viesti edellinenViite = null;
        //Silmukka, joka etsii viestejä
        for(int i = 0; i < viestienLista.koko(); i++){
            Viesti apuViite = (Viesti)viestienLista.alkio(i);
            if(apuViite.viestinTunniste() == viestinTunniste){
                edellinenViite = apuViite;
                i = viestienLista.koko();
            }
        }
        //Palauttaa viitteen
        return edellinenViite;
    }

    /**toString metodi, joka tulostaa viestien määrän.
     * merkkijonoesityksenä.
     */
    @Override
    public String toString() {
        return RISUMERKKI + viestiKetjuTunniste + EROTIN + aihe + " (" + viestienLista.koko() + " messages)";
    }
    
}
