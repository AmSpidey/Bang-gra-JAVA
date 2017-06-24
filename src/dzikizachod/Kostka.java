package dzikizachod;

import java.util.Random;

public class Kostka {

    public static int wylosuj() {
        Random r = new Random();
        int doZwrocenia = r.nextInt(6);
        return doZwrocenia + 1;
    }

}
