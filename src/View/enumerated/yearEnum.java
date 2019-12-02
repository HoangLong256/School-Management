package View.enumerated;

public enum yearEnum {
    First, Second, Third, Four, Five;

    private yearEnum(){}

    public String value() {return name();}

    public static yearEnum fromValue(String type) {return valueOf(type);}
}
