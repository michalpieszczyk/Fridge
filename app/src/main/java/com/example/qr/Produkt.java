package com.example.qr;

public class Produkt {




    String kod_kreskowy;
    String bialko;
    String gramatura;
    String ile_dni;
    String kalorie;
    String nazwa;

    public void setKod_kreskowy(String kod_kreskowy) {
        this.kod_kreskowy = kod_kreskowy;
    }

    public void setBialko(String bialko) {
        this.bialko = bialko;
    }

    public void setGramatura(String gramatura) {
        this.gramatura = gramatura;
    }

    public void setIle_dni(String ile_dni) {
        this.ile_dni = ile_dni;
    }

    public void setKalorie(String kalorie) {
        this.kalorie = kalorie;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setTluszcze(String tluszcze) {
        this.tluszcze = tluszcze;
    }

    public void setWeglowodany(String weglowodany) {
        this.weglowodany = weglowodany;
    }

    String tluszcze;

    public String getKod_kreskowy() {
        return kod_kreskowy;
    }

    public String getBialko() {
        return bialko;
    }

    public String getGramatura() {
        return gramatura;
    }

    public String getIle_dni() {
        return ile_dni;
    }

    public String getKalorie() {
        return kalorie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTluszcze() {
        return tluszcze;
    }

    public String getWeglowodany() {
        return weglowodany;
    }

    String weglowodany;

    public Produkt(){

    }

    public Produkt(String kod_kreskowy, String bialko, String gramatura, String ile_dni, String kalorie, String nazwa, String tluszcze, String weglowodany) {
        this.kod_kreskowy = kod_kreskowy;
        this.bialko = bialko;
        this.gramatura = gramatura;
        this.ile_dni = ile_dni;
        this.kalorie = kalorie;
        this.nazwa = nazwa;
        this.tluszcze = tluszcze;
        this.weglowodany = weglowodany;
    }




}
