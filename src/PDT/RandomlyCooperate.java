package PDT;

import java.util.Random;

public class RandomlyCooperate extends Strategy {

    public RandomlyCooperate(Prisoner prisoner) {
        super(prisoner);
    }

    // Randomly generates boolean
    // True = cooperate, false otherwise
    public boolean cooperate() {
        Random rd = new Random();
        return rd.nextBoolean();
    }

    @Override
    public String toString() {
        return "RandomlyCooperate";
    }

}
