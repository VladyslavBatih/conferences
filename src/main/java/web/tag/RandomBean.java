package web.tag;

import java.util.Random;

public class RandomBean {

    private static final Random RANDOM = new Random();

    public int nextInt() {
        return RANDOM.nextInt();
    }
}