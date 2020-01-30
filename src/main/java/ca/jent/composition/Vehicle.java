package ca.jent.composition;

import java.awt.*;

abstract class Vehicle {

    private Color color;

    private Vehicle() {
        this.color = Color.WHITE;
    }

    public Vehicle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    abstract String whatAmI();
}

class Car extends Vehicle {

    public Car(Color color) {
        super(color);
    }

    @Override
    String whatAmI() {
        return "Driving a " + ColorUtil.toString(this.getColor()) + " CAR";
    }
}

class Moto extends Vehicle {

    public Moto(Color color) {
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
        return "Red";
    }
}
