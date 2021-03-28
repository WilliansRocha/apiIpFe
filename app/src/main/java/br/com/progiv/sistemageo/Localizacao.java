package br.com.progiv.sistemageo;

public class Localizacao {
    private String country_code;
    private String country_name;
    private String country_state;

    public Localizacao(String code, String name, String state){
        this.country_code = code;
        this.country_name = name;
        this.country_state = state;
    }
    public String getCountryCode(){return this.country_code;}
    public String getCountryName(){return this.country_name;}
    public String getCountryState(){return this.country_state;}
}
