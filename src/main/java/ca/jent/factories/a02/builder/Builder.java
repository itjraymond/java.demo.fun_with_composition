package ca.jent.factories.a02.builder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Builder {
    private Map<String, Supplier<Vehicle>> suppliers = new HashMap<>();

    public void registerVehicle(String name, Supplier<Vehicle> supplier) {
        suppliers.put(name,supplier);
    }

    public VehicleFactory create() {
        return (String name) ->
                suppliers.getOrDefault(
                        name,
                        () -> { throw new IllegalStateException("Invalid vehicle name: " + name); }
                ).get();
    }

    // Usage
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.registerVehicle(
                "CarToyotaSuvBlack",
                Vehicle.createCar(
                        VEHICLE_BRAND.TOYOTA,
                        VEHICLE_KIND.SUV,
                        Color.BLACK,
                        0D,
                        Milage.UNIT.KILOMETER
                )
        );
        builder.registerVehicle(
                "FordTruckGreen",
                Vehicle.createCar(
                        VEHICLE_BRAND.FORD,
                        VEHICLE_KIND.TRUCK,
                        Color.GREEN,
                        50D,
                        Milage.UNIT.KILOMETER
                )
        );
        VehicleFactory factory = builder.create();

        Vehicle toyotaSuv = factory.create("CarToyotaSuvBlack");
        Vehicle fordTruck = factory.create("FordTruckGreen");

        System.out.println(toyotaSuv.toString());
        System.out.println(fordTruck.toString());

    }
}

interface VehicleFactory {
    Vehicle create(String name);
}


