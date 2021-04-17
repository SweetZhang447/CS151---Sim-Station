package PDT;

public class Tit4Tat extends Strategy {
    public Tit4Tat(Prisoner prisoner) {
        super(prisoner);
    }

    // Cooperate only if last person cooperated
    public boolean cooperate() {
        if (prisoner.getPartnerCheated()) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Tit4Tat";
    }
}
