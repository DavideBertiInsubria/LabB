package common;

public enum Vaccino {
    Pfzier,AstraZeneca,Moderna,JJ;

    public static Vaccino getTipo (String n){
        return valueOf(n);
    }
}