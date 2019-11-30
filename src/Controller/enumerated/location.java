package Controller.enumerated;

public enum location {

    SGS, HN;

    private location(){}

    public String value() {return name();}

    public static location fromValue(String type) {return valueOf(type);}
}
