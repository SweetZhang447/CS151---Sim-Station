package PDT;

public abstract class Strategy {
    Prisoner prisoner;

    public Strategy(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public abstract boolean cooperate();
}
