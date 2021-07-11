package common;

import java.io.Serializable;

public enum Vaccino implements Serializable {
    Pfzier,AstraZeneca,Moderna,JJ;

    public static Vaccino getTipo (String n){
        return valueOf(n);
    }
}