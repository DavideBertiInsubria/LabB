package common;

public enum TipologiaCentro {
    ospedaliero,hub,aziendale;

    public static TipologiaCentro getTipo (String n){
        return valueOf(n);
    }
}
