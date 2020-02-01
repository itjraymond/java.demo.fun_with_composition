package ca.jent.factories.a02.builder;

import java.awt.Color;
import java.util.function.Supplier;

abstract class Vehicle {

    private Color color;
    private VEHICLE_BRAND brand;
    private VEHICLE_KIND kind;
    private Milage milage;

    Vehicle() {
        this.color = Color.WHITE;
        this.brand = VEHICLE_BRAND.UNKNOWN;
        this.kind = VEHICLE_KIND.UNKNOWN;
        this.milage = new Milage();
    }

    abstract String whatAmI();

    public Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    public VEHICLE_BRAND getBrand() {
        return brand;
    }

    void setBrand(VEHICLE_BRAND brand) {
        this.brand = brand;
    }

    public VEHICLE_KIND getKind() {
        return kind;
    }

    void setKind(VEHICLE_KIND kind) {
        this.kind = kind;
    }

    public Milage getMilage() {
        return milage;
    }

    void setMilage(Milage milage) {
        this.milage = milage;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "color=" + ColorUtil.toString(color) + ", brand=" + brand.name() + ", kind=" + kind.name() + ", milage=" + milage + '}';
    }

    public static Supplier<Vehicle> createCar(
            VEHICLE_BRAND brand,
            VEHICLE_KIND kind,
            Color color,
            Double odometerValue,
            Milage.UNIT unit
    ) {
        return () -> {
            Car car = new Car();
            car.setBrand(brand);
            car.setKind(kind);
            car.setColor(color);
            car.setMilage(new Milage(odometerValue, unit));
            return car;
        };
    }
}

class UnknownVehicle extends Vehicle {

    UnknownVehicle() {
        super();
    }

    @Override
    String whatAmI() {
        return toString();
    }
}

class Car extends Vehicle {

    Car() {
        super();
    }

    @Override
    String whatAmI() {
        return toString();
    }
}

class Moto extends Vehicle {

    Moto() {
        super();
    }

    @Override
    String whatAmI() {
        return toString();
    }
}

class ColorUtil {
    public static String toString(Color color) {
        if (color == Color.RED) return "Red";
        if (color == Color.BLUE) return "Blue";
        if (color == Color.YELLOW) return "Yellow";
        if (color == Color.GREEN) return "Green";
        if (color == Color.BLACK) return "Black";
        return "White";
    }
}
