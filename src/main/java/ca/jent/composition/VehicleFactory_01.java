package ca.jent.composition;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Do not do this for production :-)
 * Problems:
 * 1. If a vehicleName is not registered, it throws an exception if requesting such vehicle.
 * 2. The register is exposed such that the programmer can register Vehicle supplier at any time. It would
 *    be better if we could limit the registration process to be done only once so that it is not "pepered"
 *    everywhere in the code.
 * 3. We are working with String.  no no no :-)
 *
 * What should we do?  Later, we will create a "Builder" (builder pattern)
 *
 * Disclaimer:  Some ideas derives from Remi Forax Reloaded talk.
 */
public class VehicleFactory_01 {
    private final Map<String,Supplier<Vehicle>> suppliers = new HashMap<>();

    public void register(String vehicleName, Supplier<Vehicle> supplier) {
        suppliers.put(vehicleName, supplier);
    }

    public Vehicle create(String vehicleName) {
        return suppliers.getOrDefault(
                vehicleName,
                () -> { throw new IllegalStateException("Don't know how to create " + vehicleName); }
        ).get();
    }

    // Sample usage
    public static void main(String[] args) {
        VehicleFactory_01 vehicleFactory = new VehicleFactory_01();
        vehicleFactory.register("Car", Vehicle.vehicleSupplier(Car.class));
        vehicleFactory.register("Moto", Vehicle.vehicleSupplier(Moto.class));
        vehicleFactory.register("YellowMoto", Vehicle.coloredVehicleSupplier(Moto.class, Color.YELLOW));
        vehicleFactory.register("GreenCar", Vehicle.coloredVehicleSupplier(Car.class, Color.GREEN));

        Vehicle greenCar = vehicleFactory.create("GreenCar");
        Vehicle moto = vehicleFactory.create("Moto");

        System.out.println(greenCar.whatAmI());
        System.out.println(moto.whatAmI());

        Vehicle truck;
        try {
            truck = vehicleFactory.create("Truck");
            System.out.println("Will never print");
        } catch (IllegalStateException e) {
            System.out.println("EROOR: " + e.getMessage());
        }
    }
}
