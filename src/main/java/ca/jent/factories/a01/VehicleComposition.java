package ca.jent.factories.a01;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * WIP
 *
 */
public class VehicleComposition {

    private static Supplier<Vehicle> carSupplier = Car::new;

    private static Function<Color,Vehicle> coloredCarSupplier = Car::new;
    private static Function<Color,Vehicle> coloredMotoSupplier = Moto::new;
//    private static Function<Integer, List<Vehicle>> createVehicles = v ->

    private static Function<Color,Vehicle> getRandomVehicle() {

        int v = ThreadLocalRandom.current().nextInt(0,10);

        if (v % 2 == 0) {
            return coloredCarSupplier;
        }
        return coloredMotoSupplier;

    }
    public static void main(String[] args) {

        Function<Color,Vehicle> f = getRandomVehicle();
        Color color;


        int v = ThreadLocalRandom.current().nextInt(0, 10);
        if (v % 2 == 0) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }

        Vehicle ve = f.apply(color);
        System.out.println(ve.whatAmI());

        Vehicle defaultCar = carSupplier.get();
        System.out.println(defaultCar.whatAmI());
    }
}
