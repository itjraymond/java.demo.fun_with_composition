package ca.jent.factories.a02.builder;

public enum VEHICLE_KIND {
    SEDAN("Sedan"),
    SUV("Sub"),
    TRUCK("Truck"),
    UNKNOWN("Unknown kind");

    private String kind;

    VEHICLE_KIND(String kind) {
        this.kind = kind;
    }
}
