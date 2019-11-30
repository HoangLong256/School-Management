package View.enumerated;

public enum level {
    First, Second, Third;

    private level(){}

    public String value() {return name();}

    public static level fromValue(String type) {return valueOf(type);}
}
