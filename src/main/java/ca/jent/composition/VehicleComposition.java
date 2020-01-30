package ca.jent.composition;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;


/**
 * WIP
 *
 */
public class VehicleComposition {


    public static Function<Color,Vehicle> carSupplier = Car::new;
    public static Function<Color,Vehicle> motoSupplier = Moto::new;

    public static void main(String[] args) {

        Function<Color,Vehicle> f;
        Color color;

        int v = ThreadLocalRandom.current().nextInt(0,10);

        if (v % 2 == 0) {
            f = carSupplier;
        } else {
            f = motoSupplier;
        }

        v = ThreadLocalRandom.current().nextInt(0, 10);
        if (v % 2 == 0) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }

        Vehicle ve = f.apply(color);

        System.out.println(ve.whatAmI());
    }
}
