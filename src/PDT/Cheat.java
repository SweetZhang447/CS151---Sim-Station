package PDT;

public class Cheat extends Strategy {
    public Cheat(Prisoner prisoner) {
        super(prisoner);
    }

    // Always cheats
    public boolean cooperate() {
        return false;
    }

    @Override
    public String toString() {
        return "Cheat";
    }
}
