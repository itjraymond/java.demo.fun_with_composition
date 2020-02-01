package ca.jent.composition;

import java.awt.Color;
import java.util.function.Supplier;

/**
 * Note: Bad idea to make an abstract class "public".  We will correct this later though as we evolve our example.
 */
public abstract class Vehicle {

    private Color color;

    public Vehicle() {
        this.color = Color.WHITE;
    }

    public Vehicle(Color color) {
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


class ColorUtil {
    public static String toString(Color color) {
        if (color == Color.RED) return "Red";
        if (color == Color.BLUE) return "Blue";
        if (color == Color.YELLOW) return "Yellow";
        if (color == Color.GREEN) return "Green";
        return "White";
    }
}
