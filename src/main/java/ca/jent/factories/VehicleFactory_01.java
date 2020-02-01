package ca.jent.factories;

import ca.jent.composition.Vehicle;

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
 *
 * What should we do?  Later, we will create a "Builder" (builder pattern)
 */
public class VehicleFactory_01 {
    private final Map<String,Supplier<Vehicle>> suppliers = new HashMap<>();

    public void register(String vehicleName, Supplier<Vehicle> supplier) {
        suppliers.put(vehicleName, supplier);
    }

    public Vehicle create(String vehicleName) {
        if (!suppliers.containsKey(vehicleName)) throw new IllegalStateException("Don't know how to create " + vehicleName);
        return suppliers.get(vehicleName).get();
    }
}
