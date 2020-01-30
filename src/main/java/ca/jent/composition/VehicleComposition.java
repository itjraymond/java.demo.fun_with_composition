package ca.jent.composition;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;


/**
 * WIP
 *
 */
public class VehicleComposition {


    private static Function<Color,Vehicle> carSupplier = Car::new;
    private static Function<Color,Vehicle> motoSupplier = Moto::new;
//    private static Function<Integer, List<Vehicle>> createVehicles = v ->

    private static Function<Color,Vehicle> getRandomVehicle() {

        int v = ThreadLocalRandom.current().nextInt(0,10);

        if (v % 2 == 0) {
            return carSupplier;
        }
        return motoSupplier;

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
    }
}
