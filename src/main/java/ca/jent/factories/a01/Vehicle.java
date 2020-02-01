package ca.jent.factories.a01;

import java.awt.Color;
import java.util.function.Supplier;

/**
 * Note: Bad idea to make an abstract class "public".  So make sure it is package private.
 *
 * Also, the main thing to observe is what the programmer will have to do to add a brand new Vehicle.
 * 1. Programmer will need to edit this class and add the new Vehicle class with implementation.
 * 2. Programmer will need to provide all Supplier functions for that new Vehicle.
 * 3. This works easily for class with few properties but becomes difficult when needing to construct large class.
 *
 * Some of the advantage
 * 1. Anything outside this package cannot access any Vehicle constructors forcing them to use the vehicle suppliers.
 *
 * Some asked: why is Vehicle an abstract class instead of an interface?
 *             The answer is because all Vehicle will have private fields that are common to all
 *             such as Color.  You cannot declare fields in interfaces.
 */
abstract class Vehicle {

    private Color color;

    Vehicle() {
        this.color = Color.WHITE;
    }

    Vehicle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    abstract String whatAmI();

    public static Supplier<Vehicle> vehicleSupplier(Class clazz) {
        if (Car.class.equals(clazz)) {
            return Car::new;
        } else if (Moto.class.equals(clazz)) {
            return Moto::new;
        }
        throw new IllegalStateException("Unexpected value: " + clazz);
    }

    public static Supplier<Vehicle> coloredVehicleSupplier(Class clazz, Color color) {
        if (Car.class.equals(clazz)) {
            return () -> new Car(color);
        } else if (Moto.class.equals(clazz)) {
            return () -> new Moto(color);
        }
        throw new IllegalStateException("Unexpected value: " + clazz);
    }

}

class Car extends Vehicle {

    Car() {
        super();
    }
    Car(Color color) {
        super(color);
    }

    @Override
    String whatAmI() {
        return "Driving a " + ColorUtil.toString(this.getColor()) + " CAR";
    }
}

class Moto extends Vehicle {

    Moto() {
        super();
    }
    Moto(Color color) {
        super(color);
    }

    @Override
    String whatAmI() {
        return "Driving a " + ColorUtil.toString(this.getColor()) + " MOTO";
    }
}

class Truck extends Vehicle {

    Truck() {
        super();
    }
    Truck(Color color) {
        super(color);
    }

    @Override
    String whatAmI() {
        return "Driving a " + ColorUtil.toString(this.getColor()) + " TRUCK";
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
