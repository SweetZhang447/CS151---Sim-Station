package SimStation;

import java.util.Random;

// Hoc Can Huynh: 4/12/2021 Implemented
// Hoc Can Huynh: 4/13/2021 Implemented random
public enum Heading {
    NORTH, WEST, EAST, SOUTH;

    //Get random enum value
    public static Heading random(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
