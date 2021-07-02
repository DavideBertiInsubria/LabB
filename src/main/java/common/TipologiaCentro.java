package common;

public enum TipologiaCentro {
    OSPEDALIERO,HUB,AZIENDALE;

    public static TipologiaCentro getTipo (String n){
        return valueOf(n);
    }
}
