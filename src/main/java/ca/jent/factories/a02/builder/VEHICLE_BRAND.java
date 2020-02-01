package ca.jent.factories.a02.builder;

public enum VEHICLE_BRAND {
    TOYOTA("Toyota"),
    FORD("Ford"),
    NISSAN("Nissan"),
    UNKNOWN("Unknown");

    private String brand;
    VEHICLE_BRAND(String brand) {
        this.brand = brand;
    }
}
