package PDT;

public class Cooperate extends Strategy {

    public Cooperate(Prisoner prisoner) {
        super(prisoner);
    }

    // Always cooperates
    public boolean cooperate() {
        return true;
    }

    @Override
    public String toString() {
        return "Cooperate";
    }
}
