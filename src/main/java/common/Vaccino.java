package common;

import java.io.Serializable;

public enum Vaccino implements Serializable {
    Pfizer,AstraZeneca,Moderna,JJ;

    public static Vaccino getTipo (String n){
        return valueOf(n);
    }
}