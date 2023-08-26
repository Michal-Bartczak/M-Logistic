package pl.coderslab.magazyn.generators;

import java.util.Random;

public class TrackingNumberGenerator {
    private static final Random random = new Random();

     public static String generateTrackingNumber() {
        String generatedNumber = "200";
        for (int i = 0; i < 7; i++) {
            generatedNumber += random.nextInt(10);
        }
        return generatedNumber;
    }
}
