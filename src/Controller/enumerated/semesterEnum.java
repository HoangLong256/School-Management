package Controller.enumerated;

public enum semesterEnum {
    One, Two, Three;

    private semesterEnum(){}

    public String value() {return name();}

    public static semesterEnum fromValue(String type) {return valueOf(type);}
}
