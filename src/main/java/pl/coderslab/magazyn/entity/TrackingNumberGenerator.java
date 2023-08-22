package pl.coderslab.magazyn.entity;

import java.util.Random;

public class TrackingNumberGenerator {
    private static final Random random = new Random();

     static String generateTrackingNumber() {
        String generatedNumber = "200";
        for (int i = 0; i < 7; i++) {
            generatedNumber += random.nextInt(10);
        }
        return generatedNumber;
    }
}
