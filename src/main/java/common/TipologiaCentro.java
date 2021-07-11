package common;

import java.io.Serializable;

public enum TipologiaCentro implements Serializable {
    ospedaliero,hub,aziendale;

    public static TipologiaCentro getTipo (String n){
        return valueOf(n);
    }
}
