package dzikizachod;

import java.util.Random;

public class GeneratorZycia {

    public static int wylosujmaksZycie() {
        Random r = new Random();
        int doZwrocenia = r.nextInt(2);
        return doZwrocenia + 3;
    }

}
